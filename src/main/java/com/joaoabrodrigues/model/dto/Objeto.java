package com.joaoabrodrigues.model.dto;

import java.io.Serializable;
import java.util.List;

public class Objeto implements Serializable {

	private static final long serialVersionUID = 2064417220283931734L;
	
	private String numeroRastreio;
	private String sigla;
	private String nome;
	private String categoria;
	private List<Evento> evento;
	private String erro;

}
