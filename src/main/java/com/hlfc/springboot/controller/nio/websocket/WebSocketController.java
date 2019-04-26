package com.hlfc.springboot.controller.nio.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * websocket 控制类
 * @author hxl
 *
 */
@Controller
@RequestMapping("/nio")
public class WebSocketController {
	
	private static final Logger LOG = LoggerFactory.getLogger(WebSocketController.class);
	

	//socket页面请求
	@GetMapping("/socket")
	public ModelAndView socket() {
		ModelAndView mav=new ModelAndView("/nio/websocket/socket");
		return mav;
	}


}
