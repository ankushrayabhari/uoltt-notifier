package com.ankushrayabhari.notifier;

import com.ankushrayabhari.notifier.screens.FacebookScreen;
import com.ankushrayabhari.notifier.screens.MainMenuScreen;
import com.ankushrayabhari.notifier.screens.PreferenceScreen;
import com.ankushrayabhari.notifier.screens.TwitterScreen;
import com.ankushrayabhari.notifier.screens.YoutubeScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Notifier extends Game {
 	public BitmapFont font;
 	public MainMenuScreen mainMenuScreen;
 	public FacebookScreen fScreen;
 	public TwitterScreen tScreen;
 	public YoutubeScreen yScreen;
 	public PreferenceScreen pScreen;
 	public Texture lttTexture;
 	public Image lttImage;
 	public Label titleCaption;
 	public LabelStyle labelStyle;
 	public TextButtonStyle buttonStyle;
 	public boolean prefsSet;
 	
 	@Override
	public void create () {
	    font = new BitmapFont(Gdx.files.internal("skin/font.fnt"));
	    mainMenuScreen = new MainMenuScreen(this);
	    fScreen = new FacebookScreen(this);
	    tScreen = new TwitterScreen(this);
	    yScreen = new YoutubeScreen(this);
	    pScreen = new PreferenceScreen(this);
	    lttTexture = new Texture(Gdx.files.internal("img/lttLogo.png"));
	    lttImage = new Image(lttTexture);
	    labelStyle = new LabelStyle();
	    labelStyle.font = font;
	    titleCaption = new Label("Unofficial LTT Mobile Notifier", labelStyle);
	    buttonStyle = new TextButtonStyle();
	    buttonStyle.font = font;
	    buttonStyle.pressedOffsetX = 1;
	    buttonStyle.pressedOffsetY = 1;
	    buttonStyle.fontColor = Color.WHITE;
	    buttonStyle.checkedFontColor = Color.WHITE;
	    buttonStyle.overFontColor = Color.LIGHT_GRAY;
	    buttonStyle.checkedOverFontColor = Color.LIGHT_GRAY;
	    buttonStyle.downFontColor = Color.LIGHT_GRAY;
	    Gdx.files.local("prefs.json").writeString("", true);
	    if(Gdx.files.local("prefs.json").readString().equals("")) {
		prefsSet = false;
		setScreen(pScreen);
	    }
	    else {
		prefsSet = true;
		setScreen(mainMenuScreen);
	    }
	    
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
	    lttTexture.dispose();
	}
	
	
}
