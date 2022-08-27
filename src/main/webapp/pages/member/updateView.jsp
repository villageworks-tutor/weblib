<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="user" />
	<c:param name="article" value="update" />
	<c:param name="content">
		<h2>利用者情報更新</h2>
		<section>
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
						<td>
							<input type="text" name="name" value="${requestScope.member.name}" />
						</td>
					</tr>
					<tr>
						<th>住所</th>
						<td>
							〒<input type="text" name="postal" value="${requestScope.member.zipcode}" /><br />
							<textarea name="address">${requestScope.member.address}</textarea>
						</td>
					</tr>
					<tr>
						<th>電話番号</th>
						<td>
							<input type="text" name="phone" value="${requestScope.member.phone}" />
						</td>
					</tr>
					<tr>
						<th>電子メール</th>
						<td>
							<input type="text" name="email" value="${requestScope.member.email}" />
						</td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>
							<select name="year">
								<script>
									for (i = 1900; i <= 2019; i++) {
										if (i == <fmt:formatDate value="${requestScope.member.birthday}" pattern="yyyy" />) {
											document.write("<option value=\"" + i + "\" selected>" + i + "</option>");
										} else {
											document.write("<option value=\"" + i + "\">" + i + "</option>");
										}
									}
								</script>
							</select>年
							<select name="month">
								<script>
									for (i = 1; i <= 12; i++) {
										if (i == <fmt:formatDate value="${requestScope.member.birthday}" pattern="M" />) {
											document.write("<option value=\"" + i + "\" selected>" + i + "</option>");
										} else {
											document.write("<option value=\"" + i + "\">" + i + "</option>");
										}
									}
								</script>
							</select>月
							<select name="day">
								<script>
									for (i = 1; i <= 31; i++) {
										if (i == <fmt:formatDate value="${requestScope.member.birthday}" pattern="d" />) {
											document.write("<option value=\"" + i + "\" selected>" + i + "</option>");
										} else {
											document.write("<option value=\"" + i + "\">" + i + "</option>");
										}
									}
								</script>
							</select>日
						</td>
					</tr>
					<tr>
						<th>権限</th>
						<td>
							<c:choose>
							<c:when test="${requestScope.member.priviledgeCode == 1}">
							<input type="radio" name="priviledge" id="2" value="1" checked /><label for="2">一般利用者</label>
							<input type="radio" name="priviledge" id="1" value="0" /><label for="1">システム管理者</label>
							</c:when>
							<c:otherwise>
							<input type="radio" name="priviledge" id="2" value="1" /><label for="2">一般利用者</label>
							<input type="radio" name="priviledge" id="1" value="0" checked /><label for="1">システム管理者</label>
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>登録日</th>
						<td>${requestScope.member.createdAt}</td>
					</tr>
					<tr>
						<th>最終更新日</th>
						<td>${requestScope.member.updatedAt}</td>
					</tr>
					<tr>
						<td colspan="2">
							<button formaction="updateConfirmView.html" formmethod="post">確認画面に進む</button>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</c:param>
</c:import>