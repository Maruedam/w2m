package com.superhero.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "superheros")
public class SuperHero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "poder")
	private String poder;

	public SuperHero() {
		super();
	}

	public SuperHero(long id, String nombre, String poder) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.poder = poder;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPoder() {
		return poder;
	}

	public void setPoder(String poder) {
		this.poder = poder;
	}

	@Override
	public String toString() {
		return "SuperHero [id=" + id + ", nombre=" + nombre + ", poder=" + poder + "]";
	}

}
