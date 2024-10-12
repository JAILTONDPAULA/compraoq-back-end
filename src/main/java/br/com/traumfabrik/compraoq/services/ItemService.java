package br.com.traumfabrik.compraoq.services;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.traumfabrik.compraoq.dto.ItemDto;
import br.com.traumfabrik.compraoq.entities.Item;
import br.com.traumfabrik.compraoq.infra.Retornos;
import br.com.traumfabrik.compraoq.repositories.ItemRepository;
import jakarta.validation.Valid;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private Retornos<Item> retorno;


	
	public Retornos<Item> findAll() {
		List<Item> item = itemRepository.findAll();
		retorno.setObjetos(item);
		retorno.setRetorno("Itens da item de compras");
		return retorno;
	}

	public Retornos<Item> save(@Valid ItemDto itemDto) {
		Item item 			   = itemRepository.save(itemDto.toItem());
		retorno.getObjetos().add(item);
		retorno.setRetorno("Item salvo com sucesso");
		return retorno;
	}

	public void delete(Long id) {
		itemRepository.deleteById(id);
	}
	
	public Optional<Item> findById(Long id) {
		return itemRepository.findById(id);
	}

	public Retornos<Item> update(Long id, @Valid ItemDto itemDto) {
		Item item = itemRepository.findById(id)
								     .orElseThrow(()->new RuntimeException("Item não encontrado"));
		item.setDescricao(itemDto.descricao());
		item.setLocal(itemDto.local());
		retorno.setRetorno("item atualizado");
		retorno.getObjetos().add(itemRepository.save(item));
		return retorno;
	}

    public Retornos<Item> findByList(Integer local) {
		List<Item>     lista   = itemRepository.findByLocal(local);
		retorno.setObjetos(lista);
        retorno.setRetorno("Lista de compras");
		return retorno;
    }
}
