package com.seguranca.asteroids;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.utils.Disposable;

public class ResourceManager implements Disposable {
	
	public static ResourceManager getInstance() {
		return mInstance;
	}
	
	private static ResourceManager mInstance;
	
	ResourceManager() {
		mInstance = this;
	}
	
	public Texture playerShipTexture;
	public Texture playerShipJetTexture;
	public Texture blank16Texture;
	public Texture saucerTexture;
	public Texture senhaForte;
	public Texture autenticacao;
	public Texture forcaBruta;
	public Texture vpn;
	public Texture ataqueConfidencialidade;
	public Texture ataqueDisponibilidade;
	public Texture ataqueIntegridade;
	public Texture backups;
	public Texture checksum;
	public Texture criptografia;
	public Texture planoRecuperacao;
	public Texture engenhariaSocial;
	public Texture sniffers;
	public Texture atualSoft;
	public Texture antimalware;
	public Texture firewall;
	public Texture malware;
	public final Texture[] asteroidTextureArray = new Texture[4];
	
	
	private final HashMap<Float, BitmapFont> mFontMap = new HashMap<Float, BitmapFont>(); 
	
	public BitmapFont getFontForHeight(float height) {
		BitmapFont ret;
		
		if(mFontMap.containsKey(height)) {
			ret = mFontMap.get(height);
		} else {
		
			ret = new BitmapFont(Gdx.files.internal("data/bebas_neue.fnt"), false);
			TextBounds bounds = ret.getBounds("K");
			float scaleFactor = height/bounds.height;
			ret.setScale(scaleFactor);
			mFontMap.put(height, ret);
		}
		
		return ret;
	}

	public void loadResources() {
		
		playerShipTexture = new Texture(Gdx.files.internal("data/player.png"));
		playerShipJetTexture = new Texture(Gdx.files.internal("data/player_jet.png"));
		blank16Texture = new Texture(Gdx.files.internal("data/blank16.png"));
		saucerTexture = new Texture(Gdx.files.internal("data/saucer.png"));
		senhaForte = new Texture(Gdx.files.internal("data/senhaForte.png"));
		autenticacao = new Texture(Gdx.files.internal("data/autenticacao.png"));
		forcaBruta = new Texture(Gdx.files.internal("data/forcaBruta.png"));
		vpn = new Texture(Gdx.files.internal("data/vpn.png"));
		ataqueConfidencialidade = new Texture(Gdx.files.internal("data/ataqueConfidencialidade.png"));
		ataqueDisponibilidade = new Texture(Gdx.files.internal("data/ataqueDisponibilidade.png"));
		ataqueIntegridade = new Texture(Gdx.files.internal("data/ataqueIntegridade.png"));
		backups = new Texture(Gdx.files.internal("data/backups.png"));
		checksum = new Texture(Gdx.files.internal("data/checksum.png"));
		criptografia = new Texture(Gdx.files.internal("data/criptografia.png"));
		planoRecuperacao = new Texture(Gdx.files.internal("data/planoRecuperacao.png"));
		engenhariaSocial = new Texture(Gdx.files.internal("data/engenhariaSocial.png"));
		sniffers = new Texture(Gdx.files.internal("data/sniffers.png"));
		atualSoft = new Texture(Gdx.files.internal("data/atualSoft.png"));
		antimalware = new Texture(Gdx.files.internal("data/antimalware.png"));
		firewall = new Texture(Gdx.files.internal("data/firewall.png"));
		malware = new Texture(Gdx.files.internal("data/malware.png"));
		
		for(int i = 0; i < 4; i++) {
			asteroidTextureArray[i] = new Texture(Gdx.files.internal("data/asteroid" + i + ".png"));
		}
		
	}

	@Override
	public void dispose() {
		playerShipJetTexture.dispose();
		playerShipTexture.dispose();
		blank16Texture.dispose();
		saucerTexture.dispose();
		senhaForte.dispose();
		autenticacao.dispose();
		forcaBruta.dispose();
		vpn.dispose();
		ataqueConfidencialidade.dispose();
		ataqueDisponibilidade.dispose();
		ataqueIntegridade.dispose();
		backups.dispose();
		checksum.dispose();
		criptografia.dispose();
		planoRecuperacao.dispose();
		engenhariaSocial.dispose();
		sniffers.dispose();
		atualSoft.dispose();
		antimalware.dispose();
		firewall.dispose();
		malware.dispose();
		for(Texture texture : asteroidTextureArray) {
			texture.dispose();
		}
		
		for(BitmapFont font : mFontMap.values()) {
			font.dispose();
		}

	}
	
}
