package com.example.game.utils;

import android.widget.ImageView;

public class RunnableSprites implements Runnable {
    int updateInterval = 100;
    boolean stop = false;
    public Integer[] sprits;
    ImageView img;
    int i = 0;

    public void stop() {
        this.stop = true;
    }

    public void setSprites(Integer[] sprites){
        this.sprits = sprites;
    }

    public void setImg(ImageView i){
        this.img = i;
    }

    public void setUpdateInterval(int in){
        this.updateInterval = in;
    }

    @Override
    public void run() {
        try {
            img.setImageResource(sprits[i]);
            i++;
        }
        catch (Exception e){
            i = 0;
        }

        if(!stop) {
            img.postDelayed(this, updateInterval);
        }
    }
}
