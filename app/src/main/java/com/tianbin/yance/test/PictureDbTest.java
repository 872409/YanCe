package com.tianbin.yance.test;

import android.test.AndroidTestCase;

import com.tianbin.yance.dao.PictureDao;
import com.tianbin.yance.model.Picture;

import java.util.ArrayList;

public class PictureDbTest extends AndroidTestCase {

	public void testAddPicture(){
		Picture picture = new Picture();
		picture.setPersonName("����");
		picture.setFilePath("����.jpg");
		picture.setPersonType(0);
		picture.setScore(100);
		new PictureDao(getContext()).add(picture);
		picture.setPersonName("���");
		picture.setFilePath("���.jpg");
		picture.setPersonType(1);
		picture.setScore(100);
		new PictureDao(getContext()).add(picture);
	}
	
	public void testGetPictures(){
		ArrayList<Picture> pictures = new PictureDao(getContext()).getPictures();
		System.out.println("id--->"+pictures.get(0).getId());
	}
	
	public void testGet(){
		Picture picture = new PictureDao(getContext()).get(1);
		System.out.println("name--->"+picture.getPersonName());
	}
	
	public void testGetPicturesByPersonType(){
		ArrayList<Picture> picturesByPersonType = new PictureDao(getContext()).getPicturesByPersonType(0);
		System.out.println("size--->"+picturesByPersonType.size());
	}
}
