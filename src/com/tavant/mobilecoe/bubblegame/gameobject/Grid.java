package com.tavant.mobilecoe.bubblegame.gameobject;
import android.graphics.Bitmap;
public class Grid {
	
		Bitmap image;
		String title;
		
		public Grid(Bitmap image, String title) {
			super();
			this.image = image;
			this.title = title;
		}
		public Bitmap getImage() {
			return image;
		}
		public void setImage(Bitmap image) {
			this.image = image;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		

	}


