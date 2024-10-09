package br.com.traumfabrik.compraoq.entities;

import java.io.Serializable;

import br.com.traumfabrik.compraoq.dto.ListaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="lista",schema="compraoq")
public class Lista implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    id;
	private String  descricao;
	private Integer local;
	
	public Lista(ListaDto listaDto) {
		this.descricao = listaDto.descricao();
		this.local	   = listaDto.local();
	}

}
