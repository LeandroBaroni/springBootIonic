package com.leandrobaroni2103.cursomc.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leandrobaroni2103.cursomc.domain.Pedido;
import com.leandrobaroni2103.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResourse {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}