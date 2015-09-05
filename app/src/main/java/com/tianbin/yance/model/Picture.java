package com.tianbin.yance.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "picture")
public class Picture {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(columnName = "person_name")
	private String personName;
	@DatabaseField(columnName = "file_path")
	private String filePath;
	@DatabaseField
	private double score;
	@DatabaseField(columnName = "person_type")
	private int personType;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getPersonType() {
		return personType;
	}
	public void setPersonType(int personType) {
		this.personType = personType;
	}
}
