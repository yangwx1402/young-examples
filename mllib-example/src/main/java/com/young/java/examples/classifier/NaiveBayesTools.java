package com.young.java.examples.classifier;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import scala.Tuple2;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/27.
 */
public class NaiveBayesTools {

    public static void main(String[] args){
        System.setProperty("HADOOP_USER_NAME","root");
        System.setProperty("user.name","root");
        SparkConf conf = new SparkConf().setAppName("naiveBayes").setMaster("local[2]");
        conf.set("mapreduce.app-submission.cross-platform", "true");
        JavaSparkContext jsc = new JavaSparkContext(conf);
       // jsc.addJar("/root/bayes/libs/mllib-example-1.0.jar");
        //String path = "hdfs://itl6:8020/data/mllib/sample_libsvm_data.txt";
        String path = NaiveBayesTools.class.getResource("/").getPath()+File.separator+"sample_svm_data.txt";
        System.out.println(path);
        JavaRDD<LabeledPoint> inputData = MLUtils.loadLibSVMFile(jsc.sc(), path).toJavaRDD();
        JavaRDD<LabeledPoint>[] tmp = inputData.randomSplit(new double[]{0.6, 0.4}, 12345);
        JavaRDD<LabeledPoint> training = tmp[0]; // training set
        JavaRDD<LabeledPoint> test = tmp[1]; // test set
        final NaiveBayesModel model = NaiveBayes.train(training.rdd(), 1.0);
        JavaPairRDD<Double, Double> predictionAndLabel =
                test.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
                    @Override
                    public Tuple2<Double, Double> call(LabeledPoint p) {
                        return new Tuple2<Double, Double>(model.predict(p.features()), p.label());
                    }
                });
        double accuracy = predictionAndLabel.filter(new Function<Tuple2<Double, Double>, Boolean>() {
            @Override
            public Boolean call(Tuple2<Double, Double> pl) {
                return pl._1().equals(pl._2());
            }
        }).count() / (double) test.count();
        System.out.println(accuracy);

       // model.save(jsc.sc(), "hdfs://itl6:8020/bayes/model");
//        NaiveBayesModel sameModel = NaiveBayesModel.load(jsc.sc(), "target/tmp/myNaiveBayesModel");
    }
}
