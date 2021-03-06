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
	- 회원정보 수정하기 (View) -
	<form action='${cpath }/update.do' method='post'>
		<table>
			<input type='hidden' name='num' value='${vo.getNum() }' />
			<tr>
				<td>번호</td>
				<td>${vo.getNum() }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${vo.getName() }</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type='text' name='phone' value='${vo.getPhone() }' /></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type='text' name='addr' value='${vo.getAddr() }'
					size="50" /></td>
			</tr>
			<tr>
				<td>위도</td>
				<td>${vo.getLat() }</td>
			</tr>
			<tr>
				<td>경도</td>
				<td>${vo.getLng() }</td>
			</tr>

			<tr>
				<td align='center' colspan='2'><input type='submit'
					value='수정하기' /> <input type='reset' value='취소' /> <a
					href='${cpath }/list.do'>[리스트]</a></td>
			</tr>
		</table>
	</form>




</body>
</html>