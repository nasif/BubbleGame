package com.tavant.mobilecoe.bubblegame.renderer;

import static android.opengl.GLES10.GL_CLAMP_TO_EDGE;
import static android.opengl.GLES10.GL_DEPTH_TEST;
import static android.opengl.GLES10.GL_DITHER;
import static android.opengl.GLES10.GL_FASTEST;
import static android.opengl.GLES10.GL_LINEAR;
import static android.opengl.GLES10.GL_NEAREST;
import static android.opengl.GLES10.GL_PERSPECTIVE_CORRECTION_HINT;
import static android.opengl.GLES10.GL_REPEAT;
import static android.opengl.GLES10.GL_REPLACE;
import static android.opengl.GLES10.GL_SMOOTH;
import static android.opengl.GLES10.GL_TEXTURE_2D;
import static android.opengl.GLES10.GL_TEXTURE_ENV;
import static android.opengl.GLES10.GL_TEXTURE_ENV_MODE;
import static android.opengl.GLES10.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES10.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES10.GL_TEXTURE_WRAP_S;
import static android.opengl.GLES10.GL_TEXTURE_WRAP_T;
import static android.opengl.GLES10.glDisable;
import static android.opengl.GLES10.glEnable;
import static android.opengl.GLES10.glHint;
import static android.opengl.GLES10.glShadeModel;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;

import com.tavant.mobilecoe.bubblegame.R;
import com.tavant.mobilecoe.bubblegame.gameobject.Lights;
import com.tavant.mobilecoe.bubblegame.gameobject.Water;

public class GameRenderer implements Renderer {

	public interface TextureLoader {
		void load(GL10 gl);
	}


	private Water water=null;
	private Context mctx=null;
	private int mTextureID;
	private WaterTextureLoader waterTextureLoader=null;
	private Lights light=null;

	public GameRenderer(Context ctx) {
		water=new Water();
		light=new Lights();
		waterTextureLoader=new WaterTextureLoader();
		mctx=ctx;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();   // reset the matrix to its default state
		gl.glEnable(GL10.GL_LIGHTING);
		GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		gl.glTranslatef(0.0f, -1.15f, 1.0f);
		gl.glRotatef(80, 1.0f, 0.0f, 0.0f);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glBindTexture(GL_TEXTURE_2D, mTextureID);
		gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,
				GL_REPEAT);
		gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,
		GL_REPEAT);		
		water.draw(gl);
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		// make adjustments for screen ratio
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);        // set matrix to projection mode
		gl.glLoadIdentity();                        // reset the matrix to its default state
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);  // apply the projection mat
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glDisable(GL_DITHER); 	
		glShadeModel(GL_SMOOTH);
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glClearDepthf(1.0f);
		glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_FASTEST);
		/*
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		*/
		light.LoadLight(gl);
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		mTextureID = textures[0];
		gl.glBindTexture(GL_TEXTURE_2D, mTextureID);

		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,
				GL_NEAREST);
		gl.glTexParameterf(GL_TEXTURE_2D,
				GL_TEXTURE_MAG_FILTER,
				GL_LINEAR);

		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,
				GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,
				GL_CLAMP_TO_EDGE);

		gl.glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE,
				GL_REPLACE);
		waterTextureLoader.load(gl);
	}

	private class WaterTextureLoader implements TextureLoader {
		public void load(GL10 gl) {
			InputStream is = mctx.getResources().openRawResource(
					R.raw.water_texture);
			Bitmap bitmap;
			try {
				bitmap = BitmapFactory.decodeStream(is);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					// Ignore.
				}
			}
			GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
			bitmap.recycle();
		}
	} 

}
