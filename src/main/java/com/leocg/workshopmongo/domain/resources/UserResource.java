package com.leocg.workshopmongo.domain.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leocg.workshopmongo.domain.User;
import com.leocg.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users") //Caminho do endpoint;
public class UserResource {

	@Autowired
	private UserService service;
	
	// @RequestMapping(method=RequestMethod.GET) Definindo methodo http a ser usado. 
	@GetMapping	// Poderia ser usado a annotation acima também.
	public ResponseEntity<List<User>> findAll(){	// Response entity encapsula uma resposta de http.
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
