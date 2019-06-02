package com.seguranca.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AsteroidsPhase6 implements Screen {
	
	private Stage mStage;
	
	private static AsteroidsPhase6 mInstance;
	
	public static AsteroidsPhase6 getInstance() {
		return mInstance;
	}
	
	public AsteroidsPhase6() {
		mInstance = this;
		
		mStage = new Stage();
		
		for(int i = 0; i < 10; i ++) {
			AsteroidActor asteroid = new AsteroidActor();
			asteroid.calcStartParams();
			mStage.addActor(asteroid);
		}
		
		TextActor asteroidsTextActor = new TextActor();
		asteroidsTextActor.setText("Fase Final!!!");		
		asteroidsTextActor.setFontHeight(Gdx.graphics.getWidth() / 50);
		float x = Gdx.graphics.getWidth() /2 - asteroidsTextActor.getWidth()/2;
		float y = Gdx.graphics.getHeight() - 50;
		asteroidsTextActor.setPosition(x, y);
		mStage.addActor(asteroidsTextActor);
	
		TextActor asteroidsTextActor2 = new TextActor();
		asteroidsTextActor2.setText("Utilize os conceitos aprendidos nas fases anteriores para vencer todos os inimigos!");
		 y = y - 50;
		 x = 10;
		asteroidsTextActor2.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor2.setPosition(x, y);
		mStage.addActor(asteroidsTextActor2);
		
		
		TextActor asteroidsTextActor9 = new TextActor();
		asteroidsTextActor9.setText("Atinja 100 pontos para avançar!");
		 y = y - 50;
		 x = 10;
		asteroidsTextActor9.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor9.setPosition(x, y);
		mStage.addActor(asteroidsTextActor9);	
		
		TextActor pressAnyTextActor = new TextActor();
		pressAnyTextActor.setText("Pressione qualquer tecla para iniciar!");
		pressAnyTextActor.setFontHeight(Gdx.graphics.getWidth() / 40);
		
		x = Gdx.graphics.getWidth() /2 - pressAnyTextActor.getWidth()/2;
		y = Gdx.graphics.getWidth() / 30;
		pressAnyTextActor.setPosition(x, y);
		
		mStage.addActor(pressAnyTextActor);
		
		mStage.addListener(new InputListener() {
			
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				
				AsteroidsGameScreen.getInstance().initializeGame6();
				AsteroidsGame.getInstance().setScreen(AsteroidsGameScreen.getInstance());
				return true;

			}
			
		});
		
		Gdx.input.setInputProcessor(mStage);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		mStage.act(delta);
		mStage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		mStage.setViewport(width, height);
		
	}
	
	@Override
	public void dispose() {
		mStage.dispose();
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(mStage);

	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {

		
	}

	@Override
	public void resume() {

		
	}

}
