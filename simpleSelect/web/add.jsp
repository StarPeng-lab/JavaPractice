<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        /*function checkUsername(){
            var username = document.getElementById("name").value;
            var reg_username=/^[a-zA-Z0-9_\u4e00-\u9fa5]{1,12}$/;
            var flag = reg_username.test(username);
            var err = document.getElementById("name-err");
            if(flag){
                err.innerHTML="";
            }else{
                err.innerHTML="用户名格式有误，请保证在1-12个字符内";
            }
            return flag;
        }*/

        $(function(){
            /*document.getElementById("form").onsubmit=function(){
                return checkUsername();
            }
            document.getElementById("name").onblur=checkUsername;*/

            //给id为name对应的输入框，绑定blur鼠标离开时间
            $("#name").blur(function(){
                var uname = $(this).val(); //this指js对象，用$(js对象)转为jq对象，获得文本输入框的值

                //1、验证用户名格式
                var reg_username=/^[a-zA-Z0-9_\u4e00-\u9fa5]{1,12}$/;
                var flag = reg_username.test(uname);
                var err = document.getElementById("name-err");
                if(flag){
                    err.innerHTML="";
                }else{
                    err.innerHTML="用户名格式有误，请保证在1-12个字符内";
                }

                //2、验证用户名是否重复
                //发送ajax请求，期望服务器响应回的数据格式：{"userExist":true,"msg":"此用户名太受欢迎，请更换一个"}
                //                                  {"userExist":flase,"msg":""}
                $.get("checkUsernameServlet",{username:uname},function(data){
                   var err = $("#name-err");
                   if(data.userExist){
                       //用户名已存在
                       err.css("color","red");
                       err.html(data.msg);
                   }else{
                       //用户名不存在
                       err.html(data.msg);
                   }
                });
            });


            //发送ajax请求，加载所有省份数据
            $.get("provinceServlet",{},function(data){
                //data返回的是json对象
                //[{"id":1,"name":"北京"},{"id":2,"name":"上海"},{"id":3,"name":"广州"},{"id":4,"name":"深圳"},{"id":5,"name":"杭州"}]
                //1.获取select
                var province = $("#province");
                //2.遍历json数组
                $(data).each(function () {
                    //3.创建<option>
                    var option = "<option name='"+this.id+"'>"+this.name+"</option>";

                    //4.调用select的append追加option
                    province.append(option);
                });
            });
        });

    </script>
</head>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" method="post" id="form">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
            <div id="name-err" style="color:red;"></div>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="province">
                <option>--请选择省份--</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="email" class="form-control" name="email" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="history.back()"/>
        </div>
    </form>
</div>
</body>
</html>