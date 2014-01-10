package com.tavant.mobilecoe.bubblegame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tavant.mobilecoe.renderer.GameView;

/**
 * 
 * @author Tavant
 *
 */

public class FullscreenActivity extends Activity {

	private GameView eaglContext=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fullscreen);
		eaglContext=(GameView)findViewById(R.id.gameView);		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		eaglContext.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		eaglContext.onResume();
	}
}
