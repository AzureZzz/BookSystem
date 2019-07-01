<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:16
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
    <title>图书列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 图书管理 <span class="c-gray en">&gt;</span> 图书列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="delAll()" class="btn btn-danger radius">
                <i class="Hui-iconfont">&#xe6e2;</i> 批量下架
            </a>
            <a class="btn btn-primary radius" onclick="book_add('图书入库','bookAdd')" href="javascript:;">
                <i class="Hui-iconfont">&#xe600;</i> 图书入库
            </a>
        </span>
        <span class="r">
            <a class="btn btn-success radius" href="/download/book">
                <i class="Hui-iconfont">&#xe600;</i> 导出报表
            </a>
        </span>
    </div>
    <div class="mt-20">
        <table id="book_table" class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="40"><input name="" type="checkbox" value=""></th>
                <th width="80">索书号</th>
                <th width="80">图书名称</th>
                <th width="100">封面</th>
                <th width="100">作者</th>
                <th width="150">出版社</th>
                <th width="60">馆藏总数</th>
                <th width="60">剩余数量</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../views/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../views/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../views/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="../views/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    var table = $('#book_table').dataTable({
        "language": {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(共 _MAX_ 项)",
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
        "lengthChange":true,
        "bPaginate": true,         //是否显示（应用）分页器
        "searching": true,
        "bSort": true,
        "bInfo": true,
        "bProcessing": false, // 加载条
        "bServerSide": true,
        "sAjaxSource": "bookInfos",//这个是请求的地址，Rest API or JSP的action
        "fnServerData": retrieveData, // 获取数据的处理函数
        "page": true,
        "aLengthMenu": [5,10, 25, 50],
        "columnDefs": [
            {
                "targets": 0,
                "orderable" : false,
                render:function (data, type, row, meta) {
                    return '<input name="" class="checkchild" type="checkbox" value="'+JSON.stringify(row)+'">';
                }
            },
            {
                "targets": 1,
                "data":"callNum"
            },
            {
                "targets": 2,
                "data":"name"
            },
            {
                "targets": 3,
                "orderable" : false,
                render:function (data, type, row, meta) {
                    return '<a href="javascript:;" onClick="book_show(\'图书信息\',\'bookInfoShow\',\''+row.callNum+'\',\'600\',\'600\')"><img width="210" class="book-thumb" src="../views/temp/200x150.jpg"></a>'
                }
            },
            {
                "targets": 4,
                "data":"writer"
            },
            {
                "targets": 5,
                "data":"press"
            },
            {
                "targets": 6,
                "orderable" : false,
                render:function (data, type, row, meta) {
                    return '<span class="label label-success radius">'+row.total+'</span>'
                }
            },
            {
                "targets": 7,
                "orderable" : false,
                render:function (data, type, row, meta) {
                    return '<span class="label label-danger radius">'+row.remain+'</span>'
                }
            },
            {
                "targets": -1,
                "orderable" : false,
                render:function (data, type, row, meta) {
                    return '<a style="text-decoration:none" onClick="book_num(\'馆藏信息\',\'bookNumList\',\''+row.callNum+'\')" href="javascript:;" title="查看馆藏">\n' +
                        '                        <i class="Hui-iconfont">&#xe6de;</i>\n' +
                        '                    </a>\n' +
                        '                    <a style="text-decoration:none" class="ml-5" onClick="book_edit(\'图书编辑\',\'bookEdit\',\''+row.callNum+'\',\'600\',\'600\')" href="javascript:;" title="编辑">\n' +
                        '                        <i class="Hui-iconfont">&#xe6df;</i>\n' +
                        '                    </a>\n' +
                        '                    <a style="text-decoration:none" class="ml-5" onClick="book_del(this,\''+row.callNum+'\')" href="javascript:;" title="删除">\n' +
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

    function delAll() {
        alert(table.rows('.selected').data().length +' row(s) selected' )
    }

    /*图书-添加*/
    function book_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*图书-添加数量*/
    function book_num(title,url,id,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: "/admin/"+url+"/"+id,
            area: [w+'px', h +'px']
        });
        layer.full(index);
    }

    /*图书-查看*/
    function book_show(title,url,id,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: "/admin/"+url+"/"+id,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4
        });
        layer.show(index);
    }
    /*图书-编辑*/
    function book_edit(title,url,id,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: "/admin/"+url+"/"+id,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4
        });
        layer.full(index);
    }
    function book_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'DELETE',
                url: '/admin/bookInfo/'+id,
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
