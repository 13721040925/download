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
    <style>
        * {
            margin: 0;
            padding: 0;
        }
 
 
        .box {
            width: 250px;
            height: 398px;
            margin: 10px auto;
            background: black;
            border: 1px solid black;
        }
 
        .contro1 {
            width: 100%;
            height: 50px;
            background: #fff;
            display: flex;
            border-top: 1px solid #fff;
        }
 
        .contro1 .right,
        .contro1 .left {
            flex-basis: 50px;
            background-color: #000;
            color: #fff;
            text-align: center;
            line-height: 50px;
            font-size: 20px;
        }
 
        .contro1 .progress {
            flex: 1;
        }
 
        .contro1 .current {
            width: 0%;
            height: 50px;
            background-color: gray;
        }
        #teach{
        	margin: 0 auto;
        	width: 100px;
        }
        .icon{
        	font-family: "微软雅黑";
        	color: red;
        }
        .icon:hover{
        	cursor: pointer;
        }
    </style>
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
		
		
		$(function(){
			var type="${type}";
			$("#magic").click(function(){
				var urls=$("#url").val();
				if(urls==null||urls.trim()==""){
					alert("请先复制链接！");
					return;
				}
				if(type=="2"){
					urls=urls.substring(urls.indexOf("http"),urls.lastIndexOf(" 复制"));
					urls+="/";
				}
				$.ajax({
						type:"get",
						dataType: "json",
						url:"ice/magic",  
						data:{
							"urls":urls,
							"type":type
						},
						async:true,
						success:function(res){
							var container=$(".container");
							var box=$("<div class='box'><video height='398px;' width='249px;' class='b-player-video' src='<%=request.getContextPath() %>/video/"+res+".mp4' type='video/mp4' autoplay='autoplay' poster='<%=request.getContextPath() %>/img/"+res+".jpg' preload='none' height='100%' webkit-playsinline='true' playsinline='true' x-webkit-airplay='true' controls='true'></video></div><div id='str'></div></div>");
							container.append(box);
						}
				});
			});
			$(".icon").click(function(){
				location="ice/teach?type="+type;
			});
		});
    </script>
</head>
<body>
		<header class="mui-bar mui-bar-nav">
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
            <h1 class="mui-title">${typename }短视频在线解析</h1>
        </header>
        <br /><br /><br />
        <div class="container">
        	<div class="jumbotron">
		        <p>完美还原视频的最初模样~</p>
		    </div>
		    <input class="form-control" type="text" id="url" placeholder="请将APP里复制的视频链接粘贴到这里"><br>
		    <button type="submit" class="btn btn-success btn-lg btn-block" id="magic">解析！</button><br>
		    <div id="teach">
		    	<i class="icon ion-help-circled">使用教程</i>
		    </div>
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