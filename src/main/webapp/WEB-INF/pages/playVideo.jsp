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
	<title>${movie.name}</title>
	<script src="<%=request.getContextPath() %>/js/jquery-1.12.4.js" type="text/javascript" charset="utf-8"></script>
        <script src="<%=request.getContextPath() %>/js/mui.min.js"></script>
        <link href="<%=request.getContextPath() %>/css/mui.min.css" rel="stylesheet" />
        <link href="<%=request.getContextPath() %>/css/video.css" rel="stylesheet" />
        <script src="<%=request.getContextPath() %>/js/html5video.js"></script> <!--//视频JS-->
	   <script src="<%=request.getContextPath() %>/js/hls.min.js"></script> <!--//如果需要播放m3u8格式需要引入这个JS解码,不需要可以去掉-->
        <link href="https://vjs.zencdn.net/7.4.1/video-js.css" rel="stylesheet">
    	<script src='https://vjs.zencdn.net/7.4.1/video.js'></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-contrib-hls/5.15.0/videojs-contrib-hls.min.js" type="text/javascript"></script>
		<script>     videojs.options.flash.swf = "https://www.miguvideo.com/wap/resource/pc/js/plugins/ckplayer.swf"; </script>
		<style>
	        .mui-bar{ box-shadow: none; background: white; height:50px;}
			.mui-bar .mui-icon{ margin-top:4px; font-size:28px; }
			.mui-bar-nav.mui-bar .mui-icon{padding-left:5px;}
			.mui-bar .mui-title{margin-top:6px;color: #2f2f2f; font-weight: normal;}
			.more{ margin-top: 16px; margin-right: 5px;}
			.mui-bar:after
			 {
				position: absolute;
				right: 0;
				 bottom: 0;
				left: 0;
				height: 1px;
				content: '';
				-webkit-transform: scaleY(.5);
				transform: scaleY(.5);
				background-color:#dadada; 	
			}
			body{ background: white; }
			.mui-content{ padding:0px;margin-top: 10px;background: white; }
			.user_txt{padding-left: 1rem; height: 25px; margin: 0px; margin-bottom: 20px;}
			.news_title{
				font-size: 1.4rem;
			    line-height: 1.5;
			    font-weight:500;
			    padding-left: 1rem;
			    padding-right: 1rem;
			    margin-top: .9rem;
			    margin-bottom: .3rem;
			    }
			.user_img{ width: 25px; height: 25px; border-radius: 25px; margin-right: 5px;}
			.user_name,.news_time{ position: relative; top: -8px; font-size: 12px;}
			.news_time{  margin-left: 10px;}
			.text_dome{ padding: 10px;}
			.text_dome b{ color: #000000; font-size: 16px;}
			#video_Container{margin-top:-3px ;}
			#openvideo{ height: 50px; line-height: 50px; color:white; text-align: center; position: fixed; z-index:110; bottom: 0px; width: 100%; background-color:#000000;}
     </style>
     <script type="text/javascript">
     	mui.init();
     	$(function(){
     		var tit="${movie.name}";
    		var imgs="<%=request.getContextPath() %>/moviepic/${movie.picpath}.jpg";
    		var urls="${movie.moviepath}";
    		htmlvideo(tit,urls,imgs);
     	});
     	function htmlvideo(tit,urls,imgs){
			Html5video("#video_Container",
		{
		 title:tit, //视频标题，当全屏时会显示  
		 url:urls, //支持本地视频和网络视频，格式MP4 
		 img:imgs, //视频截图封面 
		 //time:"02:30", //视频总时间以分钟单位，当网络缓慢时，没办法及时加载出来，如已知可以填写，可不填。
		 autoplay:false, //是否自动播放视频
		 isMobile:true, //是否开启当处于移动网络时，提示用户是否继续播放视频。,不支持浏览器环境
		 isFull:false, //是否点击播放就全屏显示
		 iospay:false, //如果是IOS系统是否采用苹果系统自带播放器, 可以在浏览器或微信中，全屏观看视频
		 drag:false, //是否打开 禁止拖动,调节,音量和亮度 右边亮度 左边音量 
		 isfull:true, //是否显示全屏按钮    
		 prompt:function(video) //当开启isMobile时,这里可以写提示用户的内容,h5+环境才有效
		 {  
		 	           mui.confirm('你当前处于移动手机网络将会消耗手机流量，是否继续播放？', '提示',["取消","确定"], function(e)
		 	           {
							if(e.index == 1)
							{
							  video.play();	//如果点的确定 就播放
							} 
					  },"div");		  
		 }
		});
		}
     </script>
</head>
<body>
	<header class="mui-bar mui-bar-nav">
    	    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    	    <h1 class="mui-title" id="videoName">${movie.name}</h1>
    	</header>
   <div class="mui-content" >
	 <!--以上无关，只为示例-->
	 
	 
   <!-- 视频播放区域-->
   <div class="mui-video-Container" id="video_Container">
   	<div class="mui-video-full">
   		<!--加载提示-->
   	<div class="rprogress">
   	<span class="ladtit">加载</span>
   	<div class="rschedule"></div>
	</div>
	<!--end-->
    <!--全屏时后退图标-->
   <span class="mui-full-back"> 
   <svg class="icon" aria-hidden="true">
  <use xlink:href="#icon-houtui1"></use>
   </svg>
    <span class="vtitle"></span>
  </span>
  <!--end-->
  <!--播放按钮-->
  <span class="mui-pay_ico"> 
   <svg class="icon" aria-hidden="true">
  <use xlink:href="#icon-bofang1"></use>
   </svg>
  </span>
  <!--end-->
  <div class="v_left"></div>
  <div class="b_right"></div>
  <div class="ptime"></div>
<div class="mui-video-txt"></div>
       <!-- 播放进度条-->
	   <div class="mui-videoControls">	
       <span class="mui-time-total mui-quanping"> <!--全屏按钮-->
			  <span class="mui-time-cout">00:00</span> <!--总时间-->
       		<span class="mui-full-btu" id="isFull">
					 <svg class="icon" aria-hidden="true">
		       <use xlink:href="#icon-quanping"></use>
		       </svg>
			</span>
       </span>
       <span class="mui-time-current">00:00</span> <!--进度时间-->
        <div class="mui-progressWrap"> <!--可以拖动的进度条-->
			<div class="mui-wrap-right">
	   			<input type="range" id='mui-block-range' value="0" min="0" max="100" >
		    </div>
      	</div> 
	   </div>  
	<!-- 播放进度条end-->
	</div> 
   </div>
   <!--视频播放区域end-->
	 
   </div>
</body>
</html>