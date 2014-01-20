package com.tavant.mobilecoe.bubblegame;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.GridView;
import com.tavant.mobilecoe.bubblegame.gameobject.Grid;
import com.tavant.mobilecoe.bubblegame.renderer.GameView;
import com.tavant.mobilecoe.bubblegame.renderer.GridViewAdapter;

/**
 * 
 * @author Tavant
 *
 */

public class FullscreenActivity extends Activity {
	GridView gridView;
	ArrayList<Grid> gridArray = new ArrayList<Grid>();
	GridViewAdapter customGridAdapter;
	private GameView eaglContext=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fullscreen);
		eaglContext=(GameView)findViewById(R.id.gameView);
		
		//set grid view item
				Bitmap gridIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.square);
		
				gridArray.add(new Grid(gridIcon,"0"));
				gridArray.add(new Grid(gridIcon,"1"));
				gridArray.add(new Grid(gridIcon,"2"));
				gridArray.add(new Grid(gridIcon,"3"));
				gridArray.add(new Grid(gridIcon,"4"));
				gridArray.add(new Grid(gridIcon,"5"));
				gridArray.add(new Grid(gridIcon,"6"));
				gridArray.add(new Grid(gridIcon,"7"));
				gridArray.add(new Grid(gridIcon,"8"));
				gridArray.add(new Grid(gridIcon,"9"));
		
		
		
		gridView = (GridView)findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(this, R.layout.cell_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
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
