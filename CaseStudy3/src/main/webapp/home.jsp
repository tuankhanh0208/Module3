<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 24-Nov-24
  Time: 5:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Bundle JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link href="./style/css/home.css" rel="stylesheet" >
    <title>Title</title>
</head>
<body>
<div class="container-fluid px-0 mx-1 ">
<div class="header">
    <div class="logo">
        <a href=""><img src="./style/img/logo.webp" alt="" /></a>
    </div>
    <div class="search-bar">
        <select class="search-select">
            <option value="giay">Giày dép</option>
            <option value="phu-kien">Phụ kiện</option>
            <option value="quan-ao">Quần áo</option>
            <option value="so-mi-tay-dai">Sơ mi tay dài</option>
            <option value="so-mi-tay-ngan">Sơ mi tay ngắn</option>
            <option value="san-pham-khuyen-mai">Sản phẩm khuyến mãi</option>
            <option value="san-pham-hot-trend">Sản phẩm hot trend</option>
            <option value="san-pham-noi-bat">Sản phẩm nổi bật</option>
            <option value="quan-short-nam">Quần short nam</option>
            <option value="quan-au-nam">Quần âu nam</option>
            <option value="so-mi-nam">Sơ mi nam</option>
            <option value="be-gai">Bé gái</option>
            <option value="be-trai">Bé trai</option>
            <option value="thoi-trang-nu">Thời trang nữ</option>
            <option value="thoi-trang-nam">Thời trang nam</option>
            <option value="tat-ca" selected>Tất cả</option>
        </select>
        <form action="${pageContext.request.contextPath}/products" method="get"  style="display: flex;  max-width:120px; margin: auto;">
            <input type="hidden" name="path" value="search">
            <input class="search-input" type="text" placeholder="Tìm sản phẩm bạn mong muốn" name="keyword">
            <button class="search-button" type="submit">
                <i class="fas fa-search"></i>
            </button>
        </form>
    </div>
    <div class="account">
        <div class="account-user">
            <c:if test="${sessionScope.acc != null}">
                <span style="color: #fff ; font-size: 13px">Hello ${sessionScope.acc.user}</span>
            </c:if>
            <i class="fa-solid fa-user"></i>
            <c:if test="${sessionScope.acc != null}">
                <a href="logout">Đăng xuất</a>
            </c:if>
            <c:if test="${sessionScope.acc == null}">
                <a href="login.jsp">Đăng nhập </a>
                <span class="divider">/</span>
                <a href="signup.jsp">Đăng Ký</a>
            </c:if>

        </div>
        <div class="cart">
            <a href=""><i class="fa-solid fa-cart-shopping"></i></a>
        </div>
    </div>
</div>
<div class="navigation">
    <div class="nav-menu">
        <ul>
            <li><a href="#">Trang chủ</a></li>

            <li>
                <a href="#">Thời trang Nam</a>
                <ul>
                    <li>
                        <a href="#">Sơ mi nam</a>
                        <ul>
                            <li><a href="#">Sơ mi ngắn tay</a></li>
                            <li><a href="#">Sơ mi dài tay</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Quần âu nam</a></li>
                    <li><a href="#">Quần short nam</a></li>
                </ul>
            </li>

            <li>
                <a href="#">Sản phẩm</a>
                <ul>
                    <li>
                        <a href="#">Sản phẩm nổi bật</a>
                        <ul>
                            <li><a href="#">Quần áo</a></li>
                            <li><a href="#">Phụ kiện</a></li>
                            <li><a href="#">Gìay Dép</a></li>
                            <li><a href="#">Bé nam</a></li>
                            <li><a href="#">Bé nữ</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Sản phẩm hot tend</a></li>
                    <li><a href="#">Sản phẩm khuyến mãi</a></li>
                </ul>
            </li>
            <li><a href="#">Bé trai</a></li>
            <li><a href="#">Bé gái</a></li>
            <li><a href="#">Tin tức</a></li>
            <li><a href="#">Liên hệ</a></li>
        </ul>
    </div>
    <div class="nav-hotline">
        <i class="fa-solid fa-phone">Hotline: 0972114634</i>

    </div>
