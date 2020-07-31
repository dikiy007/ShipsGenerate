package com.example.shipsgenerate;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.shipsgenerate.components.ButtonAuto;

public class Main extends ApplicationAdapter {
	private Stage stage;
	
	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Image field = new Image(new Texture("field.png"));
		field.setPosition(28, 18);

		stage.addActor(new Image(new Texture("screen.png")));
		stage.addActor(field);


		ButtonAuto buttonAuto = new ButtonAuto("buttonAuto.png", field, stage);
		buttonAuto.setPosition(431, 24);
		stage.addActor(buttonAuto);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();

	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
