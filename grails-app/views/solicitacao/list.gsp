
<%@ page import="protocolo.Solicitacao" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitacao.label', default: 'Solicitacao')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitacao" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitacao.solicitante.label" default="Solicitante" /></th>
					
						<th><g:message code="solicitacao.setor.label" default="Setor" /></th>
					
						<g:sortableColumn property="descricao" title="${message(code: 'solicitacao.descricao.label', default: 'Descricao')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'solicitacao.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'solicitacao.estado.label', default: 'Estado')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'solicitacao.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitacaoInstanceList}" status="i" var="solicitacaoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitacaoInstance.id}">${fieldValue(bean: solicitacaoInstance, field: "solicitante")}</g:link></td>
					
						<td>${fieldValue(bean: solicitacaoInstance, field: "setor")}</td>
					
						<td>${fieldValue(bean: solicitacaoInstance, field: "descricao")}</td>
					
						<td><g:formatDate date="${solicitacaoInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: solicitacaoInstance, field: "estado")}</td>
					
						<td><g:formatDate date="${solicitacaoInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitacaoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
