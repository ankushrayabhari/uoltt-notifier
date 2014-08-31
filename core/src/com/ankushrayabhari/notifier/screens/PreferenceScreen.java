package com.ankushrayabhari.notifier.screens;

import com.ankushrayabhari.notifier.Notifier;
import com.ankushrayabhari.notifier.Preferences;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Json;

public class PreferenceScreen implements Screen {
    final Notifier app;
    private Stage stage;
    private Table table;
    private TextButton rButton, apply;
    private Skin skin;
    private CheckBox tqBox, lttBox, csfBox;
    private Label yLabel, fLabel;
    private TextField username, password;
    Preferences prefs;
    
    public PreferenceScreen(Notifier notifier) {
	app = notifier;
    }

    @Override
    public void render(float delta) {
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	Gdx.gl.glClearColor(.169f, .169f, .169f, 1);
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
    Gdx.graphics.setContinuousRendering(true);
	stage = new Stage();
	table = new Table();
	table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	table.padTop(20).padLeft(20).top().left().setFillParent(true);
	prefs = new Preferences();
	Gdx.input.setInputProcessor(stage);
	rButton = new TextButton("Return to Home", app.buttonStyle);
	rButton.addListener(new ChangeListener(){
	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		app.setScreen(app.mainMenuScreen);
	    }
	});
	
	skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	lttBox = new CheckBox(" LinusTechTips", skin);
	tqBox = new CheckBox(" TechQuickie", skin);
	csfBox = new CheckBox(" ChannelSuperFun", skin);
	
	yLabel = new Label("Youtube Channels: (coming soon)", app.labelStyle);
	fLabel = new Label("Forum Login", app.labelStyle);
	
	username = new TextField("", skin);
	password = new TextField("", skin);
	username.setMessageText("Username");
	password.setMessageText("Password");
	password.setPasswordMode(true);
	
	
	apply = new TextButton("Apply", skin);
	apply.addListener(new ChangeListener(){
	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		Json json = new Json();
		prefs.setLtt(lttBox.isChecked());
		prefs.setCsf(csfBox.isChecked());
		prefs.setTq(tqBox.isChecked());
		prefs.setUsername(username.getText());
		prefs.setPassword(password.getText());
		Gdx.files.local("prefs.json").writeString(json.toJson(prefs), false);
		rButton.setDisabled(false);
		app.prefsSet = true;
	    }
	});
	
	if(app.prefsSet) {
	    Json json = new Json();
	    prefs = json.fromJson(Preferences.class, Gdx.files.local("prefs.json"));
	    lttBox.setChecked(prefs.isLtt());
	    tqBox.setChecked(prefs.isTq());
	    csfBox.setChecked(prefs.isCsf());
	    username.setText(prefs.getUsername());
	    password.setText(prefs.getPassword());
	}
	else {
	    rButton.setDisabled(true);
	}
	
	table.add(app.lttImage).uniform();
	table.add(rButton).uniform().padLeft(50);
	table.row().padBottom(25);
	table.add(app.titleCaption);
	table.row().padBottom(5);
	table.add(fLabel);
	table.row().padBottom(5);
	table.add(username);
	table.row().padBottom(20);
	table.add(password);
	table.row().padBottom(20);
	table.add(yLabel);
	table.row();
	table.add(lttBox);
	table.row();
	table.add(tqBox);
	table.row().padBottom(20);
	table.add(csfBox);
	table.row();
	table.add(apply).right();
	
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
    }

}
