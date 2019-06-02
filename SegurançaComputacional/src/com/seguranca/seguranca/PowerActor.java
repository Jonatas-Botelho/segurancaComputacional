package com.seguranca.asteroids;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class PowerActor extends DamageableActor {
	
	private String subType;
		
	private static final Random mRandomizeTime = new Random(158);

	private float getNextDirectionChangeTime() {
		return 8 + 8 * mRandomizeTime.nextFloat();
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
		//super.damage(angle);
		//AsteroidsGameScreen.getInstance().incrementScore();
		//AsteroidsGameScreen.getInstance().queueFlyingSaucerSpawn();
	}

	
	public PowerActor( String subtype) {
		float size = Gdx.graphics.getWidth() / 15;
		
		setWrapEdge(true);
		setSize(size, size);
		randomizePositionOnEdge();
		randomizeSpeed();
		randomizeAngle();
		setMtype("power");
		this.setSubType(subtype);
		
		queueDirectionChange();
		//queueFireBullet();
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
		
		
		saucerTexture = ResourceManager.getInstance().saucerTexture;		
		
		if (this.subType.equals("senhaForte")) {
			saucerTexture = ResourceManager.getInstance().senhaForte;	
		}
		
		if (this.subType.equals("autenticacao")) {
			saucerTexture = ResourceManager.getInstance().autenticacao;	
		}
		
		if (this.subType.equals("vpn")) {
			saucerTexture = ResourceManager.getInstance().vpn;	
		}
		
		if (this.subType.equals("backups")) {
			saucerTexture = ResourceManager.getInstance().backups;	
		}
		
		if (this.subType.equals("checksum")) {
			saucerTexture = ResourceManager.getInstance().checksum;	
		}
		
		if (this.subType.equals("criptografia")) {
			saucerTexture = ResourceManager.getInstance().criptografia;	
		}
		
		if (this.subType.equals("planoRecuperacao")) {
			saucerTexture = ResourceManager.getInstance().planoRecuperacao;	
		}

		if (this.subType.equals("atualSoft")) {
			saucerTexture = ResourceManager.getInstance().atualSoft;	
		}

		if (this.subType.equals("antimalware")) {
			saucerTexture = ResourceManager.getInstance().antimalware;	
		}
		
		if (this.subType.equals("firewall")) {
			saucerTexture = ResourceManager.getInstance().firewall;	
		}
	
		batch.draw(saucerTexture, getX(), getY(), getWidth(), getHeight());
		
	}


	public String getSubType() {
		return subType;
	}


	public void setSubType(String subType) {
		this.subType = subType;
	}

}
