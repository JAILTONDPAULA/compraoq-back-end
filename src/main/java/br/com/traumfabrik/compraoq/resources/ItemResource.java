package br.com.traumfabrik.compraoq.resources;

import br.com.traumfabrik.compraoq.component.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.traumfabrik.compraoq.dto.ItemDto;
import br.com.traumfabrik.compraoq.entities.Item;
import br.com.traumfabrik.compraoq.services.ItemService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemResource {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping(
			value="/v1",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	public ResponseEntity<List<Item>> findAll() {
		return ResponseEntity.ok(itemService.findAll());
	}

	@GetMapping(
			value="/v1/lista/{local}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	public ResponseEntity<List<Item>> findByList(@PathVariable Integer local) {
		return ResponseEntity.ok(itemService.findByList(local));
	}

	@GetMapping(
			value="/v1/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Item> findById(@PathVariable Long id) {
		return ResponseEntity.ok(itemService.findById(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Retorno<Item>> save(@RequestBody @Valid ItemDto itemDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemDto));
	}
	
	@DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		itemService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
	}
	
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> update(@PathVariable Long id,@RequestBody @Valid ItemDto itemDto) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.update(id,itemDto));
	}

	@PutMapping(
			value = "/v1/atualizar/{id}/{pendente}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	public ResponseEntity<Item> updateItem(@PathVariable Long id,@PathVariable Integer pendente) {
		return ResponseEntity.ok(itemService.updateItem(id,pendente));
	}

}
