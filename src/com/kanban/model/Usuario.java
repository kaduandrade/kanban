package com.kanban.model;

import java.util.Date;
import org.redmine.ta.beans.User;

public class Usuario extends User {
	
	private String senhaHashed;
	private String nome;
	private boolean isLoged;
	private String admin;
	private int authSourceId;
	private String hashedPassword;
	private String identityUrl;
	private String language;
	private String mailNotification;
	private int status;
	private String type;
	private Date updatedOn;
	
	public String getSenhaHashed() {
	
		return senhaHashed;
	}
	
	public void setSenhaHashed(String senhaHashed) {
	
		this.senhaHashed = senhaHashed;
	}
	
	public String getNome() {
	
		return nome;
	}
	
	public void setNome(String nome) {
	
		this.nome = nome;
	}
	
	public boolean isLoged() {
	
		return isLoged;
	}
	
	public void setLoged(boolean isLoged) {
	
		this.isLoged = isLoged;
	}
	
	public String getAdmin() {
	
		return admin;
	}
	
	public void setAdmin(String admin) {
	
		this.admin = admin;
	}
	
	public int getAuthSourceId() {
	
		return authSourceId;
	}
	
	public void setAuthSourceId(int authSourceId) {
	
		this.authSourceId = authSourceId;
	}
	
	public String getHashedPassword() {
	
		return hashedPassword;
	}
	
	public void setHashedPassword(String hashedPassword) {
	
		this.hashedPassword = hashedPassword;
	}
	
	public String getIdentityUrl() {
	
		return identityUrl;
	}
	
	public void setIdentityUrl(String identityUrl) {
	
		this.identityUrl = identityUrl;
	}
	
	public String getLanguage() {
	
		return language;
	}
	
	public void setLanguage(String language) {
	
		this.language = language;
	}
	
	public String getMailNotification() {
	
		return mailNotification;
	}
	
	public void setMailNotification(String mailNotification) {
	
		this.mailNotification = mailNotification;
	}
	
	public int getStatus() {
	
		return status;
	}
	
	public void setStatus(int status) {
	
		this.status = status;
	}
	
	public String getType() {
	
		return type;
	}
	
	public void setType(String type) {
	
		this.type = type;
	}
	
	public Date getUpdatedOn() {
	
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
	
		this.updatedOn = updatedOn;
	}
	
}