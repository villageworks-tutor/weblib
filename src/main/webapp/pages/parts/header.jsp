<%@page import="la.bean.SigninBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<h1>某図書館　図書管理システム</h1>
	<c:if test="${!empty sessionScope.signin}">
    <p><a href="Controller?service=top">トップメニューへ戻る</a>&nbsp:<a href="Controller?service=authenticate&action=signout">ログアウト</a></p>
    </c:if>
</header>
