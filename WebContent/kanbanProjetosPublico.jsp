<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="./css/kanban.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="./js/jquery.chained.js"></script>

<title>Kanban Bionexo</title>
</head>

<body>

	<div id="topo">
	
		<b>Bionexo do Brasil Ltda</b><br>
		
	</div>

<form action="montaKanbanPublico" method="post">
	
	<c:forEach begin="1" end="4" step="1" varStatus="contador">
	
		<select name="idProjetos">
			
			<option value="">Projeto ${contador.index}</option>
	
		<c:set var="aux" value=""/>
		<c:forEach var="item" items="${listaProjetos}">
		<c:if test="${item.name != aux}">
	
			<option disabled="disabled">&nbsp&nbsp ${item.name}</option>
			<c:set var="aux" value="${item.name}"/>
			
		</c:if>
	
			<c:forEach var="versao" items="${item.versoes}">
				<option value="${item.id}:${versao.id}">&nbsp&nbsp&nbsp&nbsp&#187; ${versao.name}</option>
			</c:forEach>
	
		</c:forEach>
		</select>
	
	</c:forEach>
	
	
	<br/><br/>
	
	<input type="submit" value="Enviar" id="submit"/>
	
</form>
	
<script type="text/javascript">

	$('#submit').click(function(e) {
		
		if ($("select").val() == '') {
			
			alert("Selecione ao menos o Projeto 1!");
			return false;			
			
		}
		
	});

</script>

</body>
</html>