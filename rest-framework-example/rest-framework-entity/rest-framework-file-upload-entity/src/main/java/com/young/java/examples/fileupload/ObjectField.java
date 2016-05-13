package com.young.java.examples.fileupload;

import java.io.Serializable;

/**
 * Created by issuser on 2016/5/12.
 */
public class ObjectField implements Serializable{

    private String fieldName;

    private String fieldType;

    private String fieldValue;

    private String index;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
