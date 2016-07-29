package com.itl.math;

import java.io.File;

import com.itl.bean.NativeBayesBean;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.deploy.SparkSubmit;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;


import scala.Tuple2;

public class NativeBayes {

    //jsc.addJar(nativeBayesBean.getPutJarPath() + File.separator + nativeBayesBean.getPutJarName());

    //private static JavaSparkContext jsc;
    //private static SparkConf sparkConf;

    public NativeBayes(NativeBayesBean nativeBayesBean){
        System.setProperty("HADOOP_USER_NAME", nativeBayesBean.getHADOOP_USER_NAME());
        System.setProperty("user.name", nativeBayesBean.getUserName());
    }

    public static void main(String args[]){

        readData();
        /*NativeBayes bayes = new NativeBayes(nativeBayesBean);
        JavaRDD<LabeledPoint>[] inputData = bayes.readData(nativeBayesBean);
        final NaiveBayesModel model = bayes.dataTraining(inputData);
        double accuracy = bayes.getModelAccuracy(model,inputData[1]);
        System.out.println(accuracy);
        bayes.saveModel(model,nativeBayesBean);
        bayes.getModel(nativeBayesBean);
        bayes.closeJSC();*/
    }

    public static void readData(){
        NativeBayesBean nativeBayesBean = new NativeBayesBean();
        nativeBayesBean.setAppName("NativeBayes");
        nativeBayesBean.setPutJarPath("/root/bayes/libs");
        nativeBayesBean.setPutJarName("NativeBayes.jar");
        nativeBayesBean.setDataBaseName("default");
        nativeBayesBean.setDataTableName("city");
        nativeBayesBean.setMasterHost("itl6");
        nativeBayesBean.setSaveModelPath("/target/tmp");
        nativeBayesBean.setSaveModelName("Bayes");
        SparkConf sparkConf = new SparkConf().setAppName(nativeBayesBean.getAppName())
                .setMaster("spark://" + nativeBayesBean.getMasterHost() + ":" + nativeBayesBean.getSparkPort())
                .set("SPARK_IDENT_STRING", "root").set("mapreduce.app-submission.cross-platform", "true")
                .set("spark.ui.port", nativeBayesBean.getSparkUiPort());
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        SparkContext sc = new SparkContext(sparkConf);
        HiveContext hive = new HiveContext(jsc.sc());
        DataFrame result = hive.table(nativeBayesBean.getDataTableName());
        JavaRDD<Row> data = result.toJavaRDD();
        JavaRDD<LabeledPoint> inputData = data.map(new Function<Row, LabeledPoint>() {
            public LabeledPoint call(Row row) throws Exception {
                double[] values = new double[row.length()-1];
                for(int i =0;i<row.length()-1;i++){
                    values[i] = row.getDouble(i+1);
                }
                return new LabeledPoint(row.getDouble(0), Vectors.dense(values));
            }
        });
        JavaRDD<LabeledPoint>[] tmp = inputData.randomSplit(new double[]{0.6, 0.4});
    }

    /*public JavaRDD<LabeledPoint>[] readData(NativeBayesBean nativeBayesBean){

        SparkConf sparkConf = new SparkConf().setAppName(nativeBayesBean.getAppName())
                .setMaster("spark://" + nativeBayesBean.getMasterHost() + ":" + nativeBayesBean.getSparkPort())
                .set("SPARK_IDENT_STRING", "root").set("mapreduce.app-submission.cross-platform", "true")
                .set("spark.ui.port", nativeBayesBean.getSparkUiPort());
        jsc = new JavaSparkContext(sparkConf);
        HiveContext hive = new HiveContext(jsc.sc());
        DataFrame result = hive.table(nativeBayesBean.getDataTableName());
        JavaRDD<Row> data = result.toJavaRDD();
        JavaRDD<LabeledPoint> inputData = data.map(new Function<Row, LabeledPoint>() {
            public LabeledPoint call(Row row) throws Exception {
                double[] values = new double[row.length()-1];
                for(int i =0;i<row.length()-1;i++){
                    values[i] = row.getDouble(i+1);
                }
                return new LabeledPoint(row.getDouble(0), Vectors.dense(values));
            }
        });
        JavaRDD<LabeledPoint>[] tmp = inputData.randomSplit(new double[]{0.6, 0.4});
        return tmp;
    }

    public NaiveBayesModel dataTraining(JavaRDD<LabeledPoint>[] data) {

        JavaRDD<LabeledPoint> training = data[0]; // training set
        final NaiveBayesModel model = NaiveBayes.train(training.rdd(), 1.0);
        SparkSubmit.main(null);
        return model;
    }

    public void saveModel(NaiveBayesModel model,NativeBayesBean nativeBayesBean){
        model.save(jsc.sc(), nativeBayesBean.getSaveModelPath() + File.separator + nativeBayesBean.getSaveModelName());
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

    public NaiveBayesModel getModel(NativeBayesBean nativeBayesBean){
        NaiveBayesModel model = NaiveBayesModel.load(jsc.sc(), nativeBayesBean.getSaveModelPath() + File.separator + nativeBayesBean.getSaveModelName());
        return model;
    }

    public void closeJSC(){
        jsc.stop();
        jsc.close();
    }*/
}