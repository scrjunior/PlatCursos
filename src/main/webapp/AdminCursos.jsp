<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cursos.bean.CursosBean" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cursos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 40px;
        }

        .curso-card {
            margin-bottom: 20px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f8f9fa;
            cursor: pointer; /* Altera o cursor para indicar que o card é clicável */
        }

        .curso-banner {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
        }

        /* Estilo do link */
        .curso-link {
            color: black; /* Cor do texto do link (preto) */
            text-decoration: none; /* Remove a decoração de sublinhado padrão */
        }

        /* Estilo do link ao passar o mouse (hover) */
        .curso-link:hover {
            color: black; /* Cor do texto do link ao passar o mouse (preto) */
            text-decoration: none; /* Remove a decoração de sublinhado ao passar o mouse */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center mb-4">Cursos Disponíveis</h1>

        <div class="row">
    <% List<CursosBean> cursos = (List<CursosBean>) request.getAttribute("cursos");
       if (cursos != null && !cursos.isEmpty()) { %>
        
        <% for (CursosBean curso : cursos) { %>
            <div class="col-md-4">
                <div class="curso-card">
                    <img src="data:image/png;base64, <%= curso.getBannerBase64() %>" alt="Banner Curso" class="curso-banner">
                    <h4 class="mt-3"><%= curso.getTitulo() %></h4>
                    <p><%= curso.getDescricao() %></p>
                    <p><strong>Duração:</strong> <%= curso.getDuracao() %> horas</p>
                    <p><strong>Preço:</strong> R$ <%= curso.getPreco() %></p>
                    
                    <div class="curso-actions">
                        <a href="EditCurso?id=<%= curso.getId() %>" class="btn btn-primary mr-2 curso-link">Editar</a>
                        
                        <!-- Delete button (Eliminar) -->
                        <form method="post" action="DeleteCurso" class="d-inline">
                            <input type="hidden" name="cursoId" value="<%= curso.getId() %>">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </div>
                </div>
            </div>
        <% } %>

    <% } else { %>
        <div class="col-md-12">
            <div class="alert alert-info" role="alert">
                Não há cursos disponíveis no momento.
            </div>
        </div>
    <% } %>
</div>

    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
