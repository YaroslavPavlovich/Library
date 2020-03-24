<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
    <c:choose>
        <c:when test="${empty book.title}">
            <title>Добавить</title>
        </c:when>
        <c:otherwise>
            <title>Редактировать</title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<c:url value="/add" var="addUrl"/>
<c:url value="/edit" var="editUrl"/>
<form class="style" action="${empty book.title ? addUrl : editUrl}" name="book" method="POST">
    <c:choose>
        <c:when test="${!empty book.title}">
            <p class="heading">Редактировать книгу</p>
            <input type="hidden" name="id" value="${book.id}">
        </c:when>
        <c:otherwise>
            <p class="heading">Добавить книгу</p>
        </c:otherwise>
    </c:choose>
    <p><input type="text" name="title" placeholder="Название" value="${book.title}" maxlength="100" required autofocus
              pattern="^[^\s]+(\s.*)?$">
    <p><input type="number" name="year" placeholder="Год" value="${book.year}" maxlength="4" required
              oninput="if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);">
    <p><input type="text" name="author" placeholder="Автор" value="${book.author}" maxlength="20" required>
    <p class="checkbox">
        <label for="watched">Прочитано
            <c:if test="${book.watched == true}">
                <input type="checkbox" name="watched" id="watched" value="${book.watched}" checked>
            </c:if>
            <c:if test="${book.watched != true}">
                <input type="checkbox" name="watched" id="watched">
            </c:if>
            <span class="checkbox-common checkbox-no">Нет</span>
            <span class="checkbox-common checkbox-yes">Да</span>
        </label>
    </p>
    <p>
        <c:set value="Добавить" var="add"/>
        <c:set value="Редактировать" var="edit"/>
        <input type="submit" value="${empty book.title ? add : edit}">
    </p>
    <p class="heading">${message}</p>
</form>
</body>
</html>
