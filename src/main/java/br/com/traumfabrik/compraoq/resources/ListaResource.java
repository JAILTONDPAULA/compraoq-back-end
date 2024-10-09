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

import br.com.traumfabrik.compraoq.dto.ListaDto;
import br.com.traumfabrik.compraoq.entities.Lista;
import br.com.traumfabrik.compraoq.infra.Retornos;
import br.com.traumfabrik.compraoq.services.ListaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/listas")
public class ListaResource {
	
	@Autowired
	private ListaService listaService;
	
	@GetMapping
	public ResponseEntity<Retornos<Lista>> findAll() {
		return ResponseEntity.ok(listaService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Retornos<Lista>> save(@RequestBody @Valid ListaDto listaDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(listaService.save(listaDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(!listaService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		listaService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Retornos<Lista>> update(@PathVariable Long id,@RequestBody @Valid ListaDto listaDto) {
		return ResponseEntity.status(HttpStatus.OK).body(listaService.update(id,listaDto));
	}
}
