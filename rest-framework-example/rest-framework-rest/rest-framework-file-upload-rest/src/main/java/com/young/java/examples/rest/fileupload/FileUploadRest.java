package com.young.java.examples.rest.fileupload;

import com.young.java.examples.fileupload.FileObject;
import com.young.java.examples.fileupload.RestResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by issuser on 2016/5/12.
 */
@Controller
@RequestMapping("/file")
@ResponseBody
public class FileUploadRest {
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public RestResult<FileObject> upload(@RequestBody FileObject fileObject) {
        System.out.println(fileObject.getFields().size());
        return new RestResult<FileObject>(1,"success",fileObject);
    }
}
