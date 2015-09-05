package com.tianbin.yance.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.j256.ormlite.dao.Dao;
import com.tianbin.yance.model.Picture;

import android.content.Context;

public class PictureDao
{
	private Dao<Picture, Integer> pictureDao;
	private DatabaseHelper helper;

	@SuppressWarnings("unchecked")
	public PictureDao(Context context)
	{
		try
		{
			helper = DatabaseHelper.getHelper(context);
			pictureDao = helper.getDao(Picture.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void add(Picture picture)
	{
		try
		{
			pictureDao.create(picture);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}


	public Picture get(int id)
	{
		Picture picture = null;
		try
		{
			picture = pictureDao.queryForId(id);

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return picture;
	}
	
	public ArrayList<Picture> getPictures(){
		ArrayList<Picture> pictureList = null;
		try {
			pictureList = (ArrayList<Picture>) pictureDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pictureList;
	}
	
	public ArrayList<Picture> getPicturesByPersonType(int personType){
		ArrayList<Picture> pictureList = null;
		try {
			pictureList = (ArrayList<Picture>) pictureDao.queryBuilder().where().eq("person_type", personType).query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pictureList;
	}

}
