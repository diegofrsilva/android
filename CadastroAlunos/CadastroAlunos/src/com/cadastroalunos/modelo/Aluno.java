package com.cadastroalunos.modelo;

import java.io.Serializable;

public class Aluno implements Serializable {

	private static final long serialVersionUID = -1597508272998112900L;

	private Long id;
	private String nome;
	private String telefone;
	private String endereco;
	private String site;
	private String foto;
	private double nota;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return nome;
	}

	public String getSiteFormatado() {
		if (site.startsWith("http:")) {
			return site;
        } else {
            return "http:" + site;
        }
	}
}
