package com.tianbin.yance.network;

import android.content.Context;

import com.hisign.facefinger.HttpRequests;
import com.tianbin.yance.util.NetUtil;

public class NetWorkManager {

//	private final static int Net_CONNECTED_WIFI = 0;
//	private final static int Net_CONNECTED_ = 1;
//	private final static int Net_CONNECTED_WIFI = 2;
	
	private String appId = "198f2b23cfcd420a9622b345a6aa00a7";
	private String appKey = "NDQ5NTAzNDc=";
	private String url_server = "http://api.facefinger.cn:8080/ff/";
	
	private NetWorkManager(){}
	private static NetWorkManager netWorkManager;
	public static NetWorkManager getInstance(){
		if(netWorkManager == null){
			netWorkManager = new NetWorkManager();
		}
		return netWorkManager; 
	}
	
	private boolean checkNetWorkConnect(Context context){
		return NetUtil.isNetworkConnected(context);
	}
	
	public HttpRequests connect(Context context){
		boolean isAvaiable = false;
		if(context != null){
			isAvaiable = checkNetWorkConnect(context);
		}
		HttpRequests httpRequests = null;
		if(isAvaiable){
			httpRequests = new HttpRequests(url_server, appId, appKey);
		}
		return httpRequests;
	}
}
