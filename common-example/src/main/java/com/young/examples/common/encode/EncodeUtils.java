package com.young.examples.common.encode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import com.google.common.hash.Hashing;

public class EncodeUtils {

	public static String randomUuid()
	{
		return UUID.randomUUID().toString();
	}
	
	public static String md5(InputStream input) throws IOException{
		return Hashing.md5().hashBytes(IOUtils.toByteArray(input)).toString();
	}
	
	public static String md5(byte[] buffer)
	{
		return Hashing.md5().hashBytes(buffer).toString();
	}
	
	public static String md5(String path)
	{
		return Hashing.md5().hashString(path, Charset.defaultCharset()).toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String md51 = EncodeUtils.md5(new FileInputStream("D:\\2\\白.doc"));
		System.out.println(md51);
		//验证中烟的生成
		String md511 = EncodeUtils.getMd5ByFile(new FileInputStream("D:\\2\\白.doc"));
		System.out.println(md511);
		
		String md52 = EncodeUtils.md5(new FileInputStream("D:\\2\\白.doc(1)"));
		System.out.println(md52);
		
		FileInputStream inputStream = new FileInputStream(".mv");
		
		byte[] bytes = new byte[1024*1024*10];
		inputStream.read(bytes);
		System.out.println(EncodeUtils.md5(bytes));
		
		inputStream.close();
	}
	
	/**
	 * 中烟提供的
	 * @param fis
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getMd5ByFile(InputStream fis) throws FileNotFoundException {  
        String md5 = null;  
        try {
			 md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
		} catch (IOException e) {
			e.printStackTrace();
		}    
       IOUtils.closeQuietly(fis);  
	    return md5;  
    }  

}
