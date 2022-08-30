<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="user" />
	<c:param name="article" value="" />
	<c:param name="content">
		<h2>利用者情報削除</h2>
		<section id="update">
			<h3>確認</h3>
			<p>以下の利用者を削除します。</p>
			<p>削除する場合は［削除する］ボタンをクリックしてください。また、削除しない場合は［戻る］ボタンをクリックすると利用者検索ページに戻ります。</p>
			<form>
				<table>
					<tr>
						<th>ID</th>
						<td>${requestScope.member.id}</td>
					</tr>
					<tr>
						<th>利用者番号</th>
						<td>${requestScope.member.card}</td>
					</tr>
					<tr>
						<th>利用者名</th>
						<td>${requestScope.member.name}</td>
					</tr>
					<tr>
						<th>住所</th>
						<td>〒${requestScope.member.zipcode} ${requestScope.member.address}</td>
					</tr>
					<tr>
						<th>電話番号</th>
						<td>${requestScope.member.phone}</td>
					</tr>
					<tr>
						<th>電子メールアドレス</th>
						<td>${requestScope.member.email}</td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>
							<fmt:formatDate value="${requestScope.member.birthday}" pattern="yyyy" />年
							<fmt:formatDate value="${requestScope.member.birthday}" pattern="M" />月
							<fmt:formatDate value="${requestScope.member.birthday}" pattern="d" />日
						</td>
					</tr>
					<tr>
						<th>権限</th>
						<td>一般利用者</td>
					</tr>
					<tr>
						<td colspan="2">
							<button formaction="deleteCompleteView.html" formmethod="post">削除する</button>
							<button formaction="Controller?service=member&action=search&mode=result" formmethod="post">戻る</button>
							<input type="hidden" name="key" value="${requestScope.member.card}" />
						</td>
					</tr>
				</table>
			</form>
		</section>
	</c:param>
</c:import>