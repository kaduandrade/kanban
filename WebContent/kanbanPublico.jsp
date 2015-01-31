<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="refresh" content="${tempoRefresh}" />
	
	<script type="text/javascript" src="./js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="./js/jquery-ui-1.8.16.custom.min.js"></script>
	
	<!-- Rating -->
	<script type="text/javascript" src="./js/jquery.MetaData.js"></script>
	<script type="text/javascript" src="./js/jquery.rating.js"></script>
	<link type="text/css" href="./css/jquery.rating.css" rel="stylesheet"/>
	<!-- Rating -->

	<link type="text/css" rel="stylesheet" href="./css/global.css" />
	<link type="text/css" href="./css/kanban.css" rel="stylesheet" />
	
	<script src="./js/slides.min.jquery.js"></script>
	
	<title>Kanban Bionexo</title>
	
</head>

<body>

	<div id="bg"><div id="bgImg"><img src="img/loadingAnimation.gif" /></div></div>

	<div id="sair"><a href="login">sair</a></div>
	<div id="slides">
		
		<div class="slides_container">
		
			<c:forEach var="projeto" items="${listaKanbans}">
			
				<div id="container">
		
					<c:set var="strAux" value=""/>
					<c:forEach var="fase" items="${listaFases}">
					<c:if test="${strAux != projeto.nomeProjeto}">
					
						<div class="top">
							<p class="publico">${projeto.nomeProjeto} - ${projeto.description}</p>
							
							<div id="containerTop">
								<c:forEach var="faseD" items="${listaFases}">
								<div class="titulo"><p>${faseD.descricao}</p></div>
								</c:forEach>
							</div>
						</div>
						<c:set var="strAux" value="${projeto.nomeProjeto}"/>
					
					</c:if>
					<div id="${fase.id}" class="recebeDrag">
	
						<c:forEach var="kanban" items="${projeto.tickets}">

						<c:if test="${kanban.idFase == fase.id}">
			
							<c:if test="${fase.id == 17}">
								<c:set var="classe" value="OK"/>
							</c:if>
			
							<c:if test="${fase.id == 18}">
								<c:set var="classe" value="NOK"/>
							</c:if>
							
								<div class="postit${classe}">
									
									<div id="${kanban.tipo}" class="tooltipNome" title="${kanban.tipo}<br><i>Autor:</i><img class='gravatar' src='${kanban.urlAvatarAutor}' border='0' /><br><b>${kanban.autor}</b><br><br>Reteste:<b>${kanban.indiceReteste}</b>"></div>
									<div id="prioridade">
										<c:forEach begin="1" end="10" varStatus="cont">
											<input name="${kanban.ticket}1" value="${ cont.index }" type="radio" disabled="disabled" class="star {split:2}" <c:if test="${ kanban.idCriticidade == cont.index }">checked="checked"</c:if>/>
										</c:forEach>
									</div>
									
									<c:set var="titulo" value="${kanban.atribuidoA}"/>
									<c:if test="${titulo == ''}">
										<c:set var="titulo" value="Não atribuído."/>
									</c:if>
									<div class="tooltipNome" title="${titulo}"><img class="gravatar" src="${kanban.urlAvatar}" border="0" /></div>
									<b><a href='http://projetos.bionexo.com.br/issues/${kanban.ticket}' target="_blank">#${kanban.ticket}</a></b>
										${kanban.titulo}
										
								</div>
									
							<c:set var="classe" value=""/>
								
						</c:if>
						</c:forEach>
							
					</div>
					</c:forEach>
					
				</div>
				
			</c:forEach>			
			
		</div>
		
	</div>
	
<script type="text/javascript">
    
	$(document).ready(function() {
		
		$('#bg').fadeOut(2000);
		 
		$('#slides').slides({
			preload: true,
			preloadImage: 'img/loadingAnimation.gif',
			play: 15000,
			hoverPause: false,
			slideSpeed: 1000
		});
		
		//Select all anchor tag with rel set to tooltip
		$('.tooltipNome').mouseover(function(e) {
			
			//Grab the title attribute's value and assign it to a variable
			var tip = $(this).attr('title');	
			
			//Remove the title attribute's to avoid the native tooltip from the browser
			$(this).attr('title','');
			
			//Append the tooltip template and its value
			$(this).append('<div id="tooltip"><div class="tipBody">' + tip + '</div></div>');		
					
			//Show the tooltip with faceIn effect
			$('#tooltip').fadeIn('1500');
			$('#tooltip').fadeTo('10',0.9);
			
		}).mousemove(function(e) {
		
			//Keep changing the X and Y axis for the tooltip, thus, the tooltip move along with the mouse
			$('#tooltip').css('top', e.pageY + 10 );
			$('#tooltip').css('left', e.pageX + 20 );
			
		}).mouseout(function() {
		
			//Put back the title attribute's value
			$(this).attr('title',$('.tipBody').html());
		
			//Remove the appended tooltip template
			$(this).children('div#tooltip').remove();
			
		});
		
	});
	
</script>

</body>
</html>