<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>汽车租赁·登录</title>
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body style="background-image: url('${pageContext.request.contextPath}/resources/images/bg1.jpg');background-repeat: no-repeat;background-size: cover;-webkit-background-size: cover;background-attachment: fixed;">
<div style="width: 400px;height: 300px;position: absolute;top: 35%;left: 50%;margin-top: -125px;margin-left: -200px;border: #dddddd 1px solid;box-shadow:  2px 2px 10px #909090;border-radius: 3%">
    <form class="layui-form layui-form-pane">
        <p style="text-align: center;height: 40px;line-height: 40px;font-size: 25px;font-weight: bold;color: #009688;margin-bottom: 10px">汽车租赁·登录</p>
        <div class="layui-form-item" style="margin-left: 30px;">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" autocomplete="off" value="admin" name="username" class="layui-input" placeholder="用户名" lay-verify="required"
                       lay-reqText="请输入用户名">
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 30px;">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="text" autocomplete="off" value="admin" name="password" class="layui-input" placeholder="密码" lay-verify="required"
                       lay-reqText="请输入密码">
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 30px;">
            <div class="layui-input-inline" style="width: 300px">
                <div id="slider"></div>
            </div>

        </div>
        <div class="layui-form-item" style="margin-left: 30px;margin-top: 30px">
            <div class="layui-input-inline">
                <button class="layui-btn" style="width: 300px" type="button" lay-submit id="subBtn" lay-filter="subBtnFilter">登录</button>
            </div>
        </div>
    </form>
</div>
<script src="resources/layui/layui.js"></script>
<script>
    layui.config({
        base: 'resources/sliderVerify/'
    }).use(['form', 'jquery', 'layer', 'sliderVerify'], function () {
        let form = layui.form;
        let $ = layui.jquery;
        let layer = layui.layer;
        let sliderVerify = layui.sliderVerify;
        sliderVerify.render({
            elem: "#slider",
            text: '验证',
            isAutoVerify: true
        });
        //表单的提交事件
        form.on("submit(subBtnFilter)", function (d) {
            let param = d.field;//获取表单数据
            $.post("${pageContext.request.contextPath}/sysuser/login.do", param, function (rs) {
                if (rs.code != 200) {
                    layer.msg(rs.msg);
                    return false;
                }
                //跳转到管理界面主页
                location.href = "page/main.do";
            })
            return false;//阻止表单默认提交行为
        })
    })
</script>
</body>
</html>
