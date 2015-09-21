package com.helgi.flappybirdgame;

import com.helgi.framework.Screen;
import com.helgi.framework.implementation.AndroidGame;

/*
 * ÞETTA ER SKRÁIN SEM RÆSIST VEGNA ÞESS AÐ ÞAÐ ER STILLT ÞANNIG Í
 * ANDROIDMANIFEST.XML UNDIR ANDROID NAME:
 * android:name="com.helgi.flappybirdgame.FlappyBirdGame".
 * 
 * ÞETTA ER ÞÁ "MAIN-KLASI" LEIKJARINS.
 * 
 * Kallað er á getInitScreen(). Það er "main-FALL" leikjarins.
 * 
 */
public class FlappyBirdGame extends AndroidGame {
	
	// Við byrjum á því að kalla á LoadingScreen.
	@Override
    public Screen getInitScreen() {
        return new LoadingScreen(this);
	}
}
