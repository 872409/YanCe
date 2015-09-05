package com.tianbin.yance.analyse;

import android.util.Log;

import com.tianbin.yance.model.BaseModel;
import com.tianbin.yance.model.FaceAlignmentFeaturenPointResult;
import com.tianbin.yance.model.FaceAlignmentResult;
import com.tianbin.yance.model.FaceContour;
import com.tianbin.yance.model.FaceInfo;
import com.tianbin.yance.model.LeftEyeBrowContour;
import com.tianbin.yance.model.LeftEyeCenter;
import com.tianbin.yance.model.LeftEyeContour;
import com.tianbin.yance.model.MouthContour;
import com.tianbin.yance.model.NoseContour;
import com.tianbin.yance.model.RightEyeBrowContour;
import com.tianbin.yance.model.RightEyeContour;
import com.tianbin.yance.util.SysUtilManager;

public class BeautiyAnalyse {

	private FaceAlignmentFeaturenPointResult alignFeaturenPoint = null;
	LeftEyeContour[] leftEyeContour;
	RightEyeContour[] rightEyeContour;
	LeftEyeBrowContour[] leftEyeBrowContour;
	RightEyeBrowContour[] rightEyeBrowContour;
	NoseContour[] noseContours;
	MouthContour[] mouthContour;
	FaceContour[] face;
	FaceContour[] faceAll;
	
	String id = null;

	public float analyse(FaceAlignmentResult alignResult) {
		FeaturePointAnalyse featurePointAnalyse = new FeaturePointAnalyse(alignResult);
		alignFeaturenPoint = featurePointAnalyse.analyse();
		if (alignFeaturenPoint == null) {
			return 0.0f;
		} else {
			init();
			getEyeScore();
			getNoseScore();
			getMouthScore();
			getFaceScore();
			return getScore();
		}
	}

	public double[] analyseDetail(String id,FaceAlignmentResult alignResult) {
		this.id= id;
		double[] resultScores = new double[4];
		FeaturePointAnalyse featurePointAnalyse = new FeaturePointAnalyse(alignResult);
		alignFeaturenPoint = featurePointAnalyse.analyse();

		if (alignFeaturenPoint == null) {
			return resultScores;
		} else {
			init();
			getEyeScore();
			getNoseScore();
			getMouthScore();
			getFaceScore();
			resultScores = getScoreDetail();
			return resultScores;
		}
	}

	// 五眼
	private double y = 0.0000f;
	private double y1 = 0.0000f, y2 = 0.0000f, y3 = 0.0000f, y4 = 0.0000f, y5 = 0.0000f;

	// 三庭
	private double t = 0.0000f;
	private double t1 = 0.0000f, t2 = 0.0000f, t3 = 0.0000f, t4 = 0.0000f, t5 = 0.0000f;

	// 面部总宽
	private double faceWidth = 0.0000f;
	// 面部总高
	private double faceHeight = 0.0000f;

	// 鼻宽
	private double noseWidth = 0.0000f;

	// 嘴宽
	private double mouthWidth = 0.0000f;

	// 眼宽系数	01分别表示正常区间，23分别表示太小，太大，需要大量扣分并提示
	private double coefficientEyeW[] = { 1.1, 1.2, 1.02, 1.28 };
	// 眼位置系数		主要根据眼睛大小跟y3值确定
	private double coefficientEyeStation[] = { 1.18, 1.26, 1.08, 1.30 };
	// 眼宽和眼位置所占分值比例
	private double coefficientEyeWScale = 0.7;

	// 鼻宽系数	01分别表示正常区间，23分别表示太小/太大，需要大量扣分并提示
	private double coefficientNoseW[] = { 0.96, 1.04, 0.90, 1.1 };
	private double coefficientNoseStation[] = { 1, 1.03, 0.97, 1.08 };
	// 鼻宽和y比例
	private double coefficientNoseWToY = 1.4;
	// 鼻宽和眼位置所占分值比例
	private double coefficientNoseWScale = 0.6;

	// 嘴宽系数	01分别表示正常区间，23分别表示太小/太大，需要大量扣分并提示
	private double coefficientMouthW[] = { 0.92, 1.08, 0.89, 1.2 };
	private double coefficientMouth = 2;// y的倍数

	// 长/圆脸系数	宽度/高度
	private double coefficientFace = 1.05;

	// 眼/鼻/嘴/脸 分值
	private double eyeScore = 0.00f, noseScore = 0.00f, mouthScore = 0.00f, faceScore = 0.00f;
	// 眼/鼻/嘴/脸 分值系数
	private double eyeScoreCoefficient = 0.2900f, noseScoreCoefficient = 0.2500f, mouthScoreCoefficient = 0.2000f,
			faceScoreCoefficient = 0.2600f;

