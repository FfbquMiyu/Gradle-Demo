<%@ page contentType="text/html; charset=MS932" %>
<%@ page import="jp.co.insightech.*" %>
<%@ page import="java.util.*" %>

<%@ page import="jp.co.insightech.BBSInfoModel"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<% BBSInfoModel bbsInfo = (BBSInfoModel) request.getAttribute("BBSInfoModel"); %>

<html>
	<head>
		<title>掲示板</title>
		<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
	</head>
	<body>
		<center>
			<form:form action="./show" modelAttribute="BBSInfoModel">

			<h1>掲示板</h1>
			
			<c:forEach items="${BBSInfoModel.errorMessageList}" var="errorMessage">
				<p><font color="red">${errorMessage}</font></p>
			</c:forEach>
			
				<table border="1" bordercolor="black">
					<tr>
						<td>名前<font color="red">*</font></td>
						<td><form:input path="name" size="50"/></td>
					</tr>
					<tr>
						<td>メールアドレス</td>
						<td><form:input path="mailaddress" size="50"/></td>
					</tr>
					<tr>
						<td>件名</td>
						<td><form:input path="subject" size="50"/></td>
					</tr>
					<tr>
						<td>メッセージ<font color="red">*</font></td>
						<td><form:textarea path="content" rows="5" cols="40" /></td>
					</tr>
					<tr>
						<td>文字色</td>
						<td>
							<font color="black"><form:radiobutton path="wordColor" label="黒" value="black" checked="checked"/></font>
							<font color="red"><form:radiobutton path="wordColor" label="赤" value="red" /></font>
							<font color="green"><form:radiobutton path="wordColor" label="緑" value="green" /></font>
							<font color="blue"><form:radiobutton path="wordColor" label="青" value="blue" /></font>
							<font color="purple"><form:radiobutton path="wordColor" label="紫" value="purple" /></font>
						</td>
					</tr>
					<tr>
						<td>パスワード<font color="red">*</font></td>
						<td><form:password path="password" size="10"/></td>
					</tr>
				</table>
				<input type="submit" name="sendMessage" value="投稿/更新">
				<input type="submit" value="リセット">
				<br>
				<font color="red">*</font>印は、入力必須項目です。
			</form:form>
			
			<hr>
				
			<form:form action="./show" modelAttribute="BBSInfoModel">
				<c:forEach items="${BBSInfoModel.messageList}" var="message">
					<table border=1 bordercolor="${message.wordColor}" width="80%">
						<tr><td width="20%">名前</td><td><c:out value="${message.name}"/></td></tr>
						<tr><td>メールアドレス</td><td><c:out value="${message.mailaddress}"/></td></tr>
						<tr><td>件名</td><td><c:out value="${message.subject}"/></td></tr>
						<tr><td>メッセージ</td><td><font color="${message.wordColor}"><c:out value="${message.content}"/></font></td></tr>
						<tr><td>投稿日時</td><td><fmt:formatDate value="${message.postDate}" pattern="yyyy年MM月dd日(E) aK時mm分ss秒" /></td></tr>
						<tr><td>パスワード</td><td><input type="password" name="sendPassword${message.id}" size="10">
							<button type="submit" name="deleteId" value="${message.id}">削除</button></td></tr>
					</table><br>
				</c:forEach>
			</form:form>
		</center>
	</body>
</html>

