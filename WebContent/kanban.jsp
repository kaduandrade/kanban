<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
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
	
	<title>Kanban Bionexo</title>
	
</head>

<body>

	<div id="modalNotas" style="display:none">
	
		<label>Notas da versão(<i>Opcional</i>):</label>
		<textarea style="text-align: left;" id="note" rows="10" cols="34"></textarea>

		<select id="idUsuarioAtribuido">
		
			<option value="" disabled="disabled">Atribuir a:</option>

		<c:set var="aux" value=""/>
		<c:forEach var="atribuir" items="${listaAtribuirA}">
		<c:if test="${atribuir.tipo != aux}">

			<option disabled="disabled">&nbsp&nbsp ${atribuir.tipo}</option>
			<c:set var="aux" value="${atribuir.tipo}"/>
			
		</c:if>

			<option value="${atribuir.id}">&nbsp&nbsp&nbsp&nbsp&#187; ${atribuir.descricao}</option>

		</c:forEach>
		</select>
		
		<p style="text-align:center">
			<input type="submit" id="TBcancel" value="Cancela" />
			<input type="submit" id="TBsubmit" value="Altera fase" />
		</p>
	
	</div>


		<div id="sair"><a href="login">sair</a></div>
		<div class="top">
		
			<p>${listaKanban.nomeProjeto} - ${listaKanban.nomeVersao}</p>
			<div id="containerTop">
				<c:forEach var="fase" items="${listaFases}">
					<div class="titulo"><p>${fase.descricao}</p></div>
				</c:forEach>
			</div>
			
		</div>
	

	<div id="container">

		<c:set var="strAux" value=""/>
		<c:set var="strAux2" value=""/>
		<c:forEach var="fase" items="${listaFases}">
		<div id="${fase.id}" class="recebeDrag">
				
			<c:forEach var="kanban" items="${listaKanban.tickets}">
			<c:if test="${kanban.idFase == fase.id}">

				<c:if test="${fase.id == 17}">
					<c:set var="classe" value="OK"/>
				</c:if>

				<c:if test="${fase.id == 18}">
					<c:set var="classe" value="NOK"/>
				</c:if>
				
				<div id="${fase.id}||${kanban.ticket}" class="itemDrag">
					
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
						
				</div>
				<c:set var="classe" value=""/>
					
			</c:if>
			</c:forEach>
				
		</div>
		</c:forEach>
		
	</div>
		
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

<script type="text/javascript">

	document.addEventListener('touchmove', function(event) {
		
		event.preventDefault();
		var touch = event.touches[0];
		alert("Touch x:" + touch.pageX + ", y:" + touch.pageY);
		
	}, false);
	
	
	$(function() {
		
		$( "div.recebeDrag" ).sortable({
			
			connectWith: "div"
			
		}).droppable({
			
			activeClass: 'dragAtivo',
			hoverClass: 'dragHover',
			revert: true,
			
			drop:function(event,ui){
				
				var data = {};
				var origem = ui.draggable.attr("id").split("||");
				var destino = this.id;

				if (origem[0] != destino) {
					
					event.preventDefault();
					tb_show('','TB_inline?height=360&width=310&inlineId=modalNotas&modal=true');
					
					
					$('input#TBcancel').click(function(){
						
						//refreshFase(destino);
						//setTimeout('refreshFase('+origem[0]+')', 250);
						tb_remove();
						setTimeout('location.reload()', 250);
						
					});
					
					$('input#TBsubmit').click(function(){
						
						$.ajax({
							
							url: "alteraFase",
							type: "POST",
							data: {
								idUsuarioAtribuido: idUsuarioAtribuido.value,
								note: note.value,
								idOrigem: origem[0],
						        idDestino: destino,
						        idTicket: origem[1]
						    },
							success: function(response){
								
								//refreshFase(destino);
								//setTimeout('refreshFase('+origem[0]+')', 250);
								tb_remove();
								setTimeout('location.reload()', 250);
																
							}
						
						});
						
					});
					
				}
				
			},
			tolerance: 'intersect'
		
		});
		
	});
	
	$(document).ready(function() {
		
		$('#note').markItUp(mySettings);
		
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
	
	function refreshFase(idFase) {
		
		var data = {};
		data.idFase = idFase;
		
		$.ajax({
			
			url: "refreshFase",
			type: "POST",
			data: data,
			success: function(data) {
				
				$('#'+idFase+'').html(data);
				
			}
		
		});
		
	};
	
</script>

</body>
</html>