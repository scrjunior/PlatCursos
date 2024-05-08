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

        <form action="UpdateCurso" method="post">
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
                <label for="preco">Preço (R$):</label>
                <input type="number" step="0.01" class="form-control" id="preco" name="preco" value="<%= ((CursosBean) request.getAttribute("curso")).getPreco() %>" required>
            </div>

            <!-- Display Videos in a Table for Editing -->
            <h3>Vídeos</h3>
            <!-- Display Videos in a Table with Edit Modal Trigger -->
<table class="table">
    <thead>
        <tr>
            <th>Título do Vídeo</th>
            <th>Editar</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="video" items="${videos}">
            <tr>
                <td>${video.titulo}</td>
                <td>
                    <!-- Button trigger modal for video edit -->
                    <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editVideoModal-${video.id}">
                        Editar
                    </button>

                    <!-- Modal for video edit -->
                    <div class="modal fade" id="editVideoModal-${video.id}" tabindex="-1" role="dialog" aria-labelledby="editVideoModalLabel-${video.id}" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editVideoModalLabel-${video.id}">Editar Vídeo</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- Video edit form -->
                                    <form id="editVideoForm-${video.id}" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="videoFile">Selecionar Novo Vídeo:</label>
                                            <input type="file" class="form-control-file" id="videoFile" name="videoFile" accept="video/*">
                                        </div>
                                        <div class="form-group">
                                            <label for="videoTitle">Título do Vídeo:</label>
                                            <input type="text" class="form-control" id="videoTitle" name="videoTitle" value="${video.titulo}">
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                    <button type="button" class="btn btn-primary" onclick="updateVideo(${video.id})">Salvar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- JavaScript for AJAX Video Update -->
<script>
function updateVideo(videoId) {
    var form = document.getElementById('editVideoForm-' + videoId);
    if (!form) {
        console.error('Form element not found for video ID: ' + videoId);
        return;
    }

    var formData = new FormData(form);

    // AJAX request to UpdateVideoServlet
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'UpdateVideoServlet');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Video updated successfully
                alert('Vídeo atualizado com sucesso!');
                location.reload(); // Reload page or update video list dynamically
            } else {
                // Error updating video
                alert('Erro ao atualizar o vídeo.');
            }
        }
    };
    xhr.send(formData);
}

</script>

            
            <button type="submit" class="btn btn-primary">Salvar</button>
            <a href="AdminCursos" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
