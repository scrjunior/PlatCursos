<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="cursos.bean.CursosBean" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plat Cursos</title>
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/promo.css">
    <link rel="stylesheet" href="styles/products.css">
    <link rel="stylesheet" href="styles/banner-ads.css">
    <link rel="stylesheet" href="styles/footer.css">
    <link rel="stylesheet" href="styles/categories.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;400;700;900&display=swap" rel="stylesheet">
</head>

<body>
    <header>
        <div class="header">

            <div class="left-section">
                <div class="logo-container">
                    <div class="inone">
                        PLAT
                    </div>
                    <div class="store">
                        CURSOS
                    </div>
                </div>
            </div>

            <div class="middle-section">
                <div class="search-filter">
                    <span>All</span>
                    <span class="down-arrow">&#9660;</span>
                </div>
                <input class="search-bar" type="text" placeholder="Buscar">
                <div class="search-button">
                    <img class="search-icon" src="icons/search.svg" alt="search-icon">
                </div>
            </div>

	            	<div class="right-section">
	    <div class="login-section">
	        <img src="icons/user-svgrepo-com(1).svg" alt="favorites">
	        <span>Login</span>
	    </div>
	    <div>
	        <img src="icons/bag.svg" alt="favorites">
	        <span>Cursos</span>
	    </div>
	</div>

<!-- Modal -->
<div id="loginModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Login</h2>
        <form id="loginForm" method="post" action="LoginServlet">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <label for="password">Senha:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Entrar</button>
        </form>
        <p>Ainda não tem uma conta? <a href="#" id="registerLink">Registrar</a></p>
    </div>
</div>
            	
        <!-- Modal de Registro -->
<div id="registerModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Registrar</h2>
        <form id="registerForm" method="post" action="RegistroServlet">
            <label for="fullName">Nome Completo:</label>
            <input type="text" id="nomeCompleto" name="nomeCompleto" required>
            <label for="email">Email:</label>
            <input type="email" id="email1" name="email" required>
            <label for="registerPassword">Senha:</label>
            <input type="password" id="senha" name="senha" required>
            <label for="currencyCode">Código da Moeda:</label>
            <input type="text" id="currencyCode" name="currencyCode" placeholder="Ex: xcusi8273shjsk" required>
            <button type="submit">Registrar</button>
        </form>
    </div>
</div>


        
            	
        </div>
    </header>

    

    <div class="banner-container">
        <div class="banner">
            <img class="banner-img" src="images/pexels-edwardeyer-811838.jpg" alt="al">
            <div class="promo-title">
                <div class="title">Desperte sua Paixão Musical: Cursos Especializados para Tocar Qualquer Instrumento!</div>
                <button>Começe agora</button>
            </div>
        </div>
    </div>
    <div class="month-deal">
        <div class="text-deal">
            <div class="name-deal">Aprenda a Tocar Seu Instrumento Favorito Hoje!</div>
            <div>🎶 Comece sua jornada musical hoje! 🎶</div>
        </div>
        <div class="sell-all-container">
            <button class="sell-all-button">Começar</button>
        </div>
    </div>

    <div class="best-seller-section">
    <div class="best-seller-head">
        <div>Curso quentes</div>
        <div class="view-all-text">VER TODOS</div>
    </div>
    <div class="products">
        <% 
    List<CursosBean> cursos = (List<CursosBean>) request.getAttribute("cursos");
    if (cursos != null && !cursos.isEmpty()) {
        for (CursosBean curso : cursos) {
            String bannerBase64 = curso.getBannerBase64();
%>

<!-- Link para detalhes do curso -->
<a href="detalhescurso?cursoId=<%= curso.getId() %>">
    <div class="product-preview product-preview-3">
        <div>
            <img class="images" src="data:image/png;base64,<%= bannerBase64 %>" alt="product-image">
        </div>
        <div>
            <div class="prod-title"><%= curso.getTitulo() %></div>
            <div class="prod-desc"><%= curso.getDescricao() %></div>
            <div class="durac"><%= curso.getDuracao() %> Horas</div>
            <div class="description-product">
                <div class="price-container">
                    <div class="price"><%= curso.getPreco() %></div>
                </div>
                <div>
                    <button class="add-to-cart">Ver mais</button>
                    <img class="add-to-favo" src="icons/favorite-svgrepo-com.svg" alt="add-to-favorites">
                </div>
            </div>
        </div>
    </div>
</a>

<% 
        }
    } else {
%>
<div class="product-preview product-preview-3">
    <div>
        <img class="images" src="https://www.example.com/default-image.jpg" alt="product-image">
    </div>
    <div>
        <div class="prod-title">Sem cursos disponíveis</div>
        <div class="desc">Infelizmente ocorreu um erro.</div>
        <div class="durac">-</div>
        <div class="description-product">
            <div class="price-container">
                <div class="price">-</div>
            </div>
        </div>
    </div>
</div>
<% 
    }
