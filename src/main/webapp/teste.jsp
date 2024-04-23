<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Upload de Vídeo</title>
  <!-- Bootstrap CSS -->
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .sortable-list li {
      cursor: move;
    }
    .video-title {
      border: none;
      background-color: transparent;
    }
  </style>
</head>
<body>
  <div class="container mt-5">
    <h1 class="mb-4">Upload de Vídeo</h1>
    
    <!-- Formulário para upload de vídeo -->
    
    <form id="uploadForm" action="uploadImage" method="post" enctype="multipart/form-data">
      
      <div class="form-group">
        <label for="courseTitle">Título do Curso:</label>
        <input type="text" class="form-control" id="courseTitle" name="titulo" placeholder="Insira o título do curso" required>
      </div>
      
      <div class="form-group">
        <label for="descricao">Descrição:</label>
        <textarea class="form-control" id="descricao" name="descricao" rows="4" required></textarea>
      </div>
      
      <div class="form-group">
        <label for="duracao">Duração do Curso:</label>
        <input type="text" class="form-control" id="duracao" name="duracao" placeholder="Insira a duração do curso" required>
      </div>
      
      <div class="form-group">
        <label for="preco">Preço do Curso:</label>
        <input type="text" class="form-control" id="preco" name="preco" placeholder="Insira o preço do curso" required>
      </div>
      <hr>

      <!-- Lista de vídeos -->
      <h4>Lista de Vídeos</h4>
      <ul id="sortable" class="list-group sortable-list">
        <!-- Vídeos adicionados dinamicamente aparo aqui -->
      </ul>

      <!-- Campo de upload de vídeo -->
      <div class="form-group mt-3">
        <label for="videoFile">Upload de Vídeo:</label>
        <input type="file" class="form-control-file" id="videoFile" name="videos" accept="video/*" multiple required>
      </div>

      <!-- Botão para adicionar vídeo -->
      <button type="button" id="addVideoButton" class="btn btn-primary mt-3">Adicionar Vídeo</button>
      <button type="submit" class="btn btn-success mt-3">Enviar para Backend</button>
    </form>
  </div>

  <!-- Bootstrap JS -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script>
    $(document).ready(function() {
      // Adicionar vídeo à lista quando um arquivo é selecionado
      $('#videoFile').on('change', function() {
        var files = $(this)[0].files;
        for (var i = 0; i < files.length; i++) {
          var fileName = files[i].name;
          $('#sortable').append('<li class="list-group-item">' + fileName + '</li>');
        }
      });

      // Adicionar comportamento ao botão "Adicionar Vídeo"
      $('#addVideoButton').click(function() {
        $('#videoFile').click();
      });
    });
  </script>
</body>
</html>
