<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="user" />
	<c:param name="article" value="update" />
	<c:param name="content">
		<h2>利用者情報更新</h2>
		<section id="update">
			<h3>確認</h3>
			<p>以下の情報で変更します。</p>
			<p>変更する場合は［更新する］ボタンをクリックしてください。また、修正する場合は［戻る］ボタンをクリックしてください。</p>
			<form action="Controller" method="post">
				<table>
					<tr>
						<th>ID</th>
						<td>${sessionScope.member.id}</td>
					</tr>
					<tr>
						<th>利用者番号</th>
						<td>${sessionScope.member.card}</td>
					</tr>
					<tr>
						<th>利用者名</th>
						<td>${sessionScope.member.name}</td>
					</tr>
					<tr>
						<th>住所</th>
						<td>〒${sessionScope.member.zipcode} ${sessionScope.member.address}</td>
					</tr>
					<tr>
						<th>電話番号</th>
						<td>${sessionScope.member.phone}</td>
					</tr>
					<tr>
						<th>電子メール</th>
						<td>${sessionScope.member.email}</td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>
							<fmt:formatDate value="${sessionScope.member.birthday}" pattern="yyyy" />年
						 	<fmt:formatDate value="${sessionScope.member.birthday}" pattern="M" />月
						 	<fmt:formatDate value="${sessionScope.member.birthday}" pattern="d" />日
						 </td>
					</tr>
					<tr>
						<th>権限</th>
						<td>${sessionScope.member.priviledgeName}</td>
					</tr>
					<tr>
						<td colspan="2">
							<button formaction="Controller?service=member&action=edit&mode=complete">
								更新する
							</button>
							<button formaction="Controller?service=member&action=edit&mode=entry">
								戻る
							</button>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</c:param>
</c:import>