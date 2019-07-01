<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>热门分类</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 统计管理 <span class="c-gray en">&gt;</span> 热门分类 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div id="container" style="min-width:700px;height:400px"></div>
</div>

<script type="text/javascript" src="../views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../views/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="../views/lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="../views/lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript" src="../views/lib/hcharts/Highcharts/5.0.6/js/highcharts-3d.js"></script>
<script>
    ﻿﻿	$(function () {
        $('#container').highcharts({
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            title: {
                text: '截至目前借阅书籍数量分类统计百分比'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '占比',
                data: [
                    ['自然科学',   45.0],
                    ['艺术',       26.8],
                    {
                        name: '文学',
                        y: 12.8,
                        sliced: true,
                        selected: true
                    },
                    ['工具书',    8.5],
                    ['小说',     6.2],
                    ['其他',   0.7]
                ]
            }]
        });
    });
</script>
</body>
</html>
