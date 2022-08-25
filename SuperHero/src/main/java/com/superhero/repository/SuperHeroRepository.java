package com.superhero.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.superhero.model.SuperHero;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
	List<SuperHero> findByNameContaining(String nombre);
}
