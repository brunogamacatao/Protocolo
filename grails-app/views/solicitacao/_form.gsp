<%@ page import="protocolo.Solicitacao" %>



<div class="fieldcontain ${hasErrors(bean: solicitacaoInstance, field: 'solicitante', 'error')} required">
	<label for="solicitante">
		<g:message code="solicitacao.solicitante.label" default="Solicitante" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitante" name="solicitante.id" from="${protocolo.Pessoa.list()}" optionKey="id" required="" value="${solicitacaoInstance?.solicitante?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitacaoInstance, field: 'setor', 'error')} required">
	<label for="setor">
		<g:message code="solicitacao.setor.label" default="Setor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="setor" name="setor.id" from="${protocolo.Setor.list()}" optionKey="id" required="" value="${solicitacaoInstance?.setor?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitacaoInstance, field: 'descricao', 'error')} required">
	<label for="descricao">
		<g:message code="solicitacao.descricao.label" default="Descricao" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descricao" cols="40" rows="5" required="" value="${solicitacaoInstance?.descricao}"/>
</div>

