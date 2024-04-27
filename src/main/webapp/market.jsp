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
    <title>Home - Inone Store</title>
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
                        INONE
                    </div>
                    <div class="store">
                        STORE
                    </div>
                </div>
            </div>

            <div class="middle-section">
                <div class="search-filter">
                    <span>All</span>
                    <span class="down-arrow">&#9660;</span>
                </div>
                <input class="search-bar" type="text" placeholder="Search">
                <div class="search-button">
                    <img class="search-icon" src="icons/search.svg" alt="search-icon">
                </div>
            </div>

            <div class="right-section">

                <div>
                    <img src="icons/user-svgrepo-com(1).svg" alt="favorites">
                    <span>Login</span>
                </div>

                <div>
                    <img src="icons/favorite-svgrepo-com.svg" alt="favorites">
                    <span>Favorites</span>
                </div>

                <div>
                    <img src="icons/bag.svg" alt="favorites">
                    <span>Cart</span>
                </div>
            </div>
        </div>
    </header>

    <nav>
        <div class="category-hover">Category
            <div class="category-list">
                <ul>
                    <li class="smart-hover">Smartphones
                        <div class="smartphone-list">
                            <ul>
                                <li>iPhone</li>
                                <li>Xiaomi</li>
                                <li>Samsung</li>
                                <li>Oneplus</li>
                                <li>Vodafone</li>
                                <li>ZTE</li>
                                <li>Nokia</li>
                            </ul>
                        </div>
                    </li>
                    <li class="clothes-hover">Clothes
                        <div class="clothes-list">
                            <ul>
                                <li>T-Shirts</li>
                                <li>Pants</li>
                                <li>Coats</li>
                                <li>Dresses</li>
                                <li>Jeans</li>
                            </ul>
                        </div>
                    </li>
                    <li class="watch-hover">Watches & Jewelry
                        <div class="watch-list">
                            <ul>
                                <li>Silicon smart watch</li>
                                <li>Diamond Watch</li>
                                <li>Mechanic Watch</li>
                                <li>Diamond Bracelet</li>
                                <li>Plastic Necklace</li>
                            </ul>
                        </div>
                    </li>
                    <li class="computers-hover">Computers & Tablets
                        <div class="computers-list">
                            <ul>
                                <li>Asus</li>
                                <li>Hp</li>
                                <li>Xiaomi</li>
                                <li>Samsung</li>
                                <li>Dell</li>
                            </ul>
                        </div>
                    </li>
                    <li class="motors-hover">Motors & Cars
                        <div class="motors-list">
                            <ul>
                                <li>Tires</li>
                                <li>Oil</li>
                                <li>Airs Bags</li>
                                <li>Led Bubles</li>
                                <li>Toyota</li>
                                <li>Tires</li>
                                <li>Oil</li>
                                <li>Airs Bags</li>
                                <li>Led Bubles</li>
                                <li>Toyota</li>
                            </ul>
                        </div>
                    </li>
                    <li class="health-hover">Health & Personal Care
                        <div class="health-list">
                            <ul>
                                <li>Soaps</li>
                                <li>Creams</li>
                                <li>Brusher</li>
                                <li>Tooth Paste</li>
                                <li>Tooth Cleaner</li>

                            </ul>
                        </div>
                    </li>
                    <li class="sports-hover">Sports
                        <div class="sports-list">
                            <ul>
                                <li>Basket</li>
                                <li>Tenis</li>
                                <li>Overboard</li>
                                <li>Bikes</li>
                                <li>Skydiving</li>
                            </ul>
                        </div>
                    </li>
                    <li class="women-hover">Women's Fashion
                        <div class="women-list">
                            <ul>
                                <li>Top</li>
                                <li>Bottom</li>
                                <li>T-shirts</li>
                                <li>Pants</li>
                                <li>Skirts</li>
                                <li>Jean's shorts</li>
                                <li>Underwears</li>
                            </ul>
                        </div>
                    </li>
                    <li class="men-hover">Men's Fashion
                        <div class="men-list">
                            <ul>
                                <li>Top</li>
                                <li>Bottom</li>
                                <li>T-shirts</li>
                                <li>Pants</li>
                                <li>Shirts</li>
                                <li>Shorts</li>
                                <li>Underwears</li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="nav-op1">New Releases</div>
        <div class="nav-op2">SALES & DEALS</div>
        <div class="nav-op3">CUPON</div>
        <div class="nav-op4">Best Seller</div>
    </nav>

    <div class="banner-container">
        <div class="banner">
            <img class="banner-img" src="images/revolt-164_6wVEHfI-unsplash.jpg" alt="al">
            <div class="promo-title">
                <div class="title">Sport Sneakers on Sale Today</div>
                <button>Buy now</button>
            </div>
        </div>
    </div>
    <div class="month-deal">
        <div class="text-deal">
            <div class="name-deal">Today's Deals - All With Free Shipping</div>
            <div>Buy everthing you need now</div>
        </div>
        <div class="sell-all-container">
            <button class="sell-all-button">See all</button>
        </div>
    </div>

    <div class="best-seller-section">
    <div class="best-seller-head">
        <div>Best Sellers</div>
        <div class="view-all-text">VIEW ALL</div>
    </div>
    <div class="products">
        <% 
            List<CursosBean> cursos = (List<CursosBean>) request.getAttribute("cursos");
            if (cursos != null && !cursos.isEmpty()) {
                for (CursosBean curso : cursos) {
                    String bannerBase64 = curso.getBannerBase64();
        %>
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
        <% 
                }
            } else {
        %>
        <div class="product-preview product-preview-3">
            <div>
                <img class="images" src="https://www.example.com/default-image.jpg" alt="product-image">
            </div>
            <div>
                <div class="prod-title">No courses available</div>
                <div class="desc">Unfortunately, there are no courses available at the moment.</div>
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




    <div class="bottom-banner">
        <div class="img-banner-container">
            <img class="shoes" src="images/products/Boots.png" alt="al">
        </div>
        <div class="bottom-title-banner">
            <h2>Under Armour Men's Micro G </h2>
            <div class="lema">Oil- & slip-resistant high traction rubber lug outsole for enhanced grip on a variety of surfaces</div>
            <button class="bottom-button">Shop now</button>
        </div>
    </div>

    

    <footer>
        <div class="newsletter-container">
            <input class="newsletter" type="text" placeholder="Enter Email to receive best offers">
            <button>Subscribe</button>
        </div>
        <div class="footer-list">
            <div class="first-foot-list">
                <h3>Company Info</h3>
                <ul>
                    <li>About Us</li>
                    <li>Terms and Conditions</li>
                    <li>Privacy Policy</li>
                    <li>Testimonials</li>
                    <li>Top Brands</li>
                </ul>
            </div>
            <div class="second-foot-list">
                <h3>Customer Service</h3>
                <ul>
                    <li>Registration</li>
                    <li>Contact us</li>
                    <li>FAQ For Newsletter Subscription</li>
                    <li>Warranty and Return</li>
                    <li>Payment Methods</li>
                </ul>
            </div>
            <div class="third-foot-list">
                <h3>Other services</h3>
                <ul>
                    <li>Sell to us</li>
                    <li>Affiliate program</li>
                    <li>Non-profit</li>
                </ul>
            </div>
            <div>
                <div class="media-icon-container">
                    <h3 class="follow-title">FOLLOW US</h3>
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
                    <h3>Payment Methods</h3>
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
            <p class="copyright-text">&copy; 2022 by Inone Store, All rights reserved.</p>
        </div>
    </footer>
</body>

</html>