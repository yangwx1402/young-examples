package com.young.java.examples.images.similarity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author shazam
 * @DATE 2018/4/24
 */
public class ImageHelper {
    // 项目根目录路径
    public static final String path = System.getProperty("user.dir");

    public static BufferedImage thumb(BufferedImage source, int width,
                                      int height, boolean b) {
        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double)width / source.getWidth();
        double sy = (double)height / source.getHeight();

        if (b) {
            if (sx > sy) {
                sx = sy;
                width = (int)(sx * source.getWidth());
            } else {
                sy = sx;
                height = (int)(sy * source.getHeight());
            }
        }

        if (type == BufferedImage.TYPE_CUSTOM) {
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else { target = new BufferedImage(width, height, type); }
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    public static void waterMark(String imgPath, String markPath, int x, int y,
                                 float alpha) {
        try {
            // 加载待处理图片文件
            Image img = ImageIO.read(new File(imgPath));

            BufferedImage image = new BufferedImage(img.getWidth(null),
                img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(img, 0, 0, null);

            // 加载水印图片文件
            Image src_biao = ImageIO.read(new File(markPath));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.drawImage(src_biao, x, y, null);
            g.dispose();

            // 保存处理后的文件
            FileOutputStream out = new FileOutputStream(imgPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void textMark(String imgPath, String text, Font font,
                                Color color, int x, int y, float alpha) {
        try {
            Font Dfont = (font == null) ? new Font("宋体", 20, 13) : font;

            Image img = ImageIO.read(new File(imgPath));

            BufferedImage image = new BufferedImage(img.getWidth(null),
                img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();

            g.drawImage(img, 0, 0, null);
            g.setColor(color);
            g.setFont(Dfont);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.drawString(text, x, y);
            g.dispose();
            FileOutputStream out = new FileOutputStream(imgPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static BufferedImage readJPEGImage(String filename) {
        try {
            InputStream imageIn = new FileInputStream(new File(filename));
            // 得到输入的编码器，将文件流进行jpg格式编码
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
            // 得到编码后的图片对象
            BufferedImage sourceImage = decoder.decodeAsBufferedImage();

            return sourceImage;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ImageFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static BufferedImage readPNGImage(InputStream inputStream) {
        try {
            BufferedImage sourceImage = ImageIO.read(inputStream);
            return sourceImage;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ImageFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int rgbToGray(int pixels) {
        // int _alpha = (pixels >> 24) & 0xFF;
        int _red = (pixels >> 16) & 0xFF;
        int _green = (pixels >> 8) & 0xFF;
        int _blue = (pixels) & 0xFF;
        return (int)(0.3 * _red + 0.59 * _green + 0.11 * _blue);
    }

    public static int average(int[] pixels) {
        float m = 0;
        for (int i = 0; i < pixels.length; ++i) {
            m += pixels[i];
        }
        m = m / pixels.length;
        return (int)m;
    }
}

