<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cursos.bean.CursosBean, cursos.bean.VideoBean" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalhes do Curso</title>
    
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding-top: 50px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 20px;
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        img {
            width: 100%;
            height: 300px;
            border-radius: 8px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            object-fit: cover;
        }
        p {
            line-height: 1.6;
            color: #666;
        }
        .price {
            font-size: 24px;
            font-weight: bold;
            color: #007bff;
        }
        .video-list {
            margin-top: 30px;
        }
        .video-item {
            margin-bottom: 20px;
        }
        /* Estilos para vídeos */
        .video-container {
            position: relative;
            width: 100%; /* Largura total do container */
            padding-bottom: 56.25%; /* Proporção 16:9 (para vídeos) */
        }
        .video-container video {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Detalhes do Curso</h1>

        <%-- Obter o objeto do curso enviado pelo servlet --%>
        <% CursosBean curso = (CursosBean) request.getAttribute("curso"); %>

        <%-- Verificar se o curso é válido e exibir os detalhes --%>
        <% if (curso != null) { %>
            <div>
                <img src="data:image/png;base64,<%= curso.getBannerBase64() %>" alt="Banner do Curso">
                <h2><%= curso.getTitulo() %></h2>
                <p><%= curso.getDescricao() %></p>
                <p><strong>Duração:</strong> <%= curso.getDuracao() %> Horas</p>
                <p><strong>Preço:</strong> MZN <%= curso.getPreco() %></p>
            </div>

            <%-- Exibir lista de vídeos como um acordeão --%>
            <div class="video-list">
                <h3>Vídeos do Curso</h3>
                <% List<VideoBean> videos = curso.getVideos(); %>
                <% if (videos != null && !videos.isEmpty()) { %>
                    <div id="accordion">
                        <% int index = 1; %>
                        <% for (VideoBean video : videos) { %>
                            <div class="card">
                                <div class="card-header" id="heading<%= index %>">
                                    <h5 class="mb-0">
                                        <button class="btn btn-link" data-toggle="collapse" data-target="#collapse<%= index %>" aria-expanded="true" aria-controls="collapse<%= index %>">
                                            <%= video.getTitulo() %>
                                        </button>
                                    </h5>
                                </div>

                                <div id="collapse<%= index %>" class="collapse" aria-labelledby="heading<%= index %>" data-parent="#accordion">
                                    <div class="card-body">
                                        <div class="video-container">
                                            <video controls>
                                                <source src="videoServlet?videoId=<%= video.getId() %>" type="video/mp4">
                                                Your browser does not support HTML5 video.
                                            </video>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% index++; %>
                        <% } %>
                    </div>
                <% } else { %>
                    <p>Nenhum vídeo disponível para este curso.</p>
                <% } %>
            </div>
        <% } else { %>
            <div>
                <p>Curso não encontrado.</p>
            </div>
        <% } %>
    </div>
    
    <!-- Bootstrap JS and dependencies CDN (optional, for enhanced UI components) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
