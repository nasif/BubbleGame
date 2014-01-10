package com.tavant.mobilecoe.renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GameView extends GLSurfaceView {

	private GameRendrer renderer;
	
	public GameView(Context context) {
		super(context);
		setEGLContextClientVersion(2);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		renderer=new GameRendrer();
		setRenderer(renderer);
		
	}

}
