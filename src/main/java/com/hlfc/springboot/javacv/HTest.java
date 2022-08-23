package com.hlfc.springboot.javacv;

import freemarker.template.utility.DateUtil;
import sun.util.resources.LocaleData;

import java.awt.*;
import java.util.Date;
import java.util.Locale;

/**
 * 测试类q
 * 
 * @author alderaan
 * @version 创建时间：2022年5月10日 下午3:47:49
 *
 */
public class HTest
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("====开始转换"+ new Date());
			// videoConvert函数，根据outputfile的格式后缀设置转码后的视频格式，推荐使用mp4格式后缀
			VideoUtil.videoConvert("C:\\Users\\ASUS\\AVI测试文件\\mewmew-ssa.avi", "C:\\Users\\ASUS\\Desktop\\333.mp4");
			System.out.println("====结束转换"+ new Date());
		} catch (java.lang.Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完毕！");
	}
}