%>

    </div>
</div>




    

    

    <footer>
        <div class="newsletter-container">
            <input class="newsletter" type="text" placeholder="Enter Email to receive best offers">
            <button>Subscrever</button>
        </div>
        <div class="footer-list">
            <div class="first-foot-list">
                <h3>Plat Cursos</h3>
                <ul>
                    <li>Sobre nós</li>
                    <li>Termos e condições</li>
                    <li>Privacidade</li>
                    <li>Testemunhas</li>
                    
                </ul>
            </div>
            <div class="second-foot-list">
                <h3>Atendimento</h3>
                <ul>
                    
                    <li>Contacte nós</li>
                    <li>FAQ</li>
                    <li>Garantia</li>
                    <li>Metódos de pagamento</li>
                </ul>
            </div>
            <div class="third-foot-list">
                <h3>Outros serviços</h3>
                <ul>
                    <li>Seja nosso parceiro</li>
                    <li>Programa de afiliados</li>
                    <li>Caridade</li>
                </ul>
            </div>
            <div>
                <div class="media-icon-container">
                    <h3 class="follow-title">SIGA NOS</h3>
                    <div class="follow-container">
                        <div class="icon-cont">
                            <img class="media-icon" src="icons/instagram.svg" alt="al">
                        </div>
                        <div class="icon-cont">
                            <img class="media-icon" src="icons/black-and-white-youtube.svg" alt="al">
                        </div>
                        <div class="icon-cont">
                            <img class="media-icon" src="icons/twitter.svg" alt="al">
                        </div>
                        <div class="icon-cont">
                            <img class="media-icon" src="icons/facebook-svgrepo-com.svg" alt="al">
                        </div>
                    </div>
                </div>
                <div class="payment-container">
                    <h3>Metódos de pagamento</h3>
                    <div class="payment-icon">
                        <div>
                            <img class="pay-method" src="icons/visa.svg" alt="visa">
                        </div>
                        <div>
                            <img class="pay-method" src="icons/master-card.svg" alt="master">
                        </div>
                        <div>
                            <img class="pay-method" src="icons/paypal.svg" alt="paypal">
                        </div>
                        <div>
                            <img class="pay-method" src="icons/apple-pay.svg" alt="apple">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="copyright-bar">
            <p class="copyright-text">&copy; 2024 by Plat Cursos, Todos os direitos reservados.</p>
        </div>
    </footer>
    
    <script>
    
 // // Obter elementos do DOM para o modal de login
const loginSection = document.querySelector('.login-section');
const loginModal = document.getElementById('loginModal');
const loginCloseButton = loginModal.querySelector('.close');
const registerLink = document.getElementById('registerLink');

// Abrir o modal de login quando clicar na seção de login
loginSection.addEventListener('click', () => {
    loginModal.style.display = 'block';
});

// Fechar o modal de login quando clicar no botão de fechar
loginCloseButton.addEventListener('click', () => {
    loginModal.style.display = 'none';
});

// Obter o modal de registro
const registerModal = document.getElementById('registerModal');
const registerModalCloseButton = registerModal.querySelector('.close');

// Abrir o modal de registro e fechar o modal de login ao clicar no link de registrar
registerLink.addEventListener('click', (event) => {
    event.preventDefault(); // Evita o comportamento padrão do link
    loginModal.style.display = 'none'; // Oculta o modal de login
    registerModal.style.display = 'block'; // Exibe o modal de registro
});

// Fechar o modal de registro quando clicar no botão de fechar
registerModalCloseButton.addEventListener('click', () => {
    registerModal.style.display = 'none';
});

// Também fechar o modal de registro se clicar fora da área do modal
window.addEventListener('click', (event) => {
    if (event.target === registerModal) {
        registerModal.style.display = 'none';
    }
});


    </script>
    
</body>

</html>