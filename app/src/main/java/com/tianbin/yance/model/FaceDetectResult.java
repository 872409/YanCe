package com.tianbin.yance.model;

import android.graphics.Point;


/**
 * {
    "msg": "���",
    "entity": [
        {
            "posAngle": {
                "pitch": 1,
                "yaw": -14,
                "roll": 0
            },
            "faceVertex": [
                {
                    "y": 63,
                    "x": 228
                },
                {
                    "y": 63,
                    "x": 328
                },
                {
                    "y": 163,
                    "x": 328
                },
                {
                    "y": 163,
                    "x": 228
                }
            ]
        }
    ],
    "code": "1001"
}
 * @author hanmushui
 *
 */
public class FaceDetectResult {

	private String msg;
	private Entity[] entity;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Entity[] getEntity() {
		return entity;
	}

	public void setEntity(Entity[] entity) {
		this.entity = entity;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "msg:"+msg+",code:"+code+",entity:"+entity[0].toString();
	}

	public static class Entity{
		private PosAngle posAngle;
		private FaceVertex[] faceVertex;
		
		public FaceVertex[] getFaceVertex() {
			return faceVertex;
		}
		public void setFaceVertex(FaceVertex[] faceVertex) {
			this.faceVertex = faceVertex;
		}
		public PosAngle getPosAngle() {
			return posAngle;
		}
		public void setPosAngle(PosAngle posAngle) {
			this.posAngle = posAngle;
		}
		@Override
		public String toString() {
			return "posAngle:"+posAngle.toString()+",faceVertext:"+faceVertex[0].toString();
		}
	}
}
