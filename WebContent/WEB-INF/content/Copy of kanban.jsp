<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href=' http://fonts.googleapis.com/css?family=Reenie+Beanie' rel='stylesheet' type='text/css'>
<title>Kanban Bionexo</title>
<style>
body{
	font: 10px Helvetica, sans-serif;
	background:#fff;
	text-align:center;
	}

#container {
	width: 1480px;
	padding: 10px;
}

#navegacao {
	width: 200px;
	height: 800px;
	background-color:#fff;
	float:left;
	border-left:1px solid #000;
	padding: 4px;
	}

div.postit { 
    text-align:center;
	padding: 2px;
	height: 80px;
    border:1px solid yellow;
margin-top: 8px;
margin-bottom: 8px;
    width:96%; 
    background-color: rgba(60, 132, 198, 0.8); 
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(#FFFAAE), to(#FFF057)); 
    background-image: -moz-linear-gradient(#FFFAAE 0%, #FFF057 90%); 
    border-top-color: #FFF057; 
    border-right-color: #FFF057; 
    border-bottom-color: #FFF057; 
    border-left-color: #FFF057; 
    -webkit-box-shadow: #AAA 0px 10px 16px; 
    -moz-box-shadow: #AAA 0px 10px 16px; /* FF 3.5+ */}

img.gravatar {
	width: 40px;
	height: 40px;
	background-color: red;
	float:left;
}
</style> 
</head>

<body>

		asasa
	<div id="container">
		<c:set var="strAux" value=""/>
		<c:set var="strAux2" value=""/>
		<c:forEach varStatus="index" var="item" items="${lista}">
		
			<c:if test="${(item.descricaoCriticidade != strAux2 && strAux2 != '')}">
				</div>
			</c:if>
			
			<c:if test="${item.descricaoCriticidade != strAux}">
				
				<div id="navegacao">${item.descricaoCriticidade}
						
			</c:if>
					<c:set var="strAux" value="${item.descricaoCriticidade}"/>
						<c:set var="strAux2" value="${strAux}"/>
							<div class="postit">
								<img class="gravatar" src="${item.urlAvatar}" border="0" />
								#${item.ticket} - ${item.titulo}
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

</body>
</html>