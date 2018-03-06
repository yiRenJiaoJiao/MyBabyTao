<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/29
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
    <title></title>
 <%-- <meta charset="utf-8">--%>
  <title>Bootstrap 实例 - 标签式的导航菜单</title>
  <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<%--  <link href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css" rel="stylesheet">--%>
</head>
<body>

    <div id="container" style="width:1920px">

        <div id="header" style="background-color:#000000 ; height:100px;" >
            <h1 style="margin-bottom:0; color: #ffffff">主要的网页标题</h1></div>

        <div id="menu" style="background-color:#339966;height:800px;width:150px;float:left;">

            <ul>
                <li>菜单</li>
                <li>HTML</li>
                <li>CSS</li>
                <li>JavaScript</li>
            </ul>
        </div>
        <div style="background-color: #ffffff ; height: 800px;width:2px;float: left;">
        </div>

        <div id="content" style="background-color:#ffffff  ;height:800px;width:1620px;float:left;">
            <%--<button id="getjson"> 获取json 数据</button>--%>
            <div style="background-color: #ffffff; height:10px;width:100%" ></div>
           <div style="background-color:#888888 ; height: 295px ;width: 100%">
               我爱我家
           </div>
            <div style="background-color: #ffffff; height:10px;width:100%" ></div>
            <div style="background-color:#888888 ; height: 295px ;width: 100%">
                我爱我家
            </div>

            <div style="background-color:#888888 ; height: 295px ;width: 100%">
                我爱我家
            </div>
        </div>
        <div id="footer" style="background-color:#000000 ;clear:both;">
            版权 © runoob.com</div>

        </div>
    </body>
<script>

    $(function(){
        $.ajax({
            type: "get",
            async: false,
            url: "http://wx.vipmaillist.com/nodeCondition/findEdmTaskListByType?userId=1350",
            dataType: "jsonp",
            jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
            jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
            success: function(json){
                alert(json.emailTaskList);
            },
            error: function(){
                alert('fail');
            }
        });


      /*  $("#getjson").click(function(){
            $.ajax({
                type: "get",
                async: false,
                url: "http://v.juhe.cn/toutiao/index?type=top&key=1a1b9e19492b34d730797b4d4fa1e6f1",
                dataType: "jsonp",
                jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
                jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
                success: function(json){
                    alert(json.result);
                },
                error: function(){
                    alert('fail');
                }
            });
        })*/
    });
</script>


</html>
