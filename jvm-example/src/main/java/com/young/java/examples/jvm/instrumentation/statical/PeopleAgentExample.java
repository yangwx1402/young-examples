package com.young.java.examples.jvm.instrumentation.statical;

/**
 * Created by yangyong on 17-3-25.
 * execute command:-javaagent:/Users/yao/workspace/private/JavaSPI/target/classes/myagent.jar
 */
public class PeopleAgentExample {

    public static void main(String[] args) {
        People people = new People();
        people.sayHello();
    }
}
