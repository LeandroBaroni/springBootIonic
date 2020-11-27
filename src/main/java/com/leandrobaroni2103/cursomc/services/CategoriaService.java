package com.leandrobaroni2103.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrobaroni2103.cursomc.domain.Categoria;
import com.leandrobaroni2103.cursomc.repositories.CategoriaRepository;
import com.leandrobaroni2103.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		/*if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado!  ID: " + id + 
					", Tipo: " + Categoria.class.getName());
		}*/
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!  ID: " + id + ", Tipo: " + Categoria.class.getName()));
		//return obj;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
}