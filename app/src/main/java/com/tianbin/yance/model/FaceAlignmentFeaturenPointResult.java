package com.tianbin.yance.model;

public class FaceAlignmentFeaturenPointResult {

	// ��ü���ֱ��¼�����м䡢����
	private LeftEyeBrowContour[] leftEyeBrow;
	// ��ü���ֱ��¼�����м䡢����
	private RightEyeBrowContour[] rightEyeBrow;
	// ���ۣ��ֱ��¼�����м䡢����
	private LeftEyeContour[] leftEye;
	// ��ü���ֱ��¼�����м䡢����
	private RightEyeContour[] rightEye;
	// ���ӣ��ֱ��¼�����м䡢����
	private NoseContour[] nose;
	// ��ͣ��ֱ��¼��������
	private MouthContour[] mouth;
	// �������ֱ��¼�������ҡ�����
	private FaceContour[] face;
	// �������е�
	private FaceContour[] faceAll;

	public FaceContour[] getFaceAll() {
		return faceAll;
	}

	public void setFaceAll(FaceContour[] faceAll) {
		this.faceAll = faceAll;
	}

	public LeftEyeBrowContour[] getLeftEyeBrow() {
		return leftEyeBrow;
	}

	public void setLeftEyeBrow(LeftEyeBrowContour[] leftEyeBrow) {
		this.leftEyeBrow = leftEyeBrow;
	}

	public RightEyeBrowContour[] getRightEyeBrow() {
		return rightEyeBrow;
	}

	public void setRightEyeBrow(RightEyeBrowContour[] rightEyeBrow) {
		this.rightEyeBrow = rightEyeBrow;
	}

	public LeftEyeContour[] getLeftEye() {
		return leftEye;
	}

	public void setLeftEye(LeftEyeContour[] leftEye) {
		this.leftEye = leftEye;
	}

	public RightEyeContour[] getRightEye() {
		return rightEye;
	}

	public void setRightEye(RightEyeContour[] rightEye) {
		this.rightEye = rightEye;
	}

	public NoseContour[] getNose() {
		return nose;
	}

	public void setNose(NoseContour[] nose) {
		this.nose = nose;
	}

	public MouthContour[] getMouth() {
		return mouth;
	}

	public void setMouth(MouthContour[] mouth) {
		this.mouth = mouth;
	}

	public FaceContour[] getFace() {
		return face;
	}

	public void setFace(FaceContour[] face) {
		this.face = face;
	}

}
