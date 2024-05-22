<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Diet</title>
</head>
<!-- Header -->
<body>
<div class="container">
	<!-- 달력 : 누르면 해당 날짜에 대한 정보를 보여주는 페이지로 이동 -->
	<!-- 오늘의 식단 -->
	<!-- 버튼 : Food전체 List 보여줌 -->
	<button onclick="location.href='/diet/foods'">음식 목록 보기</button>
	<button onclick="location.href='/createFoodForm'">음식 추가 (페이지로 이동)</button>
	<button onclick="location.href='/createFoodCategoryForm'">카테고리 추가</button>
	
	
</div>
</body>
<!-- footer -->
</html>