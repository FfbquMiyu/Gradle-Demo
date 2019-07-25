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
		<title>�f����</title>
		<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
	</head>
	<body>
		<center>
			<form:form action="./show" modelAttribute="BBSInfoModel">

			<h1>�f����</h1>
			
			<c:forEach items="${BBSInfoModel.errorMessageList}" var="errorMessage">
				<p><font color="red">${errorMessage}</font></p>
			</c:forEach>
			
				<table border="1" bordercolor="black">
					<tr>
						<td>���O<font color="red">*</font></td>
						<td><form:input path="name" size="50"/></td>
					</tr>
					<tr>
						<td>���[���A�h���X</td>
						<td><form:input path="mailaddress" size="50"/></td>
					</tr>
					<tr>
						<td>����</td>
						<td><form:input path="subject" size="50"/></td>
					</tr>
					<tr>
						<td>���b�Z�[�W<font color="red">*</font></td>
						<td><form:textarea path="content" rows="5" cols="40" /></td>
					</tr>
					<tr>
						<td>�����F</td>
						<td>
							<font color="black"><form:radiobutton path="wordColor" label="��" value="black" checked="checked"/></font>
							<font color="red"><form:radiobutton path="wordColor" label="��" value="red" /></font>
							<font color="green"><form:radiobutton path="wordColor" label="��" value="green" /></font>
							<font color="blue"><form:radiobutton path="wordColor" label="��" value="blue" /></font>
							<font color="purple"><form:radiobutton path="wordColor" label="��" value="purple" /></font>
						</td>
					</tr>
					<tr>
						<td>�p�X���[�h<font color="red">*</font></td>
						<td><form:password path="password" size="10"/></td>
					</tr>
				</table>
				<input type="submit" name="sendMessage" value="���e/�X�V">
				<input type="submit" value="���Z�b�g">
				<br>
				<font color="red">*</font>��́A���͕K�{���ڂł��B
			</form:form>
			
			<hr>
				
			<form:form action="./show" modelAttribute="BBSInfoModel">
				<c:forEach items="${BBSInfoModel.messageList}" var="message">
					<table border=1 bordercolor="${message.wordColor}" width="80%">
						<tr><td width="20%">���O</td><td><c:out value="${message.name}"/></td></tr>
						<tr><td>���[���A�h���X</td><td><c:out value="${message.mailaddress}"/></td></tr>
						<tr><td>����</td><td><c:out value="${message.subject}"/></td></tr>
						<tr><td>���b�Z�[�W</td><td><font color="${message.wordColor}"><c:out value="${message.content}"/></font></td></tr>
						<tr><td>���e����</td><td><fmt:formatDate value="${message.postDate}" pattern="yyyy�NMM��dd��(E) aK��mm��ss�b" /></td></tr>
						<tr><td>�p�X���[�h</td><td><input type="password" name="sendPassword${message.id}" size="10">
							<button type="submit" name="deleteId" value="${message.id}">�폜</button></td></tr>
					</table><br>
				</c:forEach>
			</form:form>
		</center>
	</body>
</html>

