package com.leandrobaroni2103.cursomc.resourses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leandrobaroni2103.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResourse {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		System.out.println(123);
		Categoria cat1 = new Categoria(1, "Informatica");
		System.out.println(324);
		Categoria cat2 = new Categoria(2, "Escritorio");
		List<Categoria> lista = new ArrayList<>();
		System.out.println(546);
		lista.add(cat1);
		lista.add(cat2);
		return lista;
	}
}