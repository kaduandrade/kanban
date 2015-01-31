package com.kanban.action;

import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.kanban.business.KanbanBusiness;
import com.kanban.model.FaseVO;
import com.kanban.model.GenericoVO;
import com.kanban.model.ProjetoVO;
import com.kanban.model.TicketVO;
import com.kanban.model.Usuario;
import com.opensymphony.xwork2.ActionContext;

@ParentPackage("default")
public class KanbanAction {
	
	public ProjetoVO listaKanban;
	public List<ProjetoVO> listaKanbans;
	public List<TicketVO> lista;
	public List<FaseVO> listaFases;
	public List<ProjetoVO> listaProjetos;
	public List<GenericoVO> listaAtribuirA;
	
	public String idProjeto;
	public String idVersao;
	public String nomeVersao;
	public String idTicket;
	public String idFase;
	public String idOrigem;
	public String idDestino;
	public String note;
	public String idUsuarioAtribuido;
	public String[] idProjetos;
	public String[] idVersoes;
	
	public int tempoRefresh;

	Usuario usuarioLogado = (Usuario)ActionContext.getContext().getSession().get("usuarioLogado");
	
	@Action(
		value="listaProjetos",
		results= { @Result(name="ok", location="/kanbanProjetos.jsp") }
	)
	public String listaProjetos() throws Exception {
		
		KanbanBusiness business = KanbanBusiness.getInstance();
		listaProjetos = business.listaProjetos(usuarioLogado);
		
		return "ok";
		
	}
	
	@Action(
		value="montaKanban",
		results= {@Result(name="ok", location="/kanban.jsp")},
		interceptorRefs= { @InterceptorRef("seguranca")}
	)
	public String montaKanban() throws Exception {
		
		KanbanBusiness business = KanbanBusiness.getInstance();
		Map<String, Object> parametros = ActionContext.getContext().getSession();
		
		String projeto;
		String versao;
		String nameVersao;
		
		if (idProjeto == null || idVersao == null || nomeVersao == null) {
			
			projeto = (String)parametros.get("idProjeto");
			versao = (String)parametros.get("idVersao");
			nameVersao = (String)parametros.get("nomeVersao");
			
		} else {
			
			projeto = idProjeto;
			versao = idVersao;
			nameVersao = nomeVersao;
			
		}
		
		parametros.put("idProjeto", projeto);
		parametros.put("idVersao", versao);
		parametros.put("nomeVersao", nameVersao);
		listaKanban = business.montaKanban(projeto, versao);
		listaFases = business.listaFases();
		listaAtribuirA = business.listaAtribuirA(projeto);
		
		return "ok";
		
	}
	
	@Action(
		value="listaProjetosPublico",
		results= { @Result(name="ok", location="/kanbanProjetosPublico.jsp") }
	)
	public String listaProjetosPublico() throws Exception {
		
		KanbanBusiness business = KanbanBusiness.getInstance();
		listaProjetos = business.listaProjetosPublico();
		
		return "ok";
		
	}
	
	@Action(
		value="montaKanbanPublico",
		results= { @Result(name="ok", location="/kanbanPublico.jsp") }
	)
	public String montaKanbanPublico() throws Exception {
		
		KanbanBusiness business = KanbanBusiness.getInstance();
		Map<String, Object> parametros = ActionContext.getContext().getSession();
		
		String[] projetos;
		
		if (idProjetos == null) {
			
			projetos = (String[])parametros.get("idProjetos");
			
		} else {
			
			projetos = idProjetos;
			
		}
		
		parametros.put("idProjetos", projetos);
		listaKanbans = business.montaKanbanPublico(projetos);
		listaFases = business.listaFases();
		tempoRefresh = listaKanbans.size() * 29;
		
		return "ok";
		
	}
	
	@Action(
		value="alteraFase",
		interceptorRefs= { @InterceptorRef("seguranca")}
	)
	public void alteraFase() throws Exception {
		
		KanbanBusiness business = KanbanBusiness.getInstance();
		business.alteraFase(idUsuarioAtribuido, note, idTicket, idDestino, usuarioLogado);
		
	}
	
