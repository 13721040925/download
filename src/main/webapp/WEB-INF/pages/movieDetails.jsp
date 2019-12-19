<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://lib.baomitu.com/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://lib.baomitu.com/bootstrap-table/1.12.1/bootstrap-table.min.css" rel="stylesheet">
	<title>电影详情</title>
	<meta name="keywords" content="冰山去水印助手">
    <meta name="description" content="冰山去水印助手，完美还原视频的最初模样~">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.12.4.js"></script>
    <script src="<%=request.getContextPath() %>/js/mui.min.js"></script>
    <link href="<%=request.getContextPath() %>/css/mui.min.css" rel="stylesheet"/>
    <link href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet"/>
    <script src="https://lib.baomitu.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://lib.baomitu.com/layer/3.1.1/layer.js"></script>
	<style>
		#box{
	    		width: 90%;
	    		margin: 0 auto;
	    	}
	    	#name{
	    		font-size: 25px;
	    		font-weight: bold;
	    		text-align: center;
	    	}
	    	.left{
	    		display: inline-block;
	    		width: 43%;
	    		text-align: left;
	    		float: left;
	    		position: relative;
	    		top: 30px;
	    	}
	    	.right{
	    		display: inline-block;
	    		width: 55%;
	    		position: relative;
	    		top: 30px;
	    		font-family: "微软雅黑";
	    		font-size: 15px;
	    		color: black;
	    	}
	    	img{
	    		display: block;
	    		margin: 0 auto;
	    	}
	</style>
	<script type="text/javascript">
		mui.init();
		var id="${movie.id}";
		$(function(){
			$("img").click(function(){
				location="ice/playvideo?id="+id;
			});
		});
		
	</script>
</head>
<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
	        <h1 class="mui-title">电影详情</h1>
	    </header>
	    <br /><br /><br />
	    <div id="box">
	    	<h1 id="name">${movie.name }</h1>
	    	<div class="left">
	    		<img src="<%=request.getContextPath() %>/moviepic/${movie.picpath}.jpg" width="150px;" height="200px" />
	    	</div>
	    	<div class="right">
	    		<span>${movie.detail }</span>
	    	</div>
	    </div>
</body>
</html>