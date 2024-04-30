const registerForm = document.getElementById('registerForm');

registerForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Evita o envio do formulário (comportamento padrão)

    // Obter os valores dos campos do formulário usando os novos IDs
    const nomeCompleto = document.getElementById('nomeCompleto').value;
    const email = document.getElementById('email1').value;
    const senha = document.getElementById('senha').value;
    const currencyCode = document.getElementById('currencyCode').value;

    // Exibir os valores no console para debug
    console.log('Valor do Nome Completo:', nomeCompleto);
    console.log('Valor do Email:', email);
    console.log('Valor da Senha:', senha);
    console.log('Valor do Código da Moeda:', currencyCode);

    // Verificar se todos os campos obrigatórios estão preenchidos
    if (nomeCompleto.trim() === '' || email.trim() === '' || senha.trim() === '' || currencyCode.trim() === '') {
        alert('Por favor, preencha todos os campos obrigatórios.');
        return;
    }

    // Criar objeto FormData com os dados do formulário
    const formData = new FormData();
    formData.append('nomeCompleto', nomeCompleto);
    formData.append('email', email);
    formData.append('senha', senha);

    // Criar uma requisição AJAX
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'RegistroServlet', true); // Especificar o método e o URL do servlet

    // Monitorar o estado da requisição
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Se a requisição for bem-sucedida, exibir a resposta do servlet (opcional)
                console.log('Resposta do servlet:', xhr.responseText);
                // Fechar o modal de registro após o envio bem-sucedido (você pode adicionar lógica personalizada aqui)
                const registerModal = document.getElementById('registerModal');
                if (registerModal) {
                    registerModal.style.display = 'none';
                }
            } else {
                // Se a requisição falhar, exibir mensagem de erro (opcional)
                console.error('Erro ao enviar requisição para o servlet.');
            }
        }
    };

    // Enviar os dados do formulário para o servlet
    xhr.send(formData);
});
