package com.young.java.examples.classloader.isolationclassloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * Land ClassLoader.
 *
 * @author young.yang
 */
public class IsolationClassLoader extends URLClassLoader {
    public static final String CLASS = "class ";
    private final Map<ClassLoaderType, List<String>> delegateConfig;
    private final ClassMatcher matcher;

    public IsolationClassLoader(URL[] urls) {
        super(urls,getSystemClassLoader());
        this.delegateConfig = new HashMap<>();
        delegateConfig.put(ClassLoaderType.CHILD_ONLY, Arrays.asList("."));
        this.matcher = new DefaultClassMatcher();
    }

    public IsolationClassLoader(URL[] urls, Map<ClassLoaderType, List<String>> delegateConfig) {
        this(urls, delegateConfig, null, null);
    }

    public IsolationClassLoader(URL[] urls, Map<ClassLoaderType, List<String>> delegateConfig, ClassLoader parent) {
        this(urls, delegateConfig, null, parent);
    }

    public IsolationClassLoader(URL[] urls, Map<ClassLoaderType, List<String>> delegateConfig, ClassMatcher matcher) {
        this(urls, delegateConfig, matcher, null);
    }

    /**
     * @param urls           class path
     * @param delegateConfig delegate config.
     * @param matcher        class name matcher
     * @param parent         parent classloader
     */
    public IsolationClassLoader(URL[] urls, Map<ClassLoaderType, List<String>> delegateConfig, ClassMatcher matcher, ClassLoader parent) {
        super(urls, parent == null ? getSystemClassLoader() : parent);

        if (delegateConfig == null) {
            delegateConfig = new EnumMap<>(ClassLoaderType.class);
        }
        this.matcher = matcher == null ? new DefaultClassMatcher() : matcher;
        for (Map.Entry<ClassLoaderType, List<String>> typeEntry : delegateConfig.entrySet()) {
            for (String pattern : typeEntry.getValue()) {
                this.matcher.validate(pattern);
            }
        }
        this.delegateConfig = delegateConfig;
    }

    static {
        ClassLoader.registerAsParallelCapable();
    }

    @Override
    protected Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(className)) {
            Class c = loadClass0(className);
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    private Class loadClass0(String className) throws ClassNotFoundException {
        // 0. check if the class has already been loaded
        Class c = findLoadedClass(className);
        if (c != null) {
            return c;
        }

        // 1. check if the class is in system class loader
        try {
            return getSystemClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            // ClassNotFoundException thrown if class not found
        }

        // 2. get class according to delegate config
        ClassLoaderType delegateType = findDelegateTypeInDelegateConfigs(className, delegateConfig, matcher);
        ClassLoader parent = getParent();
        switch (delegateType) {
            case NONE:
                throw new ClassNotFoundException(CLASS + className + "(NONE) is forbidden by land config!");
            case PARENT_ONLY:
                return parentOnly(className, parent);
            case CHILD_ONLY:
                // TODO improve exception message
                return findClass(className);
            case PARENT_CHILD:
                return parentChild(className, parent);
            case CHILD_PARENT:
                return childParent(className, parent);
            default:
                throw new IllegalStateException("Unsupported delegate config " + delegateType);
        }
    }

    private Class parentOnly(String className, ClassLoader parent) throws ClassNotFoundException {
        if (parent == getSystemClassLoader()) {
            throw new ClassNotFoundException(CLASS + className + "(PARENT_ONLY) not found in parent class loader");
        } else {
            // TODO improve exception message
            return parent.loadClass(className);
        }
    }

    private Class parentChild(String className, ClassLoader parent) throws ClassNotFoundException {
        // parent does not load this class before
        if (parent != getSystemClassLoader()) {
            try {
                return parent.loadClass(className);
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
            }
        }
        return findClass(className);
    }

    private Class childParent(String className, ClassLoader parent) throws ClassNotFoundException {
        try {
            return findClass(className);
        } catch (ClassNotFoundException e) {
            // ClassNotFoundException thrown if class not found
        }
        if (parent != getSystemClassLoader()) {
            try {
                return parent.loadClass(className);
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
            }
        }
        throw new ClassNotFoundException(CLASS + className + "(CHILD_PARENT) not found in parent class loader");
    }

    static ClassLoaderType findDelegateTypeInDelegateConfigs(String className, Map<ClassLoaderType, List<String>> delegateConfig, ClassMatcher matcher) {
        for (Map.Entry<ClassLoaderType, List<String>> entry : delegateConfig.entrySet()) {
            if (matchPatterns(className, entry.getValue(), matcher)) {
                return entry.getKey();
            }
        }
        return ClassLoaderType.PARENT_CHILD;
    }

    static boolean matchPatterns(String className, List<String> patterns, ClassMatcher matcher) {
        for (String pattern : patterns) {
            if (matcher.match(className, pattern)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return IsolationClassLoader.class.getSimpleName() + "(urls: " + Arrays.toString(getURLs()) + ")";
    }
}
