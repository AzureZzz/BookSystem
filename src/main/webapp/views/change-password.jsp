<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
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

    <title>修改密码</title>
 </head>
<body>
<article class="page-container">
    <form action="/" method="post" class="form form-horizontal" id="form-change-password">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>读者账号：</label>
            <input type="hidden" value="${username}" id="username" name="username">
            <div class="formControls col-xs-8 col-sm-9"> ${username} </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" placeholder="不修改请留空" name="newpassword" id="newpassword">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" placeholder="不修改请留空" name="newpassword2" id="newpassword2">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${ctx}/views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${ctx}/views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/views/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ctx}/views/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${ctx}/views/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    $(function(){
        $("#form-change-password").validate({
            rules:{
                newpassword:{
                    required:true,
                    minlength:6,
                    maxlength:16
                },
                newpassword2:{
                    required:true,
                    minlength:6,
                    maxlength:16,
                    equalTo: "#newpassword"
                },
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                var newpassword = $("#newpassword").val();
                var newpassword2 = $("#newpassword2").val();
                if(newpassword != newpassword2) {
                    layer.msg("两次密码不一致！", {icon: 2});
                }
                var data={
                    "username":$("#username").val(),
                    "newPassword":newpassword
                };
                var url="/reader/changepassword";
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
                                parent.$('.btn-refresh').click();
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
