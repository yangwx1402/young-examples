package softstone.paper.hivesql.hive;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 解析properties文件
 * @author doublejia
 *
 */
public class PropertiesResolve {


	/**
	 * 读取文件中所有属性
	 * @param url
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  List<HashMap<String,Object>> readMapAllKey(String url){
		InputStream ins = this.getClass().getResourceAsStream(url);
		 BufferedReader bf = new BufferedReader(new    InputStreamReader(ins));
		 Properties p = new  Properties();
		 try   {
			  p.load(bf);
			   }   catch  (IOException e1)  {
			  e1.printStackTrace();
			  }
			 List<HashMap<String,Object>> properties=new ArrayList<HashMap<String,Object>>();
			 Enumeration en = p.propertyNames();
			 while (en.hasMoreElements()) {
        	 	HashMap<String,Object> promap=new HashMap<String,Object>();
        	 	String key = (String) en.nextElement();
                String Property = p.getProperty (key);
                promap.put("key",key);
                promap.put("value",Property);
                properties.add(promap);
            }
			 Collections.reverse(properties);
			 return properties;
	}


	/**
	 * 通过KEY获取值
	 * @param url
	 * @param key
	 * @return
	 */
	public  String readMapByKey(String url,String key){
		 InputStream ins = this.getClass().getResourceAsStream(url);
		 BufferedReader bf = new BufferedReader(new    InputStreamReader(ins));
		 Properties p = new  Properties();
		 try   {
			  p.load(bf);
			   }   catch  (IOException e1)  {
			  e1.printStackTrace();
			  }

		   String Property = p.getProperty (key);
		   return Property;
	}

	public static void main(String[] args) {
		PropertiesResolve a=new PropertiesResolve();
		String somename=a.readMapByKey("/system.properties", "步骤1");
		System.out.println(somename);
	}
	public   String getFileIO(String name,String fileURL){
        Properties prop = new Properties();
        InputStream in = PropertiesResolve.class.getResourceAsStream(fileURL);
        try {
           prop.load(in);
           return prop.getProperty(name);
       } catch (IOException e) {
           e.printStackTrace();
       }finally{
           try {
               in.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       return null;
   }
   public   void writeData(String key, String value,String fileURL) {
       Properties prop = new Properties();
       InputStream fis = null;
       OutputStream fos = null;
       try {
             java.net.URL  url = PropertiesResolve.class.getResource(fileURL);
           File file = new File(url.toURI());
           if (!file.exists())
               file.createNewFile();
           fis = new FileInputStream(file);
           prop.load(fis);
           fis.close();//一定要在修改值之前关闭fis
           fos = new FileOutputStream(file);
           prop.setProperty(key, value);
           prop.store(fos, "Update '" + key + "' value");
           fos.close();

       } catch (IOException e) {
           System.err.println("Visit " + fileURL + " for updating "
           + value + " value error");
       } catch (URISyntaxException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       finally{
           try {
               fos.close();
               fis.close();
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }
   }


	//写入Properties信息
     public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
	        Properties pps = new Properties();
	         InputStream in = new FileInputStream(filePath);
	        //从输入流中读取属性列表（键和元素对）
         pps.load(in);
         //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
	        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	        OutputStream out = new FileOutputStream(filePath);
	        pps.setProperty(pKey, pValue);
	        //以适合使用 load 方法加载到 Properties 表中的格式，
        //将此 Properties 表中的属性列表（键和元素对）写入输出流
	       pps.store(out, "Update " + pKey + " name");
	     }
	
	
	
	
}
