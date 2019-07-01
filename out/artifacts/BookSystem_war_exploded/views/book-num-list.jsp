<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:18
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
    <title>书籍馆藏列表</title>
</head>
<body>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
                <i class="Hui-iconfont">&#xe6e2;</i> 批量删除
            </a>
            <a class="btn btn-primary radius" onclick="book_add('新增图书','bookNumAdd','${callNum}','360','240')" href="javascript:;">
                <i class="Hui-iconfont">&#xe600;</i> 新增图书
            </a>
        </span>
    </div>
    <div class="mt-20">
        <table id="book_num_table" class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="40"><input name="" type="checkbox" value=""></th>
                <th>图书编号</th>
                <th>馆藏地点</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${ctx}/views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${ctx}/views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/views/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ctx}/views/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    $('#book_num_table').dataTable({
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
        "searching": true,
        "bSort": true,
        "bInfo": true,
        "bProcessing": false, // 加载条
        "bServerSide": true,
        "sAjaxSource": "/bookInfos/${callNum}",//这个是请求的地址，Rest API or JSP的action
        "fnServerData": retrieveData, // 获取数据的处理函数
        "page": true,
        "aLengthMenu": [5,10, 25, 50],
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
                "data":"bookNo"
            },
            {
                "targets": 2,
                "data":"place"
            },
            {
                "targets": 3,
                "orderable" : false,
                render:function (data, type, row, meta) {
                    if(row.state == 0){
                        return '<span class="label label-success radius">在藏</span>'
                    }else{
                        return '<span class="label label-danger radius">借出</span>'
                    }
                }
            },
            {
                "targets": -1,
                "orderable" : false,
                render:function (data, type, row, meta) {
                    return '<a style="text-decoration:none" class="ml-5" onClick="book_edit(\'图书编辑\',\'bookNumEdit\',\''+row.bookNo+'\',\'360\',\'240\')" href="javascript:;" title="编辑">\n' +
                        '                        <i class="Hui-iconfont">&#xe6df;</i>\n' +
                        '                    </a>\n' +
                        '                    <a style="text-decoration:none" class="ml-5" onClick="book_del(this,\''+row.bookNo+'\')" href="javascript:;" title="删除">\n' +
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

    /*图书-添加*/
    function book_add(title,url,id,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: '/admin/'+url+'/'+id,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4
        });
        layer.full(index);
    }


    /*图书-编辑*/
    function book_edit(title,url,id,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: "/admin/"+url+'/'+id,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4
        });
        layer.full(index);
    }

    /*图书-删除*/
    function book_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'DELETE',
                url: '/book/'+id,
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
