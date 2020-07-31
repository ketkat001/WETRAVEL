<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Img테스트</title> 
</head>
<body>
<form action="/saveImage" enctype="multipart/form-data" method="post">
    <input type="file" name="imgFile" />
    <input type="submit" value="이미지저장"/>
</form>

<br>

<img alt="" src="/getByteImage" />
</body>
</html>