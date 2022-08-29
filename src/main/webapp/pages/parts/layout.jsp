<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>某図書館　図書管理システム</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css" />
	<script src="<%= request.getContextPath() %>/assets/js/function.js"></script>
</head>
<body>
	<!-- ヘッダ領域：相対パスの例 -->
	<c:import url="${requestScope.contextPath}/pages/parts/header.jsp" />
	<!-- コンテンツ領域 -->
	<main id="${param.main}">
		<article id="${param.article}">
			${param.content}
		</article>
	</main>
	<!-- フッタ領域：絶対パスの例 -->
	<c:import url="${requestScope.contextPath}/pages/parts/footer.jsp" />
</body>
</html>