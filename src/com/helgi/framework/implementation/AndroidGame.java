package com.helgi.framework.implementation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.helgi.framework.Audio;
import com.helgi.framework.FileIO;
import com.helgi.framework.Game;
import com.helgi.framework.Graphics;
import com.helgi.framework.Input;
import com.helgi.framework.Screen;
import android.graphics.Paint;

// Fyrir Highscore
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class AndroidGame extends Activity implements Game {
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;
    
    public static int frameBufferWidth;
    public static int frameBufferHeight;
    Paint FlappyFont;
    Paint FlappyFont_Ri;
    Paint FlappyFont_Bc;
    Paint FlappyFont_Bc_Ri;
    Typeface tf_1;
    Typeface tf_2;
    Typeface tf_3;
    Typeface tf_4;
    SharedPreferences prefs;
    public Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        frameBufferWidth = isPortrait ? 480: 800;
        frameBufferHeight = isPortrait ? 800: 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);
        
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getInitScreen();
        setContentView(renderView);
        
        ////////////////
        // CUSTOM FONTS
        ////////////////
        // FLAPPYFONT
        //////////////
        tf_1 = Typeface.createFromAsset(getAssets(),"Fonts/FlappyFont.ttf");
        tf_2 = Typeface.createFromAsset(getAssets(),"Fonts/FlappyFont.ttf");
        FlappyFont     = new Paint();
        FlappyFont_Ri = new Paint();
        FlappyFont.setTypeface(tf_1);
        FlappyFont_Ri.setTypeface(tf_2);
        //////////////////
        // FLAPPYFONT_Bc
        //////////////////
        tf_3 = Typeface.createFromAsset(getAssets(),"Fonts/FlappyFont_Bc.ttf");
        tf_4 = Typeface.createFromAsset(getAssets(),"Fonts/FlappyFont_Bc.ttf");
        FlappyFont_Bc = new Paint();
        FlappyFont_Bc_Ri = new Paint();
        FlappyFont_Bc.setTypeface(tf_3);
        FlappyFont_Bc_Ri.setTypeface(tf_4);
        //////////////////
        //////////////
        // HIGHSCORE
        /////////////
        prefs = this.getSharedPreferences("myPrefsKey", 0);
        editor = prefs.edit();
        
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "MyGame");
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }
    
    @Override
    public Paint getFlappyFont() {
        return FlappyFont;
    }
    
    @Override
    public Paint getFlappyFont_Ri() {
        return FlappyFont;
    }
    
    
    
    @Override
    public Paint getFlappyFont_Bc() {
        return FlappyFont_Bc;
    }
    
    @Override
    public Paint getFlappyFont_Bc_Ri() {
        return FlappyFont_Bc;
    }

    /*
    @Override
    public SharedPreferences getSharedPreferences(String a, int b) {
        return getSharedPreferences(a, b);
    }*/
    
    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }
    
    public Screen getCurrentScreen() {

    	return screen;
    }
}