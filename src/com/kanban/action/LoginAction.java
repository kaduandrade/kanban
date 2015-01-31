package com.kanban.action;

import java.io.IOException;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.redmine.ta.AuthenticationException;
import org.redmine.ta.RedmineException;
import com.kanban.business.KanbanBusiness;
import com.kanban.model.Usuario;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String login;
	private String senha;

	@Action(value="login", results= {
		@Result(name="ok", type="redirectAction", params= {"actionName", "listaProjetos"}),
		@Result(name="invalido", location="/index.jsp")
	})
	public String login() {
		
		Usuario usuarioLogado = (Usuario)ActionContext.getContext().getSession().get("usuarioLogado");

		if ( usuarioLogado == null || !usuarioLogado.getLogin().equals(login) || !usuarioLogado.getPassword().equals(senha) ) {
			
			try {
				
				if (login != null && senha != null) {
					
					usuarioLogado = KanbanBusiness.getInstance().autorizaUsuario(login, senha);
					usuarioLogado.setLogin(login);
					usuarioLogado.setPassword(senha);
					ActionContext.getContext().getSession().put("usuarioLogado", usuarioLogado);
				
				} else {
					
					return "invalido";
					
				}
				
			} catch (IOException e) {
				
				addActionError(e.getMessage());
				
			} catch (AuthenticationException e) {
				
				addActionError("Usúario ou senha incorretos!");
				
			} catch (RedmineException e) {
				
				addActionError("Erro ao consultar o Redmine!");
				
			}

			return "invalido";

		}
			
		return "ok";
		
	}
	
	public String getLogin() {
		
		return login;
	}
	
	public void setLogin(String login) {
	
		this.login = login;
	}

	
	public String getSenha() {
	
		return senha;
	}

	
	public void setSenha(String senha) {
	
		this.senha = senha;
	}

	
	public Usuario getUsuario() {
		
		return usuario;
		
	}
	
	public void setUsuario(Usuario usuario) {
		
		this.usuario = usuario;
		
	}
	
}