<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<s:form action="/login" method="POST">

	<tr>
	
		<td colspan="2">Kanban Bionexo</td>
		
	</tr>
	
	<c:if test="${!empty fieldErrors || !empty actionErrors}">
	
		<div class="red">
		
			<ul>
			<c:forEach items="${fieldErrors}" var="fieldError">
				<c:forEach items="${fieldError.value}" var="error">
					<li>${error}</li>
				</c:forEach>
			</c:forEach>
			<c:forEach items="${actionErrors}" var="actionError">
				<li>${actionError}</li>
			</c:forEach>
			</ul>
			
		</div>
		
	</c:if>
	
	<s:textfield name="login" label="Login"/>
	<s:password name="senha" label="Senha"/>
	<s:submit value="Login" align="center"/>
	
</s:form>

<br/>

<a href="<s:url action="listaProjetosPublico"/>">Acesso público</a>

</body>

</html>