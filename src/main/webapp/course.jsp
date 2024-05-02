<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cursos.bean.CursosBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalhes do Curso</title>
</head>
<body>
    <h1>Detalhes do Curso</h1>

    <%-- Obter o objeto do curso enviado pelo servlet --%>
    <% CursosBean curso = (CursosBean) request.getAttribute("curso"); %>

    <%-- Verificar se o curso é válido e exibir os detalhes --%>
    <% if (curso != null) { %>
        <div>
            <h2><%= curso.getTitulo() %></h2>
            <p><%= curso.getDescricao() %></p>
            <p>Duração: <%= curso.getDuracao() %> Horas</p>
            <p>Preço: R$ <%= curso.getPreco() %></p>
            <%-- Exibir a imagem do banner usando Base64 --%>
            <img src="data:image/png;base64,<%= curso.getBannerBase64() %>" alt="Banner do Curso">
        </div>
    <% } else { %>
        <div>
            <p>Curso não encontrado.</p>
        </div>
    <% } %>
</body>
</html>

