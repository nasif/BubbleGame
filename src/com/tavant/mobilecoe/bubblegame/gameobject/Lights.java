package com.tavant.mobilecoe.bubblegame.gameobject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.YuvImage;

public class Lights {
	
	private FloatBuffer amBientLightBuffer;
	private FloatBuffer lightDiffusebuffer;
	private FloatBuffer lightPositionBuffer;
	private final static float LightAmbient[]= { 1.0f, 0.0f, 0.0f, 1.0f };
	private final static float LightDiffuse[]= { 1.0f, 0.0f, 0.0f, 1.0f };
	private final static float LightPosition[]= { 0.0f,0.0f, 2.0f, 1.0f };
	
	
	public Lights(){
	}
	
	
	public void LoadLight(GL10 gl){
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, FloatBuffer.wrap(LightAmbient));
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, FloatBuffer.wrap(LightDiffuse));
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, FloatBuffer.wrap(LightPosition));	
	}

}
