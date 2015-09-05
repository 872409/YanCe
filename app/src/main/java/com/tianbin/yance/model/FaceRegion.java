package com.tianbin.yance.model;

public class FaceRegion {
	private PosAngle posAngle;
	private FaceVertex faceVertex[];
	
	public PosAngle getPosAngle() {
		return posAngle;
	}
	public void setPosAngle(PosAngle posAngle) {
		this.posAngle = posAngle;
	}
	public FaceVertex[] getFaceVertex() {
		return faceVertex;
	}
	public void setFaceVertex(FaceVertex[] faceVertex) {
		this.faceVertex = faceVertex;
	}
	
	@Override
	public String toString() {
		return "posAngle:"+posAngle+",faceVertex:"+faceVertex[0]+"\n";
	}
	
}
