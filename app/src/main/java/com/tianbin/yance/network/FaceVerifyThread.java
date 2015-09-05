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

public class FaceVerifyThread implements Runnable{

	private PostParameters bioService;
	private Context mContext;
	private Handler mHandler ;
	
	public FaceVerifyThread(String value1,String value2,Handler handler,Context context) {
		bioService = createPostParameters(value1,value2);
		mContext = context;
		mHandler = handler;
	}

	public FaceVerifyThread(File file1,File file2,Handler handler,Context context) {
		bioService = createPostParameters(file1,file2);
		mContext = context;
		mHandler = handler;
	}
	
	public FaceVerifyThread(byte[] buffer1,byte[] buffer2,Handler handler,Context context) {
		bioService = createPostParameters(buffer1,buffer2);
		mContext = context;
		mHandler = handler;
	}
	
	@Override
	public void run() {
		try {
			NetWorkManager request = NetWorkManager.getInstance();
			HttpRequests connect = request.connect(mContext);
			if(connect != null){
				JSONObject result = connect.request("face", "verify", bioService);
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
	
	private PostParameters createPostParameters(String value1,String value2){
		bioService = new PostParameters();
		bioService.addAttribute("img1", value1);
		bioService.addAttribute("img2", value2);
		return bioService;
	}
	
	private PostParameters createPostParameters(File file1,File file2){
		bioService = new PostParameters();
		bioService.addAttribute("img1", file1);
		bioService.addAttribute("img2", file2);
		return bioService;
	}
	
	private PostParameters createPostParameters(byte[] buffer1,byte[] buffer2){
		bioService = new PostParameters();
		bioService.addAttribute("img1", buffer1);
		bioService.addAttribute("img2", buffer2);
		return bioService;
	}
	
}
