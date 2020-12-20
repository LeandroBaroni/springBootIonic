package com.leandrobaroni2103.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leandrobaroni2103.cursomc.domain.ItemPedido;
import com.leandrobaroni2103.cursomc.domain.PagamentoComBoleto;
import com.leandrobaroni2103.cursomc.domain.Pedido;
import com.leandrobaroni2103.cursomc.domain.enums.EstadoPagamento;
import com.leandrobaroni2103.cursomc.repositories.ItemPedidoRepository;
import com.leandrobaroni2103.cursomc.repositories.PagamentoRepository;
import com.leandrobaroni2103.cursomc.repositories.PedidoRepository;
import com.leandrobaroni2103.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		/*tem dois modos de fazer a exception
		 * if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado!  ID: " + id + 
					", Tipo: " + Categoria.class.getName());
		}*/
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!  ID: " + id + ", Tipo: " + Pedido.class.getName()));
		//return obj;
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setPagamento(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.buscar(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}