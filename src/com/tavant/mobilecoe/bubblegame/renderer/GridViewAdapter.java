package com.tavant.mobilecoe.bubblegame.renderer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tavant.mobilecoe.bubblegame.R;
import com.tavant.mobilecoe.bubblegame.gameobject.Grid;

public class GridViewAdapter extends BaseAdapter{
	Context context;
	int layoutResourceId;
	ArrayList<Grid> data = new ArrayList<Grid>();
	
	public GridViewAdapter(Context context, int layoutResourceId,
			ArrayList<Grid> data) {
		super();
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
//	public View getGridRow(int position, View convertView, ViewGroup parent) {
//		View row = convertView;
//		RecordHolder holder = null;
//
//		if (row == null) {
//			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//			row = inflater.inflate(layoutResourceId, parent, false);
//
//			holder = new RecordHolder();
//			holder.txtTitle = (TextView) row.findViewById(R.id.grid_text);
//			holder.imageItem = (ImageView) row.findViewById(R.id.grid_image);
//			row.setTag(holder);
//		} else {
//			holder = (RecordHolder) row.getTag();
//		}
//
//		Grid grid = data.get(position);
//		holder.txtTitle.setText(grid.getTitle());
//		holder.imageItem.setImageBitmap(grid.getImage());
//		return row;
//
//	}
	
	static class RecordHolder {
		TextView txtTitle;
		ImageView imageItem;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.grid_text);
			holder.imageItem = (ImageView) row.findViewById(R.id.grid_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		Grid grid = data.get(position);
		holder.txtTitle.setText(grid.getTitle());
		holder.imageItem.setImageBitmap(grid.getImage());
		return row;
		}
}
