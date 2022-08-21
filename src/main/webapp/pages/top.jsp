<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="top" />
	<c:param name="article" value="switch" />
	<c:param name="content">
		<section id="user">
			<h2>利用者管理</h2>
			<ul>
				<li><a href="./pages/user/registView.html">登録</a></li>
				<li><a href="./pages/user/searchView.html">更新</a></li>
			</ul>
		</section>
		<section id="book">
			<h2>資料管理</h2>
			<ul>
				<li><a href="./pages/book/registView.html">登録</a></li>
				<li><a href="./pages/book/searchView.html">更新</a></li>
			</ul>
		</section>
		<section id="service">
			<h2>図書館業務</h2>
			<ul>
				<li><a href="./pages/operation/searchView.html">貸出</a></li>
				<li><a href="#">返却</a></li>
				<li><a href="./pages/service/bookCatalogueView.html">資料目録</a></li>
				<li><a href="./pages/service/bookMasterView.html">資料台帳</a></li>
			</ul>
		</section>
	</c:param>
</c:import>