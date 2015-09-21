package com.helgi.flappybirdgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.helgi.framework.Game;
import com.helgi.framework.Graphics;
import com.helgi.framework.Image;
import com.helgi.framework.Screen;
import com.helgi.framework.Input.TouchEvent;


public class ScoreScreen extends Screen {
	// Viðfangsbreytur
	Paint paint; // Þetta er leturgerðin í notkun
	
    public ScoreScreen(Game game) {
        super(game);
        
        // Stillum allt sem við viljum fyrir font í scores
        paint = new Paint();
        paint.setTextSize(60);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // Þegar ýtt er einhversstaðar á skjáinn verður len > 0 og því keyrist forlykkjan
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) { 
            	
            	///////////////
              	// BACK takki
              	///////////////
                if (inBounds(event, gameWidth/1.8, gameHeight/1.33, res*230, res*77)) {
                    // Aftur til MainMenuScreen
                    game.setScreen(new MainMenuScreen(game));              
                }
            }
        }
    }


    private boolean inBounds(TouchEvent event, double x, double y, double width, double height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	
    	////////////////////////////////////////////////////////////////
    	// Hérna höfum við öll ScoreScreen elementin okkar, takka o.s.frv.
    	////////////////////////////////////////////////////////////////
    	
    	// Skalinn byrjar efst til vinstri í glugganum
        // þ.e.a.s. hnitakerfið. Skalinn er 800 x 1280 á S2
    	
    	g.drawImage(Assets.background, matrix, 0, 0, 0, res*1.04, false);
    	g.drawImage(Assets.backgroundBase, matrix, 0, gameHeight/1.1445, 0, res*1.04*0.9615, false);
    	//g.drawImage(Assets.scoreSmall, matrix, gameWidth/4, gameHeight/1.3, 0, res, false);
        g.drawImage(Assets.flappyBirdLogo, matrix, gameWidth/13.71, gameHeight/16, 0, res, false);
        g.drawImage(Assets.bird2, matrix, gameWidth/1.16, gameHeight/10, 0, res, true);
        //g.drawImage(Assets.back, matrix, gameWidth/1.8, gameHeight/1.33, 0, res, false);
        
        // hérna setjum við inn forlykkju sem setur inn öll stigin
    }


    @Override
    public void pause() {
    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {
        //Display "Exit Game?" Box


    }
}
