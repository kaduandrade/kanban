package com.kanban.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.redmine.ta.beans.Version;
import org.utopia.efreet.DAOException;
import org.utopia.efreet.DAOFactory;
import org.utopia.efreet.DataAccessObject;
import org.utopia.efreet.EfreetException;
import org.utopia.efreet.QueryParameter;
import org.utopia.efreet.QueryResult;
import com.kanban.business.KanbanBusiness;
import com.kanban.model.FaseVO;
import com.kanban.model.GenericoVO;
import com.kanban.model.ProjetoVO;
import com.kanban.model.TicketVO;
import com.kanban.model.Usuario;
import com.kanban.util.Util;

public final class KanbanDAO {
	
	private static KanbanDAO instance = null;
	
	protected KanbanDAO() {}
	
	public static KanbanDAO getInstance() {
		
		if(instance == null) {
			
			instance = new KanbanDAO();
			
		}
		
		return instance;
		
	}
	
	public List<ProjetoVO> listaProjetosPublico(String fases) throws Exception {
    	
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
		Collection<QueryResult> qrLista = dao.executeQuery("qryListaProjetosPublico");
		List<ProjetoVO> lista = null;
	
		if (qrLista != null && qrLista.size() > 0) {
			
			lista = new ArrayList<ProjetoVO>();			
			Iterator<QueryResult> it = qrLista.iterator();
			
			while (it.hasNext()) {
				
				QueryResult qr = it.next();
				
				List<Version> versoes = listaVersoes(qr.getInt("id"), fases);
				
				if (versoes != null && !versoes.isEmpty()) {
				
					ProjetoVO vo = new ProjetoVO();
					vo.setId(qr.getInt("id"));
					vo.setName(qr.getString("name"));
					vo.setVersoes(versoes);
					
					lista.add(vo);
				
				}
				
			}
			
		}
		
		return lista;
		
	}

    public List<ProjetoVO> listaProjetos(Usuario usuario, String fases) throws Exception {
    	
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
	
		QueryParameter param = new QueryParameter();
		param.put("id", String.valueOf(usuario.getId()));
	
		Collection<QueryResult> qrLista = dao.executeQuery("qryListaProjetos", param);
		
		List<ProjetoVO> lista = null;
	
		if (qrLista != null && qrLista.size() > 0) {
			
			lista = new ArrayList<ProjetoVO>();			
			Iterator<QueryResult> it = qrLista.iterator();
			
			while (it.hasNext()) {
				
				ProjetoVO vo = new ProjetoVO();
				QueryResult qr = it.next();
				vo.setId(qr.getInt("id"));
				vo.setName(qr.getString("name"));
				vo.setVersoes(listaVersoes(qr.getInt("id"), fases));
				
				lista.add(vo);
				
			}
			
		}
		
		return lista;
		
	}
    
    private List<Version> listaVersoes(int idProjeto, String fases) throws Exception {
    	
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
	
		QueryParameter param = new QueryParameter();
		param.put("idProjeto", idProjeto);
		param.setVariable("fasesIN", fases);
	
		Collection<QueryResult> qrLista = dao.executeQuery("qryListaVersoes", param);
		
		List<Version> lista = null;
	
		if (qrLista != null && qrLista.size() > 0) {
			
			lista = new ArrayList<Version>();			
			Iterator<QueryResult> it = qrLista.iterator();
			
			while (it.hasNext()) {
				
				QueryResult qr = it.next();
				
				if (Integer.parseInt(qr.getString("count")) > 0) {
					
					Version vo = new Version();
					vo.setId(qr.getInt("id"));
					vo.setName(qr.getString("name"));
					vo.setDescription(qr.getString("description"));
					
					lista.add(vo);
					
				}
				
			}
			
		}
		
		return lista;
		
	}
    
