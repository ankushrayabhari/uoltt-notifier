package com.ankushrayabhari.notifier.screens;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ankushrayabhari.notifier.Notifier;
import com.ankushrayabhari.notifier.Preferences;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Json;

public class MainMenuScreen implements Screen{
    final Notifier app;
    private Stage stage;
    private Table table;
    private Texture fTexture, tTexture, yTexture, nTexture;
    private Image fImage, tImage, yImage, nImage;
    private Label fCaption, tCaption, yCaption, nCaption;
    private ImageButton fButton, tButton, yButton, nButton;
    private TextButton pButton, gButton;
    private Document doc;
    private Element notification;
    public float timer;
    Preferences prefs;
    
    public MainMenuScreen(Notifier sApp) {
	this.app = sApp;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(.169f, .169f, .169f, 1);
        
        Json json = new Json();
        prefs = json.fromJson(Preferences.class, Gdx.files.local("prefs.json"));
        timer = timer + delta;
        if((timer >= 10) && !((Gdx.app.getType().equals(ApplicationType.Android) || (Gdx.app.getType().equals(ApplicationType.iOS))))) {
            getNotifications();
        }
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        
    }

    @Override
    public void resize(int width, int height) {
	stage.getViewport().update(width, height, true);
	table.invalidateHierarchy();
    }

    @Override
    public void show() {
	timer = 10;
	stage = new Stage();
	Gdx.input.setInputProcessor(stage);
	table = new Table();
	table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	table.padLeft(20).padTop(20).top().left().setFillParent(true);
	
	//Images
	
	fTexture = new Texture(Gdx.files.internal("img/fLogo.png"));
	fImage = new Image(fTexture);
	tTexture = new Texture(Gdx.files.internal("img/tLogo.png"));
	tImage = new Image(tTexture);
	yTexture = new Texture(Gdx.files.internal("img/yLogo.png"));
	yImage = new Image(yTexture);
	nTexture = new Texture(Gdx.files.internal("img/lttForum.png"));
	nImage = new Image(nTexture);
	
	pButton = new TextButton("Preferences", app.buttonStyle);
	pButton.addListener(new ChangeListener(){
	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		app.setScreen(app.pScreen);
	    }
	});
	gButton = new TextButton("Get Notifications", app.buttonStyle);
	gButton.setScale(0.75f);
	gButton.addListener(new ChangeListener(){
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			getNotifications();
		}
	});
	fButton = new ImageButton(fImage.getDrawable());
	fButton.addListener(new ChangeListener(){
	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		//app.setScreen(app.fScreen);
	    }
	});
	
	tButton = new ImageButton(tImage.getDrawable());
	tButton.addListener(new ChangeListener(){
	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		//app.setScreen(app.tScreen);
	    }
	});
	
	yButton = new ImageButton(yImage.getDrawable()); 
	yButton.addListener(new ChangeListener(){
	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		//app.setScreen(app.yScreen);
	    }
	});
	
	nButton = new ImageButton(nImage.getDrawable());
	nButton.addListener(new ChangeListener(){
	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		Gdx.net.openURI("http://linustechtips.com/main/index.php?app=core&module=usercp&tab=core&area=notificationlog");
	    }
	});
	
	
	//Labels
	
	fCaption = new Label("Coming Soon", app.labelStyle);
	tCaption = new Label("Coming Soon", app.labelStyle);
	yCaption = new Label("Coming Soon", app.labelStyle);
	nCaption = new Label("Press Get Notifications", app.labelStyle);

	
	//Add Actors to Table
	table.add(app.lttImage);
	table.add(pButton);
	table.row().padBottom(20);
	table.add(app.titleCaption);
	table.add(gButton);
	table.row().padBottom(5);
	table.add(fButton).uniform();
	table.add(tButton).uniform();
	table.row().padBottom(30);
	table.add(fCaption);
	table.add(tCaption);
	table.row().padBottom(5);
	table.add(yButton);
	table.add(nButton);
	table.row().padBottom(30);
	table.add(yCaption);
	table.add(nCaption);
	stage.addActor(table);
	
	
    }

    @Override
    public void hide() {
	
    }

    @Override
    public void pause() {
	
    }

    @Override
    public void resume() {
	
    }

    @Override
    public void dispose() {
	yTexture.dispose();
	tTexture.dispose();
	fTexture.dispose();
	nTexture.dispose();
	stage.dispose();
    }
    
    public void getNotifications(){
    	 try {
    		 doc = Jsoup.connect("https://linustechtips.com/main/index.php?app=core&module=global&section=login&do=process").data("ips_username", prefs.getUsername()).data("ips_password", prefs.getPassword()).data("auth_key", "880ea6a14ea49e853634fbdc5015a024").userAgent("Firefox").post();
     	     Gdx.app.log("Conn", "Success");
     	     notification = doc.getElementById("ipbwrapper").getElementById("header_bar").getElementById("user_navigation").getElementById("notify_link");
    	 } catch (IOException e) {
    		 Gdx.app.log("Conn:", "Failed");
    		 nCaption.setText("Cannot Connect to Forum");
    		 timer = 0;
    		 return;
    	 }
     	 if(doc.getElementById("ipbwrapper").getElementById("header_bar").getElementById("user_navigation").attr("class").equals("not_logged_in")) {
     	     nCaption.setText("Login failed! Check credentials!");
     	 }
     	 else{
         	 if(notification != null) {
         		 Gdx.app.log("Notifications", notification.text());
         		 Gdx.app.log("PrefsUser", prefs.getUsername());
         		 if(notification.text().equals(Character.toString((char) 160))) {
         			 nCaption.setText("You have no notifications.");
         		 }
         		 else {
         			 nCaption.setText("You have "+notification.text()+"notifications.");
         			 app.notification.toast("You have "+notification.text()+"notifications.");
         		 }
         	 }
     	 }
     	 timer = 0;
    }
    
}
