package com.young.java.examples.pattern.structure.bridge;

/**
 * Created by yangyong3 on 2017/6/30.
 * 桥接模式，具体用在定义一个接口，实现可以任意替换的场景，典型的场景为jdbc链接就是采用桥接模式实现
 * 虽然mysql和oracle的打开链接方式可能不相同，但是通过桥接模式，调用方可以采用相同的业务代码。
 */
public class BridgeExample extends Bridge {
    public BridgeExample(DriverManager driverManager) {
        super(driverManager);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(MysqlDriverManager.class.getName());
        BridgeExample bridge = new BridgeExample((DriverManager) clazz.newInstance());
        bridge.getConnection("","","");
    }
}
