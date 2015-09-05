package com.tianbin.yance.analyse;


import com.tianbin.yance.model.BaseModel;
import com.tianbin.yance.model.FaceAlignmentFeaturenPointResult;
import com.tianbin.yance.model.FaceAlignmentResult;
import com.tianbin.yance.model.FaceContour;
import com.tianbin.yance.model.FaceInfo;
import com.tianbin.yance.model.LeftEyeBrowContour;
import com.tianbin.yance.model.LeftEyeContour;
import com.tianbin.yance.model.MouthContour;
import com.tianbin.yance.model.NoseContour;
import com.tianbin.yance.model.RightEyeBrowContour;
import com.tianbin.yance.model.RightEyeContour;
import com.tianbin.yance.model.FaceAlignmentResult.Entity;

/**
 *
 * 对特征点进行分析，取得有用的点
 * */
public class FeaturePointAnalyse {
	private FaceAlignmentResult alignResult = null;

	// FeaturePointAnalyse featurePointAnalyse = new FeaturePointAnalyse();

	public FeaturePointAnalyse(FaceAlignmentResult alignResult) {
		this.alignResult = alignResult;
	}

	public void setSourceModel(FaceAlignmentResult alignResult) {
		this.alignResult = alignResult;
	}

	public FaceAlignmentFeaturenPointResult analyse() {
		FaceAlignmentFeaturenPointResult alignFeature = new FaceAlignmentFeaturenPointResult();
		if (alignResult == null) {
			return null;
		}

		Entity[] entity = alignResult.getEntity();
		FaceInfo faceInfo = entity[0].getFaceInfo();

		LeftEyeBrowContour[] leftEyeBrow = { faceInfo.getLeftEyeBrowContour()[0], faceInfo.getLeftEyeBrowContour()[2],
				faceInfo.getLeftEyeBrowContour()[4] };
		RightEyeBrowContour[] rightEyeBrow = { faceInfo.getRightEyeBrowContour()[4],
				faceInfo.getRightEyeBrowContour()[2], faceInfo.getRightEyeBrowContour()[0] };
		LeftEyeContour[] leftEye = { faceInfo.getLeftEyeContour()[0], faceInfo.getLeftEyeContour()[2],
				faceInfo.getLeftEyeContour()[4] };
		RightEyeContour[] rightEye = { faceInfo.getRightEyeContour()[4], faceInfo.getRightEyeContour()[2],
				faceInfo.getRightEyeContour()[0] };
		int[] noseIndex = getMinAndMaxXYIndex(faceInfo.getNoseContour());
		NoseContour[] nose = { faceInfo.getNoseContour()[noseIndex[0]], faceInfo.getNoseContour()[noseIndex[3]] ,faceInfo.getNoseContour()[noseIndex[1]] };
		int[] mouthIndex = getMinAndMaxXYIndex(faceInfo.getMouthContour());
		MouthContour[] mouth = { faceInfo.getMouthContour()[mouthIndex[0]], faceInfo.getMouthContour()[mouthIndex[1]] };
		int[] faceIndex = getMinAndMaxXYIndex(faceInfo.getFaceContour());
		FaceContour[] face = { faceInfo.getFaceContour()[faceIndex[0]], faceInfo.getFaceContour()[faceIndex[1]] , faceInfo.getFaceContour()[faceIndex[3]] ,};

		alignFeature.setLeftEyeBrow(leftEyeBrow);
		alignFeature.setRightEyeBrow(rightEyeBrow);
		alignFeature.setLeftEye(leftEye);
		alignFeature.setRightEye(rightEye);
		alignFeature.setNose(nose);
		alignFeature.setMouth(mouth);
		alignFeature.setFace(face);
		alignFeature.setFaceAll(faceInfo.getFaceContour());
		
		return alignFeature;
	}

	private int[] getMinAndMaxXYIndex(BaseModel[] contours) {
		int[] indexs = new int[4];  //分别表示最小x点，最大x点，最小y点，最大y点
		double minX = 0.0000f, maxX = 0.0000f, minY = 0.0000f, maxY = 0.0000f;

		if (contours == null)
			return indexs;
		for (int i = 0; i < contours.length; i++) {

			BaseModel contour = contours[i];
			if (i == 0) {
				minX = contour.getX();
				maxX = contour.getX();
				minY = contour.getY();
				maxY = contour.getY();
			} else {
				if (contour.getX() < minX) {
					minX = contour.getX();
					indexs[0] = i;
				}
				if (contour.getX() > maxX) {
					maxX = contour.getX();
					indexs[1] = i;
				}
				if (contour.getY() < minY) {
					minY = contour.getY();
					indexs[2] = i;
				}
				if (contour.getY() > maxY) {
					maxY = contour.getY();
					indexs[3] = i;
				}
			}
		}
		return indexs;
	}

}
