<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>用户登录</title>
<link rel="shortcut icon" href="images/ico.ico">
	<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/usercenter.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/Usericon/iconfont.css">
</head>
<body>
<div class="online-service"><a href="http://chat10.live800.com/live800/chatClient/chatbox.jsp?companyID=417447&configID=212669&lan=zh&jid=3516738910&enterurl=http%3A%2F%2Fwww.focussend.com%2F&timestamp=1500023952902&pagereferrer=http%3A%2F%2Fwww%2Efocussend%2Ecom%2F&firstEnterUrl=http%3A%2F%2Fwww%2Efocussend%2Ecom%2Fcase3a%2Ehtml&pagetitle=%E9%82%AE%E4%BB%B6%E8%90%A5%E9%94%80+%7C+%E7%94%B5%E5%AD%90%E9%82%AE%E4%BB%B6%E8%90%A5%E9%94%80+%7C+%E7%9F%AD%E4%BF%A1%E8%90%A5%E9%94%80+%7C+EDM%E8%90%A5%E9%94%80+%7C+Focussend%E5%85%A8%E7%90%83%E9%A2%86%E5%85%88%E7%9A%84%E6%99%BA%E8%83%BD%E5%8C%96%E8%90%A5%E9%94%80%E6%9C%8D%E5%8A%A1%E5%95%86" target="_blank"><i class="iconfont">&#xe680;</i></a></div>
<div class="home-logo">
	<a href="http://www.focussend.com"><i></i></a>
</div>
<div class="user-inter">
	<div class="user-interbox">
		<div class="user-center-block">
			<div class="user-center-box">
				<div class="user-win-block">
					<div class="user-win-box">
						<span class="f28">用户登陆</span>
						<span class="mb25 f14 f-gray">还没有账户？<a href="UserSign.html" class="f14 f-dblue">立即注册</a></span>
						<span class="mb20"><input type="text" placeholder="手机号 / 邮箱" value="手机号 / 邮箱" onfocus="if(this.value=='手机号 / 邮箱'){this.value='';};" onblur="if(this.value==''){this.value='手机号 / 邮箱';};" /></span>
						<span class="mb10"><input type="text" placeholder="密码" value="密码" onfocus="if(this.value=='密码'){this.value='';};" onblur="if(this.value==''){this.value='密码';};" /></span>
						<span class="txt-right mb20"><a href="Password.html" class="f12 f-gray">忘记密码？</a></span>
						<span class="mb10"><em class="f-red">您输入的账号或密码错误，请重新输入！</em></span>
						<span class="mb10"><a href="#" class="btn-blue-lg buttons">登 录</a></span>
						<span><a href="UserSign.html" class="f12 f-lgray">旧版登陆</a></span>
						<span class="f-gray l-heit2"><a href="#" class="f12 f-dblue"><i class="iconfont f-dblue f18">&#xe65d;</i> 联系在线客服</a> 或拨打客服电话：400 687 697</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