    public List<FaseVO> listaFases(String fases) throws Exception {
    	
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
	
		QueryParameter param = new QueryParameter();
		param.setVariable("fasesIN", fases);
	
		Collection<QueryResult> qrLista = dao.executeQuery("qryListaFases", param);
		
		List<FaseVO> lista = null;
	
		if (qrLista != null && qrLista.size() > 0) {
			
			lista = new ArrayList<FaseVO>();			
			Iterator<QueryResult> it = qrLista.iterator();
			
			while (it.hasNext()) {
				
				FaseVO vo = new FaseVO();
				QueryResult qr = it.next();
				vo.setId(qr.getInt("id"));
				vo.setDescricao(qr.getString("name"));
				vo.setPosition(qr.getInt("position"));
				
				lista.add(vo);
				
			}
			
		}
		
		return lista;
		
	}
    
	public ProjetoVO listaKanban(String idProjeto, String idVersao) throws Exception {
	
		KanbanBusiness business = KanbanBusiness.getInstance();
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
	
		QueryParameter param = new QueryParameter();
		param.put("idProjeto", idProjeto);
		param.put("idVersao", idVersao);
	
		String query = "qryListaKanban";
		dao.appendConditionalToQuery(query, "qryListaKanban_ORDER_BY");
	
		Collection<QueryResult> qrLista = dao.executeQuery(query, param);
		
		ProjetoVO result = new ProjetoVO();
		List<TicketVO> lista = null;
	
		if (qrLista != null && qrLista.size() > 0) {
			
			lista = new ArrayList<TicketVO>();			
			Iterator<QueryResult> it = qrLista.iterator();
			
			while (it.hasNext()) {
				
				TicketVO vo = new TicketVO();
				QueryResult qr = it.next();
				vo.setTicket(qr.getInt("id"));
				vo.setIndiceReteste(calculaReteste(qr.getInt("id")));
				vo.setTitulo(qr.getString("subject"));
				
				String name = qr.getString("name")+" ";
				name = name.substring(0, 2).trim();
				Util util = new Util();
				if ( util.ehInteiro(name) ) {
					
					vo.setIdCriticidade(10 - Integer.parseInt(name));
					
				} else {
					
					vo.setIdCriticidade(999);
					
				}
				
				vo.setDescricaoCriticidade(qr.getString("name"));
				
				vo.setIdFase(qr.getInt("id_2"));
				vo.setDescricaoFase(qr.getString("name_1"));
				vo.setEmail(qr.getString("mail"));
				vo.setTipo(qr.getString("name_2"));
				vo.setUrlAvatar(business.urlAvatar(qr.getString("mail")));
				vo.setAtribuidoA(qr.getString("atribuidoA"));
				vo.setAutor(qr.getString("author"));
				vo.setUrlAvatarAutor(business.urlAvatar(qr.getString("mail_1")));
				
				result.setNomeProjeto(qr.getString("name_3"));
				result.setNomeVersao(qr.getString("name_4"));
				result.setDescription(qr.getString("description"));
				
				lista.add(vo);
								
			}
			
		}

		result.setTickets(lista);
		
		return result;
		
	}
	
	private int calculaReteste(int idTicket) throws Exception {
		
		int result = 0;
		
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
		String query = "qryCountReteste";
		
		QueryParameter param = new QueryParameter();
		param.put("idTicket", idTicket);
		
		QueryResult qrs = dao.executeQuerySingle(query, param);
		result = Integer.parseInt(qrs.getString("count"));
		
		return result;
		
	}
	
	public List<GenericoVO> listaAtribuirA(String idProjeto) throws Exception {
		
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
		String query = "qryListaAtribuirA";
		
		QueryParameter param = new QueryParameter();
		param.put("idProjeto", idProjeto);
		
		Collection<QueryResult> qrLista = dao.executeQuery(query, param);
		
		List<GenericoVO> lista = null;
	
		if (qrLista != null && qrLista.size() > 0) {
			
			lista = new ArrayList<GenericoVO>();			
			Iterator<QueryResult> it = qrLista.iterator();
			
			while (it.hasNext()) {
				
				GenericoVO vo = new GenericoVO();
				QueryResult qr = it.next();
				vo.setId(qr.getInt("id"));
				vo.setDescricao(qr.getString("atribuirA"));
				vo.setTipo(qr.getString("name"));
				
				lista.add(vo);
				
			}
			
		}
		
		return lista;
		
	}
	
