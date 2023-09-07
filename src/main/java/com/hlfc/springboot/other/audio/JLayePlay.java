package com.hlfc.springboot.other.audio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class JLayePlay {
    public  void play(String path) {
        try {
            long time = new Date().getTime();
                Player player = new Player(new FileInputStream(path));
            System.out.println("====是否结束111"+(player.isComplete()?"是":"否"));
            player.isComplete();
                player.play();
            System.out.println("====是否结束2222"+(player.isComplete()?"是":"否"));
               player.isComplete();
        }  catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
