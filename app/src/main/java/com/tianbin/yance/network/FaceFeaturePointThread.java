package com.tianbin.yance.network;

import java.io.File;

import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hisign.error.HiwitParseException;
import com.hisign.facefinger.HttpRequests;
import com.hisign.facefinger.PostParameters;
import com.tianbin.yance.framework.Constants;

public class FaceFeaturePointThread implements Runnable{

	private PostParameters bioService;
	private Context mContext;
	private Handler mHandler ;
	
	public FaceFeaturePointThread(byte[] data,Handler handler,Context context) {
		bioService = createPostParameters(data);
		mContext = context;
		mHandler = handler;
	}

	public FaceFeaturePointThread(File file,Handler handler,Context context) {
		bioService = createPostParameters(file);
		mContext = context;
		mHandler = handler;
	}
	
	public FaceFeaturePointThread(byte[] data,String fileName,Handler handler,Context context) {
		bioService = createPostParameters(data,fileName);
		mContext = context;
		mHandler = handler;
	}
	
	@Override
	public void run() {
		try {
			NetWorkManager request = NetWorkManager.getInstance();
			HttpRequests connect = request.connect(mContext);
			if(connect != null){
				JSONObject result = connect.request("face", "featurePoint", bioService);
				String resultStr = result.toString();
				Message msg = Message.obtain();
				msg.what = Constants.FACE_ALIGNMENT;
				msg.obj = resultStr;
				mHandler.sendMessage(msg);
				Log.e("tag", resultStr);
			}else{
				Log.e("tag", "network is not available");
			}
		} catch (HiwitParseException e) {
			e.printStackTrace();
		}
	}
	
	private PostParameters createPostParameters(byte[] data){
		bioService = new PostParameters();
		bioService.setImg(data);
		addAttribute();
		return bioService;
	}
	
	private PostParameters createPostParameters(File file){
		bioService = new PostParameters();
		bioService.setImg(file);
		addAttribute();
		return bioService;
	}
	
	private PostParameters createPostParameters(byte[] data,String fileName){
		bioService = new PostParameters();
		bioService.setImg(data,fileName);
		addAttribute();
		return bioService;
	}
	
	private void addAttribute(){
		bioService.addAttribute("detectROI", "30, 100, 20, 110"); // ��ѡ
	}
	
	
}
