
<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/views/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/views/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${ctx}/views/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/views/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/views/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/views/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${ctx}/views/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="${ctx}/views/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>图书查看</title>
</head>
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
    <img src="${ctx}/views/static/h-ui/images/ucnter/avatar-default.jpg">
</div>
<div class="pd-20">
    <table class="table">
        <tbody>
        <tr>
            <th class="text-r" width="80">图书编号：</th>
            <td>${book.bookNo}</td>
        </tr>
        <tr>
            <th class="text-r" width="80">图书名称：</th>
            <td>《${book.bookInfo.name}》</td>
        </tr>
        <tr>
            <th class="text-r">作者：</th>
            <td>${book.bookInfo.writer}</td>
        </tr>
        <tr>
            <th class="text-r">出版社：</th>
            <td>${book.bookInfo.press}</td>
        </tr>
        <tr>
            <th class="text-r">出版时间：</th>
            <td><fmt:formatDate value="${book.bookInfo.pubTime}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
            <th class="text-r">页数：</th>
            <td>${book.bookInfo.pageNum}</td>
        </tr>
        <tr>
            <th class="text-r">价格：</th>
            <td>￥${book.bookInfo.piece}</td>
        </tr>
        <tr>
            <th class="text-r">索书号：</th>
            <td>${book.bookInfo.callNum}</td>
        </tr>
        <tr>
            <th class="text-r">ISBN：</th>
            <td>${book.bookInfo.isbn}</td>
        </tr>
        <tr>
            <th class="text-r">馆藏地：</th>
            <td>${book.place}</td>
        </tr>
        <tr>
            <th class="text-r">状态：</th>
            <td>
                <c:if test="${book.state} == 0">
                    入藏
                </c:if>
                <c:if test="${book.state} == 1">
                    借出
                </c:if>
            </td>
        </tr>
        <tr>
            <th class="text-r">摘要：</th>
            <td>${book.bookInfo.digest}</td>
        </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript" src="${ctx}/views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${ctx}/views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/views/static/h-ui.admin/js/H-ui.admin.js"></script>
</body>
</html>
