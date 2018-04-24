package com.young.java.examples.images;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.young.java.examples.images.similarity.HttpDownloader;
import com.young.java.examples.images.similarity.SimilarImage;

/**
 * @author shazam
 * @DATE 2018/4/24
 */
public class ImageSimilarityExample {
    public static void main(String[] args) {
        List<String> images = Arrays.asList("http://img5.mtime.cn/pi/2017/08/08/143023.69811887_1000X1000.jpg",
            "http://img5.mtime.cn/pi/2017/08/08/143059.24402984_1000X1000.jpg",
            "http://img31.mtime.cn/pi/2015/04/07/101200.23097599_1000X1000.jpg");
        List<InputStream> streams = images.stream().map(HttpDownloader::download).collect(Collectors.toList());
        List<String> features = streams.stream().map(SimilarImage::getFingerPrint).collect(Collectors.toList());
        for (int i = 0; i < features.size() - 1; i++) {
            for (int j = i + 1; j < features.size(); j++) {
                System.out.println(SimilarImage.hammingDistance(features.get(i), features.get(j)));
            }
        }
    }
}
