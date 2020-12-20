package com.leandrobaroni2103.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.leandrobaroni2103.cursomc.domain.Categoria;
import com.leandrobaroni2103.cursomc.domain.Pedido;
import com.leandrobaroni2103.cursomc.domain.Produto;
import com.leandrobaroni2103.cursomc.repositories.CategoriaRepository;
import com.leandrobaroni2103.cursomc.repositories.ProdutoRepository;
import com.leandrobaroni2103.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepo;

	public Produto buscar(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		/*tem dois modos de fazer a exception
		 * if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado!  ID: " + id + 
					", Tipo: " + Categoria.class.getName());
		}*/
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!  ID: " + id + ", Tipo: " + Pedido.class.getName()));
		//return obj;
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
