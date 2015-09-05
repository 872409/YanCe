package com.tianbin.yance.model;

public class FaceVerifyResult {

	private String msg;
	private String code;
	private MyEntity entity;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MyEntity getEntity() {
		return entity;
	}

	public void setEntity(MyEntity entity) {
		this.entity = entity;
	}

	public static class MyEntity{
		private String similarity;

		public String getSimilarity() {
			return similarity;
		}

		public void setSimilarity(String similarity) {
			this.similarity = similarity;
		}
	}
}
