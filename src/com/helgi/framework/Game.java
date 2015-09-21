package com.helgi.framework;

import android.content.SharedPreferences;
import android.graphics.Paint;

public interface Game {
	// Öll þessi föll þarf
	
	public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();
    
    public Paint getFlappyFont();
    public Paint getFlappyFont_Ri();
    
    public Paint getFlappyFont_Bc();
    public Paint getFlappyFont_Bc_Ri();
    
    //public SharedPreferences getSharedPreferences(String a, int b);

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();
	
}
