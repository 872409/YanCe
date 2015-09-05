package com.tianbin.yance.model;

public class FaceAlignmentResult {

	private String msg;
	private String code;
	private Entity[] entity;

	public Entity[] getEntity() {
		return entity;
	}

	public void setEntity(Entity[] entity) {
		this.entity = entity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "msg:"+msg+",entity:"+entity[0]+",code:"+code+"\n";
	}

	public static class Entity{
		private FaceInfo faceInfo;
		private FaceRegion faceRegion;
		private int glassesType;
		private double qualityScore;
		public FaceInfo getFaceInfo() {
			return faceInfo;
		}
		public void setFaceInfo(FaceInfo faceInfo) {
			this.faceInfo = faceInfo;
		}
		public FaceRegion getFaceRegion() {
			return faceRegion;
		}
		public void setFaceRegion(FaceRegion faceRegion) {
			this.faceRegion = faceRegion;
		}
		public int getGlassesType() {
			return glassesType;
		}
		public void setGlassesType(int glassesType) {
			this.glassesType = glassesType;
		}
		public double getQualityScore() {
			return qualityScore;
		}
		public void setQualityScore(double qualityScore) {
			this.qualityScore = qualityScore;
		}
		
		@Override
		public String toString() {
			return "faceInfo:"+faceInfo+",faceRegion:"+faceRegion+",glassesType:"+glassesType+",qualityScore:"+qualityScore+"\n";
		}
	}
}
