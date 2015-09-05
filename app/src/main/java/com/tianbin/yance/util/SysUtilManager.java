package com.tianbin.yance.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianbin.yance.framework.ApplicationHelper;
import com.tianbin.yance.framework.Constants;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class SysUtilManager {
	public static final String TAG = "SysUtilManager";
	private static long lastClickTime;
	private static long do_time = 700;

	// �����洢�豸��Ϣ���쳣��Ϣ
	private static Map<String, String> infos = new HashMap<String, String>();

	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < do_time) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * ���ؼ���
	 * 
	 * @param
	 */
	public static void KeyBoardCancle(Activity activity) {
		activity.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	/**
	 * ���ؼ���
	 * 
	 * @param
	 */
	public static void keyBoardDisplay(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * ��ȡϵͳ��SDK�汾��
	 * 
	 * @return
	 */
	public static int getSystemVersion() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * ���������Ϣ���ļ���
	 * 
	 * @param ex
	 * @return �����ļ����,���ڽ��ļ����͵�������
	 */
	public static String saveCrashInfo2File(Throwable ex) {

		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		try {
			long timestamp = System.currentTimeMillis();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String time = formatter.format(new Date());
			String fileName = "exception-" + time + "-" + timestamp + ".txt";
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				String path = Environment.getExternalStorageDirectory().getPath() + "/" + Constants.PRO_NAME + "/log/";
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(path + fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
			return fileName;
		} catch (Exception e) {
			Log.e(TAG, "an error occured while writing file...", e);
		}
		return null;
	}

	/**
	 * ������־��Ϣ���ļ���
	 * 
	 * @param
	 * @return �����ļ����,���ڽ��ļ����͵�������
	 */
	public static String saveLog2File(String conent, boolean addToFile) {

		// StringBuffer sb = new StringBuffer();
		// for (Map.Entry<String, String> entry : infos.entrySet()) {
		// String key = entry.getKey();
		// String value = entry.getValue();
		// sb.append(key + "=" + value + "\n");
		// }
		// Writer writer = new StringWriter();
		// PrintWriter printWriter = new PrintWriter(writer);
		// ex.printStackTrace(printWriter);
		// Throwable cause = ex.getCause();
		// while (cause != null) {
		// cause.printStackTrace(printWriter);
		// cause = cause.getCause();
		// }
		// printWriter.close();
		// String result = writer.toString();
		// sb.append(result);

		try {

			String fileName = "mylog.txt";

			String path = Environment.getExternalStorageDirectory().getPath() + "/" + Constants.PRO_NAME + "/log/";
			//String path ="sdcard/"+ Constants.PRO_NAME + "/log/";
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File logs = new File(path + fileName);
			if (!logs.exists())
				logs.createNewFile();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logs, addToFile)));
			out.write(conent);
			out.write("\n");
			out.close();
			return fileName;
		} catch (Exception e) {
			Log.e(TAG, "an error occured while writing file...", e);
		}
		return null;
	}

	public static String strFormat(String str) {
		if (str == null) {
			return "";
		} else if (str.equals("null")) {
			return "";
		} else {
			return str.trim();
		}

	}

	/**
	 * �ռ��豸������Ϣ
	 * 
	 * @param ctx
	 */
	public static void collectDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				String versionName = pi.versionName == null ? "null" : pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			saveCrashInfo2File(e);
		}
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
			} catch (Exception e) {
				saveCrashInfo2File(e);
			}
		}
	}

	/**
	 * ��assets�ļ����е��ļ�
	 * 
	 * @param path
	 * @param assetManager
	 * @return
	 */
	public static byte[] openAssets(String path, AssetManager assetManager) {
		byte[] data = null;
		try {
			InputStream is = assetManager.open(path);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] temp = new byte[1024];
			int len = 0;
			while ((len = is.read(temp)) != -1) {
				out.write(temp, 0, len);
			}
			data = out.toByteArray();
			is.close();
			out.flush();
			out.close();
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
