<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Авторы</title>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
</head>
<body>
<table class="style">
    <caption class="heading">Авторы</caption>

        <tr>
            <td colspan="7" class="left-side link right-side">
                <form action="/searchAuthor" method="post">
                    <input type="text" name="author" placeholder="Название" value="${book.author}" maxlength="100" required>
                    <input type="submit" value="Поиск">
                </form>
                <a style="font-size: 100%" href="<c:url value="/"/>">
                    Перейти на главную страницу
                </a>
                <a style="font-size: 100%" href="<c:url value="/books"/>">
                    Перейти к списку книг
                </a>
            </td>
        </tr>
    <c:if test="${authorCount > 0}">
        <tr>
            <th class="left-side">№</th>
            <th>Автор произведений</th>
        </tr>
        <c:forEach var="author" items="${bookList}" varStatus="i">
            <tr>
                <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                <td>
                    <a style="font-size: 100%" href="<c:url value="/authorName/${author}"/>">
                        ${author}
                    </a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${authorCount == 0}">
        <tr>
            <td colspan="7" style="font-size: 150%" class="left-side right-side">
                Список авторов пуст, вы можете добавить новую книгу!
            </td>
        </tr>
    </c:if>
    <tr>
        <td colspan="7" class="left-side link right-side">
            <a style="margin-right: 70px; font-size: 100%" href="<c:url value="/add"/>">
                <span class="icon icon-add"></span>Добавить книгу
            </a>
            <c:if test="${pagesCount > 1}">
                <c:set value="disabled" var="disabled"/>
                <c:set value="" var="active"/>
                <c:url value="/authors" var="url">
                    <c:param name="page" value="1"/>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-first"></span>&nbsp
                </a>
                <c:url value="/authors" var="url">
                    <c:param name="page" value="${page - 1}"/>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-prev"></span>&nbsp
                </a>

                <c:if test="${pagesCount <= 5}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${pagesCount}"/>
                </c:if>
                <c:if test="${pagesCount > 5}">
                    <c:choose>
                        <c:when test="${page < 3}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:when>
                        <c:when test="${page > pagesCount - 2}">
                            <c:set var="begin" value="${pagesCount - 4}"/>
                            <c:set var="end" value="${pagesCount}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${page - 2}"/>
                            <c:set var="end" value="${page + 2}"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                    <c:url value="/authors" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <c:set value="current-page" var="current"/>
                    <c:set value="" var="perspective"/>
                    <a class="${page == i.index ? current : perspective}" href="${url}">${i.index}</a>
                </c:forEach>

                <c:url value="/authors" var="url">
                    <c:param name="page" value="${page + 1}"/>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-next"></span>&nbsp
                </a>
                <c:url value="/authors" var="url">
                    <c:param name="page" value="${pagesCount}"/>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-last"></span>&nbsp
                </a>
            </c:if>
            <span style="margin-left: 70px; font-size: 120%">Всего авторов: ${authorCount}</span>
        </td>
    </tr>
</table>
</body>
</html>
