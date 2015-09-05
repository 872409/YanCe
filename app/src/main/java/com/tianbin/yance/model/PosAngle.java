package com.tianbin.yance.model;

public class PosAngle {

	private int pitch;
	private int yaw;
	private int roll;
	public int getPitch() {
		return pitch;
	}
	public void setPitch(int pitch) {
		this.pitch = pitch;
	}
	public int getYaw() {
		return yaw;
	}
	public void setYaw(int yaw) {
		this.yaw = yaw;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	@Override
	public String toString() {
		return "pitch:"+pitch+",yaw:"+yaw+",roll:"+roll;
	}
}
