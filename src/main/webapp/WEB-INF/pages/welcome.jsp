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
    <title>冰山流媒体</title>
    <meta name="keywords" content="冰山去水印助手">
    <meta name="description" content="冰山去水印助手，完美还原视频的最初模样~">
    <link rel="shortcut icon" href="http://www.dybug/favicon.ico" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.12.4.js"></script>
    <script src="<%=request.getContextPath() %>/js/mui.min.js"></script>
    <link href="<%=request.getContextPath() %>/css/mui.min.css" rel="stylesheet"/>
    <link href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet"/>
    <script src="https://lib.baomitu.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://lib.baomitu.com/layer/3.1.1/layer.js"></script>
	<style>
		#topNav{
			height: 50px;
			width: 100%;
			margin: 0 auto;
			background: #008080;
		}
		#topNav a{
			display: inline-block;
			height: 100%;
			vertical-align:middle;
			line-height: 50px;
			width: 49%;
			text-align: center;
			color: white;
			font-family: "微软雅黑";
		}
		#topNav a:hover{
			text-decoration: none;
		}
		.on{
			background: #009ACD;
		}
		#shuiyin{
			display: none;
		}
		
		
		
		#box{
			width: 300px;
			margin: 20px auto 20px;
		}
		ul,li{
			display: block;
			margin: 0 auto;
		}
		li{
			border: 1px solid black;
			height: 200px;
			width: 200px;
		}
		.pic{
			display: block;
			margin: 20px auto 20px;
		}
		p{
			text-align: center;
			color: black;
			font-weight: bold;
			font-family: "微软雅黑";
			font-size: 20px;
		}
		h3{
			text-align: center;
			margin-top: 25px;
		}
		
		#movie nav{
			border-bottom:  1px solid #7D26CD;
			height: 40px;
			line-height: 40px;
		}
		#movie nav a{
			display: inline-block;
			width: 32%;
			font-family: "微软雅黑";
			text-align: center;
			text-decoration:none;
			color: blue;
		}
		#movie nav a:hover{
			text-decoration: underline;
		}
		#movie nav .beSelected{
			text-decoration: underline;
			color: red;
		}
		table{
			display: block;
			margin: 20px auto;
			width: 360px;
		}
		tr{
			display: block;
			height: 200px;
			width: 350px;
			margin: 0 auto;
			padding: 0;
		}
		td{
			display: inline-block;
			margin: 0 auto;
			width: 110px;
			height: 200px;
			text-align: center;
		}
		td img{
			display: block;
			margin: 10px auto;
		}
		td span{
			display: block;
			font-size: 14px;
			margin-top: -20px;
		}
		#down{
			text-align: center;
		}
		#down a{
			text-decoration: none;
			color: red;
			font-family: "微软雅黑";
		}
		#down a:hover{
			text-decoration: none;
		}
		#zhezhao{max-width: 100%; height: auto;}
	</style>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
      	$(function(){
      		getMovies(1);
      		$("#topNav a").click(function(){
      			$(this).siblings("a").removeClass("on");
      			$(this).addClass("on");
      		});
      		$(".shuiyin").click(function(){
      			$("#movie").hide();
      			$("#shuiyin").show();
      		});
      		$(".movie").click(function(){
      			$("#shuiyin").hide();
      			$("#movie").show();
      		});
      		$("#movie nav a").click(function(){
      			$(this).siblings("a").removeClass("beSelected");
      			$(this).addClass("beSelected");
      		});
      	});
      	function toPage(id){
      		location="ice/moviedetails?id="+id;
      	}
      	function getMovies(type){
      		$.ajax({
				type:"post",
				dataType: "json",
				url:"ice/getMovieList",  
				data:{
					"type":type
				},
				async:true,
				success:function(res){
					var table=$("table");
					var tr=$("<tr></tr>");
					for(var i=0;i<res.length;i++){
						var td=$("<td onclick='toPage("+res[i].id+")'><img src='<%=request.getContextPath() %>/moviepic/"+res[i].picpath+".jpg' width='100px' height='150px' /><br /><span>"+res[i].name+"</span></td>");
						tr.append(td);
						if((i+1)%3==0){
							table.append(tr);
							tr=$("<tr></tr>");
						}
					}
				}
			});
      	}
      	function selType(type){
      		$("table").remove();
      		var table=$("<table></table>");
      		$("#movie").append(table);
      		getMovies(type);
      	}
    </script>
</head>
<body>
	<header class="mui-bar mui-bar-nav">
        <h1 class="mui-title">冰山流媒体</h1>
    </header>
    <br /><br />
    <nav id="topNav">
		<a class="on movie">冰山影视</a>
		<a class="shuiyin" style="float: right;">冰山去水印助手</a>
	</nav>
	
	<div id="movie">
		<nav>
			<a onclick="selType(1)" class="beSelected">最热电影</a>
			<a onclick="selType(2)">最新电影</a>
			<a onclick="selType(3)">用户好评</a>
		</nav>
		<table>
		</table>
	</div>
	<div id="shuiyin">
		<h3>请选择您要解析的短视频的平台：</h3>
	    <div id="box">
	    	<ul>
	    		<li onclick="window.location='ice/video?type=1'">
	    			<img class="pic" src="<%=request.getContextPath() %>/logo/douyin.jpg" width="120px;" height="120px;"/>
	    			<p>抖音</p>
	    		</li>
	    		<li onclick="window.location='ice/video?type=2'">
	    			<img class="pic" src="<%=request.getContextPath() %>/logo/kuaishou.jpg" width="120px;" height="120px;"/>
	    			<p>快手</p>
	    		</li>
	    		<li onclick="window.location='ice/video?type=3'">
	    			<img class="pic" src="<%=request.getContextPath() %>/logo/huoshan.jpg" width="120px;" height="120px;"/>
	    			<p>火山</p>
	    		</li>
	    	</ul>
	    </div>
	</div>
    <div id="down">
		<a href='<%=request.getContextPath() %>/apk/icemedia.apk' download="icemedia.apk">一键下载冰山流媒体APP</a>
	</div>
    <script type="text/javascript">
		function is_weixin() {
		    var ua = navigator.userAgent.toLowerCase();
		    if (ua.match(/MicroMessenger/i) == "micromessenger") {
		        return true;
		    } else {
		        return false;
		    }
		}
		var isWeixin = is_weixin();
		var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
		var weixinTip = $('<div id="weixinTip"><p><img id="zhezhao" src="<%=request.getContextPath() %>/images/live_weixin.png"  alt="请用浏览器打开"/></p></div>');
		
		if(isWeixin){
			$("body").append(weixinTip);
		}
		$("#weixinTip").css({
			"position":"fixed",
			"left":"0",
			"top":"0",
			"height":winHeight,
			"width":"100%",
			"z-index":"1000",
			"background-color":"rgba(0,0,0,0.8)",
			"filter":"alpha(opacity=80)",
		});
		$("#weixinTip p").css({
			"text-align":"center",
			"margin-top":"10%",
			"padding-left":"5%",
			"padding-right":"5%"
		});
	</script>
</body>
</html>