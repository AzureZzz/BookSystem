<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:22
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
    <link rel="Bookmark" href="../views//favicon.ico" >
    <link rel="Shortcut Icon" href="../views//favicon.ico" />
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
    <title>图书管理系统</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">图书借阅管理系统</a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onclick="bood_add('新增图书','bookAdd','','510')"><i class="Hui-iconfont">&#xe616;</i> 图书</a></li>
                            <li><a href="javascript:;" onclick="reader_add('新增读者','readerAdd','550','350')"><i class="Hui-iconfont">&#xe60d;</i> 读者</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li>图书管理员</li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A">${admin.admin}<i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="/admin/logout">退出</a></li>
                        </ul>
                    </li>
                    <!--<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>-->
                    <li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe616;</i> 借阅管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="borrowList" data-title="借阅管理" href="javascript:void(0)">借阅管理</a></li>
                    <li><a data-href="reserveList" data-title="预定管理" href="javascript:void(0)">预订管理</a></li>
                    <%--<li><a data-href="overList" data-title="预定管理" href="javascript:void(0)">超期管理</a></li>--%>
                    <li><a data-href="borrowAdd" data-title="借书登记" href="javascript:void(0)">借书登记</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe6e5;</i> 图书管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="bookList" data-title="图书管理" href="javascript:void(0)">图书管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-reader">
            <dt><i class="Hui-iconfont">&#xe60d;</i> 读者管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="readerList" data-title="会员列表" href="javascript:;">读者列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-tongji">
            <dt><i class="Hui-iconfont">&#xe61a;</i> 统计管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="borrowLine" data-title="借阅统计" href="javascript:void(0)">综合统计</a></li>
                    <%--<li><a data-href="hotKind" data-title="热门分类" href="javascript:void(0)">热门分类</a></li>--%>
                    <li><a data-href="hotReader" data-title="十大读者" href="javascript:void(0)">十大读者</a></li>
                    <li><a data-href="hotBook" data-title="十大好书" href="javascript:void(0)">十大好书</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="welcome.html">我的桌面</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前 </li>
        <li id="closeall">关闭全部 </li>
    </ul>
</div>

<script type="text/javascript" src="../views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../views/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="../views/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">

    /*个人信息*/
    function myselfinfo(){
        layer.open({
            type: 1,
            area: ['300px','200px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4,
            title: '查看信息',
            content: '<div>这家伙很懒什么都没写！</div>'
        });
    }

    function change_password(title,url,id,w,h){
        layer_show(title,url,w,h);
    }

    /*图书-添加*/
    function bood_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*读者-添加*/
    function reader_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

</script>

</body>
</html>
