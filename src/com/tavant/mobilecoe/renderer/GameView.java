package com.tavant.mobilecoe.renderer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.widget.Toast;

public class GameView extends GLSurfaceView {

	private GameRenderer renderer;
	
	public GameView(Context context) {
		super(context);
		if (hasGLES20(context)) {
			renderer=new GameRenderer();
            setEGLContextClientVersion(2);
            //setPreserveEGLContextOnPause(true);
            setRenderer(renderer);
            setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        } else {
            Toast.makeText(context, "this phone wont support opengles 2.0", Toast.LENGTH_SHORT).show();
        }

	}
	

	
	public GameView(Context context, AttributeSet attrs) {
	      super(context, attrs);    
	      if (hasGLES20(context)) {
				renderer=new GameRenderer();
	            setEGLContextClientVersion(2);
	            //setPreserveEGLContextOnPause(true);
	            setRenderer(renderer);
	            setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	        } else {
	            Toast.makeText(context, "this phone wont support opengles 2.0", Toast.LENGTH_SHORT).show();
	        }
	}
	     
	
	private boolean hasGLES20(Context ctx) {
        ActivityManager am = (ActivityManager)
                ctx.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return info.reqGlEsVersion >= 0x20000;
    }

}
