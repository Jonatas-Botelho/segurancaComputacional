package com.seguranca.asteroids;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class EnemyActor extends DamageableActor {
	
	private final ArrayList<String> weaknesses = 
			new ArrayList<String>();
	
	private String subType;
	
	private static final Random mRandomizeTime = new Random(158);
	private static final Random mRandomizeFireAngle = new Random(170);

	private float getNextDirectionChangeTime() {
		return 8 + 8 * mRandomizeTime.nextFloat();
	}
	
	public void addWeakness(String weakness) {
	weaknesses.add(weakness);	
	}

	
	public void setWeaknesses() {
	
		if (subType.equals("forcaBruta")){
			addWeakness("senhaForte");
			addWeakness("autenticacao");
		}

		if (subType.equals("ataqueConfidencialidade")){
			addWeakness("criptografia");
			addWeakness("autenticacao");
		}

		if (subType.equals("ataqueDisponibilidade")){
			addWeakness("planoRecuperacao");
			addWeakness("backups");
		}
		
		if (subType.equals("ataqueIntegridade")){
			addWeakness("checksum");
			addWeakness("backups");
		}
		
		if (subType.equals("engenhariaSocial")){
			addWeakness("autenticacao");
		}

		if (subType.equals("sniffers")){
			addWeakness("criptografia");
		}
		
		if (subType.equals("malware")){
			addWeakness("atualSoft");
			addWeakness("antimalware");
			addWeakness("firewall");
		}
		
	}
	
	public void queueDirectionChange() {
		addAction(Actions.sequence(Actions.delay(getNextDirectionChangeTime()), Actions.run(new Runnable() {
			
			@Override
			public void run() {
				randomizeAngle();
				queueDirectionChange();
			}
		})));
	}
	
	@Override
	public void damage(float angle) {
		
		if (this.weaknesses.contains(AsteroidsGameScreen.getInstance().getHudPowerSubType())) {
			super.damage(angle);
			AsteroidsGameScreen.getInstance().incrementScore();
		}
		
		//AsteroidsGameScreen.getInstance().incrementScore();
		//AsteroidsGameScreen.getInstance().queueFlyingSaucerSpawn();
	}

	
	public EnemyActor( String subtype) {
		float size = Gdx.graphics.getWidth() / 15;
		
		setWrapEdge(true);
		setSize(size, size);
		randomizePositionOnEdge();
		randomizeSpeed();
		randomizeAngle();
		setMtype("enemy");
		this.setSubType(subtype);
		setWeaknesses();
		
		queueDirectionChange();
		queueFireBullet();
	}

	public void fireBullet() {
		
		BulletActor bullet = AsteroidsGameScreen.getInstance().addBullet(true);

		float fireAngle = 2 * 3.14f * mRandomizeFireAngle.nextFloat();
		
		float x = getX() + getWidth() * 0.5f;
		float y = getY() + getHeight() * 0.5f;
		
		
		x -= getWidth()* 0.75f * Math.sin(fireAngle);
		y += getHeight() * 0.75f * Math.cos(fireAngle);
		
		bullet.setPosition(x, y);
		bullet.setAngle(fireAngle);
	}
	
	public void queueFireBullet() {
		addAction(Actions.sequence(Actions.delay(getNextShootTime()), Actions.run(new Runnable() {
			
			@Override
			public void run() {
				fireBullet();
				queueFireBullet();
			}
		})));
	}	

	private float getNextShootTime() {
		return 0.2f + 10f * mRandomizeTime.nextFloat();
	}	
	
	@Override
	public void act(float deltaTime) {
		super.act(deltaTime);
		
		float x = getX();
		float y = getY();
		x -= deltaTime * mSpeed  * Math.sin(mAngle);
		y += deltaTime * mSpeed  * Math.cos(mAngle);
		setPosition(x, y); 

	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Texture saucerTexture;
		super.draw(batch, parentAlpha);
		
		batch.setColor(getColor().r, getColor().g, getColor().b,
				getColor().a * parentAlpha);
		
		saucerTexture = ResourceManager.getInstance().autenticacao;
		
		if (this.subType.equals("forcaBruta")) {
			saucerTexture = ResourceManager.getInstance().forcaBruta;	
		}
		
		if (this.subType.equals("ataqueConfidencialidade")) {
			saucerTexture = ResourceManager.getInstance().ataqueConfidencialidade;	
		}
		
		if (this.subType.equals("ataqueDisponibilidade")) {
			saucerTexture = ResourceManager.getInstance().ataqueDisponibilidade;	
		}
		
		if (this.subType.equals("ataqueIntegridade")) {
			saucerTexture = ResourceManager.getInstance().ataqueIntegridade;	
		}
	
		if (this.subType.equals("engenhariaSocial")) {
			saucerTexture = ResourceManager.getInstance().engenhariaSocial;	
		}	

		if (this.subType.equals("sniffers")) {
			saucerTexture = ResourceManager.getInstance().sniffers;	
		}		

		if (this.subType.equals("malware")) {
			saucerTexture = ResourceManager.getInstance().malware;	
		}
		
		batch.draw(saucerTexture, getX(), getY(), getWidth(), getHeight());
		
	}


	public String getSubType() {
		return subType;
	}


	public void setSubType(String subType) {
		this.subType = subType;
	}

	public ArrayList<String> getWeaknesses() {
		return weaknesses;
	}

}
