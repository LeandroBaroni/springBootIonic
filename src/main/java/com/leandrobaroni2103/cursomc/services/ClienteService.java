package com.leandrobaroni2103.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrobaroni2103.cursomc.domain.Categoria;
import com.leandrobaroni2103.cursomc.domain.Cliente;
import com.leandrobaroni2103.cursomc.repositories.ClienteRepository;
import com.leandrobaroni2103.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		/*if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado!  ID: " + id + 
					", Tipo: " + Categoria.class.getName());
		}*/
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!  ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}