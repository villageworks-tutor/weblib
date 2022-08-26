<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="${requestScope.contextPath}/pages/parts/layout.jsp">
	<c:param name="main" value="user" />
	<c:param name="article" value="search" />
	<c:param name="content">
      <h2>利用者検索</h2>
      <section id="condition">
        <h3>条件入力</h3>
        <p>検索したい利用者の利用者カード番号を入力して［検索］ボタンを押してください。<br />利用者カード番号を入力せずに［検索］ボタンを押すと全利用者が対象となります。</p>
        <form action="Controller" method="post">
          <input type="text" name="key" value="12058021" /><button type="submit">検索</button>
          <input type="hidden" name="service" value="member" />
          <input type="hidden" name="action" value="search" />
          <input type="hidden" name="mode" value="result" />
        </form>
      </section>
    </c:param>
</c:import>
