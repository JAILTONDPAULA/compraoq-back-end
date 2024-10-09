package br.com.traumfabrik.compraoq.dto;

import br.com.traumfabrik.compraoq.entities.Lista;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ListaDto(@NotBlank(message = "Informe o nome do item da lista") String descricao, @NotNull(message = "Informe a lista que será adicionado") Integer local) {
	
	public Lista toLista() {
		return new Lista(this);
	}
			
}
