package com.tianbin.yance.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.text.TextUtils;
import android.util.Log;

public class FileUtil {
	/**
	 * inputstreamתfile
	 * 
	 * @param ins
	 * @param file
	 */
	public static void inputstreamtofile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[7024];
			while ((bytesRead = ins.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.flush();
			ins.close();
			os.close();
		} catch (Exception e) {
			SysUtilManager.saveCrashInfo2File(e);
		}

	}

	/**
	 * ��һ��byte[]д��SDCard����Ϊһ��ͼƬ�ļ�
	 * */
	public static File writeToSDCardFromInput(String path, byte[] buffer) {
		File file = null;
		OutputStream outputStream = null;

		try {
			file = createFileInSDCard(path);

			outputStream = new FileOutputStream(file);
			InputStream inputStream = new ByteArrayInputStream(buffer);
			byte data[] = new byte[4 * 1024];
			int tmp;
			while ((tmp = inputStream.read(data)) != -1) {
				outputStream.write(data, 0, tmp);
			}
			outputStream.flush();
		} catch (Exception e) {

		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
			}
		}
		return file;
	}

	/**
	 * @param file
	 * @return
	 */
	public static byte[] getBytesFromFile(File file) {
		if (file == null) {
			return null;
		}
		if (file != null && !file.exists()) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1) {
				out.write(b, 0, n);
			}
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * ��sdcard�д����ļ���
	 * 
	 * @param path
	 *            �ļ���·��
	 * @param
	 *            �Ƿ����´���
	 */
	public static void createDir(String path) {
		if (!TextUtils.isEmpty(path)) {
			try {
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * ��SDCard�ϴ����ļ�
	 * */
	public static File createFileInSDCard(String path) throws Exception {
		File file = new File(path);
		if (file.exists())
			return file;
		try {
			File parent = file.getParentFile();
			if (!parent.exists())
				parent.mkdirs();
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			Log.e("kuantest", "errer");
		}
		return file;
	}
}
