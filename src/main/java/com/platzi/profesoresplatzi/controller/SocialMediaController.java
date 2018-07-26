package com.platzi.profesoresplatzi.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.platzi.profesoresplatzi.model.SocialMedia;
import com.platzi.profesoresplatzi.service.SocialMediaService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class SocialMediaController {
	
	@Autowired
	SocialMediaService _socialMediaService;
	
	//GET
	@RequestMapping(value="/socialMedias", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias(){
		
		List<SocialMedia> socialMedias = new ArrayList<>();
		socialMedias = _socialMediaService.findAllSocialMedias();
		if (socialMedias.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
	}
	
	//POST
	
	@RequestMapping(value="/socialMedias", method = RequestMethod.POST,  headers = "Accept=application/json" )
	public ResponseEntity<?> createSocialMedia(@RequestBody SocialMedia socialMedia, UriComponentsBuilder uriComponentsBuilder){
		
		/**Si la respuesta al crear es null o esta vacia  devuelve no content*/
		if(socialMedia.getName().equals(null) ||socialMedia.getName().isEmpty() ) {			
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		if (_socialMediaService.findByName(socialMedia.getName())!=null) {
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		
		_socialMediaService.saveSocialMedia(socialMedia);
		SocialMedia socialMedia2=_socialMediaService.findByName(socialMedia.getName());
		
		HttpHeaders headers= new HttpHeaders();
		
		headers.setLocation(
				uriComponentsBuilder.path("/v1/socialMedias/{id}")
				.buildAndExpand(socialMedia2.getIdSocialMedia())
				.toUri());
		
		return new  ResponseEntity<String>(headers,HttpStatus.CREATED);
		
		
		
	}
	

}
