package com.kanban.interceptor;

import com.kanban.model.Usuario;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AutorizadorInterceptor implements Interceptor {
	
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
		
		Usuario usuarioLogado = (Usuario)invocation.getInvocationContext().getSession().get("usuarioLogado");
		
		if(usuarioLogado == null) {
			
			return "naoLogado";
			
		} else {
			
			return invocation.invoke();
			
		}
		
	}

	@Override
	public void destroy() {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {

		// TODO Auto-generated method stub
		
	}

}