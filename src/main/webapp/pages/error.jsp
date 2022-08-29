<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="message" />
	<c:param name="article" value="error" />
	<c:param name="content">
		<h2>システムエラー</h2>
		<section id="message-list">
			<p class="error"><c:out value="${requestScope.message}" /></p>
			<p><a href="Controller">ユーザ認証ページに戻る</a></p>
		</section>
	</c:param>
</c:import>
