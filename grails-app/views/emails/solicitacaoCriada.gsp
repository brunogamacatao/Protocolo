<%@ page contentType="text/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
  "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Solicitação Criada</title>
  </head>
  <body>
    <p>Prezado(a) ${solicitacao.solicitante.nome},</p>
    <p>sua solicitação foi registrada com o número ${solicitacao.id} e 
       será atendida o quanto antes.</p>
    <p>Você poderá acompanhar o progresso da sua solicitação através do 
       link abaixo, ou consultando no site pelo número da solicitação.</p>
    <a href="http://localhost:8080${createLink(controller:"solicitacao", action: "show", id: solicitacao.id)}">
    Clique aqui para acompanhar sua solicitação
    </a>
    <br/>
    <p>Cumprimentos,</p>
    <p>Equipe CESED.</p>
  </body>
</html>