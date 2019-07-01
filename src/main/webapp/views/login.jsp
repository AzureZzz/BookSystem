<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/7/4
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>登陆</title>
    <link href="../views/static/style_log.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../views/static/style.css">
    <link rel="stylesheet" type="text/css" href="../views/static/userpanel.css">
    <link rel="stylesheet" type="text/css" href="../views/static/jquery.ui.all.css">

</head>

<body class="login" mycollectionplug="bind">
<div class="login_m">
    <div class="login_logo"><img src="../views/static/logo.png" width="400" height="41"></div>
    <div class="login_boder">

        <div class="login_padding" id="login_model">

            <h2>管理员账号</h2>
            <label>
                <input type="text" id="admin" class="txt_input txt_input2" value="">
            </label>
            <h2>密码</h2>
            <label>
                <input type="password" name="password" id="password" class="txt_input" value="">
            </label>

            <div class="rem_sub">
                <div class="rem_sub_l">
                    <input type="checkbox" name="checkbox" id="save_password">
                    <label for="save_password">记住密码</label>
                </div>
                <label>
                    <input type="submit" class="sub_button" name="button" id="login" value="登陆" style="opacity: 0.7;">
                </label>
            </div>
        </div>

        <div class="copyrights">数据库系统原理综合实验设计——1600300128王涛</div>
    </div>
</div>
<br><br>
<p align="center">数据库系统原理综合实验设计——1600300128王涛</p>

<script type="text/javascript" src="../views/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../views/lib/layer/2.4/layer.js"></script>

<script>
    $(function () {
        $("#login").click(function () {
            var data={
                "admin":$("#admin").val(),
                "password":$("#password").val()
            };
            $.ajax({
                type: 'POST',
                url: '/admin/login',
                data:JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function(data){
                    if(data.code == 0){
                        layer.msg(data.msg,{icon:1,time:1000});
                        setTimeout(function () {
                            window.location.href="index";
                        },2000);
                    }else{
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });
        });
    });
</script>

</body>
</html>
