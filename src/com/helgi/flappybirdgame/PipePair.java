package com.helgi.flappybirdgame;

import android.graphics.Matrix;
import com.helgi.framework.Graphics; // Til að geta búið til Graphics g
import com.helgi.framework.Game; // til að geta sótt föllin eins og drawImage
import com.helgi.framework.Image;

import java.util.Random;
import com.helgi.framework.Screen;
import com.helgi.flappybirdgame.Bird;

public class PipePair {
	Graphics g;
	double res;
	double gameWidth;
	double gameHeight;
    public double X;
    public double Y;
    //public int deg;
    private Random r;
    private Matrix matrix;
    
  
    // (X,Y) er punkturinn sem miðja bilsins milli pípuparsins leynist
  
    public PipePair(Game game, Screen scr, double X/*, int deg*/) {
	    g = game.getGraphics();
	    gameWidth = scr.gameWidth;
	    gameHeight = scr.gameHeight;
	    res = scr.res;
        this.X = X;
        //this.deg = deg;
        r = new Random();
        Y = gameHeight/6.5+r.nextInt((int)(gameHeight/1.8)); // Handahófskennd Y staðsetning pípa
        matrix = new Matrix();
    }
  
    public void update(float deltaTime) {
    	if (X < -gameWidth*0.39) {
    		X = gameWidth*1.12;
    		Y = gameHeight/6.5+r.nextInt((int)(gameHeight/1.8)); // Handahófskennd Y staðsetning pípa
        }
        X -= 1.8*deltaTime;
    }
  
    public void draw() {
    	g.drawImage(Assets.pipe, matrix, X-(res*145)/2.0, Y-gameHeight/1.29, 0, res, false);
    	g.drawImage(Assets.pipe, matrix, X-(res*145)/2.0, Y+gameHeight/10.2, 180, res, false);
    }
  
    public double getX() { return X; }
    public double getY() { return Y; }

}