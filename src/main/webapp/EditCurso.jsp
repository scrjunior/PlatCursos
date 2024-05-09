<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cursos.bean.CursosBean" %>
<%@ page import="cursos.bean.VideoBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Curso</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="text-center mb-4">Editar Curso</h1>

        <form action="UpdateCurso" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%= ((CursosBean) request.getAttribute("curso")).getId() %>">
    <div class="form-group">
        <label for="titulo">Título:</label>
        <input type="text" class="form-control" id="titulo" name="titulo" value="<%= ((CursosBean) request.getAttribute("curso")).getTitulo() %>" required>
    </div>
    <div class="form-group">
        <label for="descricao">Descrição:</label>
        <textarea class="form-control" id="descricao" name="descricao" rows="3" required><%= ((CursosBean) request.getAttribute("curso")).getDescricao() %></textarea>
    </div>
    <div class="form-group">
        <label for="duracao">Duração (horas):</label>
        <input type="number" class="form-control" id="duracao" name="duracao" value="<%= ((CursosBean) request.getAttribute("curso")).getDuracao() %>" required>
    </div>
    <div class="form-group">
        <label for="preco">Preço (MZN):</label>
        <input type="number" step="0.01" class="form-control" id="preco" name="preco" value="<%= ((CursosBean) request.getAttribute("curso")).getPreco() %>" required>
    </div>
    
     <div class="form-group">
        <label for="bannerImage">Novo Banner:</label>
        <input type="file" class="form-control-file" id="bannerImage" name="bannerImage">
    </div>

    <!-- Display Videos in a Table for Editing -->
    <h3>Vídeos</h3>
    <!-- Display Videos in a Table -->
    <table class="table">
        <thead>
            <tr>
                <th>Título do Vídeo</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="video" items="${videos}">
                <tr>
                    <td>
                        <input type="text" class="form-control" name="videoTitle-${video.id}" value="${video.titulo}">
                        <input type="hidden" name="videoId-${video.id}" value="${video.id}">
                    </td>
                </tr>
            </c:forEach>
            <tr>
                        <td>
                            <label for="novoVideo">Novo Vídeo:</label>
                            <input type="file" class="form-control-file" id="novoVideo" name="novoVideo">
                        </td>
                    </tr>
        </tbody>
    </table>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a href="AdminCursos" class="btn btn-secondary">Cancelar</a>
</form>

    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
