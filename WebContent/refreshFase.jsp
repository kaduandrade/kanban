<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="./js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="./js/jquery-ui-1.8.16.custom.min.js"></script>
	<script type="text/javascript" src="./js/thickbox.js"></script>
	
	<!-- Editor HTML Textile -->
	<script type="text/javascript" src="./markitup/jquery.markitup.js"></script>
	<script type="text/javascript" src="./markitup/sets/textile/set.js"></script>
	<link type="text/css" href="./markitup/skins/markitup/style.css" rel="stylesheet" />
	<link type="text/css" href="./markitup/sets/textile/style.css" rel="stylesheet" />
	<!-- Editor HTML Textile -->
	
	<!-- Rating -->
	<script type="text/javascript" src="./js/jquery.MetaData.js"></script>
	<script type="text/javascript" src="./js/jquery.rating.js"></script>
	<link type="text/css" href="./css/jquery.rating.css" rel="stylesheet"/>
	<!-- Rating -->	
	
	<link type="text/css" href="./css/kanban.css" rel="stylesheet" />
	<link type="text/css" href="./css/thickbox.css" rel="stylesheet" />
<c:forEach varStatus="contador" var="kanban" items="${lista}">
	
	<c:if test="${fase.id == 17}">
		<c:set var="classe" value="OK"/>
	</c:if>

	<c:if test="${fase.id == 18}">
		<c:set var="classe" value="NOK"/>
	</c:if>
	
	<div id="${fase.id}||${kanban.ticket}" class="itemDrag">
		
		<div class="postit${classe}">
			
			<div id="${kanban.tipo}" class="tooltipNome" title="${kanban.tipo}"></div>
			<div id="prioridade">
				<c:forEach begin="1" end="10" varStatus="cont">
					<input name="${kanban.ticket}" value="${ cont.index }" type="radio" disabled="disabled" class="star {split:2}" <c:if test="${ kanban.idCriticidade == cont.index }">checked="checked"</c:if>/>
				</c:forEach>
			</div>
			
			<c:set var="titulo" value="${kanban.atribuidoA}"/>
			<c:if test="${titulo == ''}">
				<c:set var="titulo" value="Não atribuído."/>
			</c:if>
			<div class="tooltipNome" title="${titulo}"><img class="gravatar" src="${kanban.urlAvatar}" border="0" /></div>
			<b><a href='http://projetos.bionexo.com.br/issues/${kanban.ticket}' target="_blank">#${kanban.ticket}</a></b><br/>
				${kanban.titulo}
				
		</div>
			
	</div>
	
</c:forEach>