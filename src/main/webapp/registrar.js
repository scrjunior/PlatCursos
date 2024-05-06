
    document.addEventListener('DOMContentLoaded', function() {
        // Coloque aqui o código para chamar o servlet quando a página for carregada
        carregarCursos();
    });

    function carregarCursos() {
        // Criar uma instância de XMLHttpRequest para enviar uma requisição AJAX
        var xhr = new XMLHttpRequest();

        // Configurar a requisição AJAX para chamar o servlet GetCursosServlet
        xhr.open('GET', 'getcursos', true);

        // Configurar a função de callback para lidar com a resposta da requisição
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Se a requisição foi bem-sucedida, você pode manipular os dados aqui
                    // Por exemplo, atualizar a página com os cursos recebidos
                    // Aqui, você pode implementar a lógica para atualizar a página com os cursos recebidos
                    console.log('Cursos carregados com sucesso!');
                } else {
                    // Se houver um erro ao chamar o servlet, manipule de acordo
                    console.error('Erro ao carregar cursos:', xhr.status);
                }
            }
        };

        // Enviar a requisição AJAX
        xhr.send();
    }

