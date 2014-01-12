package com.tavant.mobilecoe.bubblegame.renderer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.ETC1Util;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.widget.Toast;

public class GameView extends GLSurfaceView {

	// Currently this application is using opengles version 1.0
	private GameRenderer renderer;


	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);    
		renderer=new GameRenderer(context);
		setEGLContextClientVersion(1);
		setPreserveEGLContextOnPause(true);
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}


	private boolean hasGLES20(Context ctx) {
		ActivityManager am = (ActivityManager)
				ctx.getSystemService(Context.ACTIVITY_SERVICE);
		ConfigurationInfo info = am.getDeviceConfigurationInfo();
		return info.reqGlEsVersion >= 0x20000;
	}

}
