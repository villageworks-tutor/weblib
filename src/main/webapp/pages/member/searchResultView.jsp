<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="user" />
	<c:param name="article" value="" />
	<c:param name="content">
		<h2>利用者検索</h2>
		<section id="condition">
			<h3>条件入力</h3>
			<p>検索したい利用者の電子メールアドレスを入力して［検索］ボタンを押してください。電子メールアドレスを入力せずに［検索］ボタンを押すと全利用者が対象となります。</p>
			<form>
				<input type="text" name="key" value="${requestScope.key}" /><button formaction="./searchResultView.html" formmethod="post">検索</button>
			</form>
		</section>
		<section id="result">
			<h3>検索結果</h3>
			<form>
				<table>
					<tr>
						<th>ID</th>
						<th>利用者名</th>
						<th>住所</th>
						<th>電話番号</th>
						<th>電子メールアドレス</th>
						<th class="date">生年月日</th>
						<th>処理選択</th>
					</tr>
					<tr>
						<td>${requestScope.member.id}</td>
						<td>${requestScope.member.name}</td>
						<td>〒${requestScope.member.zipcode} ${requestScope.member.address}</td>
						<td>${requestScope.member.phone}</td>
						<td>${requestScope.member.email}</td>
						<td>${requestScope.member.birthday}</td>
						<td>
							<form>
								<button formaction="./updateView.html" formmethod="post">編集</button>
								<button formaction="./deleteCOnfirmView.html" formmethod="post">削除</button>
							</form>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</c:param>
</c:import>