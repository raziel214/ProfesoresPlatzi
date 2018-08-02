package com.platzi.profesoresplatzi.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/")
	@ResponseBody
	public String index(){
		String response = "Bienvenido a <a href='http://platzi.com'>platzi.com</a> :)";
		return response;
		
		
		
	}
	
}
