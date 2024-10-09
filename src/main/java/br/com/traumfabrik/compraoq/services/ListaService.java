package br.com.traumfabrik.compraoq.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.traumfabrik.compraoq.dto.ListaDto;
import br.com.traumfabrik.compraoq.entities.Lista;
import br.com.traumfabrik.compraoq.infra.Retornos;
import br.com.traumfabrik.compraoq.repositories.ListaRepository;
import jakarta.validation.Valid;

@Service
public class ListaService {
	
	@Autowired
	private ListaRepository listaRepository;
	
	public Retornos<Lista> findAll() {
		Retornos<Lista> retorno = new Retornos<Lista>();
		List<Lista> lista = listaRepository.findAll();
		retorno.setObjetos(lista);
		retorno.setRetorno("Itens da lista de compras");
		return retorno;
	}

	public Retornos<Lista> save(@Valid ListaDto lista) {
		Retornos<Lista> retorno =  new Retornos<Lista>();
		Lista item 				= listaRepository.save(lista.toLista());
		retorno.getObjetos().add(item);
		retorno.setRetorno("Item salvo com sucesso");
		return retorno;
	}

	public void delete(Long id) {
		listaRepository.deleteById(id);
	}
	
	public Optional<Lista> findById(Long id) {
		return listaRepository.findById(id);
	}

	public Retornos<Lista> update(Long id, @Valid ListaDto listaDto) {
		Lista lista = listaRepository.findById(id)
								     .orElseThrow(()->new RuntimeException("Item não encontrado"));
		lista.setDescricao(listaDto.descricao());
		lista.setLocal(listaDto.local());
		Retornos<Lista> retorno = new Retornos<Lista>();
		retorno.setRetorno("item atualizado");
		retorno.getObjetos().add(listaRepository.save(lista));
		return retorno;
	}
}
