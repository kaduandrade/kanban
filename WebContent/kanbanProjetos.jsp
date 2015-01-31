<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="./js/jquery-1.6.2.js" type="text/javascript"></script>
<link href="./css/kanban.css" rel="stylesheet" type="text/css" />
<title>Kanban Bionexo</title>
</head>

<body>

	<div id="topo">
	
		<b>Bionexo do Brasil Ltda</b><br>
		
	</div>
	
	<ul id="accordion">
	
	<c:forEach varStatus="index" var="item" items="${listaProjetos}">
	
		<c:if test="${!empty item.versoes}">
		<li><div>${item.name}</div>
		
			<ul>
			
				<table id="projetos" cellspacing="0">
				
					<caption>Versões</caption>
					<c:forEach var="versao" items="${item.versoes}">
						<tr><td><a href="./montaKanban?idProjeto=${item.id}&idVersao=${versao.id}&nomeVersao=${versao.name}">${versao.name}</a></td></tr>
					</c:forEach>
					
				</table>
				
			</ul>
			
		</li>
		</c:if>
		
	</c:forEach>
	</ul>


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


<script>
		$("#accordion > li > div").click(function(){
			 
		    if(false == $(this).next().is(':visible')) {
		        $('#accordion ul').slideUp(300);
		    }
		    $(this).next().slideToggle(300);
		});
		$('#accordion ul:eq(0)').show();
		</script>

</body>
</html>