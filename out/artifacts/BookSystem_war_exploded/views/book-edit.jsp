

<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:12
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
    <title>新增图书</title>
    <link href="${ctx}/views/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <form class="form form-horizontal" id="form-article-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>索书号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" disabled value="${bookInfo.callNum}" placeholder="" id="callNum" name="callNum">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>图书名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${bookInfo.name}" placeholder="" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>作者：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${bookInfo.writer}" placeholder="" id="writer" name="writer">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>出版社：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${bookInfo.press}" placeholder="" id="press" name="press">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>出版时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${bookInfo.pubTime}" pattern="yyyy-MM-dd" />" id="pubTime" name="pubTime" class="input-text Wdate">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">ISBN：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${bookInfo.isbn}" placeholder="" id="isbn" name="isbn">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">页数：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${bookInfo.pageNum}" placeholder="" id="pageNum" name="pageNum">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${bookInfo.piece}" placeholder="" id="piece" name="piece">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>图书摘要：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="digest" id="digest" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！">${bookInfo.digest}</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;修改&nbsp;&nbsp;">
                <button onClick="layer_close();" class="btn btn-danger radius" type="button">取消</button>
            </div>
        </div>
    </form>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${ctx}/views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${ctx}/views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/views/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ctx}/views/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript">

    $(function() {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
        $("#form-article-add").validate({
            rules: {
                name: {
                    required: true
                },
                writer: {
                    required: true
                },
                call_num: {
                    required: true
                },
                press: {
                    required: true
                },
                pub_time: {
                    required: true
                },
                digest: {
                    required: true
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                var data={
                    "callNum":$("#callNum").val(),
                    "name":$("#name").val(),
                    "writer":$("#writer").val(),
                    "isbn":$("#isbn").val(),
                    "press":$("#press").val(),
                    "pubTime":$("#pubTime").val(),
                    "pageNum":$("#pageNum").val(),
                    "piece":$("#piece").val(),
                    "digest":$("#digest").val()
                };
                var url="/admin/bookInfo";
                $.ajax({
                    url: url,
                    type: 'PUT',
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function(data){
                        if(data.code == 0){
                            layer.msg(data.msg, {icon: 1});
                            setTimeout(function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            },2000);
                        }else{
                            layer.msg(data.msg, {icon: 2});
                        }
                    }
                });
            }
        });
    });
</script>
</body>
</html>
