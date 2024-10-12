package br.com.traumfabrik.compraoq.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.traumfabrik.compraoq.infra.Retornos;
import br.com.traumfabrik.compraoq.services.ItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens")
public class ItemResource {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public ResponseEntity<Retornos<Item>> findAll() {
		return ResponseEntity.ok(itemService.findAll());
	}

	@GetMapping("/lista/{local}")
	public ResponseEntity<Retornos<Item>> findByList(@PathVariable Integer local) {
		return ResponseEntity.ok(itemService.findByList(local));
	}
	
	@PostMapping
	public ResponseEntity<Retornos<Item>> save(@RequestBody @Valid ItemDto itemDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(!itemService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		itemService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Retornos<Item>> update(@PathVariable Long id,@RequestBody @Valid ItemDto itemDto) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.update(id,itemDto));
	}
}
