package com.example.shipsgenerate;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.example.shipsgenerate.components.ButtonAuto;
import com.example.shipsgenerate.components.MyStage;
import com.example.shipsgenerate.utils.Constants;

public class Main extends ApplicationAdapter {
	private MyStage stage;
	private SpriteBatch batch;
	private Texture field;
	private ButtonAuto buttonAuto;
	private ShaderProgram shader;
	
	@Override
	public void create () {
		field = new Texture("field.png");

		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shader.vert"),
				(Gdx.files.internal("shader.frag")));
		if (!shader.isCompiled()) {
			System.err.println(shader.getLog());
			System.exit(0);
		}

		batch = new SpriteBatch();

		stage = new MyStage(batch, field);
		Gdx.input.setInputProcessor(stage);

		buttonAuto = new ButtonAuto("buttonAuto.png");
		buttonAuto.setPosition(431, 24);
		stage.addActor(buttonAuto);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		batch.begin();
		batch.draw(new Texture("screen.png"), 0, 0);
		if (stage.isCheckedField()) {
			batch.setShader(shader);
		}
		batch.draw(field, Constants.FIELD_X, Constants.FIELD_Y);
		if (buttonAuto.isChecked()) {
			buttonAuto.draw(field, batch);
		}

		batch.setShader(null);
		batch.end();
		stage.draw();
	}

	@Override
	public void dispose() {
		shader.dispose();
		stage.dispose();
		field.dispose();
		batch.dispose();
	}
}
