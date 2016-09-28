package com.tiyssa.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebsiteController {

	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest request, ModelAndView mv) {
		if(request.getParameter("name") != null){
			mv.addObject("name", request.getParameter("name"));
		}
		mv.setViewName("home");
		return mv;
 	}
	
	@RequestMapping("/about")
    public ModelAndView about(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("about");
        return mv;
    }

	@RequestMapping("/help")
    public ModelAndView help(HttpServletRequest request, ModelAndView mv) {
		HashMap<String, String> helpMessages = new HashMap<String, String>();
		helpMessages.put("1", "First Message");
		helpMessages.put("2", "Second Message");
		helpMessages.put("3", "Third Message");
		helpMessages.put("4", "Fourth Message");
		helpMessages.put("5", "Fifth Message");
		String str = request.getParameter("id");
	    mv.addObject("desc", helpMessages.get(str));
	    mv.addObject("id", str);
		
        mv.setViewName("help");
        return mv;
    }

} 