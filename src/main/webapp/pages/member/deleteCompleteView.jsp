<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="user" />
	<c:param name="article" value="" />
	<c:param name="content">
		<h2>利用者情報削除</h2>
		<section id="complete">
			<h3>完了</h3>
			<p>利用者カード番号 ${requestScope.card} の利用者の情報を削除しました。</p>
			<p><a href="Controller?service=member&action=search">利用者検索に戻る</a></p>
			<p>トップページに戻るには、ページ右上にあるリンクをクリックしてください。</p>
		</section>
	</c:param>
</c:import>