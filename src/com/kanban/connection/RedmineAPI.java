package com.kanban.connection;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.redmine.ta.AuthenticationException;
import org.redmine.ta.RedmineException;
import org.redmine.ta.RedmineManager;
import org.redmine.ta.RedmineManager.INCLUDE;
import org.redmine.ta.beans.Issue;
import org.redmine.ta.beans.User;

import com.kanban.model.Usuario;

public class RedmineAPI {
	
	protected RedmineAPI() {};

	private static String redmineHost = "http://localhost/redmine";
	private static RedmineAPI instance;
	
	public static RedmineAPI getInstance() {
		
		if (instance == null) {
			
			instance = new RedmineAPI();
			
		}
		
		return instance;
		
	}

	public Usuario AutorizaUsuario(String login, String password) throws IOException, AuthenticationException, RedmineException {
		
		Usuario usuario = null;
		RedmineManager mgr = new RedmineManager(redmineHost, login, password);
		usuario = castingUser(mgr.getCurrentUser());
		
		return usuario;
		
	}
	
	private Usuario castingUser(User user) {
		
		Usuario usuario = new Usuario();
		usuario.setCreatedOn(user.getCreatedOn());
		usuario.setFirstName(user.getFirstName());
		usuario.setFullName(user.getFullName());
		usuario.setId(user.getId());
		usuario.setLastLoginOn(user.getLastLoginOn());
		usuario.setLastName(user.getLastName());
		usuario.setLogin(user.getLogin());
		usuario.setMail(user.getMail());
		usuario.setPassword(user.getPassword());
		
		return usuario;
		
	}
	
	
	public static void main(String[] args) {
		
		RedmineManager mgr = new RedmineManager(redmineHost, "candrade", "caedanq3w2e1");
		
		try {
			
			//Issue r = mgr.getIssueById(4446, INCLUDE.relations);
			//r.setStatusId(17);
			//mgr.updateIssue(r);
			//System.out.println("ok");
			
			List<Issue> projects = mgr.getIssues("5", null, INCLUDE.relations);
			for (Iterator<Issue> iterator = projects.iterator(); iterator.hasNext();) {
				Issue issue = (Issue) iterator.next();
				System.out.println(issue.getDescription());
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void tryUpdateIssue(String idUsuarioAtribuido, String note, String idTicket, String idStatus, Usuario usuarioLogado) throws Exception {
		
		RedmineManager mgr = new RedmineManager(redmineHost, usuarioLogado.getLogin(), usuarioLogado.getPassword());
		
		try {
			
			Issue ticket = mgr.getIssueById(Integer.valueOf(idTicket), INCLUDE.relations);
			ticket.setStatusId(Integer.valueOf(idStatus));
			
			if (!"".equals(note)) {
				
				ticket.setNotes(note);
				
			}
			
			if (!"".equals(idUsuarioAtribuido)) {
				
				User user = new User();
				user.setId(Integer.valueOf(idUsuarioAtribuido));
				ticket.setAssignee(user);
				
			}
			
			mgr.updateIssue(ticket);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}