<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>


    <%--  静态包含base标签,css样式,jQuery文件 --%>
    <%@include file="/pages/common/head.jsp"%>


    <script type="text/javascript">

        //页面加载完成后
        $(function () {

            $("#username").blur(function () {
               //1 获取用户名
               var username = this.value;

               $.getJSON("http://localhost:8080/book/userServletajaxExistsUsername","?username=" + username,function (data) {
                    // console.log(data)
                   if (data.existUsername){
                       $("span.errorMsg").text("用户名已存在！");
                   }else {
                       $("span.errorMsg").text("用户名可用！");
                   }
               });

            });



            //给验证码的图片绑定单击事件
            $("#code_img").click(function () {
                //在事件响应的function函数中有一个this对象，这个this对象是当前正在响应的dom对象
                //src属性表示验证码img标签 图片路径 它可读，可写
               this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            });





            //给注册绑定单击事件
            $("#sub_btn").click(function () {
                //验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var usernameTest = $("#username").val();
                //2 创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!usernamePatt.test(usernameTest)) {
                    //4 提醒用户不合法
                    $("span.errorMsg").text("用户名不合法!");

                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不合法！");

                    return false;
                }



                //验证确认密码，和密码相同
                //1.获取确认密码内容
                var repwdText = $("#repwd").val();
                //2 和密码相比较
                if (repwdText.length != passwordText.length) {
                    //提示用户
                    $("span.errorMsg").text("确认密码和密码不一致！");

                    return false;
                }

                //邮箱验证：xxxx@xxx.com
                //1.获取邮箱的内容
                var emailText = $("#email").val();
                //2.创建正则表达式
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3.使用test方式验证是否合法
                if (!emailPatt.test(emailText)) {
                    //4.提示用户
                    $("span.errorMsg").text("邮箱格式不合法！");
                    return false;
                }

                //验证码
                var codeTest = $("#code").val();

                //去掉验证码前后空格
                codeTest = $.trim(codeTest);

                if (codeTest == null || codeTest == "") {
                    //提示用户
                    $("span.errorMsg").text("验证码不能为空！");
                    return false;
                }

                //去掉错误信息
                $("span.errorMsg").text("");

            });


        });

    </script>
    <style type="text/css">
        .login_form{
            height:420px;
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        ${ requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="userServletregist" method="post">
<%--                        <input type="hidden" name="action" value="regist">--%>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               value="${requestScope.username}"
                               autocomplete="off" tabindex="1" name="username" id="username"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               value="${requestScope.email}"
                               autocomplete="off" tabindex="1" name="email" id="email"

                        />
                        <br/>
                        <br/>
                        <label>验证码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 46px" width="100px" height="40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<%--	静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>


</body>
</html>