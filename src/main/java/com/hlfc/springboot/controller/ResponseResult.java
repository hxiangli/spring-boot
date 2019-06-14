package com.hlfc.springboot.controller;

import java.io.Serializable;

/**
 * 封装返回页面的结果对象
 * @author hxl
 * @param  返回对象类型
 */
public class ResponseResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4364938165652684340L;
	
	
	private boolean success = true; // 正确的标识
	private Object content; //返回内容
	private String message; //错误内容

	public ResponseResult(Object content){
		this.content = content;
	}

	public ResponseResult() {

	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}	
	
}
