package com.seguranca.asteroids;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class AsteroidsGameScreen implements Screen {
	
	private Stage mStage;
	
	private int mNextPhase;
	
	private PlayerShipActor mPlayer;
	
	private String hudSubType;
	
	protected final ArrayList<BaseActor> mActorList = 
			new ArrayList<BaseActor>();
	
	private final ArrayList<DamageableActor> mDamageableActorList = 
			new ArrayList<DamageableActor>();
	
	private final ArrayList<BulletActor> mBulletActorList = 
			new ArrayList<BulletActor>();
	
	private static AsteroidsGameScreen mInstance;
	
	
	public static AsteroidsGameScreen getInstance() {
		return mInstance;
	}
	
	public SparkEffectActor addSparkEffect() {
		SparkEffectActor spark = new SparkEffectActor();
		mActorList.add(spark);
		return spark;
	}
	
	
	public PlayerShipActor addPlayer() {
		PlayerShipActor player = new PlayerShipActor();
		mActorList.add(player);
		return player;
	}
	
	public BulletActor addBullet() {
		BulletActor bullet = new BulletActor();
		mActorList.add(bullet);
		return bullet;
		
	}
	
	public BulletActor addBullet(Boolean isEnemy) {
		BulletActor bullet = new BulletActor(isEnemy);
		mActorList.add(bullet);
		return bullet;
		
	}
	

	
	public PowerActor addPower(String subtype) {
		PowerActor power = new PowerActor(subtype);
		mActorList.add(power);
		return power;
	}	
	
	public EnemyActor addEnemy(String subtype) {
		EnemyActor enemy = new EnemyActor(subtype);
		mActorList.add(enemy);
		return enemy;
	}	
	
	protected int mScore;
	protected int mNumLives;
	
	private Stage mHudStage;
	protected TextActor mHudScoreActor;
	protected final PlayerShipActor[] mHudLivesArray = new PlayerShipActor[3];
	private DamageableActor hudPower = new DamageableActor();
	
	private void doGameOver() {
		AsteroidsGameOverScreen.getInstance().setScore(mScore);
		AsteroidsGame.getInstance().setScreen(AsteroidsGameOverScreen.getInstance());
	}

	private void doNextPhase() {
		switch (mNextPhase) {
		case 2:
			AsteroidsGame.getInstance().setScreen(AsteroidsPhase2.getInstance());
			break;
		case 3:
			AsteroidsGame.getInstance().setScreen(AsteroidsPhase3.getInstance());
			break;
		case 4:
			AsteroidsGame.getInstance().setScreen(AsteroidsPhase4.getInstance());
			break;			
		case 5:
			AsteroidsGame.getInstance().setScreen(AsteroidsPhase5.getInstance());
			break;
		case 6:
			AsteroidsGame.getInstance().setScreen(AsteroidsPhase6.getInstance());
			break;	
		case 7:
			AsteroidsGame.getInstance().setScreen(AsteroidsEnd.getInstance());
			break;		
		default:
			
		
		
		}
		
	}
	
	public void queueGameOver() {
		
		mStage.addAction(Actions.sequence(Actions.delay(1f), Actions.run(new Runnable() {
			
			@Override
			public void run() {
				doGameOver();
			}
		})));
	}
	
	public void killPlayer() {
		
		mNumLives -= 1;
		
		for(PlayerShipActor life : mHudLivesArray) {
			life.setVisible(false);
		}
		
		for(int i = 0; i < mNumLives; i ++) {
			PlayerShipActor life = mHudLivesArray[i];
			life.setVisible(true);
		}
			
		if(mNumLives != 0) {
			queuePlayerSpawn();
			
		} else {
			queueGameOver();
		}
	}
	
	public void incrementScore() {
		mScore += 25; 
		mHudScoreActor.setText("SCORE : " + mScore);
		if (mScore >= 100) {
			doNextPhase();
		}
	}
	
	public AsteroidsGameScreen() {
		mInstance = this;
		mStage = new Stage();
		mHudStage = new Stage();

		float padding = Gdx.graphics.getHeight() / 60;
		float fontHeight = Gdx.graphics.getHeight() / 20;
		float x = padding; 
		float y = Gdx.graphics.getHeight() - padding - fontHeight;
		
		mHudScoreActor = new TextActor();
		mHudScoreActor.setFontHeight(fontHeight);
		mHudScoreActor.setPosition(x, y);
		
		mHudStage.addActor(mHudScoreActor);
		
		float size =  Gdx.graphics.getHeight() / 20;
		
		y -= size + padding;
		x -= size / 3;
		
		for(int i = 0; i < 3; i ++) {
			PlayerShipActor life = new PlayerShipActor();
			life.setPosition(x, y); 
			
			
			life.setSize(size, size);
			
			x += 0.25 * padding + life.getWidth(); 
			
			mHudLivesArray[i] = life;
			mHudStage.addActor(life);
		}
		
		
		Gdx.input.setInputProcessor(mStage);
	}
	
	public void addHudPower(PowerActor power) {
		if (hudPower != null) {
			hudPower.kill();
			hudPower.setVisible(false);
			hudPower.remove();
		}
		
		float padding = Gdx.graphics.getHeight() / 50;
		float fontHeight = Gdx.graphics.getHeight() / 20;
		float x = padding; 
		float y = Gdx.graphics.getHeight() - padding - fontHeight;
		
		float size =  Gdx.graphics.getHeight() / 10;
		y -= size + padding + 40;
		x -= size / 3 - 10;
		//PowerActor currpower = new PowerActor();
		power.setPosition(x, y); 
		power.setSize(size, size);
		power.setVisible(true);
		
		
		hudPower = power;
		hudSubType = power.getSubType();
		mHudStage.addActor(hudPower);
		
	}

	public String getHudPowerSubType() {
		return hudSubType;
	}
	
	public void initializeGame() {
		
		mActorList.clear();
		mNextPhase = 2;
		
		addPlayer();
		for(int i = 0; i < 3; i ++) {
			addPower("autenticacao");
		}

		for(int i = 0; i < 3; i ++) {
			addPower("senhaForte");		
		}
		
		for(int i = 0; i < 3; i ++) {
			addPower("vpn");
		}
		
		for(int i = 0; i < 10; i ++) {
			addEnemy("forcaBruta");
		}
		
		for(int i = 0; i < 2; i ++) {
			//addFlyingSaucer();
		}
		
		mScore = 0;
		mNumLives = 3;
		mHudScoreActor.setText("SCORE : 0");
		for(PlayerShipActor life : mHudLivesArray) {
			life.setVisible(true);
		}
	}
	
	public void initializeGame2() {
		
		mActorList.clear();
		mNextPhase = 3;
		
		addPlayer();
		for(int i = 0; i < 2; i ++) {
			addPower("backups");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("checksum");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("criptografia");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("planoRecuperacao");
		}		
		
	
		for(int i = 0; i < 2; i ++) {
			addPower("senhaForte");		
		}
		
		for(int i = 0; i < 2; i ++) {
			addPower("vpn");
		}
		

		for(int i = 0; i < 2; i ++) {
			addEnemy("ataqueConfidencialidade");
		}
		
		for(int i = 0; i < 2; i ++) {
			addEnemy("ataqueDisponibilidade");
		}
		
		for(int i = 0; i < 2; i ++) {
			addEnemy("ataqueIntegridade");
		}		
		
		mScore = 0;
		mNumLives = 3;
		mHudScoreActor.setText("SCORE : 0");
		for(PlayerShipActor life : mHudLivesArray) {
			life.setVisible(true);
		}
	}

	public void initializeGame3() {
		
		mActorList.clear();
		mNextPhase = 4;
		
		addPlayer();
		for(int i = 0; i < 2; i ++) {
			addPower("backups");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("checksum");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("criptografia");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("planoRecuperacao");
		}		
		
	
		for(int i = 0; i < 2; i ++) {
			addPower("senhaForte");		
		}
		
		for(int i = 0; i < 2; i ++) {
			addPower("vpn");
		}
		

		for(int i = 0; i < 5; i ++) {
			addPower("autenticacao");
		}		
		
		for(int i = 0; i < 5; i ++) {
			addEnemy("engenhariaSocial");
		}			
		
		mScore = 0;
		mNumLives = 3;
		mHudScoreActor.setText("SCORE : 0");
		for(PlayerShipActor life : mHudLivesArray) {
			life.setVisible(true);
		}
	}	

	public void initializeGame4() {
		
		mActorList.clear();
		mNextPhase = 5;
		
		addPlayer();
		for(int i = 0; i < 2; i ++) {
			addPower("backups");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("checksum");
		}

		for(int i = 0; i < 5; i ++) {
			addPower("criptografia");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("planoRecuperacao");
		}		
		
	
		for(int i = 0; i < 2; i ++) {
			addPower("senhaForte");		
		}
		
		for(int i = 0; i < 2; i ++) {
			addPower("vpn");
		}
		

		for(int i = 0; i < 2; i ++) {
			addPower("autenticacao");
		}		
		
		for(int i = 0; i < 5; i ++) {
			addEnemy("sniffers");
		}			
		
		mScore = 0;
		mNumLives = 3;
		mHudScoreActor.setText("SCORE : 0");
		for(PlayerShipActor life : mHudLivesArray) {
			life.setVisible(true);
		}
	}		

	public void initializeGame5() {
		
		mActorList.clear();
		mNextPhase = 6;
		
		addPlayer();
		for(int i = 0; i < 2; i ++) {
			addPower("backups");
		}

		for(int i = 0; i < 1; i ++) {
			addPower("checksum");
		}

		for(int i = 0; i < 1; i ++) {
			addPower("criptografia");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("planoRecuperacao");
		}		
		
	
		for(int i = 0; i < 1; i ++) {
			addPower("senhaForte");		
		}
		
		for(int i = 0; i < 2; i ++) {
			addPower("vpn");
		}
		

		for(int i = 0; i < 2; i ++) {
			addPower("autenticacao");
		}		

		for(int i = 0; i < 2; i ++) {
			addPower("atualSoft");
		}	
		
		for(int i = 0; i < 2; i ++) {
			addPower("firewall");
		}	
		
		for(int i = 0; i < 2; i ++) {
			addPower("antimalware");
		}	
		
		
		for(int i = 0; i < 5; i ++) {
			addEnemy("malware");
		}			
		
		mScore = 0;
		mNumLives = 3;
		mHudScoreActor.setText("SCORE : 0");
		for(PlayerShipActor life : mHudLivesArray) {
			life.setVisible(true);
		}
	}	

	public void initializeGame6() {
		
		mActorList.clear();
		mNextPhase = 7;

		for(int i = 0; i < 3; i ++) {
			addPower("autenticacao");
		}

		for(int i = 0; i < 3; i ++) {
			addPower("senhaForte");		
		}
		
		for(int i = 0; i < 3; i ++) {
			addPower("vpn");
		}

		for(int i = 0; i < 5; i ++) {
			addEnemy("sniffers");
		}	

		for(int i = 0; i < 5; i ++) {
			addEnemy("engenhariaSocial");
		}			
		
		for(int i = 0; i < 2; i ++) {
			addEnemy("ataqueConfidencialidade");
		}
		
		for(int i = 0; i < 2; i ++) {
			addEnemy("ataqueDisponibilidade");
		}
		
		for(int i = 0; i < 2; i ++) {
			addEnemy("ataqueIntegridade");
		}	

		for(int i = 0; i < 2; i ++) {
			addEnemy("forcaBruta");
		}
		
		addPlayer();
		for(int i = 0; i < 2; i ++) {
			addPower("backups");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("checksum");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("criptografia");
		}

		for(int i = 0; i < 2; i ++) {
			addPower("planoRecuperacao");
		}		
		
	
		for(int i = 0; i < 2; i ++) {
			addPower("senhaForte");		
		}
		
		for(int i = 0; i < 2; i ++) {
			addPower("vpn");
		}
		

		for(int i = 0; i < 2; i ++) {
			addPower("autenticacao");
		}		

		for(int i = 0; i < 2; i ++) {
			addPower("atualSoft");
		}	
		
		for(int i = 0; i < 2; i ++) {
			addPower("firewall");
		}	
		
		for(int i = 0; i < 2; i ++) {
			addPower("antimalware");
		}	
		
		
		for(int i = 0; i < 2; i ++) {
			addEnemy("malware");
		}			
		
		mScore = 0;
		mNumLives = 3;
		mHudScoreActor.setText("SCORE : 0");
		for(PlayerShipActor life : mHudLivesArray) {
			life.setVisible(true);
		}
	}	
	
	
	@Override
	public void dispose() {
		mStage.dispose();
		
	}
	
	private void handleKillableActors() {
		
		mStage.getActors().clear();
		mBulletActorList.clear();
		mDamageableActorList.clear();

		for(BaseActor actor : mActorList) {
			 
			if(!actor.isDead()) {
				 mStage.addActor(actor);
				 
				 if(PlayerShipActor.class.isInstance(actor)) {
					 mPlayer = (PlayerShipActor) actor;
				 }
				 
				 if(DamageableActor.class.isInstance(actor)) {
					 mDamageableActorList.add((DamageableActor) actor);
				 }
				 
				 if(BulletActor.class.isInstance(actor)) {
					 mBulletActorList.add((BulletActor) actor);
				 }
				 
			}
		}
		
		mActorList.clear();
		
		for(Actor actor : mStage.getActors()) {
			
			mActorList.add((BaseActor) actor);
		}
	}
	
	private void handleBullets() {
		
		for(BulletActor bullet : mBulletActorList) {
			for(DamageableActor actor : mDamageableActorList) {
				
				if (bullet.isEnemy() && actor!= mPlayer) {
					continue;
				}
				
				if(bullet.intersects(actor)) {
					bullet.kill();
					actor.damage(bullet.getAngle());
					
					SparkEffectActor spark = addSparkEffect();
					spark.setPosition(bullet.getX(), bullet.getY());
				}
			}
		}
	}
	
	
	
	public void queuePlayerSpawn() {
		mStage.addAction(Actions.sequence(Actions.delay(1f), Actions.run(new Runnable() {
			
			@Override
			public void run() {
				addPlayer();
				
			}
		})));
	}
	

	
	private void handleCollision() {

		for(DamageableActor actor: mDamageableActorList) {
			
			
			if(mPlayer != null && actor!= mPlayer && actor.intersects(mPlayer) ) {
				
				if ( !actor.getMtype().equals("power"))
				{
				mPlayer.damage(actor.getAngle());
				//actor.damage(mPlayer.getAngle());
				Vector2 sparkPos = mPlayer.getTipPosition();
				SparkEffectActor spark = addSparkEffect();
				spark.setPosition(sparkPos.x, sparkPos.y);
				mPlayer = null;				
				}
				else {
					addHudPower((PowerActor) actor);
					actor.kill();
				}
				
			}
		}
		
	}
	

	@Override
	public void resize(int width, int height) {
		mStage.setViewport(width, height);

	}



	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		handleKillableActors();
		handleCollision();
		handleBullets();
		
		mStage.act(delta);
		mStage.draw();
		mHudStage.draw();
		
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
