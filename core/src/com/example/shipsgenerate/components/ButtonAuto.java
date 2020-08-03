package com.example.shipsgenerate.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.example.shipsgenerate.utils.Constants;
import com.example.shipsgenerate.utils.Rotation;

import java.awt.Point;
import java.util.Random;

public class ButtonAuto extends ImageButton {
    private boolean[][] gameField;

    public ButtonAuto(final String imagePath) {
        super(new SpriteDrawable(new Sprite(new Texture(imagePath))));
        this.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ButtonAuto.this.touchUp();
                setChecked(true);

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

    }

    private void touchUp() {
        this.gameField = new boolean[10][10];

        Random random = new Random();
        int heightChip = 4;
        for(int i = 0; i < 10; i++) {
            if (i == 1 || i == 3 || i == 6) {
                heightChip--;
            }
            while (true) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                if (!isBadChip(x, y) && isDrawChip(Rotation.values()[random.nextInt(4)], heightChip, x, y)) {
                    break;
                }
            }
        }
    }

    private boolean isDrawChip(Rotation rotation, int height, int x, int y) {
        if (isBadChip(x, y)) {
            return false;
        }

        int countBadChip = 0;
        int i = 1;
        int dynamicX = x;
        int dynamicY = y;
        Point[] points = new Point[height];
        points[0] = new Point(x, y);
        while (i != height) {
            switch (rotation) {
                case UP: {
                    dynamicX--;
                    if (isBadChip(dynamicX, dynamicY)) {
                        dynamicX = x;
                        rotation = Rotation.RIGHT;
                        i = 0;
                        countBadChip++;
                    } else {
                        points[i] = new Point(dynamicX, dynamicY);
                    }
                    break;
                }
                case RIGHT: {
                    dynamicY++;
                    if (isBadChip(dynamicX, dynamicY)) {
                        dynamicY = y;
                        rotation = Rotation.DOWN;
                        i = 0;
                        countBadChip++;

                    } else {
                        points[i] = new Point(dynamicX, dynamicY);
                    }
                    break;
                }
                case DOWN: {
                    dynamicX++;
                    if (isBadChip(dynamicX, dynamicY)) {
                        dynamicX = x;
                        rotation = Rotation.LEFT;
                        i = 0;
                        countBadChip++;

                    } else {
                        points[i] = new Point(dynamicX, dynamicY);
                    }
                    break;
                }
                case LEFT: {
                    dynamicY--;
                    if (isBadChip(dynamicX, dynamicY)) {
                        dynamicY = y;
                        rotation = Rotation.UP;
                        i = 0;
                        countBadChip++;

                    } else {
                        points[i] = new Point(dynamicX, dynamicY);
                    }
                    break;
                }
            }

            if(countBadChip == 4) {
                return false;
            }

            i++;
        }

        for(i = 0; i < points.length; i++) {
            gameField[points[i].x][points[i].y] = true;
        }

        return true;
    }

    private boolean isBadChip(int x, int y) {
        if (x < 0 || y < 0 || x == 10 || y == 10 || gameField[x][y]) {
            return true;
        }

        if (x == 0 && y == 0) {
            return gameField[x][y + 1] || gameField[x + 1][y + 1] || gameField[x + 1][y];
        } else if (x == 0 && y == 9) {
            return gameField[x][y - 1] || gameField[x + 1][y - 1] || gameField[x + 1][y];
        } else if (x == 9 && y == 9) {
            return gameField[x][y - 1] || gameField[x - 1][y - 1] || gameField[x - 1][y];
        } else if (x == 9 && y == 0) {
            return gameField[x - 1][y] || gameField[x - 1][y + 1] || gameField[x][y + 1];
        } else if (x == 0) {
            return gameField[x][y + 1] || gameField[x + 1][y + 1] || gameField[x + 1][y]
                    || gameField[x + 1][y - 1] || gameField[x][y - 1];
        } else if (x == 9) {
            return gameField[x][y - 1] || gameField[x - 1][y - 1] || gameField[x - 1][y]
                    || gameField[x - 1][y + 1] || gameField[x][y + 1];
        } else if (y == 0) {
            return gameField[x - 1][y] || gameField[x - 1][y + 1] || gameField[x][y + 1]
                    || gameField[x + 1][y + 1] || gameField[x + 1][y];
        } else if (y == 9) {
            return gameField[x + 1][y] || gameField[x + 1][y - 1] || gameField[x][y - 1]
                    || gameField[x - 1][y - 1] || gameField[x - 1][y];
        } else return gameField[x - 1][y] || gameField[x - 1][y + 1] || gameField[x][y + 1]
                || gameField[x + 1][y + 1] || gameField[x + 1][y] || gameField[x + 1][y - 1]
                || gameField[x][y - 1] || gameField[x - 1][y - 1];
    }

    public void draw(Texture field, SpriteBatch batch) {
        float diffX = (float) field.getHeight() / 10f;
        float diffY = (float) field.getWidth() / 10f;


        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j]) {
                    Texture ship = new Texture("oneShip.png");
                    float x = Constants.FIELD_X + (diffX * i) - 2;
                    float y = Constants.FIELD_Y + (diffY * j);

                    batch.draw(ship, x, y);
                }
            }
        }
    }
}