	private void getLog() {
		StringBuilder sb = new StringBuilder();
		sb.append("faceWidth=" + faceWidth + ";");
		sb.append("faceHeight=" + faceHeight + ";");
		sb.append("noseWidth=" + noseWidth + ";");
		sb.append("mouthWidth=" + mouthWidth + ";");
		sb.append("y=" + y + ";");
		sb.append("y1=" + y1 + ";");
		sb.append("y2=" + y2 + ";");
		sb.append("y3=" + y3 + ";");
		sb.append("y4=" + y4 + ";");
		sb.append("y5=" + y5 + ";");
		sb.append("t=" + t + ";");
		sb.append("t1=" + t1 + ";");
		sb.append("t2=" + t2 + ";");

		Log.v("tag", id+"------"+sb.toString());
		String content =id+"------"+sb.toString();
		SysUtilManager.saveLog2File(content, true);
	}

	private void init() {

		leftEyeContour = alignFeaturenPoint.getLeftEye();
		rightEyeContour = alignFeaturenPoint.getRightEye();
		leftEyeBrowContour = alignFeaturenPoint.getLeftEyeBrow();
		rightEyeBrowContour = alignFeaturenPoint.getRightEyeBrow();
		noseContours = alignFeaturenPoint.getNose();
		mouthContour = alignFeaturenPoint.getMouth();
		face = alignFeaturenPoint.getFace();
		faceAll = alignFeaturenPoint.getFaceAll();

		faceWidth = face[1].getX() - face[0].getX();
		faceHeight = face[2].getY() - rightEyeBrowContour[0].getY();

		y = faceWidth / 5;
		y1 = leftEyeContour[0].getX() - face[0].getX();
		y2 = leftEyeContour[2].getX() - leftEyeContour[0].getX();
		y3 = rightEyeContour[0].getX() - leftEyeContour[2].getX();
		y4 = rightEyeContour[2].getX() - rightEyeContour[0].getX();
		y5 = face[1].getX() - rightEyeContour[2].getX();

		t = faceHeight / 2;
		t1 = noseContours[1].getY() - rightEyeBrowContour[0].getY();
		t2 = faceHeight - t1;

		noseWidth = noseContours[2].getX() - noseContours[0].getX();

		mouthWidth = mouthContour[1].getX() - mouthContour[0].getX();

	}

	private void getEyeScore() {
		double e1 = 50.00f, e2 = 50.00f, e3 = 100.00f;

		// 计算左眼分值
		double y2_scale = y2 / y;
		if (y2_scale >= coefficientEyeW[0] && y2_scale <= coefficientEyeW[1]) {// ����
			e1 = 50.00f - (Math.abs(y2_scale - 1)) * 20;
		} else if (y2_scale >= coefficientEyeW[2] && y2_scale < coefficientEyeW[0]) {// ��С
			e1 = 50.00f - (Math.abs(y2_scale - 1)) * 40;
		} else if (y2_scale < coefficientEyeW[2]) {// �ǳ�С
			e1 = 50.00f - (Math.abs(y2_scale - 1)) * 60;
		} else if (y2_scale > coefficientEyeW[1] && y2_scale <= coefficientEyeW[3]) {// �ϴ�
			e1 = 50.00f - (Math.abs(y2_scale - 1)) * 40;
		} else if (y2_scale > coefficientEyeW[3]) {// �ϴ�
			e1 = 50.00f - (Math.abs(y2_scale - 1)) * 60;
		}
		// 计算右眼分值
		double y4_scale = y4 / y;
		if (y4_scale >= coefficientEyeW[0] && y4_scale <= coefficientEyeW[1]) {// ����
			e2 = 50.00f - (Math.abs(y4_scale - 1)) * 20;
		} else if (y4_scale >= coefficientEyeW[2] && y4_scale < coefficientEyeW[0]) {// ��С
			e2 = 50.00f - (Math.abs(y4_scale - 1)) * 40;
		} else if (y4_scale < coefficientEyeW[2]) {// �ǳ�С
			e2 = 50.00f - (Math.abs(y4_scale - 1)) * 60;
		} else if (y4_scale > coefficientEyeW[1] && y4_scale <= coefficientEyeW[3]) {// �ϴ�
			e2 = 50.00f - (Math.abs(y4_scale - 1)) * 40;
		} else if (y4_scale > coefficientEyeW[3]) {// �ϴ�
			e2 = 50.00f - (Math.abs(y4_scale - 1)) * 60;
		}
		// 计算眼距分值
		double y3_scale = y3 / y;
		if (y3_scale >= coefficientEyeStation[0] && y3_scale <= coefficientEyeStation[1]) {// ����
			e3 = 100.00f - (Math.abs(y3_scale - 1)) * 40;
		} else if (y3_scale >= coefficientEyeStation[2] && y3_scale < coefficientEyeStation[0]) {// ��С
			e3 = 100.00f - (Math.abs(y3_scale - 1)) * 80;
		} else if (y3_scale < coefficientEyeStation[2]) {// �ǳ�С
			e3 = 100.00f - (Math.abs(y3_scale - 1)) * 120;
		} else if (y3_scale > coefficientEyeStation[1] && y3_scale <= coefficientEyeStation[3]) {// �ϴ�
			e3 = 100.00f - (Math.abs(y3_scale - 1)) * 80;
		} else if (y3_scale > coefficientEyeStation[3]) {// �ϴ�
			e3 = 100.00f - (Math.abs(y3_scale - 1)) * 120;
		}

		eyeScore = (e1 + e2) * coefficientEyeWScale + e3 * (1 - coefficientEyeWScale);

	}

