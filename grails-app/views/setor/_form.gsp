<%@ page import="protocolo.Setor" %>



<div class="fieldcontain ${hasErrors(bean: setorInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="setor.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${setorInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: setorInstance, field: 'responsavel', 'error')} required">
	<label for="responsavel">
		<g:message code="setor.responsavel.label" default="Responsavel" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="responsavel" name="responsavel.id" from="${protocolo.Pessoa.list()}" optionKey="id" required="" value="${setorInstance?.responsavel?.id}" class="many-to-one"/>
</div>

