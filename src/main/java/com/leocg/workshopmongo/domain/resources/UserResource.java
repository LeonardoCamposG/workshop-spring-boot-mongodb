package com.leocg.workshopmongo.domain.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leocg.workshopmongo.domain.Post;
import com.leocg.workshopmongo.domain.User;
import com.leocg.workshopmongo.dto.UserDTO;
import com.leocg.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users") //Caminho do endpoint;
public class UserResource {

	@Autowired
	private UserService service;
	
	// @RequestMapping(method=RequestMethod.GET) Definindo methodo http a ser usado. 
	@GetMapping	// Poderia ser usado a annotation acima também.
	public ResponseEntity<List<UserDTO>> findAll(){	// Response entity encapsula uma resposta de http.
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().
				map(x -> new UserDTO(x)).
				collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){	// Response entity encapsula uma resposta de http.
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){	
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();	// Created retorna o 201 da request(criado novo elemento).
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){	
		service.delete(id);
		return ResponseEntity.noContent().build(); //Retornando 204.
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){	
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){	
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
	
}
