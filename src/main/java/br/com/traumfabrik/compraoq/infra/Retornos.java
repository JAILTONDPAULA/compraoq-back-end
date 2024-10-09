package br.com.traumfabrik.compraoq.infra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Retornos<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private Boolean sucess;
	private String  retorno;
	private List<T> objetos = new ArrayList<>();
	
	public Retornos() {
		this.sucess = true;
	}

}
