package com.helgi.flappybirdgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.helgi.framework.Game;
import com.helgi.framework.Graphics;
import com.helgi.framework.Screen;
import com.helgi.framework.Input.TouchEvent;

//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    // Stillum þetta svona svo það sé fyrst kallað á updateReady aðferðina
    GameState state = GameState.Ready;

    // Variable Setup
    // You would create game objects here.
    Bird flappy;
    PipePair[] pipes;
    int pipenum;
    int score;
    int Highscore;
    //boolean scoreIsSaved;
    Game game;

    public GameScreen(Game game) {
        super(game);
        this.game = game;
        
        flappy = new Bird(game, this);

        pipes = new PipePair[3];
        pipes[0] = new PipePair(game, this, 1.5*gameWidth);
        pipes[1] = new PipePair(game, this, 2.0*gameWidth);
        pipes[2] = new PipePair(game, this, 2.5*gameWidth);
        pipenum = 0;

        FlappyFont.setTextSize(70);
        FlappyFont.setAntiAlias(true);
        FlappyFont_Ri.setTextSize(70);
        FlappyFont_Ri.setAntiAlias(true);
        
        FlappyFont_Bc.setTextSize(70);
        FlappyFont_Bc.setColor(Color.WHITE);
        FlappyFont_Bc.setAntiAlias(true);
        FlappyFont_Bc_Ri.setTextSize(70);
        FlappyFont_Bc_Ri.setColor(Color.WHITE);
        FlappyFont_Bc_Ri.setAntiAlias(true);
        
        score = 0;
        Highscore = 0;
        //scoreIsSaved = false;
        /*
        //getting preferences
        SharedPreferences prefs = game.getSharedPreferences("myPrefsKey", 0);
        try {
        	Highscore = prefs.getInt("key", 0); //0 is the default value
        } catch (Exception e) {
        	Highscore = 0;
        }*/
    }

    // Eftir að MainMenuScreen kallar á GameScreen ræsist þessi aðferð, update.
    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        
        // Notfærum okkur enum Gamestate sem við skilgreindum efst til þess að keyra mismunandi
        // update aðferðir við mismunandi aðstæður leiksins
        if (state == GameState.Ready)
            updateReady(touchEvents); // Í upphafi er þá kallað á þetta
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
    	
    	int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
            	state = GameState.Running;
            }
        }
    	
    	/*
        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!
       
    	// Ef það er einhversskonar snerting gerð á snertiskjáinn keyrum við leikinn hér
        if (touchEvents.size() > 0)
            state = GameState.Running;*/
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
       
        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (inBounds(event, 0, 0, 72, 77))
            	pause();
            else if (event.type == TouchEvent.TOUCH_DOWN) flappy.up(); 
        }
        
        // if (event.x < 640)
        /*int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	if (inBounds(event, gameHeight/19, gameHeight/19, res*72, res*77)) pause();
            }
            else if (event.type == TouchEvent.TOUCH_DOWN) flappy.up(); 
            
            // if (event.x < 640)
        }*/
        
        ////////////
        // UPDATES
        ////////////

        if (state == GameState.Running) {
        	flappy.update(deltaTime);
        	for (int i = 0; i < 3; i++) {
        		pipes[i].update(deltaTime);
        	}
        }
        
        ///////////
        // CHECKS
        ///////////
        
        // Árekstur
        checkCollision();
        
        // Stigagjöf
        if (Math.abs((pipes[pipenum].X+20)-flappy.X) < 20) {
        	score++;
        	if (pipenum != 2) pipenum++;
        	else pipenum = 0;
        }
        

    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	if (inBounds(event, gameHeight/19, gameHeight/8, res*72, res*77)) {
            		resume();
            	}
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
    	
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	if (inBounds(event, gameWidth/6, gameHeight/1.33, res*230, res*77)) {
            		nullify();
            		game.setScreen(new MainMenuScreen(game));
            		return;
            	}
            }
        }

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        
        ////////////////////////////////////////////////////////////////
        // Hérna höfum við öll GetReady elementin okkar, takka o.s.frv.
        ////////////////////////////////////////////////////////////////

        // Skalinn byrjar efst til vinstri í glugganum
        // þ.e.a.s. hnitakerfið. Skalinn er 480 x 800 á S2
        
        g.drawImage(Assets.getReady, matrix, gameWidth/5.33, gameHeight/4.44, 0, res, false);
        g.drawImage(Assets.getReadyTap, matrix, gameWidth/2.67, gameHeight/2.47, 0, res, false);
    }

    // Þetta teiknar hvern hlut í því standi sem hann er í
    private void drawRunningUI() {
        Graphics g = game.getGraphics();
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(120, 0, 0, 0);
        g.drawImage(Assets.playButton, matrix, gameHeight/19, gameHeight/8, 0, res, false);
        g.drawImage(Assets.paused, matrix, gameWidth/2.2, gameHeight/8.8, 0, res, true);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.gameOver, matrix, gameWidth/2.3, gameHeight/3, 0, res, true);
        
        g.drawImage(Assets.GameOverScore, matrix, gameWidth/2.3, gameHeight/1.85, 0, res, true);
        
        if 	(score < 50)  
        	g.drawImage(Assets.MedalNone, matrix, gameWidth/4, gameHeight/1.75, 0, res, true);
        else if (score < 100)
        	g.drawImage(Assets.MedalBronze, matrix, gameWidth/4, gameHeight/1.75, 0, res, true);
        else if (score < 150)
        	g.drawImage(Assets.MedalSilver, matrix, gameWidth/4, gameHeight/1.75, 0, res, true);
        else
        	g.drawImage(Assets.MedalPremium, matrix, gameWidth/4, gameHeight/1.75, 0, res, true);
        
        // Current score
        FlappyFont_Ri.setTextAlign(Paint.Align.RIGHT);
        FlappyFont_Bc_Ri.setTextAlign(Paint.Align.RIGHT);
        g.drawString(""+score, gameWidth/1.19, gameHeight/1.81, FlappyFont_Bc_Ri);
        g.drawString(""+score, gameWidth/1.19, gameHeight/1.81, FlappyFont_Ri);
        // Highcore
        g.drawString(""+score, gameWidth/1.19, gameHeight/1.5, FlappyFont_Bc_Ri);
        g.drawString(""+score, gameWidth/1.19, gameHeight/1.5, FlappyFont_Ri);
        
        g.drawImage(Assets.ok, matrix, gameWidth/6, gameHeight/1.33, 0, res, false);   
    }

    private boolean inBounds(TouchEvent event, double x, double y, double width,
            double height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }
    
    public void checkCollision() {
    	// Jörð
    	if (flappy.Y > gameHeight*0.83) state = GameState.GameOver;
    	// Pípur
    	for (int i = 0; i < 3; i++) {
    		if (Math.abs(flappy.X-pipes[i].X) < 60) {
    			if (Math.abs(flappy.Y-pipes[i].Y) > 61) {
        	   		state = GameState.GameOver;
        	   	}
    		}
    	}
    }

    
    public double dist(Bird flappy, PipePair pipe) {
    	return Math.sqrt(Math.pow(flappy.X-pipe.X, 2) + Math.pow(flappy.Y-pipe.Y, 2));
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        // Þar sem það er fyrst kallað á paint() teiknum við öll grunnelement hér
        g.drawImage(Assets.background, matrix, 0, 0, 0, res*1.04, false);
        
        for (int i = 0; i < 3; i++) {
        	pipes[i].draw();
        }
        
        g.drawImage(Assets.pauseButton, matrix, gameHeight/19, gameHeight/19, 0, res, false);
        
        g.drawImage(Assets.backgroundBase, matrix, 0, gameHeight/1.1445, 0, res*1.04*0.9615, false);
        
        flappy.draw();
        
        FlappyFont.setTextAlign(Paint.Align.CENTER);
        FlappyFont_Bc.setTextAlign(Paint.Align.CENTER);
        g.drawString(""+score, gameWidth/2, gameHeight/8.8, FlappyFont_Bc);
        g.drawString(""+score, gameWidth/2, gameHeight/8.8, FlappyFont);

        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.

        // Call garbage collector to clean up memory.
        System.gc();
    }
    
    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;
    }

    @Override
    public void resume() {
    	if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }
}
