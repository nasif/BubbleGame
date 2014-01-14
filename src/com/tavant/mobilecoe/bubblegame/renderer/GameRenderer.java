package com.tavant.mobilecoe.bubblegame.renderer;

import static android.opengl.GLES10.GL_CLAMP_TO_EDGE;
import static android.opengl.GLES10.GL_DEPTH_TEST;
import static android.opengl.GLES10.GL_DITHER;
import static android.opengl.GLES10.GL_FASTEST;
import static android.opengl.GLES10.GL_LINEAR;
import static android.opengl.GLES10.GL_MODULATE;
import static android.opengl.GLES10.GL_NEAREST;
import static android.opengl.GLES10.GL_PERSPECTIVE_CORRECTION_HINT;
import static android.opengl.GLES10.GL_REPEAT;
import static android.opengl.GLES10.GL_REPLACE;
import static android.opengl.GLES10.GL_SMOOTH;
import static android.opengl.GLES10.GL_TEXTURE0;
import static android.opengl.GLES10.GL_TEXTURE_2D;
import static android.opengl.GLES10.GL_TEXTURE_COORD_ARRAY;
import static android.opengl.GLES10.GL_TEXTURE_ENV;
import static android.opengl.GLES10.GL_TEXTURE_ENV_MODE;
import static android.opengl.GLES10.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES10.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES10.GL_TEXTURE_WRAP_S;
import static android.opengl.GLES10.GL_TEXTURE_WRAP_T;
import static android.opengl.GLES10.GL_VERTEX_ARRAY;
import static android.opengl.GLES10.glActiveTexture;
import static android.opengl.GLES10.glBindTexture;
import static android.opengl.GLES10.glClearColor;
import static android.opengl.GLES10.glDisable;
import static android.opengl.GLES10.glEnable;
import static android.opengl.GLES10.glEnableClientState;
import static android.opengl.GLES10.glGenTextures;
import static android.opengl.GLES10.glHint;
import static android.opengl.GLES10.glShadeModel;
import static android.opengl.GLES10.glTexEnvf;
import static android.opengl.GLES10.glTexEnvx;
import static android.opengl.GLES10.glTexParameterf;
import static android.opengl.GLES10.glTexParameterx;

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
import com.tavant.mobilecoe.bubblegame.gameobject.Water;

public class GameRenderer implements Renderer {

	public interface TextureLoader {
		void load(GL10 gl);
	}


	private Water water=null;
	private Context mctx=null;
	private int mTextureID;
	private WaterTextureLoader waterTextureLoader=null;

	public GameRenderer(Context ctx) {
		water=new Water();
		waterTextureLoader=new WaterTextureLoader();
		mctx=ctx;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glTexEnvx(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE,
				GL_MODULATE);

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();   // reset the matrix to its default state
		GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);


		gl.glEnableClientState(GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		gl.glActiveTexture(GL_TEXTURE0);
		gl.glBindTexture(GL_TEXTURE_2D, mTextureID);
		gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,
				GL_REPEAT);
		gl.glTranslatef(0.0f, -0.8f, 0.0f);
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
		glHint(GL_PERSPECTIVE_CORRECTION_HINT,
				GL_FASTEST);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		glShadeModel(GL_SMOOTH);
		glEnable(GL_DEPTH_TEST);
		gl.glEnable(GL_TEXTURE_2D);
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
