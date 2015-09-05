package com.tianbin.yance.util;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;

public class ImgUtil {

	/**
	 * ���bitmap�ĵõ�byte[]���
	 * 
	 * @param bitmap
	 * @return byte[]
	 */
	public static byte[] getBytesFromBitmap(Bitmap bitmap) {
		byte[] images = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			if (baos != null && bitmap != null) {
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
				images = baos.toByteArray();
				baos.flush();
				baos.close();
				baos = null;
				System.gc();
			}
		} catch (Exception e) {
			return null;
		}
		return images;
	}

}