	private void getNoseScore() {
		double n1 = 100.00f, n2 = 100.00f;

		// 计算鼻宽分值
		double nw_scale = noseWidth / (y * coefficientNoseWToY);
		if (nw_scale >= coefficientNoseW[0] && nw_scale <= coefficientNoseW[1]) {// ����
			n1 = 100.00f - (Math.abs(nw_scale - 1)) * 50;
		} else if (nw_scale >= coefficientNoseW[2] && nw_scale < coefficientNoseW[0]) {// ��С
			n1 = 100.00f - (Math.abs(nw_scale - 1)) * 90;
		} else if (nw_scale < coefficientNoseW[2]) {// �ǳ�С
			n1 = 100.00f - (Math.abs(nw_scale - 1)) * 130;
		} else if (nw_scale > coefficientNoseW[1] && nw_scale <= coefficientNoseW[3]) {// �ϴ�
			n1 = 100.00f - (Math.abs(nw_scale - 1)) * 90;
		} else if (nw_scale > coefficientNoseW[3]) {// �ϴ�
			n1 = 100.00f - (Math.abs(nw_scale - 1)) * 130;
		}

		// 计算鼻位置分值
		double ns_scale = t1 / t;
		if (ns_scale >= coefficientNoseStation[0] && ns_scale <= coefficientNoseStation[1]) {// ����
			n2 = 100.00f - (Math.abs(ns_scale - 1)) * 50;
		} else if (ns_scale >= coefficientNoseStation[2] && ns_scale < coefficientNoseStation[0]) {// ��С
			n2 = 100.00f - (Math.abs(ns_scale - 1)) * 90;
		} else if (ns_scale < coefficientNoseStation[2]) {// �ǳ�С
			n2 = 100.00f - (Math.abs(ns_scale - 1)) * 130;
		} else if (ns_scale > coefficientNoseStation[1] && ns_scale <= coefficientNoseStation[3]) {// �ϴ�
			n2 = 100.00f - (Math.abs(ns_scale - 1)) * 90;
		} else if (ns_scale > coefficientNoseStation[3]) {// �ϴ�
			n2 = 100.00f - (Math.abs(ns_scale - 1)) * 130;
		}

		noseScore = n1 * coefficientNoseWScale + n2 * (1 - coefficientNoseWScale);
	}

	private void getMouthScore() {

		double m1 = 100.00f;

		// // 计算嘴宽分值
		double mw_scale = mouthWidth / (y * coefficientMouth);
		if (mw_scale >= coefficientMouthW[0] && mw_scale <= coefficientMouthW[1]) {// ����
			m1 = 100.00f - (Math.abs(mw_scale - 1)) * 50;
		} else if (mw_scale >= coefficientMouthW[2] && mw_scale < coefficientMouthW[0]) {// ��С
			m1 = 100.00f - (Math.abs(mw_scale - 1)) * 90;
		} else if (mw_scale < coefficientMouthW[2]) {// �ǳ�С
			m1 = 100.00f - (Math.abs(mw_scale - 1)) * 130;
		} else if (mw_scale > coefficientMouthW[1] && mw_scale <= coefficientMouthW[3]) {// �ϴ�
			m1 = 100.00f - (Math.abs(mw_scale - 1)) * 90;
		} else if (mw_scale > coefficientMouthW[3]) {// �ϴ�
			m1 = 100.00f - (Math.abs(mw_scale - 1)) * 130;
		}
		mouthScore = m1;
	}

