package com.solxiom.signincontrol.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
   
    String jotain;
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
        @RequestMapping(value="/json")
	public @ResponseBody List<String> test_json(){
            List<String> logList = new LinkedList<String>();
                logList.add(" mnmnmnmnmn ");
                
            return logList;
	}
}
