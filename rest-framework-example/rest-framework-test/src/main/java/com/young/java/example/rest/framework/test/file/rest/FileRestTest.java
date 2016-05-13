package com.young.java.example.rest.framework.test.file.rest;

import com.google.common.collect.Lists;
import com.young.examples.common.json.JsonUtils;
import com.young.java.examples.fileupload.FileObject;
import com.young.java.examples.fileupload.ObjectField;
import com.young.java.examples.testcase.testframework.annotations.TestCase;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by issuser on 2016/5/13.
 */
@TestCase("fileRest")
public class FileRestTest {

    public void uploadFileObject() throws IOException {
        HttpClient http = HttpClients.createDefault();
        String url = "http://localhost:8080/young-rest/file/upload";
        String filePath = "D:\\net-object.thrift";
        FileBody fileBody = new FileBody(new File(filePath));
        HttpPost httppost = new HttpPost(url);
        FileObject object = new FileObject();
        object.setFileId("sdfsdfsdf");
        List<ObjectField> fileds = Lists.newArrayList();
        ObjectField field = new ObjectField();
        field.setIndex("true");
        field.setFieldName("name");
        field.setFieldValue("lukuan");
        fileds.add(field);
        object.setFields(fileds);
        String json = JsonUtils.toJson(object);
        System.out.println(json);
        //HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("fileObject", new StringBody(json, ContentType.APPLICATION_JSON)).build();
        HttpEntity reqEntity = new StringEntity(json,ContentType.APPLICATION_JSON);
        httppost.setEntity(reqEntity);
        HttpResponse response = http.execute(httppost);
        System.out.println(IOUtils.toString(response.getEntity().getContent()));
    }
    public static void main(String[] argss) throws IOException {
        FileRestTest test = new FileRestTest();
        test.uploadFileObject();
    }
}