	private void getFaceScore() {
		// double argles[] = new double[20];
		// for (int i = 0; i < 20; i++) {
		// argles[i] = getArgleByPoints(faceAll[i], faceAll[i + 1]);
		// }

		double[] distance = new double[20];

		double m = (faceWidth/110)*10;
		double fscoreM = 0.00f;
		
		for (int i = 0; i < 20; i++) {
			distance[i] = faceAll[i + 1].getX() - faceAll[i].getX();
			 if(distance[i]>m){
				// fscoreM+= (distance[i]-m)*20;
			 }
		}
		
		double face4point = faceAll[17].getX()-faceAll[3].getX();
		double face5point = faceAll[16].getX()-faceAll[4].getX();
		double face6point = faceAll[15].getX()-faceAll[5].getX();
		double face7point = faceAll[14].getX()-faceAll[6].getX();
		double face8point = faceAll[13].getX()-faceAll[7].getX();
		double face9point = faceAll[12].getX()-faceAll[8].getX();
		double face10point = faceAll[11].getX()-faceAll[9].getX();
		
		double s4 = face4point/faceWidth;
		double s5 = face5point/faceWidth;
		double s6 = face6point/faceWidth;
		double s7 = face7point/faceWidth;
		double s8 = face8point/faceWidth;
		double s9 = face9point/faceWidth;
		double s10 = face10point/faceWidth;
		
		double coe = s10/0.166;
		double c9 =Math.abs( s9- coe*0.3067);
		double c8 =Math.abs(  s8- coe*0.4433);
		double c7 =Math.abs(  s7- coe*0.5694);
		double c6 = Math.abs( s6- coe*0.6911);
		double c5 = Math.abs( s5- coe*0.7914);
		double c4 = Math.abs( s4- coe*0.8671);
		double c = 0;
		if(c9>0.05){
			c+=c9;
		}
		if(c8>0.05){
			c+=c8;
		}
		if(c7>0.05){
			c+=c7;
		}
		if(c6>0.05){
			c+=c6;
		}
		if(c5>0.05){
			c+=c5;
		}
		if(c4>0.05){
			c+=c4;
		}
//		if(c>0.1){
		 fscoreM = c*200;
//		}
		StringBuilder sb = new StringBuilder();
		sb.append("�������"+s5);
		sb.append("�������"+( faceAll[15].getX()-faceAll[5].getX())/faceWidth);
		sb.append("���߸���"+( faceAll[14].getX()-faceAll[6].getX())/faceWidth);
		sb.append("�ڰ˸���"+( faceAll[13].getX()-faceAll[7].getX())/faceWidth);
		sb.append("�ھŸ���"+( faceAll[12].getX()-faceAll[8].getX())/faceWidth);
		sb.append("��ʮ����"+( faceAll[11].getX()-faceAll[9].getX())/faceWidth);
		Log.v("tag", id+"------"+sb.toString());
		Log.v("tag", id+"------"+"faceall="+formatBaseModel(faceAll));
		Log.v("tag", id+"------"+"distance="+formatSz(distance));
		SysUtilManager.saveLog2File(sb.toString(), true);
		String content = id+"------"+"faceall="+formatBaseModel(faceAll);
		SysUtilManager.saveLog2File(content, true);
		String content2 = id+"------"+"distance="+formatSz(distance);
		SysUtilManager.saveLog2File(content2, true);
		
		faceScore = 100.00f-fscoreM;
	}

	private String formatBaseModel(BaseModel[] model){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<model.length;i++){
			sb.append(model[i].toString());
		}
		return sb.toString();
	} 
	private String formatSz(double[] model){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<model.length;i++){
			sb.append(model[i]+";");
		}
		return sb.toString();
	} 
	
	private double getArgleByPoints(FaceContour startPoint, FaceContour endPoint) {
		// TODO Auto-generated method stub
		double x = (endPoint.getX() - startPoint.getX());
		double y = (endPoint.getY() - startPoint.getY());
		double z = Math.sqrt(x * x + y * y);
		double argle = Math.round((double) (Math.asin(y / z) / Math.PI * 180.00f));// ���սǶ�
		return argle;
	}

	// �ܷ�
	private float getScore() {
		double score = eyeScore * eyeScoreCoefficient + noseScore * noseScoreCoefficient + mouthScore
				* mouthScoreCoefficient + faceScore * faceScoreCoefficient;

		return (float) (score);
	}

	// ������ϸ
	private double[] getScoreDetail() {
		double score[] = { eyeScore * eyeScoreCoefficient, noseScore * noseScoreCoefficient,
				mouthScore * mouthScoreCoefficient, faceScore * faceScoreCoefficient };

		getLog();

		return (score);
	}
}
