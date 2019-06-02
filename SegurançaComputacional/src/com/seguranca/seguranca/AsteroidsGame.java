package com.seguranca.asteroids;


import com.badlogic.gdx.Game;


public class AsteroidsGame extends Game {
	
	private AsteroidsGameOverScreen mGameOverScreen;
	private AsteroidsPhase1 mGamePhase1;
	private AsteroidsPhase2 mGamePhase2;
	private AsteroidsPhase3 mGamePhase3;
	private AsteroidsPhase4 mGamePhase4;
	private AsteroidsPhase5 mGamePhase5;
	private AsteroidsPhase6 mGamePhase6;
	private AsteroidsEnd   mGamePhaseEnd;
	private AsteroidsGameIntroScreen mIntroScreen;
	private AsteroidsGameScreen mGameScreen;
	private ResourceManager mResources;
	
	
	private static AsteroidsGame mInstance;
	
	public static AsteroidsGame getInstance() {
		return mInstance;
	}

	@Override
	public void create() {	
		
		mInstance = this;
		
		mResources = new ResourceManager();
		mResources.loadResources();
		
		mIntroScreen = new AsteroidsGameIntroScreen();
		mGameScreen = new AsteroidsGameScreen();
		mGameOverScreen = new AsteroidsGameOverScreen();
		mGamePhase1 = new AsteroidsPhase1();
		mGamePhase2 = new AsteroidsPhase2();
		mGamePhase3 = new AsteroidsPhase3();
		mGamePhase4 = new AsteroidsPhase4();
		mGamePhase5 = new AsteroidsPhase5();
		mGamePhase6 = new AsteroidsPhase6();
		mGamePhaseEnd = new AsteroidsEnd();
		
		setScreen(mIntroScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		mGameScreen.dispose();
		mResources.dispose();
		mGameOverScreen.dispose();
		mGamePhase1.dispose();
		mGamePhase2.dispose();
		mGamePhase3.dispose();
		mGamePhase4.dispose();
		mGamePhase5.dispose();
		mGamePhase6.dispose();
		mGamePhaseEnd.dispose();
	
	}
	
}
