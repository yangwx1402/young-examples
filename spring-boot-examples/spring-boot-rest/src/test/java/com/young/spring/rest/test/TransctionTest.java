package com.young.spring.rest.test;

import com.young.spring.rest.config.BootConfig;
import com.young.spring.rest.dao.UserDao;
import com.young.spring.rest.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by yangyong3 on 2017/2/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootConfig.class)
public class TransctionTest {

    @Autowired
    private UserDao userDao;

    @Test
    //抛出异常后会rollback，同时测试会可以使用@Rollback，这样在测试完成后可以进行回滚，不影响数据库数据
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    /**
     * propagation为事务的执行行为
     * REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
     SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
     MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
     REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
     NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
     NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
     NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于REQUIRED。
     指定方法：通过使用propagation属性设置，例如：1
     @Transactional(propagation = Propagation.REQUIRED)

     */
    public void testAdd(){
       for(int i=0;i<10;i++){
          userDao.saveAndFlush(new User("yangyong_"+(i*2),10*i));
       }
    }

    public static void main(String[] args){

    }
}
