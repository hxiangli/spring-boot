package com.hlfc.springboot.javacv;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.bytedeco.javacv.Frame;
import org.bytedeco.librealsense.frame;

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
        JavaCvUtil.convert(new File("C:\\Users\\ASUS\\Desktop\\videoformat\\5.mov"));
    }

    public static String convert(File file) {
        System.out.println(new Date());
//        FFmpegLogCallback.set();
        Frame frame;
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
             fileFullPathName = "C:\\Users\\ASUS\\Desktop\\333.mp4";
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
            // 两个关键帧之间的帧数
//            recorder.setGopSize((int) frameGrabber.getVideoFrameRate());
            // 一秒内的帧数，帧率
//            recorder.setFrameRate((int) frameGrabber.getVideoFrameRate());

            // 设置音频通道数，与视频源的通道数相等
//            recorder.setAudioChannels(frameGrabber.getAudioChannels());


            recorder.start();
            System.out.println(new Date());

            int videoframenum = 0;
            int audioframenum = 0;
            int dataframenum = 0;
            // 持续从视频源取帧
            while (null != (frame = frameGrabber.grabFrame()))
            {
                if(frame.image !=null){
                    videoframenum++;
                }
                if(frame.samples !=null){
                    audioframenum++;
                }
                if(frame.data !=null){
                    dataframenum++;
                }
                // 有图像，就把视频帧加一
                if (null != frame.image)
                {
                    // 取出的每一帧，都保存到视频
                    recorder.record(frame);
                }

                // 有声音，就把音频帧加一
                if (null != frame.samples)
                {
                    // 取出的每一帧，都保存到视频
                    recorder.record(frame);
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
