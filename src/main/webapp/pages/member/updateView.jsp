<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="user" />
	<c:param name="article" value="update" />
	<c:param name="content">
		<h2>利用者情報更新</h2>
		<section>
			<form action="Controller?service=member" method="post">
				<c:if test="${!empty requestScope.messages}">
					<ol class="error">
					<c:forEach items="${requestScope.messages}" var="message">
						<li>${message}</li>
					</c:forEach>
					</ol>
				</c:if>
				<table>
					<tr>
						<th>ID</th>
						<td>
							${requestScope.member.id}
							<input type="hidden" name="id" value="${requestScope.member.id}" />
						</td>
					</tr>
					<tr>
						<th>利用者番号</th>
						<td>
							${requestScope.member.card}
							<input type="hidden" name="card" value="${requestScope.member.card}" />
						</td>
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
							〒<input type="text" name="zipcode" value="${requestScope.member.zipcode}" /><br />
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
									createYearOptions(new Date().getFullYear(), <fmt:formatDate value="${requestScope.member.birthday}" pattern="yyyy" />);
								</script>
							</select>年
							<select name="month">
								<script>
									createMonthOptions(<fmt:formatDate value="${requestScope.member.birthday}" pattern="M" />);
								</script>
							</select>月
							<select name="day">
								<script>
									createDayOptions(<fmt:formatDate value="${requestScope.member.birthday}" pattern="d" />);
								</script>
							</select>日
						</td>
					</tr>
					<tr>
						<th>権限</th>
						<td>
							<c:choose>
								<c:when test="${requestScope.member.priviledgeCode == 1}">
									<input type="radio" name="priviledgeCode" id="2" value="1" checked /><label for="2">一般利用者</label>
									<input type="radio" name="priviledgeCode" id="1" value="0" /><label for="1">システム管理者</label>
								</c:when>
								<c:otherwise>
									<input type="radio" name="priviledgeCode" id="2" value="1" /><label for="2">一般利用者</label>
									<input type="radio" name="priviledgeCode" id="1" value="0" checked /><label for="1">システム管理者</label>
								</c:otherwise>
							</c:choose>
							<input type="hidden" name="priviledgeName" value="${requestScope.member.priviledgeName}" />
						</td>
					</tr>
					<tr>
						<th>登録日</th>
						<td>
							${requestScope.member.createdAt}
							<input type="hidden" name="createdAt" value="${requestScope.member.createdAt}" />
						</td>
					</tr>
					<tr>
						<th>最終更新日</th>
						<td>
							${requestScope.member.updatedAt}
							<input type="hidden" name="updatedAt" value="${requestScope.member.updatedAt}" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" name="service" value="member">
								確認画面に進む
								<input type="hidden" name="action" value="edit" />
								<input type="hidden" name="mode" value="confirm" />
							</button>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</c:param>
</c:import>