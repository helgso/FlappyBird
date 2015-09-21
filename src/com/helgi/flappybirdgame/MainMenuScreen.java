package com.helgi.flappybirdgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.helgi.framework.Game;
import com.helgi.framework.Graphics;
import com.helgi.framework.Image;
import com.helgi.framework.Screen;
import com.helgi.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen {
	// Viðfangsbreytur
	Paint font; // Þetta er leturgerðin í notkun
	
    public MainMenuScreen(Game game) {
        super(game);
        
        /* Dæmi fyrir stillingar um font
        font = new Paint();
        font.setTextSize(30);
        font.setTextAlign(Paint.Align.CENTER);
        font.setAntiAlias(true);
        font.setColor(Color.WHITE);
        
        síðan er gert t.d.:
        Graphics g = game.getGraphics();
         g.drawString("Tap each side of the screen to", 400, 650, paint); */
    }


    /* Þegar LoadingScreen kallar á MainMenuScreen þá keyrist þessi aðferð. Það er kallað í sífellu á þessa
     * update aðferð þangað til að eitthvað gerist
     */
    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // Þegar ýtt er einhversstaðar á skjáinn verður len > 0 og því keyrist forlykkjan
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	
            	/////////////////////////////////////////////////////////
            	// Hérna er það sem gerist þegar ýtt er á takka o.s.frv.
            	/////////////////////////////////////////////////////////
            	
            	// Ef snertiskjárinn skynjar snertingu á fleti sem hefur hornpunkt (0,0), breidd 250 og hæð 250
            	// Þá er kallað á GameScreen sem er leikurinn sjálfur í keyrslu. (0,0) er efst til vinstri í
            	// glugga Android tækisins.
            	// Þurfum að stilla þetta að réttu gildi, hverjum takka o.s.frv.
            	
            	///////////////
            	// START takki
            	///////////////
                if (inBounds(event, gameWidth/6, gameHeight/1.33, res*230, res*77)) {
                	// Hefjum spilun
                    game.setScreen(new GameScreen(game));              
                }
                /*///////////////
              	// SCORE takki
              	///////////////
                  if (inBounds(event, gameWidth/1.8, gameHeight/1.33, res*230, res*77)) {
                  	// Hefjum spilun
                      game.setScreen(new ScoreScreen(game));              
                  }*/
            }
        }
    }


    private boolean inBounds(TouchEvent event, double x, double y, double width,
            double height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }


    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	
    	////////////////////////////////////////////////////////////////
    	// Hérna höfum við öll MainMenu elementin okkar, takka o.s.frv.
    	////////////////////////////////////////////////////////////////
    	
    	// Skalinn byrjar efst til vinstri í glugganum
        // þ.e.a.s. hnitakerfið. Skalinn er 800 x 1280 á S2
    	
        g.drawImage(Assets.background, matrix, 0, 0, 0, res*1.04, false);
        g.drawImage(Assets.backgroundBase, matrix, 0, gameHeight/1.1445, 0, res*1.04*0.9615, false);
        g.drawImage(Assets.flappyBirdLogo, matrix, gameWidth/13.71, gameHeight/3.9, 0, res, false);
        g.drawImage(Assets.bird2, matrix, gameWidth/1.247, gameHeight/3.70, 0, res, false);
        g.drawImage(Assets.start, matrix, gameWidth/6, gameHeight/1.33, 0, res, false);
        //g.drawImage(Assets.score, matrix, gameWidth/1.8, gameHeight/1.33, 0, res, false);
        
        // Teiknar fylltan kassa
        //g.drawRect(0, 0, 780, 1260, Color.BLACK);
        // Þetta væri dæmi um þegar skrifaður er strengur á skjáinn.
        // Hvað stendur, staðsetning og letur. Letrið font er viðfangsbreyta í þessum klasa
        // g.drawString("Tap to play.", 240, 400, font);
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
