<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Criar Curso</title>
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
    <h1 class="mb-4">Criar Curso</h1>
    
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
      
      <div class="form-group">
        <label for="bannerImage">Banner do Curso:</label>
        <input type="file" class="form-control-file" id="bannerImage" name="bannerImage" accept="image/*" required>
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

      
      
      <button type="submit" class="btn btn-success mt-3">Enviar</button>
      <input type="hidden" id="videoDuration" name="videoDuration" value="">
      
    </form>
  </div>

  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script>
  $(document).ready(function() {
	    
	    $('#videoFile').on('change', function() {
	        var files = $(this)[0].files;
	        for (var i = 0; i < files.length; i++) {
	            var file = files[i];
	            var fileName = file.name;
	            
	            
	            var videoTitle = fileName.replace(/\.[^/.]+$/, "");
	            
	            
	            $('#sortable').append('<li class="list-group-item">' + videoTitle + '</li>');
	            
	            
	            console.log('Video Title:', videoTitle);
	            
	            // Create a FormData object to send data to the servlet
	            var formData = new FormData();
	            formData.append('videos', file); // Add the video file to FormData
	            formData.append('videoTitulo', videoTitle); // Add video title to FormData
	            
	            // Send video file and additional data to the servlet
	            $.ajax({
	                url: 'uploadImage',
	                type: 'POST',
	                data: formData,
	                processData: false,
	                contentType: false,
	                success: function(response) {
	                    console.log('Video uploaded successfully');
	                    // Redirect to success page after successful upload
	                    window.location.href = 'success.jsp';
	                },
	                error: function(xhr, status, error) {
	                    console.error('Error uploading video: ' + error);
	                    // Handle error as needed
	                }
	            });
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
