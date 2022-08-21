<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="signin" />
	<c:param name="article" value="entry" />
	<c:param name="content">
		<h2>ユーザ認証</h2>
		<section id="entry">
			<c:if test="${not empty requestScope.errors}">
				<ol>
				<c:forEach items="${requestScope.errors}" var="error">
					<li>${pageScope.error}</li>
				</c:forEach>
				</ol>
			</c:if>
			<form name="signin_form" action="Controller" method="post">
				<dl>
					<dt>利用者番号</dt>
					<dd><input type="text" name="card" value="12056692" /></dd>
					<dt>パスワード</dt>
					<dd><input type="text" name="password" value="QnLmqZ9b" /></dd>
				</dl>
				<div>
					<button type="submit">サインイン</button>
					<input type="hidden" name="service" value="authenticate" />
					<input type="hidden" name="action" value="signin" />
				</div>
 			</form>
		</section>
	</c:param>
</c:import>

