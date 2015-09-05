package com.tianbin.yance.framework;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

import com.tianbin.yance.exception.CrashHandler;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;

public class ApplicationHelper extends Application {

	// �洢ȫ�ֱ���
	private static HashMap<Object, Object> map = null;
	// ��¼���д򿪹��Activity
	private static ArrayList<Activity> activities = null;

	private static ApplicationHelper application;

	@Override
	public void onCreate() {
		super.onCreate();
		map = new HashMap<Object, Object>();
		activities = new ArrayList<Activity>();
		application = this;
		exceptionManage();
		initDirs();
	}

	// ��ʼ���ļ�Ŀ¼
	private void initDirs() {

	}

	public static Object getMap(Object object) {
		return map.get(object);
	}

	public static void putMap(Object key, Object value) {
		if (map.containsKey(key)) {
			map.remove(key);
		}
		map.put(key, value);
	}

	public static void removerObj(Object key) {
		if (map.get(key) != null)
			map.remove(key);
	}

	public static void clearMap() {
		map.clear();
	}

	public static ApplicationHelper getApplication() {
		return application;
	}

	public static void addActivity(Activity activity) {
		activities.add(activity);
	}

	public static void removeActivity(Activity activity) {
		for (int i = 0; i < activities.size(); i++) {
			if (activity.getLocalClassName().equals(activities.get(i).getLocalClassName())) {
				activities.remove(i);
				break;
			}
		}
	}

	/**
	 * �˳����ܡ�
	 */
	public static void exit() {

		if (activities != null && activities.size() > 0)
			for (int i = 0; i < activities.size(); i++) {
				try {
					activities.get(i).finish();
				} catch (Exception e) {
				}
			}
	}

	public static ArrayList<Activity> getActivities() {
		return activities;
	}

	public static void setActivities(ArrayList<Activity> activities) {
		ApplicationHelper.activities = activities;
	}

	/**
	 * ȫ���쳣��׽����
	 */
	private void exceptionManage() {
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}

}
