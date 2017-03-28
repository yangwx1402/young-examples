package com.young.java.examples.jvm.instrumentation.dynamical;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * Created by yangyong3 on 2017/3/28.
 */
public class DynamicalTest {
    public static void main(String[] args) throws InterruptedException, IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        String pid = "17384";
        VirtualMachine vm = VirtualMachine.attach(pid); //正在运行的java 程序 ps id
        vm.loadAgent("D:\\young\\java\\young-examples\\out\\artifacts\\Peopleagent\\Peopleagent.jar");
        //刚刚编译好的agent jar 位置
    }
}