</div>
<div class="main">
            <div class="side-bar">
                <div class="side-bar-top"> </div>
                <div class="side-bar-bot"></div>
            </div>
    <div class="mid">
                    <div class="mid-top">
                        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
                                        class="active" aria-current="true" aria-label="Slide 1"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                                        aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                                        aria-label="Slide 3"></button>
                            </div>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dang-cap-hien-dai_113856116.png"
                                         class="d-block w-100" alt="...">
                                </div>
                                <div class="carousel-item">
                                    <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dang-cap-hien-dai_113856116.png"
                                         class="d-block w-100" alt="...">
                                </div>
                                <div class="carousel-item">
                                    <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dang-cap-hien-dai_113856116.png"
                                         class="d-block w-100" alt="...">
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>
        <div class="mid-bot">
            <div class="row">
                <c:forEach items="${list}" varStatus="loop" var="item">
                    <div class="card" style="width: 18rem">
                        <img src="${item.image}" class="card-img-top" alt="..." />
                        <div class="card-body">

                            <h5 class="card-title">${item.name}</h5>
                                <%--                                <h5 class="card-title">${item.id}</h5>--%>
                            <h4 class="card-title">${item.title}</h4>
                            <h3 class="card-title">${item.price}</h3>
                            <p class="card-text">
                                    ${item.description}
                            </p>
                            <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                        </div>
                        <div style="display: flex; justify-content: space-between;">
                            <a href="http://localhost:8080/products?path=edit&id=${item.id}" class="btn btn-primary edit">Sửa</a>
                            <a href="http://localhost:8080/products?path=delete&id=${item.id}" class="btn btn-primary red">Xóa</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
</div>
<%--        <div class="right-main"></div>--%>
</div>
<div class="footer">
    <div class="left-footer">
        <img src="/logo.webp" alt="">
        <p>Shop Thời trang và phụ kiện Alena</p>
        <div class="contact-info">
            <div class="info-item">
                <i class="fas fa-map-marker-alt"></i>
                <span>Tầng 6, Tòa nhà Ladeco, 266 Đội Cấn, Phường Liễu Giai, Quận Ba Đình, TP Hà Nội</span>
            </div>
            <div class="info-item">
                <i class="fas fa-clock"></i>
                <span>Giờ làm việc: Từ 9:00 đến 22:00 các ngày trong tuần từ Thứ 2 đến Chủ nhật</span>
            </div>
            <div class="info-item">
                <i class="fas fa-phone-alt"></i>
                <span>Hotline 1900 6750</span>
            </div>
        </div>

    </div>
    <div class="middle-footer">
        <div class="about">
            <h2>Về chúng tôi</h2>
            <ul>
                <li><a href="#">Trang chủ</a></li>
                <li><a href="#">Thời trang Nam</a></li>
                <li><a href="#">Sản phẩm</a></li>
                <li><a href="#">Bé trai</a></li>
                <li><a href="#">Bé gái</a></li>
                <li><a href="#">Tin tức</a></li>
                <li><a href="#">Liên hệ</a></li>
            </ul>
        </div>
        <div class="support">
            <h2>Hỗ trợ khách hàng</h2>
            <ul>
                <li><a href="#">Trang chủ</a></li>
                <li><a href="#">Thời trang Nam</a></li>
                <li><a href="#">Sản phẩm</a></li>
                <li><a href="#">Bé trai</a></li>
                <li><a href="#">Bé gái</a></li>
                <li><a href="#">Tin tức</a></li>
                <li><a href="#">Liên hệ</a></li>
            </ul>
        </div>

    </div>
    <div class="right-footer">

        <h2>Dịch vụ</h2>
        <ul>
            <li><a href="#">Trang chủ</a></li>
            <li><a href="#">Thời trang Nam</a></li>
            <li><a href="#">Sản phẩm</a></li>
            <li><a href="#">Bé trai</a></li>
            <li><a href="#">Bé gái</a></li>
            <li><a href="#">Tin tức</a></li>
            <li><a href="#">Liên hệ</a></li>
        </ul>
        <div class="social-icons">
            <a href="#"><i class="fab fa-youtube"></i></a>
            <a href="#"><i class="fab fa-instagram"></i></a>
            <a href="#"><i class="fab fa-facebook-f"></i></a>
        </div>
    </div>
</div>
</body>
</html>
