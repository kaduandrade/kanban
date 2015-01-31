package com.kanban.model;

import java.io.Serializable;

public class TicketVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	int ticket;
	String versao;
	String titulo;
	int idCriticidade;
	String descricaoCriticidade;
	int idFase;
	String descricaoFase;
	String email;
	String urlAvatar;
	String tipo;
	String atribuidoA;
	String autor;
	String urlAvatarAutor;
	String emailAutor;
	int indiceReteste;
	
	
	
	public int getIndiceReteste() {
	
		return indiceReteste;
	}


	
	public void setIndiceReteste(int indiceReteste) {
	
		this.indiceReteste = indiceReteste;
	}


	public String getAtribuidoA() {
	
		return atribuidoA;
	}

	
	public void setAtribuidoA(String atribuidoA) {
	
		this.atribuidoA = atribuidoA;
	}

	public String getTipo() {
	
		return tipo;
	}
	
	public void setTipo(String tipo) {
	
		this.tipo = tipo;
	}

	public int getIdCriticidade() {
	
		return idCriticidade;
	}
	
	public void setIdCriticidade(int idCriticidade) {
	
		this.idCriticidade = idCriticidade;
	}

	
	public int getIdFase() {
	
		return idFase;
	}

	
	public void setIdFase(int idFase) {
	
		this.idFase = idFase;
	}

	
	public String getDescricaoFase() {
	
		return descricaoFase;
	}

	
	public void setDescricaoFase(String descricaoFase) {
	
		this.descricaoFase = descricaoFase;
	}

	public String getVersao() {
		
		return versao;
	}
	
	public void setVersao(String versao) {
		
		this.versao = versao;
	}
	
	public String getUrlAvatar() {
	
		return urlAvatar;
	}
	
	public void setUrlAvatar(String urlAvatar) {
	
		this.urlAvatar = urlAvatar;
	}

	public String getEmail() {
	
		return email;
	}
	
	public void setEmail(String email) {
	
		this.email = email;
	}

	public int getTicket() {
	
		return ticket;
	}
	
	public void setTicket(int ticket) {
	
		this.ticket = ticket;
	}
	
	public String getTitulo() {
	
		return titulo;
	}
	
	public void setTitulo(String titulo) {
	
		this.titulo = titulo;
	}
	
	public String getDescricaoCriticidade() {
	
		return descricaoCriticidade;
	}
	
	public void setDescricaoCriticidade(String descricaoCriticidade) {
	
		this.descricaoCriticidade = descricaoCriticidade;
	}


	
	public String getAutor() {
	
		return autor;
	}


	
	public void setAutor(String autor) {
	
		this.autor = autor;
	}


	
	public String getUrlAvatarAutor() {
	
		return urlAvatarAutor;
	}


	
	public void setUrlAvatarAutor(String urlAvatarAutor) {
	
		this.urlAvatarAutor = urlAvatarAutor;
	}


	
	public String getEmailAutor() {
	
		return emailAutor;
	}


	
	public void setEmailAutor(String emailAutor) {
	
		this.emailAutor = emailAutor;
	}
	
}