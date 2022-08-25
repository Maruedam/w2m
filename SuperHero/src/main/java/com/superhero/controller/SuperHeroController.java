package com.superhero.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.model.SuperHero;
import com.superhero.repository.SuperHeroRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SuperHeroController {

	@Autowired
	SuperHeroRepository superHeroRepository;

	// Usaremos un solo método para hacer la llamada de todos los super heroes(si no
	// se introduce parametro)
	// o de los super heroes que contengan el nombre si se introduce parametro
	@GetMapping("/superheros")
	public ResponseEntity<List<SuperHero>> getAllSuperHeros(@RequestParam(required = false) String nombre) {
		try {
			List<SuperHero> superHeros = new ArrayList<SuperHero>();
			if (nombre == null)
				superHeroRepository.findAll().forEach(superHeros::add);
			else
				superHeroRepository.findByNameContaining(nombre).forEach(superHeros::add);
			if (superHeros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(superHeros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/superseros/{id}")
	public ResponseEntity<SuperHero> getSuperHeroById(@PathVariable("id") long id) {
		Optional<SuperHero> superHeroData = superHeroRepository.findById(id);
		if (superHeroData.isPresent()) {
			return new ResponseEntity<>(superHeroData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/superheros")
	public ResponseEntity<SuperHero> createSuperHero(@RequestBody SuperHero superHero) {
		try {

			SuperHero _superHero = superHeroRepository.save(superHero);
			return new ResponseEntity<>(_superHero, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/superheros/{id}")
	public ResponseEntity<SuperHero> updateSuperHero(@PathVariable("id") long id, @RequestBody SuperHero superHero) {
		Optional<SuperHero> superHeroData = superHeroRepository.findById(id);
		if (superHeroData.isPresent()) {
			SuperHero _superHero = superHeroData.get();
			_superHero.setNombre(superHero.getNombre());
			_superHero.setPoder(superHero.getPoder());
			return new ResponseEntity<>(superHeroRepository.save(_superHero), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/superheros/{id}")
	public ResponseEntity<HttpStatus> deleteSuperHero(@PathVariable("id") long id) {
		try {
			superHeroRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/superheros")
	public ResponseEntity<HttpStatus> deleteAllSuperHeros() {
		try {
			superHeroRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
