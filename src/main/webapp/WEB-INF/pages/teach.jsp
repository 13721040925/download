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
	    <title>在线${typename }短视频去水印</title>
	    <meta name="keywords" content="冰山去水印助手">
	    <meta name="description" content="冰山去水印助手，完美还原视频的最初模样~">
	    <link rel="shortcut icon" href="http://www.dybug/favicon.ico" />
	    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.12.4.js"></script>
	    <script src="<%=request.getContextPath() %>/js/mui.min.js"></script>
	    <link href="<%=request.getContextPath() %>/css/mui.min.css" rel="stylesheet"/>
	    <link href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet"/>
	    <script src="https://lib.baomitu.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://lib.baomitu.com/layer/3.1.1/layer.js"></script>
	    <script type="text/javascript" charset="utf-8">
	      	mui.init();
	      	function QQ(){
	      		if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
				    var u = navigator.userAgent;

		            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
		            if(isiOS){
		                if(u.toLowerCase().match(/MicroMessenger/i) == "micromessenger"){
		                	window.open('mqq://im/chat?chat_type=wpa&uin=328407307@&version=1&src_type=web');
		                }else{
		                	window.open('mqq://im/chat?chat_type=wpa&uin=328407307@&version=1&src_type=web');
		                }
		            }else{
		                if(u.toLowerCase().match(/MicroMessenger/i) == "micromessenger"){
		                	window.open('mqqwpa://im/chat?chat_type=wpa&uin=328407307&version=1&src_type=web&web_src=oicqzone.com');
		                }else{
		                    window.open('mqqwpa://im/chat?chat_type=wpa&uin=328407307&version=1&src_type=web&web_src=oicqzone.com');
						} 
		            }
				} else{
					window.location="tencent://message/?uin=328407307&amp;Site=www.xxx.com&amp;Menu=yes/";
				}
			}
			function tel(){
				plus.device.dial("13721040925");
			}
	    </script>
	    <style>
	    	#box{
	    		margin-left: 10px;
	    	}
	    	p{
	    		margin: 10px auto;
	    		font-family: "微软雅黑";
	    		font-size: 20px;
	    		color: black;
	    		font-weight: bold;
	    	}
	    	.pic{
	    		display: block;
	    		margin: 0 auto;
	    	}
	    </style>
</head>
<body>
		<header class="mui-bar mui-bar-nav">
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
            <h1 class="mui-title">使用教程</h1>
        </header>
        <br /><br /><br />
        <div id="box">
        	<p>
        		<span>1.打开${typename }视频分享，点击复制链接</span><br /><br />
        		<img class="pic" src="<%=request.getContextPath() %>/image/1${type }.png" width="250px;" height="398px;"/>
        	</p>
        	<p>
        		<span>2.打开冰山去水印助手，点击${typename }图标</span><br /><br />
        		<img class="pic" src="<%=request.getContextPath() %>/image/2${type }.png" width="250px;" height="398px;"/>
        	</p>
        	<p>
        		<span>3.将链接粘贴到搜索框中，点击解析按钮</span><br /><br />
        		<img class="pic" src="<%=request.getContextPath() %>/image/3${type }.png" width="250px;" height="398px;"/>
        	</p>
        	<p>
        		<span>4.点击<img src="<%=request.getContextPath() %>/image/ico.png"/>图标，下载无水印视频到本地相册</span><br /><br />
        		<img class="pic" src="<%=request.getContextPath() %>/image/4.jpg" width="250px;" height="398px;"/><br /><br />
        		<img class="pic" src="<%=request.getContextPath() %>/image/5.jpg" width="250px;" height="398px;"/>
        	</p>
        </div>
		<br /><br /><br />
		<nav class="mui-bar mui-bar-tab">
	        <a class="mui-tab-item" id="tel" onclick="tel()">
	            <span class="mui-icon mui-icon-phone"></span>
	            <span class="mui-tab-label">电话客服</span>
	        </a>
	        <a class="mui-tab-item" id="qq" onclick="QQ()">
	            <span class="mui-icon mui-icon-qq"></span>
	            <span class="mui-tab-label">QQ客服</span>
	        </a>
	    </nav>
</body>
</html>