package com.joaoabrodrigues.model.dto;

import java.io.Serializable;

public class Destino implements Serializable {
	
	private static final long serialVersionUID = -2177180849020702001L;
	
	private String local;
	
	private String codigo;
	
	private String cidade;
	
	private String bairro;
	
	private String uf;
	
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
}
