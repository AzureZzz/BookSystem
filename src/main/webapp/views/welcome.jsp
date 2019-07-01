<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../views/lib/html5shiv.js"></script>
    <script type="text/javascript" src="../views/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="../views/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="../views/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="../views/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../views/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="../views/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../views/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>Welcome</title>
</head>
<body>
<div class="page-container">
    <p class="f-20 text-success">欢迎来到图书借阅管理系统！</p>
    <%--<p>当前时间：2018-07-01 19:38</p>--%>
    <!--<p>登录次数：18 </p>-->
    <!--<p>上次登录IP：222.35.131.79.1  上次登录时间：2014-6-14 11:19:55</p>-->
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th colspan="7" scope="col">信息统计</th>
        </tr>
        <tr class="text-c">
            <th>统计</th>
            <th>用户</th>
            <th>借出</th>
            <th>归还</th>
            <th>预订</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-c">
            <td>总数</td>
            <td>${readerTotal}</td>
            <td>${borrowTotal}</td>
            <td>${returnTotal}</td>
            <td>${reserveTotal}</td>
        </tr>
        <tr class="text-c">
            <td>今日</td>
            <td>${readerToday}</td>
            <td>${borrowToday}</td>
            <td>${returnToday}</td>
            <td>${reserveToday}</td>
        </tr>
        <tr class="text-c">
            <td>昨日</td>
            <td>${readerYesterday}</td>
            <td>${borrowYesterday}</td>
            <td>${returnYesterday}</td>
            <td>${reserveYesterday}</td>
        </tr>
        <tr class="text-c">
            <td>本周</td>
            <td>${readerWeek}</td>
            <td>${borrowWeek}</td>
            <td>${returnWeek}</td>
            <td>${reserveWeek}</td>
        </tr>
        <tr class="text-c">
            <td>本月</td>
            <td>${readerMonth}</td>
            <td>${borrowMonth}</td>
            <td>${returnMonth}</td>
            <td>${reserveMonth}</td>
        </tr>
        </tbody>
    </table>
</div>
<footer class="footer mt-20">
    <div class="container">
        <p>数据库系统原理综合实验设计——1600300128王涛</p>
    </div>
</footer>
<script type="text/javascript" src="../views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../views/static/h-ui/js/H-ui.min.js"></script>

</body>
</html>
