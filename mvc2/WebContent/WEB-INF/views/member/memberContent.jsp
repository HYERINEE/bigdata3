<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	- ȸ������ �����ϱ� (View) -
	<form action='${cpath }/update.do' method='post'>
		<table>
			<input type='hidden' name='num' value='${vo.getNum() }' />
			<tr>
				<td>��ȣ</td>
				<td>${vo.getNum() }</td>
			</tr>
			<tr>
				<td>�̸�</td>
				<td>${vo.getName() }</td>
			</tr>
			<tr>
				<td>��ȭ��ȣ</td>
				<td><input type='text' name='phone' value='${vo.getPhone() }' /></td>
			</tr>
			<tr>
				<td>�ּ�</td>
				<td><input type='text' name='addr' value='${vo.getAddr() }'
					size="50" /></td>
			</tr>
			<tr>
				<td>����</td>
				<td>${vo.getLat() }</td>
			</tr>
			<tr>
				<td>�浵</td>
				<td>${vo.getLng() }</td>
			</tr>

			<tr>
				<td align='center' colspan='2'><input type='submit'
					value='�����ϱ�' /> <input type='reset' value='���' /> <a
					href='${cpath }/list.do'>[����Ʈ]</a></td>
			</tr>
		</table>
	</form>




</body>
</html>