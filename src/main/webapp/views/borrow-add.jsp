<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="../views/favicon.ico" >
    <link rel="Shortcut Icon" href="../views/favicon.ico" />
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

    <title>借书登记</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="add_borrow">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2 col-sm-offset-2"><span class="c-red">*</span>读者账号：</label>
            <div class="formControls col-xs-3 col-sm-4 ">
                <input type="text" class="input-text" value="" placeholder="" id="username" name="username">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2 col-sm-offset-2"><span class="c-red">*</span>图书编号：</label>
            <div class="formControls col-xs-3 col-sm-4" >
                <input type="text" class="input-text" value="" placeholder="" id="bookNo" name="bookNo">
            </div>
        </div>
        <br/>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-4">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认借出&nbsp;&nbsp;">
                <button onClick="layer_close();" class="btn btn-danger radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
    </form>
</article>

<script type="text/javascript" src="../views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../views/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../views/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../views/static/h-ui.admin/js/H-ui.admin.js"></script>

<script type="text/javascript" src="../views/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="../views/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="../views/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    $(function(){
        $('#add_borrow').validate({
            rules:{
                username:{
                    required:true
                },
                bookNo:{
                    required:true
                }
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                var data={
                    "username":$("#username").val(),
                    "bookNo":$("#bookNo").val()
                };
                var url="/borrow";
                $.ajax({
                    url: url,
                    type: 'POST',
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


