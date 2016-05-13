package com.young.java.examples.fileupload;

import java.io.Serializable;
import java.util.List;

/**
 * Created by issuser on 2016/5/12.
 */
public class FileObject implements Serializable{
    private String fileId;

    private String md5;

    private List<ObjectField> fields;

    private byte[] fileData;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public List<ObjectField> getFields() {
        return fields;
    }

    public void setFields(List<ObjectField> fields) {
        this.fields = fields;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
