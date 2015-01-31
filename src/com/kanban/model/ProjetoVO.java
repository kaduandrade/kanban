package com.kanban.model;

import java.util.List;
import org.redmine.ta.beans.Project;
import org.redmine.ta.beans.Version;

public class ProjetoVO extends Project {
	
	private static final long serialVersionUID = 1L;

	private String idProjeto;
	private String nomeProjeto;
	private String idVersao;
	private String nomeVersao;
	List<TicketVO> tickets;
	List<Version> versoes;
	
	public String getIdProjeto() {
	
		return idProjeto;
	}

	
	public void setIdProjeto(String idProjeto) {
	
		this.idProjeto = idProjeto;
	}

	
	public String getNomeProjeto() {
	
		return nomeProjeto;
	}

	
	public void setNomeProjeto(String nomeProjeto) {
	
		this.nomeProjeto = nomeProjeto;
	}

	
	public String getIdVersao() {
	
		return idVersao;
	}

	
	public void setIdVersao(String idVersao) {
	
		this.idVersao = idVersao;
	}

	
	public String getNomeVersao() {
	
		return nomeVersao;
	}

	
	public void setNomeVersao(String nomeVersao) {
	
		this.nomeVersao = nomeVersao;
	}

	public List<TicketVO> getTickets() {
	
		return tickets;
	}
	
	public void setTickets(List<TicketVO> tickets) {
	
		this.tickets = tickets;
	}

	public List<Version> getVersoes() {
	
		return versoes;
	}
	
	public void setVersoes(List<Version> versoes) {
	
		this.versoes = versoes;
	}
	
}
