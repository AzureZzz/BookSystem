<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:26
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
    <title>读者管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 读者管理 <span class="c-gray en">&gt;</span> 读者管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
			<a href="javascript:;" onclick="reader_add('添加读者','readerAdd','500','380')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加读者</a>
		</span>
        <span class="r">
            <a class="btn btn-success radius" href="/download/reader">
                <i class="Hui-iconfont">&#xe600;</i> 导出报表
            </a>
        </span>
    </div>
    <div class="mt-20">
        <table id="reader_table" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="100">读者名</th>
                <th width="40">姓名</th>
                <th width="90">读者类型</th>
                <th width="150">邮箱</th>
                <th width="">余额</th>
                <th width="130">借阅次数</th>
                <th width="130">未还数量</th>
                <th width="130">超期数量</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../views/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../views/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../views/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../views/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    $('#reader_table').dataTable({
        "language": {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        "paging": true,
        "aLengthMenu": [5,10, 25, 50],
        "lengthChange":true,
        "searching": true,
        "bSort": true,
        "bInfo": true,
        "bProcessing": false, // 加载条
        "bServerSide": true,//这个用来指明是通过服务端来取数据
        "sAjaxSource": "/readers",//这个是请求的地址，Rest API or JSP的action
        "fnServerData": retrieveData, // 获取数据的处理函数
        "columnDefs": [
            {
                "targets": 0,
                "orderable" : false,
                render:function () {
                    return '<input name="" type="checkbox" value="">';
                }
            },
            {
                "targets": 1,
                render:function (data, type, row, meta) {
                    return '<u style="cursor:pointer" class="text-primary" onclick="reader_show(\'查看\',\'readerShow\',\''+ row.username +'\',\'360\',\'300\')">'+row.username+'</u>'
                }
            },
            {
                "targets": 2,
                "data":"name"
            },
            {
                "targets": 3,
                render:function (data, type, row, meta) {
                    if(row.type == 0){
                        return '本科生';
                    }
                    if(row.type == 1){
                        return '研究生'
                    }
                    if(row.type == 2){
                        return '老师'
                    }
                }
            },
            {
                "targets": 4,
                "data":"email"
            },
            {
                "targets": 5,
                "data":"balance"
            },
            {
                "targets": 6,
                "orderable" : false,
                "data":"borrowNum"
            },
            {
                "targets": 7,
                "orderable" : false,
                "data":"unReturn"
            },
            {
                "targets": 8,
                "orderable" : false,
                render:function (data, type, row, meta) {
                    return '<span class="label label-danger radius">'+row.overNum+'</span>'
                }
            },
            {
                "targets": -1,
                render:function (data, type, row, meta) {
                    return '<a title="编辑" href="javascript:;" onclick="reader_edit(\'编辑\',\'readerEdit\',\''+row.username+'\',\'400\',\'350\')" class="ml-5" style="text-decoration:none">\n' +
                        '                        <i class="Hui-iconfont">&#xe6df;</i>\n' +
                        '                    </a>\n' +
                        '                    <a style="text-decoration:none" class="ml-5" onClick="change_password(\'修改密码\',\'changePassword\',\''+row.username+'\',\'600\',\'270\')" href="javascript:;" title="修改密码">\n' +
                        '                        <i class="Hui-iconfont">&#xe63f;</i>\n' +
                        '                    </a>\n' +
                        '                    <a title="删除" href="javascript:;" onclick="reader_del(this,\''+row.username+'\')" class="ml-5" style="text-decoration:none">\n' +
                        '                        <i class="Hui-iconfont">&#xe6e2;</i>\n' +
                        '                    </a>'
                }
            }
        ]
    });

    function retrieveData(sSource, aoData, fnCallback ) {
        $.ajax({
            url : sSource,
            data : {"aoData":JSON.stringify(aoData)},
            type : 'GET',
            dataType : 'json',
            success : function(result) {
                fnCallback(result);
            },
            error : function(msg) {
                alert(msg);
            }
        });
    };

    function reader_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    function reader_show(title,url,id,w,h){
        var u = '/admin/'+url+'/'+id;
        layer_show(title,u,w,h);
    }

    function reader_edit(title,url,id,w,h){
        var u = '/admin/'+url+'/'+id;
        layer_show(title,u,w,h);
    }

    function change_password(title,url,id,w,h){
        var u = '/admin/'+url+'/'+id;
        layer_show(title,u,w,h);
    }

    function reader_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'DELETE',
                url: '/reader/'+id,
                contentType: 'application/json',
                dataType: 'json',
                success: function(data){
                    if(data.code == 0){
                        $(obj).parents("tr").remove();
                        layer.msg(data.msg,{icon:1,time:1000});
                    }else{
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });
        });
    }
</script>
</body>
</html>
