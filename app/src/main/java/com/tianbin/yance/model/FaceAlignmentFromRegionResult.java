package com.tianbin.yance.model;

public class FaceAlignmentFromRegionResult {

	private String code;
	private String msg;
	private Entity[] entity;
	
	
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


	public Entity[] getEntity() {
		return entity;
	}


	public void setEntity(Entity[] entity) {
		this.entity = entity;
	}


	@Override
	public String toString() {
		return "code:"+code+",msg:"+msg+",entity:"+entity[0];
	}


	public static class Entity{
		private LeftEyeCenter leftEyeCenter;
		private LeftEyeTail leftEyeTail;
		private LeftEyeInner leftEyeInner;
		private RightEyeCenter rightEyeCenter;
		private RightEyeTail rightEyeTail;
		private RightEyeInner rightEyeInner;
		private LeftNose leftNose;
		private RightNose rightNose;
		private TopNose topNose;
		private LeftMouth leftMouth;
		private RightMouth rightMouth;
		private MouthCenter mouthCenter;
		private TopCenterUpperLip topCenterUpperLip;
		private BottomCenterUpperLip bottomCenterUpperLip;
		private TopCenterLowerLip topCenterLowerLip;
		private BottomCenterLowerLip bottomCenterLowerLip;
		private LeftEyeContour[] leftEyeContour;
		private RightEyeContour[] rightEyeContour;
		private LeftEyeBrowContour[] leftEyeBrowContour;
		private RightEyeBrowContour[] rightEyeBrowContour;
		private NoseContour[] noseContour;
		private MouthContour[] mouthContour;
		private FaceContour[] faceContour;
		
		public LeftEyeCenter getLeftEyeCenter() {
			return leftEyeCenter;
		}

		public void setLeftEyeCenter(LeftEyeCenter leftEyeCenter) {
			this.leftEyeCenter = leftEyeCenter;
		}

		public LeftEyeTail getLeftEyeTail() {
			return leftEyeTail;
		}

		public void setLeftEyeTail(LeftEyeTail leftEyeTail) {
			this.leftEyeTail = leftEyeTail;
		}

		public LeftEyeInner getLeftEyeInner() {
			return leftEyeInner;
		}

		public void setLeftEyeInner(LeftEyeInner leftEyeInner) {
			this.leftEyeInner = leftEyeInner;
		}

		public RightEyeCenter getRightEyeCenter() {
			return rightEyeCenter;
		}

		public void setRightEyeCenter(RightEyeCenter rightEyeCenter) {
			this.rightEyeCenter = rightEyeCenter;
		}

		public RightEyeTail getRightEyeTail() {
			return rightEyeTail;
		}

		public void setRightEyeTail(RightEyeTail rightEyeTail) {
			this.rightEyeTail = rightEyeTail;
		}

		public RightEyeInner getRightEyeInner() {
			return rightEyeInner;
		}

		public void setRightEyeInner(RightEyeInner rightEyeInner) {
			this.rightEyeInner = rightEyeInner;
		}

		public LeftNose getLeftNose() {
			return leftNose;
		}

		public void setLeftNose(LeftNose leftNose) {
			this.leftNose = leftNose;
		}

		public RightNose getRightNose() {
			return rightNose;
		}

		public void setRightNose(RightNose rightNose) {
			this.rightNose = rightNose;
		}

		public TopNose getTopNose() {
			return topNose;
		}

		public void setTopNose(TopNose topNose) {
			this.topNose = topNose;
		}

		public LeftMouth getLeftMouth() {
			return leftMouth;
		}

		public void setLeftMouth(LeftMouth leftMouth) {
			this.leftMouth = leftMouth;
		}

		public RightMouth getRightMouth() {
			return rightMouth;
		}

		public void setRightMouth(RightMouth rightMouth) {
			this.rightMouth = rightMouth;
		}

		public MouthCenter getMouthCenter() {
			return mouthCenter;
		}

		public void setMouthCenter(MouthCenter mouthCenter) {
			this.mouthCenter = mouthCenter;
		}

		public TopCenterUpperLip getTopCenterUpperLip() {
			return topCenterUpperLip;
		}

		public void setTopCenterUpperLip(TopCenterUpperLip topCenterUpperLip) {
			this.topCenterUpperLip = topCenterUpperLip;
		}

		public BottomCenterUpperLip getBottomCenterUpperLip() {
			return bottomCenterUpperLip;
		}

		public void setBottomCenterUpperLip(BottomCenterUpperLip bottomCenterUpperLip) {
			this.bottomCenterUpperLip = bottomCenterUpperLip;
		}

		public TopCenterLowerLip getTopCenterLowerLip() {
			return topCenterLowerLip;
		}

		public void setTopCenterLowerLip(TopCenterLowerLip topCenterLowerLip) {
			this.topCenterLowerLip = topCenterLowerLip;
		}

		public BottomCenterLowerLip getBottomCenterLowerLip() {
			return bottomCenterLowerLip;
		}

		public void setBottomCenterLowerLip(BottomCenterLowerLip bottomCenterLowerLip) {
			this.bottomCenterLowerLip = bottomCenterLowerLip;
		}

		public LeftEyeContour[] getLeftEyeContour() {
			return leftEyeContour;
		}

		public void setLeftEyeContour(LeftEyeContour[] leftEyeContour) {
			this.leftEyeContour = leftEyeContour;
		}

		public RightEyeContour[] getRightEyeContour() {
			return rightEyeContour;
		}

		public void setRightEyeContour(RightEyeContour[] rightEyeContour) {
			this.rightEyeContour = rightEyeContour;
		}

		public LeftEyeBrowContour[] getLeftEyeBrowContour() {
			return leftEyeBrowContour;
		}

		public void setLeftEyeBrowContour(LeftEyeBrowContour[] leftEyeBrowContour) {
			this.leftEyeBrowContour = leftEyeBrowContour;
		}

		public RightEyeBrowContour[] getRightEyeBrowContour() {
			return rightEyeBrowContour;
		}

		public void setRightEyeBrowContour(RightEyeBrowContour[] rightEyeBrowContour) {
			this.rightEyeBrowContour = rightEyeBrowContour;
		}

		public NoseContour[] getNoseContour() {
			return noseContour;
		}

		public void setNoseContour(NoseContour[] noseContour) {
			this.noseContour = noseContour;
		}

		public MouthContour[] getMouthContour() {
			return mouthContour;
		}

		public void setMouthContour(MouthContour[] mouthContour) {
			this.mouthContour = mouthContour;
		}

		public FaceContour[] getFaceContour() {
			return faceContour;
		}

		public void setFaceContour(FaceContour[] faceContour) {
			this.faceContour = faceContour;
		}
	}
}
