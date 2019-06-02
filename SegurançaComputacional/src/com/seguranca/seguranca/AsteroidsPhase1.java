package com.seguranca.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AsteroidsPhase1 implements Screen {
	
	private Stage mStage;
	
	private static AsteroidsPhase1 mInstance;
	
	public static AsteroidsPhase1 getInstance() {
		return mInstance;
	}
	
	public AsteroidsPhase1() {
		mInstance = this;
		
		mStage = new Stage();
		
		for(int i = 0; i < 10; i ++) {
			AsteroidActor asteroid = new AsteroidActor();
			asteroid.calcStartParams();
			mStage.addActor(asteroid);
		}
		
		
		TextActor asteroidsTextActor = new TextActor();
		asteroidsTextActor.setText("Fase 1");		
		asteroidsTextActor.setFontHeight(Gdx.graphics.getWidth() / 50);
		float x = Gdx.graphics.getWidth() /2 - asteroidsTextActor.getWidth()/2;
		float y = Gdx.graphics.getHeight() - 50;
		asteroidsTextActor.setPosition(x, y);
		mStage.addActor(asteroidsTextActor);
	
		TextActor asteroidsTextActor2 = new TextActor();
		asteroidsTextActor2.setText("Em criptografia, um ataque de força bruta, ou busca exaustiva de chave, é um ataque criptoanalítico que");
		 y = y - 50;
		 x = 10;
		asteroidsTextActor2.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor2.setPosition(x, y);
		mStage.addActor(asteroidsTextActor2);
		
		TextActor asteroidsTextActor3 = new TextActor();
		asteroidsTextActor3.setText("consiste de verificação sistemática de todas as possíveis chaves e senhas até que as corretas sejam encontradas.");
		 y = y - 50;
		 x = 10;
		asteroidsTextActor3.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor3.setPosition(x, y);
		mStage.addActor(asteroidsTextActor3);		

		TextActor asteroidsTextActor4 = new TextActor();
		asteroidsTextActor4.setText("Senhas fortes podem trazer mais segurança contra ataques de força bruta, apesar de não serem totalmente eficazes.");
		 y = y - 50;
		 x = 10;
		asteroidsTextActor4.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor4.setPosition(x, y);
		mStage.addActor(asteroidsTextActor4);
		
		TextActor asteroidsTextActor5 = new TextActor();
		asteroidsTextActor5.setText("Mais fatores de autenticação, além da senha, garantem maior segurança.");
		 y = y - 50;
		 x = 10;
		asteroidsTextActor5.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor5.setPosition(x, y);
		mStage.addActor(asteroidsTextActor5);

		TextActor asteroidsTextActor6 = new TextActor();
		asteroidsTextActor6.setText("Exemplo: verificação em duas etapas, disponível para contas Google.");
		 y = y - 50;
		 x = 10;
		asteroidsTextActor6.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor6.setPosition(x, y);
		mStage.addActor(asteroidsTextActor6);		

		TextActor asteroidsTextActor7 = new TextActor();
		asteroidsTextActor7.setText("Utilize os conceitos apresentados para atingir 100 pontos e avançar para a próxima fase.");
		 y = y - 100;
		 x = 10;
		asteroidsTextActor7.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor7.setPosition(x, y);
		mStage.addActor(asteroidsTextActor7);			

		TextActor asteroidsTextActor8 = new TextActor();
		asteroidsTextActor8.setText("Lembre-se de pegar um termo adequado para atacar os inimigos da segurança computacional :)");
		 y = y - 50;
		 x = 10;
		asteroidsTextActor8.setFontHeight(Gdx.graphics.getWidth() / 60);
		asteroidsTextActor8.setPosition(x, y);
		mStage.addActor(asteroidsTextActor8);			
		
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
				
				AsteroidsGameScreen.getInstance().initializeGame();
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
