package com.hlfc.springboot.javacv.jave;

import it.sauronsoftware.jave.*;

import java.io.File;
import java.io.IOException;

/**
 * 微信语音转 mp3
 * @Auther: hxl
 * @Date: 2022/6/13 15:48
 */
public class AmrHTest {

    public static void main(String[] args) {
        System.setProperty("ffmpeg.home", "E:\\Workspaces\\spring-boot\\src\\main\\resources\\bin");
        File source = new File("C:\\Users\\ASUS\\Desktop\\aud文件\\mp3转成amr后\\71.amr");
        File target = new File("C:\\Users\\ASUS\\Desktop\\aud文件\\aud原文件\\71.mp3");

//        Encoder encoder = new Encoder();
//        try {
//            String[] strings  = encoder.getSupportedDecodingFormats();
//            AudioAttributes audio = new AudioAttributes();
//            System.out.println(strings);
//        } catch (EncoderException e) {
//            e.printStackTrace();
//        }
//        AudioUtils.amrToMp3(source, target);
          audtomp3();
//        m4aToWav();
//        AudioUtils.
//        encodingFormats();
//        codec();
    }

    public static void audtomp3() {
        System.setProperty("ffmpeg.home", "E:\\Workspaces\\spring-boot\\src\\main\\resources\\bin");

        try {
            String path = "cmd /c copy C:\\Users\\ASUS\\Desktop\\aud文件\\amrhead.txt/b + C:\\Users\\ASUS\\AppData\\Local\\Temp\\media\\77.aud C:\\Users\\ASUS\\AppData\\Local\\Temp\\media\\77.amr";
//            String path = "cmd /c copy "+ "C:\\Users\\ASUS\\Desktop\\aud文件\\amrhead.txt/b +" + " C:\\Users\\ASUS\\Desktop\\aud文件\\77.aud"+ " C:\\Users\\ASUS\\Desktop\\aud文件\\77.amr";
            System.out.println("==="+path);
            Runtime.getRuntime().exec(path);
//            File source = new File("C:\\Users\\ASUS\\Desktop\\aud文件\\77.amr");
//            File target = new File("C:\\Users\\ASUS\\Desktop\\aud文件\\77.mp3");
//            AudioUtils.amrToMp3(source, target);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static boolean m4aToWav() {
        boolean succeeded = true;
        try {
            File source = new File("C:\\Users\\ASUS\\Desktop\\aud文件\\mp3转成amr后\\71.amr");
            File target = new File("C:\\Users\\ASUS\\Desktop\\aud文件\\aud原文件\\71.mp3");

            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
//            audio.setCodec("pcm_s16le");//wav
//            audio.setCodec("libmp3lame");//mp3
            audio.setCodec("libmp3lame");//amr
            audio.setBitRate(16000);
            audio.setChannels(1);
            audio.setSamplingRate(16000);

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp3");
            attrs.setAudioAttributes(audio);

            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(source, target, attrs);

        } catch (Exception ex) {
            ex.printStackTrace();
            succeeded = false;
        }
        return succeeded;
    }

    //对于格式属性，可以调整成需要的数值。如果需要其他的Codec，可以使用如下方法打印出所有支持的Codec，选择所需要的。
    public static void codec() {
        Encoder encoder = new Encoder();
        try {
            for (int i = 0; i < encoder.getAudioEncoders().length; i++) {
                System.out.println(encoder.getAudioEncoders()[i].toString());
            }
        } catch (EncoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void encodingFormats() {
        Encoder encoder = new Encoder();
        try {
            for (int i = 0; i < encoder.getSupportedEncodingFormats().length; i++) {
                System.out.println(encoder.getSupportedEncodingFormats()[i].toString());
            }
        } catch (EncoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
