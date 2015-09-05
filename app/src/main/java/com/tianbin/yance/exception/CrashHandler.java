package com.tianbin.yance.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.tianbin.yance.framework.ApplicationHelper;
import com.tianbin.yance.util.SysUtilManager;

/**
 * UncaughtException������,��������Uncaught�쳣��ʱ��,�и������ӹܳ���,����¼���ʹ��󱨸�.
 * 
 * @author user
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {
	
	public static final String TAG = "CrashHandler";
	
	//ϵͳĬ�ϵ�UncaughtException������ 
	private UncaughtExceptionHandler mDefaultHandler;
	//CrashHandlerʵ��
	private static CrashHandler INSTANCE = new CrashHandler();
	//�����Context����
	private Context mContext;
	/** ��ֻ֤��һ��CrashHandlerʵ�� */
	private CrashHandler() {
	}

	/** ��ȡCrashHandlerʵ�� ,����ģʽ */
	public static CrashHandler getInstance() {
		return INSTANCE;
	}

	/**
	 * ��ʼ��
	 * 
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;
		//��ȡϵͳĬ�ϵ�UncaughtException������
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		//���ø�CrashHandlerΪ�����Ĭ�ϴ�����
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * ��UncaughtException����ʱ��ת��ú���������
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			//����û�û�д�������ϵͳĬ�ϵ��쳣������������
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				SysUtilManager.saveCrashInfo2File(e);
			}
			ApplicationHelper.exit();
		}
	}

	/**
	 * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����.
	 * 
	 * @param ex
	 * @return true:������˸��쳣��Ϣ;���򷵻�false.
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		//ʹ��Toast����ʾ�쳣��Ϣ
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, "�ܱ�Ǹ,��������쳣,�����˳�.", Toast.LENGTH_LONG).show();
				Looper.loop();
			}
		}.start();
		//�ռ��豸������Ϣ 
		SysUtilManager.collectDeviceInfo(mContext);
		//������־�ļ� 
		SysUtilManager.saveCrashInfo2File(ex);
		
		//��������ҳ��
	     Intent intent = new Intent(Intent.ACTION_MAIN);  
	     ComponentName componentName = new ComponentName("com.hisign.idverification", "com.hisign.idverification.activity.LockActivity");  
	     intent.setComponent(componentName);      
	     mContext.startActivity(intent);
		return true;
	}
	

}
