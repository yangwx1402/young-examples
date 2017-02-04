package com.young.spring.rest.entity;

import java.util.List;

/**
 * Created by yangyong3 on 2017/2/3.
 */
public class RestResult<Model> {

    private int code;

    private String message;

    private Model model;

    private List<Model> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<Model> getList() {
        return list;
    }

    public void setList(List<Model> list) {
        this.list = list;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public RestResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResult(int code, String message, Model t) {
        this.code = code;
        this.message = message;
        this.model = t;
    }

    public RestResult(int code, String message, List<Model> list) {
        this.code = code;
        this.message = message;
        this.list = list;
    }
}
