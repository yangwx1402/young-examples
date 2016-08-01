package com.young.java.examples.classifier;

public class NativeBayesBean {
	
	private String HADOOP_USER_NAME;
	private String userName;
	private String appName;
	private String masterHost;
	private String sparkPort;
	private String sparkUiPort;
	private String putJarPath;//上传jar包路径
	private String putJarName;//上传jar包名称
	private String dataBaseName;
	private String dataTableName;
	private int classifyColumnIndex;//分类字段索引
	private String saveModelPath;//模型存储路径
	private String saveModelName;//模型保存名称
	
	public NativeBayesBean(){
		HADOOP_USER_NAME = "root";
		userName = "root";
		sparkPort = "7077";
		sparkUiPort = "8080";
	}

	public String getHADOOP_USER_NAME() {
		return HADOOP_USER_NAME;
	}

	public void setHADOOP_USER_NAME(String hADOOP_USER_NAME) {
		HADOOP_USER_NAME = hADOOP_USER_NAME;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getMasterHost() {
		return masterHost;
	}

	public void setMasterHost(String masterHost) {
		this.masterHost = masterHost;
	}

	public String getSparkPort() {
		return sparkPort;
	}

	public void setSparkPort(String sparkPort) {
		this.sparkPort = sparkPort;
	}

	public String getSparkUiPort() {
		return sparkUiPort;
	}

	public void setSparkUiPort(String sparkUiPort) {
		this.sparkUiPort = sparkUiPort;
	}

	public String getPutJarPath() {
		return putJarPath;
	}

	public void setPutJarPath(String putJarPath) {
		this.putJarPath = putJarPath;
	}

	public String getPutJarName() {
		return putJarName;
	}

	public void setPutJarName(String putJarName) {
		this.putJarName = putJarName;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getDataTableName() {
		return dataTableName;
	}

	public void setDataTableName(String dataTableName) {
		this.dataTableName = dataTableName;
	}

	public int getClassifyColumnIndex() {
		return classifyColumnIndex;
	}

	public void setClassifyColumnIndex(int classifyColumnIndex) {
		this.classifyColumnIndex = classifyColumnIndex;
	}

	public String getSaveModelPath() {
		return saveModelPath;
	}

	public void setSaveModelPath(String saveModelPath) {
		this.saveModelPath = saveModelPath;
	}

	public String getSaveModelName() {
		return saveModelName;
	}

	public void setSaveModelName(String saveModelName) {
		this.saveModelName = saveModelName;
	}

}
