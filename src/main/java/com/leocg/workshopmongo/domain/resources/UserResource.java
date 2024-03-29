package com.leocg.workshopmongo.domain.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leocg.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users") //Caminho do endpoint;
public class UserResource {

	// @RequestMapping(method=RequestMethod.GET) Definindo methodo http a ser usado. 
	@GetMapping	// Poderia ser usado a annotation acima também.
	public ResponseEntity<List<User>> findAll(){	// Response entity encapsula uma resposta de http.
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
	}
}
