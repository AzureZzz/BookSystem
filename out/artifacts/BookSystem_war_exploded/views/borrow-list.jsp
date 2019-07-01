<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:21
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
    <title>借阅列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 借阅管理 <span class="c-gray en">&gt;</span> 借阅列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <%--<div class="text-c">--%>
        <%--<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>--%>
        <%--<span class="select-box inline">--%>
		<%--<select name="" class="select">--%>
			<%--<option value="0">读者</option>--%>
			<%--<option value="1">图书编号</option>--%>
		<%--</select>--%>
		<%--</span> 日期范围：--%>
        <%--<input type="text" onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="logmin" class="input-text Wdate" style="width:120px;">--%>
        <%-----%>
        <%--<input type="text" onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="logmax" class="input-text Wdate" style="width:120px;">--%>
        <%--<input type="text" name="" id="1" placeholder=" 请输入" style="width:250px" class="input-text">--%>
        <%--<button name="" id="12" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>--%>
    <%--</div>--%>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
			</a>
		</span>
        <span class="r">
            <a class="btn btn-success radius" href="/download/borrow">
                <i class="Hui-iconfont">&#xe600;</i> 导出报表
            </a>
        </span>
    </div>
    <div class="mt-20">
        <table id="borrow_table" class="layui-hide table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="100">图书编号</th>
                <th width="100">借阅读者</th>
                <th width="150">借阅时间</th>
                <th width="150">归还时间</th>
                <th width="100">续借次数</th>
                <th width="100">借阅状态</th>
                <th width="120">操作</th>
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
<script type="text/javascript" src="../views/static/h-ui.admin/js/H-ui.admin.js"></script>

<script type="text/javascript" src="../views/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../views/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../views/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    $('.table-sort').dataTable({
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
        "sAjaxSource": "/borrows",//这个是请求的地址，Rest API or JSP的action
        "fnServerData": retrieveData, // 获取数据的处理函数
        "columnDefs": [
            {
                "targets": 0,
                render:function () {
                    return '<input name="" type="checkbox" value="">';
                }
            },
            {
                "targets": 1,
                render:function (data, type, row, meta) {
                    return '<u style="cursor:pointer" class="text-primary" onClick="book_show(\'查看\',\'bookShow\',\''+ row.bookNo +'\',\'600\',\'600\')" title="查看">'+row.bookNo+'</u>'
                }
            },
            {
                "targets": 2,
                render:function (data, type, row, meta) {
                    return '<u style="cursor:pointer" class="text-primary" onClick="reader_show(\'查看\',\'readerShow\',\''+ row.username +'\',\'360\',\'300\')" title="查看">'+row.username+'</u>'
                }
            },
            {
                "targets": 3,
                render:function (data, type, row, meta) {
                    return formatDate(row.borTime);
                }
            },
            {
                "targets": 4,
                render:function (data, type, row, meta) {
                    return formatDate(row.retTime);
                }
            },
            {
                "targets": 5,
                "data":"reNew"
            },
            {
                "targets": 6,
                render:function (data, type, row, meta) {
                    if(row.state == 0) {
                        return '<span class="label label-danger radius">借阅中</span>'
                    }else{
                        return '<span class="label label-success radius">已归还</span>'
                    }
                }
            },
            {
                "targets": -1,
                render:function (data, type, row, meta) {
                    if(row.state == 0) {
                        return '<a style="text-decoration:none" onClick="book_return(this,\''+row.bookNo+'\',\''+row.username+'\',\''+row.borTime+'\')" href="javascript:;" title="登记归还">\n' +
                            '                        <i class="Hui-iconfont">&#xe6de;</i>\n' +
                            '                    </a>\n' +
                            '                    <a style="text-decoration:none" class="ml-5" onClick="borrow_edit(\'借阅编辑\',\'borrowEdit\',\''+row.bookNo+'\',\''+row.username+'\',\''+row.borTime+'\',\'600\',\'450\')" href="javascript:;" title="编辑">\n' +
                            '                        <i class="Hui-iconfont">&#xe6df;</i>\n' +
                            '                    </a>\n' +
                            '                    <a style="text-decoration:none" class="ml-5" onClick="borrow_del(this,\''+row.bookNo+'\',\''+row.username+'\',\''+row.borTime+'\')" href="javascript:;" title="删除">\n' +
                            '                        <i class="Hui-iconfont">&#xe6e2;</i>\n' +
                            '                    </a>'
                    }else{
                        return '<a style="text-decoration:none" class="ml-5" onClick="borrow_edit(\'借阅编辑\',\'borrowEdit\',\''+row.bookNo+'\',\''+row.username+'\',\''+row.borTime+'\',\'600\',\'450\')" href="javascript:;" title="编辑">\n' +
                            '                        <i class="Hui-iconfont">&#xe6df;</i>\n' +
                            '                    </a>\n' +
                            '                    <a style="text-decoration:none" class="ml-5" onClick="borrow_del(this,\''+row.bookNo+'\',\''+row.username+'\',\''+row.borTime+'\')" href="javascript:;" title="删除">\n' +
                            '                        <i class="Hui-iconfont">&#xe6e2;</i>\n' +
                            '                    </a>'
                    }

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

    function borrow_edit(title,url,book_no,username,borTime,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: '/admin/'+url+'/'+username+'/'+book_no+'/'+borTime,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4,
        });
        layer.full(index);
    }

    function borrow_del(obj,book_no,username,borTime){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'DELETE',
                url: '/borrow/'+username+"/"+book_no+"/"+borTime,
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

    function book_show(title,url,bookNo,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: '/admin/'+url+'/'+bookNo,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4
        });
        layer.show(index);
    }

    function reader_show(title,url,username,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: '/admin/'+url+'/'+username,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4
        });
        layer.show(index);
    }

    function book_return(obj,book_no,username,borTime){
        layer.confirm('确认该书已归还？',function(index){
            var data = {
                "bookNo":book_no,
                "username":username,
                "borTime":borTime
            }
            $.ajax({
                type: 'POST',
                url: '/returnBook',
                data:JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function(data){
                    if(data.code == 0){
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已归还</span>');
                        $(obj).remove();
                        layer.msg(data.msg, {icon:6,time:1000});
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
