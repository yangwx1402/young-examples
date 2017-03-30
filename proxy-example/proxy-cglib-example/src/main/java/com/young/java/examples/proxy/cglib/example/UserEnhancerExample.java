package com.young.java.examples.proxy.cglib.example;

import com.young.java.examples.proxy.cglib.enhancer.TransactionIncepter;
import com.young.java.examples.proxy.cglib.enhancer.UserDaoCallbackFilter;
import com.young.java.examples.proxy.cglib.real.*;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public class UserEnhancerExample {

    public static <T> Object getProxy(Class<T> targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallbackFilter(new UserDaoCallbackFilter());
        Transaction transaction = new MysqlTransaction();
        MethodInterceptor interceptor = new TransactionIncepter(transaction);
        //这里的顺序要跟filter里的返回值对应
        enhancer.setCallbacks(new Callback[]{ NoOp.INSTANCE,interceptor});
        return enhancer.create();
    }

    public static void main(String[] args) throws DaoException {
        Dao dao = (Dao) UserEnhancerExample.getProxy(UserDao.class);
        dao.get("111");
        dao.add("yyy");
        dao.update("11");
        dao.delete("11");
    }
}
