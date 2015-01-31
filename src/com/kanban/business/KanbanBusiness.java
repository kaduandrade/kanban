package com.kanban.business;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.redmine.ta.AuthenticationException;
import org.redmine.ta.RedmineException;
import com.kanban.connection.RedmineAPI;
import com.kanban.dao.KanbanDAO;
import com.kanban.model.FaseVO;
import com.kanban.model.GenericoVO;
import com.kanban.model.ProjetoVO;
import com.kanban.model.TicketVO;
import com.kanban.model.Usuario;
import com.kanban.util.PropertiesHandler;

public final class KanbanBusiness {
	
	private static KanbanBusiness instance = null;
	
	protected KanbanBusiness() {}
	
	public static KanbanBusiness getInstance() {
		
		if(instance == null) {
			
			instance = new KanbanBusiness();
			
		}
		
		return instance;
		
	}
	
	public List<ProjetoVO> listaProjetosPublico() throws Exception {
		
		KanbanDAO dao = KanbanDAO.getInstance();
		
		Properties prop = PropertiesHandler.getAppProperties();
		String fases = prop.getProperty("fases");
		
		return dao.listaProjetosPublico(fases);
		
	}
	
	public List<ProjetoVO> listaProjetos(Usuario usuario) throws Exception {
		
		KanbanDAO dao = KanbanDAO.getInstance();
		
		Properties prop = PropertiesHandler.getAppProperties();
		String fases = prop.getProperty("fases");
		
		return dao.listaProjetos(usuario, fases);
		
	}
	
	public List<FaseVO> listaFases() throws Exception {
		
		Properties prop = PropertiesHandler.getAppProperties();
		String fases = prop.getProperty("fases");
    	
		KanbanDAO dao = KanbanDAO.getInstance();
    	return dao.listaFases(fases);
		
	}
	
	public ProjetoVO montaKanban(String idProjeto, String idVersao) throws Exception {
		
		KanbanDAO dao = KanbanDAO.getInstance();
		return dao.listaKanban(idProjeto, idVersao);
		
	}
	
	public List<ProjetoVO> montaKanbanPublico(String[] projetos) throws Exception {
		
		KanbanDAO dao = KanbanDAO.getInstance();
		List<ProjetoVO> lista = new ArrayList<ProjetoVO>();
		
		for (int i = 0; i < projetos.length; i++) {
			
			if (!projetos[i].isEmpty()) {
			
				String[] tmp = projetos[i].split(":");
				ProjetoVO vo = dao.listaKanban(tmp[0], tmp[1]);
				lista.add(vo);
			
			}
			
		}
		
		return lista;
		
	}
	
	public List<GenericoVO> listaAtribuirA(String idProjeto) throws Exception {
		
		KanbanDAO dao = KanbanDAO.getInstance();
		return dao.listaAtribuirA(idProjeto);
		
	}
	
	public void alteraFase(String idUsuarioAtribuido, String note, String idTicket, String idFase, Usuario usuarioLogado) throws Exception {
		
		RedmineAPI api = RedmineAPI.getInstance();
		api.tryUpdateIssue(idUsuarioAtribuido, note, idTicket, idFase, usuarioLogado);
				
	}
	
	public List<TicketVO> refreshFase(String idProjeto, String idVersao, String idFase) throws Exception {
		
		KanbanDAO dao = KanbanDAO.getInstance();
		return dao.refreshFase(idProjeto, idVersao, idFase);
		
	}
	
	private static final String url = "http://www.gravatar.com/avatar/";
	
	public String urlAvatar(String email) {
		
		String resultado = "";
		String tamanho = "?s=40";
		
		String emailHashed = stringHexa(hash(email));
		resultado = url+emailHashed+tamanho;			
		
		return resultado;
		
	}
	
	private byte[] hash(String email) {
		
		byte[] hash = null;
		try {
			
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			md.update(email.getBytes());
			hash = md.digest();
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
		}
		
		return hash;
		
	}
	
	private static String stringHexa(byte[] bytes) {
		
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < bytes.length; i++) {
			
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0) s.append('0');
			s.append(Integer.toHexString(parteAlta | parteBaixa));
			
		}
		
		return s.toString();
		
	}
	
	public Usuario autorizaUsuario(String login, String password) throws IOException, AuthenticationException, RedmineException {
		
		RedmineAPI api = RedmineAPI.getInstance();
		Usuario usuario = null;
		usuario = api.AutorizaUsuario(login, password);
			
		return usuario;
		
	}
	
	@SuppressWarnings("unused")
	private boolean verificaSenha(String senhaRedmine, String senha) {
		
		boolean result = false;		
		MessageDigest md;
		MessageDigest md2;
		String strRedmine = "";
		String strSenha = "";
		
		try {
			
			md = MessageDigest.getInstance("MD5");
			md.update(senhaRedmine.getBytes());
			byte[] hashRedmine = md.digest();
			strRedmine = stringHexa(hashRedmine);
			
			md2 = MessageDigest.getInstance("SHA1");
			md2.update(senha.getBytes());
			byte[] hashSenha = md2.digest();
			strSenha = stringHexa(hashSenha);
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
		}
		
		if (strRedmine.equals(strSenha)) {
			
			result = true;
			
		}
		
		return result;
		
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		//MessageDigest md;
		//md = MessageDigest.getInstance("MD5");
		//md.update("66e1945aa25790f5da2edf713ec63ac91c5621ba".getBytes());
		//byte[] hash = md.digest();
		//66e1945aa25790f5da2edf713ec63ac91c5621ba
		//12237bcf3d32f3889f21f4cc5bdd9d57
		//12237bcf3d32f3889f21f4cc5bdd9d57
		//String emailHashed = stringHexa(hash);
		//System.out.println(emailHashed);
		
		//MessageDigest md2;
		//md2 = MessageDigest.getInstance("SHA1");
		//md2.update("caedanq3w2e1".getBytes());
		//byte[] hash2 = md2.digest();
		//66e1945aa25790f5da2edf713ec63ac91c5621ba
		//12237bcf3d32f3889f21f4cc5bdd9d57
		//12237bcf3d32f3889f21f4cc5bdd9d57
		//String emailHashed2 = stringHexa(hash2);
		//System.out.println(emailHashed2);
		
		String[] tmp = {"19||123", "123||12"};
		
		for (int i = 0; i < tmp.length; i++) {
			
			String[] a = tmp[i].split("||");
			for (int j = 0; j < a.length; j++) {
				
				System.out.println(a[i]);
				
			}
			
		}
		
		
	}
	
}