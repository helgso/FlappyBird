package com.helgi.framework;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;

public interface Graphics {
	public static enum ImageFormat {
		ARGB8888, ARGB4444, RGB565
	}

	public Image newImage(String fileName, ImageFormat format);

	public void clearScreen(int color);

	public void drawLine(int x, int y, int x2, int y2, int color);

	public void drawRect(int x, int y, int width, int height, int color);

	public void drawImage(Image Image, float x, float y);
	
	public void drawImage(Bitmap bitmap, Matrix position);
	
	public void drawImage(Image Image, Matrix matrix, double x, double y,
			double rotation, boolean center);
	
	public void drawImage(Image image, Matrix matrix, double x, double y,
			double rotation, double scale, boolean center);
	
	// nota þetta ekki
	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight);

	void drawString(String text, double x, double y, Paint paint);

	public int getWidth();

	public int getHeight();

	public void drawARGB(int i, int j, int k, int l);

	

}