	public void alteraStatus(String idTicket, String idStatus, Usuario usuarioLogado) throws Exception {
    	
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
	
		QueryParameter param = new QueryParameter();
		param.put("idTicket", idTicket);
		param.put("idStatus", idStatus);
	
		dao.executeQuerySingle("updAlteraStatus", param);
		
	}
	
	public List<TicketVO> refreshFase(String idProjeto, String idVersao, String idFase) throws Exception {
		
		KanbanBusiness business = KanbanBusiness.getInstance();
		DataAccessObject dao = DAOFactory.createDAO("efreet-kanban");
		
		QueryParameter param = new QueryParameter();
		param.put("idProjeto", idProjeto);
		param.put("idVersao", idVersao);
		param.put("idFase", idFase);
		
		String query = "qryListaKanban";
		dao.appendConditionalToQuery(query, "qryListaKanban_filtroFase");
		dao.appendConditionalToQuery(query, "qryListaKanban_ORDER_BY");
	
		Collection<QueryResult> qrLista = dao.executeQuery(query, param);
		
		List<TicketVO> lista = null;
	
		if (qrLista != null && qrLista.size() > 0) {
			
			lista = new ArrayList<TicketVO>();			
			Iterator<QueryResult> it = qrLista.iterator();
			
			while (it.hasNext()) {
				
				TicketVO vo = new TicketVO();
				QueryResult qr = it.next();
				vo.setTicket(qr.getInt("id"));
				vo.setTitulo(qr.getString("subject"));	
				
				String name = qr.getString("name")+" ";
				name = name.substring(0, 2).trim();
				Util util = new Util();
				if ( util.ehInteiro(name) ) {
					
					vo.setIdCriticidade(10 - Integer.parseInt(name));
					
				} else {
					
					vo.setIdCriticidade(999);
					
				}
				
				vo.setDescricaoCriticidade(qr.getString("name"));
				
				vo.setIdFase(qr.getInt("id_2"));
				vo.setDescricaoFase(qr.getString("name_1"));
				vo.setEmail(qr.getString("mail"));
				vo.setTipo(qr.getString("name_2"));
				vo.setUrlAvatar(business.urlAvatar(qr.getString("mail")));
				
				lista.add(vo);
				
			}
			
		}
		
		return lista;
		
	}
	
	public Usuario buscaUsuario(String login) {
		
		DataAccessObject dao;
		Usuario usuario = null;
		
		try {
			
			dao = DAOFactory.createDAO("efreet-kanban");
			QueryParameter param = new QueryParameter();
			param.put("login", login);		
			
			QueryResult qr = dao.executeQuerySingle("qryBuscaUsuario", param);
			
			if (qr != null) {
				
			usuario = new Usuario();
			usuario.setId(qr.getInt("id"));
			usuario.setLogin(qr.getString("login"));
			usuario.setHashedPassword(qr.getString("hashed_password"));
			usuario.setFirstName(qr.getString("firstname"));
			usuario.setLastName(qr.getString("lastname"));
			usuario.setAdmin(qr.getString("admin"));
			usuario.setStatus(qr.getInt("status"));
			usuario.setLastLoginOn(qr.getTimestamp("last_login_on"));
			usuario.setLanguage(qr.getString("language"));
			usuario.setAuthSourceId(qr.getInt("auth_source_id"));
			usuario.setCreatedOn(qr.getTimestamp("created_on"));
			usuario.setUpdatedOn(qr.getTimestamp("updated_on"));
			usuario.setType(qr.getString("type"));
			usuario.setIdentityUrl(qr.getString("identity_url"));
			usuario.setMailNotification(qr.getString("mail_notification"));
			
			}
			
		} catch (EfreetException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (DAOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return usuario;
		
	}
	 
}