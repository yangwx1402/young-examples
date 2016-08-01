package com.young.java.examples.classifier;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;
import scala.Tuple2;

import java.io.File;
import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NativeBayes implements Serializable {

    public NativeBayesBean getConfig(){
        NativeBayesBean nativeBayesBean = new NativeBayesBean();
        nativeBayesBean.setAppName("NativeBayes");
        nativeBayesBean.setPutJarPath("/root/bayes/libs");
        nativeBayesBean.setPutJarName("mllib-example-1.0.jar");
        nativeBayesBean.setDataBaseName("default");
        nativeBayesBean.setDataTableName("city");

        nativeBayesBean.setSaveModelName("Bayes");
        nativeBayesBean.setMasterHost("itl6");
        nativeBayesBean.setSparkPort("7077");
        nativeBayesBean.setSparkUiPort("8080");

        nativeBayesBean.setSaveModelPath("hdfs://itl6:8020/math");
        nativeBayesBean.setSaveModelName("Bayes"+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
        return nativeBayesBean;
    }

    public SparkContext getSparkContext(NativeBayesBean nativeBayesBean){
        SparkConf sparkConf = new SparkConf().setAppName(nativeBayesBean.getAppName())
                .setMaster("spark://" + nativeBayesBean.getMasterHost() + ":" + nativeBayesBean.getSparkPort())
                .set("mapreduce.app-submission.cross-platform", "true");
        SparkContext sparkContext = new  SparkContext(sparkConf);
        sparkContext.addJar(nativeBayesBean.getPutJarPath()+File.separator+nativeBayesBean.getPutJarName());
        return sparkContext;
    }

    public static void main(String args[]){
        NativeBayes bayes = new NativeBayes();
        bayes.runBayes();
    }

    public void runBayes(){
        System.setProperty("hadoop.home.dir","/opt/hadoop");
        NativeBayesBean bayesBean = getConfig();
        System.setProperty("HADOOP_USER_NAME", bayesBean.getHADOOP_USER_NAME());
        System.setProperty("user.name", bayesBean.getUserName());
		System.out.println("SparkContext#######################begin:");
        SparkContext sparkContext = getSparkContext(bayesBean);
		System.out.println("SparkContext#######################end:");
        JavaRDD<LabeledPoint>[] data = readData(sparkContext,bayesBean);
		System.out.println("readData#######################end:");
        NaiveBayesModel model = dataTraining(data);
		System.out.println("dataTraining#######################end:");
        saveModel(sparkContext, model, bayesBean);
		System.out.println("saveModel#######################end:");
        double accuracy = getModelAccuracy(getModel(sparkContext,bayesBean), data[1]);
        System.out.println(accuracy);
    }

    public static JavaRDD<LabeledPoint>[] readData(SparkContext sc,NativeBayesBean nativeBayesBean){
    	System.out.println("readData#######################1:");
        HiveContext hive = new HiveContext(sc);
        System.out.println("readData#######################2:");
        System.out.println("select * from " + nativeBayesBean.getDataBaseName() + "." + nativeBayesBean.getDataTableName());
        DataFrame result = hive.sql("select * from " + nativeBayesBean.getDataBaseName() + "." + nativeBayesBean.getDataTableName());
        System.out.println("readData#######################3:");
        JavaRDD<Row> data = result.toJavaRDD();
        System.out.println("readData#######################4:");
        JavaRDD<LabeledPoint> inputData = data.map(new Function<Row, LabeledPoint>() {
            public LabeledPoint call(Row row) throws Exception {
                double[] values = new double[row.length()-1];
                for(int i =0;i<row.length()-1;i++){
                    values[i] = row.getDouble(i+1);
                }
                return new LabeledPoint(row.getDouble(0), Vectors.dense(values));
            }
        });
        System.out.println("readData#######################4:");
        JavaRDD<LabeledPoint>[] tmp = inputData.randomSplit(new double[]{0.6, 1 - 0.6});
        System.out.println("readData#######################5:");
        return tmp;
    }



    public NaiveBayesModel dataTraining(JavaRDD<LabeledPoint>[] data) {
        JavaRDD<LabeledPoint> training = data[0]; // training set
        NaiveBayesModel model = NaiveBayes.train(training.rdd(), 1.0);
        return model;
    }

    public void saveModel(SparkContext context,NaiveBayesModel model,NativeBayesBean nativeBayesBean){
        model.save(context, nativeBayesBean.getSaveModelPath() + File.separator + nativeBayesBean.getSaveModelName());
    }

    public double getModelAccuracy(final NaiveBayesModel model,JavaRDD<LabeledPoint> test){
        JavaPairRDD<Double, Double> predictionAndLabel =
                test.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
                    public Tuple2<Double, Double> call(LabeledPoint p) {
                        return new Tuple2<Double, Double>(model.predict(p.features()), p.label());
                    }
                });
        double accuracy = predictionAndLabel.filter(new Function<Tuple2<Double, Double>, Boolean>() {
            public Boolean call(Tuple2<Double, Double> pl) {
                return pl._1().equals(pl._2());
            }
        }).count() / (double) test.count();
        return accuracy;
    }

    public NaiveBayesModel getModel(SparkContext context,NativeBayesBean nativeBayesBean){
        NaiveBayesModel model = NaiveBayesModel.load(context, nativeBayesBean.getSaveModelPath() + File.separator + nativeBayesBean.getSaveModelName());
        return model;
    }


}