	@Action(
		value="refreshFase",
		results= { @Result(name="ok", location="/refreshFase.jsp") },
		interceptorRefs= { @InterceptorRef("seguranca") }
	)
	public synchronized String refreshFase() throws Exception {
		
		KanbanBusiness business = KanbanBusiness.getInstance();
		Map<String, Object> parametros = ActionContext.getContext().getSession();
		
		String projeto;
		String versao;
		
		if (idProjeto == null || idVersao == null) {
			
			projeto = (String)parametros.get("idProjeto");
			versao = (String)parametros.get("idVersao");
			
			if (projeto == null || versao == null) {
				
				parametros.put("idProjeto", projeto);
				parametros.put("idVersao", versao);
				
			}
			
		} else {
			
			projeto = idProjeto;
			versao = idVersao;
			
		}

		lista = business.refreshFase(projeto, versao, idFase);
		listaKanban = business.montaKanban(projeto, versao);
		
		return "ok";
		
	}
	
	// Getters e Setters
	
	public int getTempoRefresh() {
	
		return tempoRefresh;
	}
	
	public void setTempoRefresh(int tempoRefresh) {
	
		this.tempoRefresh = tempoRefresh;
	}

	public List<ProjetoVO> getListaKanbans() {
	
		return listaKanbans;
	}

	
	public void setListaKanbans(List<ProjetoVO> listaKanbans) {
	
		this.listaKanbans = listaKanbans;
	}

	public String[] getIdProjetos() {
	
		return idProjetos;
	}

	
	public void setIdProjetos(String[] idProjetos) {
	
		this.idProjetos = idProjetos;
	}

	
	public String[] getIdVersoes() {
	
		return idVersoes;
	}

	
	public void setIdVersoes(String[] idVersoes) {
	
		this.idVersoes = idVersoes;
	}

	public String getIdUsuarioAtribuido() {
		
		return idUsuarioAtribuido;
	}

	
	public void setIdUsuarioAtribuido(String idUsuarioAtribuido) {
	
		this.idUsuarioAtribuido = idUsuarioAtribuido;
	}
	
	public ProjetoVO getListaKanban() {
		
		return listaKanban;
	}
	
	public void setListaKanban(ProjetoVO listaKanban) {
		
		this.listaKanban = listaKanban;
	}

	public List<ProjetoVO> getListaProjetos() {
		
		return listaProjetos;
	}

	
	public void setListaProjetos(List<ProjetoVO> listaProjetos) {
	
		this.listaProjetos = listaProjetos;
	}

	
	public Usuario getUsuarioLogado() {
	
		return usuarioLogado;
	}

	
	public void setUsuarioLogado(Usuario usuarioLogado) {
	
		this.usuarioLogado = usuarioLogado;
	}
	
	public String getIdProjeto() {
		
		return idProjeto;
	}

	
	public void setIdProjeto(String idProjeto) {
	
		this.idProjeto = idProjeto;
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
	
	public String getIdTicket() {
		
		return idTicket;
	}

	
	public void setIdTicket(String idTicket) {
	
		this.idTicket = idTicket;
	}

	
	public String getIdFase() {
	
		return idFase;
	}

	
	public void setIdFase(String idFase) {
	
		this.idFase = idFase;
	}
	
	public List<FaseVO> getListaFases() {
	
		return listaFases;
	}

	
	public void setListaFases(List<FaseVO> listaFases) {
	
		this.listaFases = listaFases;
	}
	
	public List<TicketVO> getLista() {
		
		return lista;
	}

	
	public void setLista(List<TicketVO> lista) {
	
		this.lista = lista;
	}

	
	public String getIdOrigem() {
	
		return idOrigem;
	}

	
	public void setIdOrigem(String idOrigem) {
	
		this.idOrigem = idOrigem;
	}

	
	public String getIdDestino() {
	
		return idDestino;
	}

	
	public void setIdDestino(String idDestino) {
	
		this.idDestino = idDestino;
	}

	
	public String getNote() {
	
		return note;
	}

	
	public void setNote(String note) {
	
		this.note = note;
	}

	
	public List<GenericoVO> getListaAtribuirA() {
	
		return listaAtribuirA;
	}

	
	public void setListaAtribuirA(List<GenericoVO> listaAtribuirA) {
	
		this.listaAtribuirA = listaAtribuirA;
	}
	
}
