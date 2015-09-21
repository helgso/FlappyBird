package com.helgi.flappybirdgame;


import com.helgi.framework.Game;
import com.helgi.framework.Graphics;
import com.helgi.framework.Image;
import com.helgi.framework.Screen;
import com.helgi.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    // Þegar kallað er á loading screen er kallað á þessa update aðferð í sífellu uns kallað er á MainMenuScreen
    // neðst í fallinu (þ.e. þetta fall keyrist bara einu sinni)
    @Override
    public void update(float deltaTime) {
    	/* Hérna hlöðum við inn skrárnar sem verða í notkun í forritinu.
         * Allar skrárnar þurfa að vera í "assets" möppu verkefnisins (project).
         * 
         * Svona "loadast" leikurinn. Hlöðum öllum myndum og hljóðum inn
         * í viðfangsbreytur sem eru skilgreindar inn í Assets.java
    	 * 
    	 * Búum til eintak af þeim paint föllum sem við þurfum að nota.
    	 * LoadingScreen tók inn "game" og viljum fá graphics föllin sem
    	 * getGraphics() gefur.
    	 */
    	
        Graphics g = game.getGraphics();
        
        //////////////////
        // MainMenuScreen
        //////////////////
        Assets.background 		= g.newImage("Background.png", ImageFormat.RGB565);
        Assets.backgroundBase	= g.newImage("BackgroundBase.png", ImageFormat.RGB565);
        Assets.flappyBirdLogo 	= g.newImage("MainMenuScreen/FlappyBirdLogo.png", ImageFormat.ARGB4444);
        Assets.start 			= g.newImage("MainMenuScreen/Start.png", ImageFormat.RGB565);
        
        
        ///////////////
        // ScoreScreen
        ///////////////
        //Assets.back 			= g.newImage("ScoreScreen/Back.png", ImageFormat.RGB565);
        //Assets.score 			= g.newImage("MainMenuScreen/Score.png", ImageFormat.RGB565);
        //Assets.scoreSmall		= g.newImage("ScoreScreen/ScoreSmall.png", ImageFormat.RGB565);
        
        //////////////
        // GameScreen
        //////////////
        Assets.getReady 		= g.newImage("GameScreen/GetReady.png", ImageFormat.ARGB4444);
        Assets.getReadyTap		= g.newImage("GameScreen/GetReadyTap.png", ImageFormat.ARGB4444);
        Assets.pauseButton		= g.newImage("GameScreen/PauseButton.png", ImageFormat.RGB565);
        
        Assets.bird1 			= g.newImage("GameScreen/Bird/Bird1.png", ImageFormat.ARGB4444);
        Assets.bird2 			= g.newImage("GameScreen/Bird/Bird2.png", ImageFormat.ARGB4444);
        Assets.bird3 			= g.newImage("GameScreen/Bird/Bird3.png", ImageFormat.ARGB4444);
        Assets.pipe  			= g.newImage("GameScreen/Pipe.png", ImageFormat.ARGB4444);
        
        ////////////////
        // PausedScreen
        ////////////////
        Assets.playButton		= g.newImage("PausedScreen/PlayButton.png", ImageFormat.RGB565);
        Assets.paused			= g.newImage("PausedScreen/Paused.png", ImageFormat.ARGB4444);
        
        //////////////////
        // GameOverScreen
        //////////////////
        Assets.gameOver			= g.newImage("GameOverScreen/GameOver.png", ImageFormat.ARGB4444);
        Assets.GameOverScore	= g.newImage("GameOverScreen/GameOverScore.png", ImageFormat.RGB565);
        Assets.MedalNone		= g.newImage("GameOverScreen/MedalNone.png", ImageFormat.ARGB4444);
        Assets.MedalBronze		= g.newImage("GameOverScreen/MedalBronze.png", ImageFormat.ARGB4444);
        Assets.MedalSilver		= g.newImage("GameOverScreen/MedalSilver.png", ImageFormat.ARGB4444);
        Assets.MedalPremium		= g.newImage("GameOverScreen/MedalPremium.png", ImageFormat.ARGB4444);
        Assets.ok 				= g.newImage("GameOverScreen/Ok.png", ImageFormat.RGB565);
        
        // Dæmi um að búa til hljóð
        //Assets.click = game.getAudio().createSound("explode.ogg");

        // Hérna runnar svo leikurinn eftir að búið er að hlaða inn öllum hlutunum.
        // Þ.e., hoppar yfir í MainMenuScreen.java
        game.setScreen(new MainMenuScreen(game));


    }


    @Override
    public void paint(float deltaTime) {
	    // Gerum svartan ferning svo borðinn fyrir appið birtist ekki
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
    	

    }
}
