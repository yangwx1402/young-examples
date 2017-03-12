package com.young.java.examples.jdk;

import sun.awt.image.ToolkitImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by young.yang on 2017/3/11.
 */
public class ImageBufferExample {
    /**
     * 图片缩放
     * @param imageFile 图片原文件
     * @param distFile  缩放后的路径
     * @param ratio     缩放比例
     * @throws IOException
     */
    public static void suofang(File imageFile,File distFile,float ratio) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        System.out.println("scale before width="+width+",height="+height);
        //缩放图片
        ToolkitImage outImage = (ToolkitImage) bufferedImage.getScaledInstance((int) (width*ratio), (int) (height*ratio), Image.SCALE_DEFAULT);
        System.out.println("scale after width="+outImage.getWidth()+",height="+outImage.getHeight());
        ImageIO.write(outImage.getBufferedImage(),"jpg",new FileOutputStream(distFile));
    }

    public static BufferedImage toBufferedImage(Image image){
        if(image instanceof BufferedImage)
            return (BufferedImage) image;
        return null;
    }
    public static void main(String[] args) throws IOException {
        String path = ImageBufferExample.class.getResource("/").getPath();
        File file = new File(path,"1.jpg");
        File outFile = new File(path,"1_suofangx2.jpg");
        ImageBufferExample.suofang(file,outFile,5f);
    }
}
