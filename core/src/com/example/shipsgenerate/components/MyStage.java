package com.example.shipsgenerate.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.shipsgenerate.utils.Constants;

public class MyStage extends Stage {
    private boolean isCheckedField;
    private float clickX;
    private float clickY;

    public MyStage(SpriteBatch batch, final Texture field) {
        super(new ScreenViewport(), batch);
        isCheckedField = false;
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (x >= Constants.FIELD_X && x <= Constants.FIELD_X + field.getHeight()
                        && y >= Constants.FIELD_Y && y <= Constants.FIELD_Y + field.getWidth()) {
                    isCheckedField = true;
                    clickX = x;
                    clickY = y;
                } else {
                    isCheckedField = false;
                }
            }
        });
    }

    public boolean isCheckedField() {
        return isCheckedField;
    }

    public float getClickX() {
        return clickX;
    }

    public float getClickY() {
        return clickY;
    }
}
