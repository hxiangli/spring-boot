package com.hlfc.springboot.javacv;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.io.File;
import java.util.Date;

/**
 * JavaCvUtil
 *
 * @author wangbh
 * @date 18:05 2022/6/13
 */
public class JavaCvUtil {

    public static void main(String[] args) {
        JavaCvUtil.convert(new File("C:\\Users\\ASUS\\Desktop\\2-audio-streams.avi"));
    }

    public static String convert(File file) {
        System.out.println(new Date());
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file.getAbsolutePath());
        String fileName = null;
        String fileFullPathName = null;

        Frame captured_frame = null;

        FFmpegFrameRecorder recorder = null;

        try {
            frameGrabber.start();
            //获取转码后的视频名称
            fileName = file.getName().replace(file.getName().substring(file.getName().lastIndexOf(".")),".mp4");
            //更换转码后视频存储位置
             fileFullPathName = "C:\\Users\\ASUS\\Desktop\\2-audio-streams.mp4";
            //如果想把转码后的视频还是保存到原文件目录下
            //fileFullPathName = file.getAbsolutePath().replace(file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".")), ".mp4");
            recorder = new FFmpegFrameRecorder(fileFullPathName, frameGrabber.getImageWidth(), frameGrabber.getImageHeight(), frameGrabber.getAudioChannels());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("mp4");
            recorder.setFrameRate(frameGrabber.getFrameRate());
            recorder.setVideoBitrate(frameGrabber.getVideoBitrate());
            recorder.setAudioBitrate(frameGrabber.getAudioBitrate());
            recorder.setAudioOptions(frameGrabber.getAudioOptions());
            recorder.setAudioQuality(0);
            recorder.setSampleRate(frameGrabber.getSampleRate());
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
//            recorder.setAudioCodec(frameGrabber.getAudioCodec());
            recorder.start();
            System.out.println(new Date());
            while (true) {
                try {
                    captured_frame = frameGrabber.grabFrame();

                    if (captured_frame == null) {
                        System.out.println("!!! Failed cvQueryFrame");
                        break;
                    }
                    recorder.record(captured_frame);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            recorder.stop();
            recorder.release();
            frameGrabber.stop();
            frameGrabber.release();
            recorder.close();
            frameGrabber.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
        //返回转码后视频文件名称
        return fileName;
        //返回转码后视频全路径
        //return fileFullPathName;
    }
}
