<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>카페 메뉴판</title>
    <link rel="stylesheet" href="/css/style.css"> <!-- 여기로 연결 -->
</head>
<body>
<h1>🍵 카페 메뉴판</h1>

<c:choose>
    <c:when test="${empty items}">
        <p class="empty-message">해당 메뉴는 다 떨어졌습니다.</p>
    </c:when>
    <c:otherwise>
        <ul class="menu-list">
            <c:forEach var="item" items="${items}">
                <li class="menu-item ${item.soldOut ? 'soldout' : ''}">
                    <h3>
                        <a href="/menus/detail/${item.id}">${item.name}</a> <!-- 상세보기 링크 -->
                    </h3>
                    <p>${item.description}</p>
                    <p>가격: ${item.price}원</p>
                    <p>상태: <c:choose>
                        <c:when test="${item.soldOut}">❌ 품절</c:when>
                        <c:otherwise>✅ 판매중</c:otherwise>
                    </c:choose></p>
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

</body>
</html>
