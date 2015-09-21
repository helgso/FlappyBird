package com.helgi.flappybirdgame;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.helgi.framework.Graphics; // Til að geta búið til Graphics g
import com.helgi.framework.Game; // til að geta sótt föllin eins og drawImage
import com.helgi.framework.Image;
import com.helgi.framework.implementation.Animation;
import com.helgi.framework.Screen;

public class Bird {
	  
	Graphics g;
	double res;
	double gameWidth;
	double gameHeight;
	Matrix matrix; // Búum til reglu um hvernig pixlunum er raðað í snúnu myndinni
	public double X;
	public double Y;
	private double rotation;
	private double YSpeed;
	private double rotSpeed;
	private Animation anim;
	
	/* X er [0;800]
	 * Y er [0;1280]
	 * YSpeed [-0.9; 0.6] og er hraði Flappy í y-stefnu
	 * rotation er á bilinu [-20°;20°] og er stefna Flappy
	 * rotSpeed er sá hraði sem Flappy er að snúast um
	 * lastUp er tími þegar up() var síðast virkjað
	 */
	
	public Bird (Game game, Screen scr) {
		g = game.getGraphics();
		gameWidth = scr.gameWidth;
		gameHeight = scr.gameHeight;
		res = scr.res;
	    X = gameWidth/2.055;
	    Y = gameHeight/2.33;
	    YSpeed = 0;
	    rotation = 0;
	    rotSpeed = 0;
	    matrix = new Matrix();
	    anim = new Animation();
	    anim.addFrame(Assets.bird1, 40);
		anim.addFrame(Assets.bird2, 40);
		anim.addFrame(Assets.bird3, 40);
	}

	public void update(float deltaTime) {
	    manageAccAndRot(deltaTime); // Hröðun niður á við og snúningur
	    anim.update(deltaTime);  
	}
	
	public void draw() {
		g.drawImage(anim.getImage(), matrix, X-5, Y, rotation, res, true);
	}
	  
	public void up() {
	    if (Y > gameHeight/20) {
	      YSpeed = gameHeight/60; // Hröðun upp á við
	      Y -= gameHeight/200;
	      rotSpeed = 18; 
	  }
	}
	  
	public void manageAccAndRot(float deltaTime) {
		if (YSpeed > -200) YSpeed -= 0.6*deltaTime;// Hröðun niður á við, YSpeed
		Y -= YSpeed;
		
		if (rotSpeed > -4) rotSpeed -= 0.24*deltaTime; // Hámarkshornhraði réttsælissnúnings er |-4|
	    if (-90 < rotation || rotSpeed > 0) rotation += rotSpeed;
	    if (rotation > 45) {rotation = 45; rotSpeed = 0;} // Svo fuglinn fari ekki í yfirsnúning
	}
	  
	  
	public double getX() {return X;}
	public double getY() {return Y;}
}