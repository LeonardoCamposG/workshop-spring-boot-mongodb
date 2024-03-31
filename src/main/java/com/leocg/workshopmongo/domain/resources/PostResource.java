package com.leocg.workshopmongo.domain.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leocg.workshopmongo.domain.Post;
import com.leocg.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts") //Caminho do endpoint;
public class PostResource {

	@Autowired
	private PostService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){	// Response entity encapsula uma resposta de http.
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}