package com.young.example.spider.http.cookie;

import com.young.example.spider.utils.CommandTools;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class CommandRunner implements Callable<String> {

    private static final Logger log = LogManager.getLogger(CommandRunner.class);

    private String command;

    public CommandRunner(String command){
        this.command = command;
    }

    @Override
    public String call() throws Exception {
        log.info("execute command in backgroud command is ["+command+"]");
        return CommandTools.process(command);
    }
}
