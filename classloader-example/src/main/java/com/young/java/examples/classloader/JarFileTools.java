package com.young.java.examples.classloader;

import com.young.java.examples.classloader.beans.ClassInfo;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/14.
 */
public class JarFileTools {

    public static List<ClassInfo> unzipJarFile(InputStream inputStream) throws Exception {
        ZipArchiveInputStream zais = null;
        List<ClassInfo> result = new ArrayList<ClassInfo>();
        try {
            zais = new ZipArchiveInputStream(inputStream);
            ArchiveEntry archiveEntry = null;
            String name = null;
            byte[] temp = null;
            while ((archiveEntry = zais.getNextEntry()) != null) {
                temp = new byte[(int) archiveEntry.getSize()];
                name = archiveEntry.getName();
                if(name.endsWith(".class")){
                    IOUtils.readFully(zais, temp);
                   result.add(new ClassInfo(name,temp));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
