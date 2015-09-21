package com.helgi.framework;

import com.helgi.framework.Graphics.ImageFormat;
import android.graphics.Bitmap;

public interface Image {

	public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
    public Bitmap getBitmap();
}
