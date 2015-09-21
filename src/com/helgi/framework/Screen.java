package com.helgi.framework;

import com.helgi.framework.Game;
import com.helgi.framework.implementation.AndroidGame;
import android.graphics.Paint;

import android.graphics.Matrix;

public abstract class Screen {
    public final Game game;
    public final Matrix matrix;
    public final double res;
    public final double gameWidth;
    public final double gameHeight;
    public final Paint FlappyFont;
    public final Paint FlappyFont_Ri;
    public final Paint FlappyFont_Bc;
    public final Paint FlappyFont_Bc_Ri;

    public Screen(Game game) {
        this.game = game;
        this.matrix = new Matrix();
        this.gameWidth = AndroidGame.frameBufferWidth;
        this.gameHeight = AndroidGame.frameBufferHeight;
        this.res = (AndroidGame.frameBufferWidth)/800.0;
        this.FlappyFont        = game.getFlappyFont();
        this.FlappyFont_Ri    = game.getFlappyFont();
        this.FlappyFont_Bc     = game.getFlappyFont_Bc();
        this.FlappyFont_Bc_Ri = game.getFlappyFont_Bc();
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
    
	public abstract void backButton();
}
