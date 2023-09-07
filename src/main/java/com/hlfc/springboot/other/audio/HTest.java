package com.hlfc.springboot.other.audio;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author hxli
 * @since 2023/8/30/030
 */
public class HTest {

    public static void main(String[] args) {

        //wav 播放
        AudioPlay audioPlay = new AudioPlay("C:\\Users\\Administrator\\Desktop\\qqq.wav");
        long time = new Date().getTime();
        System.out.println("========开始播放！！");
        audioPlay.start();
        System.out.println("========结束播放！！" + (new Date().getTime() - time) / 1000);

        // mp3播放
        JLayePlay jLayePlay = new JLayePlay();
        new Thread(() -> {
            System.out.println("====开始播放33333");
            jLayePlay.play("/data/app/audio/www.mp3");
        }).start();
    }
}
