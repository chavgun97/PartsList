
<%--
  Created by IntelliJ IDEA.
  User: Nikolaj
  Date: 21.09.2018
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="button" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Part List</title>
    <style type="text/css">
        #left{
            float: left;
            width: 44%;
            height: 100%;
            margin: 3%;
        }
        #right{
            width: 50%;
            height: 100%;
            float: right;
        }
        #addEdit{
            margin-top: 70px;

        }
        #sort{

            margin-top: 30px;
        }
        #search{
            float: left;
            margin-top: 10px;
        }

        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<div id ="left">
    <h1>Part list</h1>
    <c:if test = "${!empty listParts}">
        <table class = "tg">
            <tr>
                <th width="120">Наименование</th>
                <th width="120">Необходимость</th>
                <th width="80">Количество</th>
                <th width="60">Изменить</th>
                <th width="60">Удалить</th>
            </tr>
            <c:forEach items="${listParts}" var = "part">
            <tr>
                <td>${part.name}</td>
                <td>${part.necessarily}</td>
                <td>${part.count}</td>
                <td><a href="<c:url value='/edit/${part.id}'/>">Изменить</a></td>
                <td><a href="<c:url value='/remove/${part.id}'/>">Удалить</a></td>
            </tr>
            </c:forEach>
        </table>
    </c:if>

    <h2>Можно собрать:</h2><table class="tg">

        <tr>
            <th width="120">Можно собрать:</th>
            <th width="120">${countComputers}</th>
            <th width="80">Компьютеров</th>
            <th width="60"> </th>
            <th width="60"></th>
        </tr>
    </table>

    <a href="<c:url value="/previous" />"><--------Предыдущая страниц.............</a>
    <a href="<c:url value="/next" />">.............Следующая страница--------></a>
</div>

<div id = "right">
    <div id = "addEdit">
        <h1>Добавить деталь:</h1>
        <c:url var="addAction" value = "/parts/add"/>

        <form:form action="${addAction}" commandName="part">
            <table>
                <c:if test="${!empty part.name}">
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message text="ID"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        <form:label path="Name">
                            <spring:message text="Имя"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="Necessarily">
                            <spring:message text="Необходимость"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="necessarily"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="Count">
                            <spring:message text="Количество"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="count"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty part.name}">
                            <input type="submit"
                                   value="<spring:message text="Изменить деталь"/>"/>
                        </c:if>
                        <c:if test="${empty part.name}">
                            <input type="submit"
                                   value="<spring:message text="Добавить деталь"/>"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
    <div id = "sort">
        <h1>Сортировка:</h1>
        <h4><a href="<c:url value="/standardSort" />">Все детали </a></h4>
        <h4><a href="<c:url value="/onlyNecessSort" />">Только важные детали </a></h4>
        <h4><a href="<c:url value="/NotNecessSort" />">Функциональные детали </a></h4>
    </div>
    <div id = "search">
        <h1>Поиск:</h1>
        <form:form method="post" action="/parts/search" commandName="partName">
            <input name="name" type="text"/>
            <input type="submit" value="Поиск"/>
        </form:form>
        <h5 style="color:#ff0000">${msg}</h5>

    </div>
</div>
</body>
</html>
