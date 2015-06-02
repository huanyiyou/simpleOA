<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<html>
<head>
    <title>用户信息</title>
    <%@include file="/WEB-INF/views/public/commons.jspf"%>
    <script>
        $().ready(function () {
            $("#user").validate({
                rules: {
                    loginName: "required",
                    name:"required"

                },
                messages: {
                    loginName: "*登录名必填字段",
                    name:"*显示名必填字段"

                }
            });
        });
    </script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form:form action="${id == null ? 'add' : 'edit'}"  modelAttribute="user" method="post">
        <form:hidden path="id"/>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
            <img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div>
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td>
                            登录名:
                        </td>
                        <td>
                            <form:input path="loginName"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            显示名:
                        </td>
                        <td>
                            <form:input path="name"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
            <img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div>
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">角色名称：</td>
                        <td>
                            <form:checkboxes path="roleIds" items="${roleList}" itemLabel="name" itemValue="id"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form:form>
</div>

<div class="Description">
    说明：<br />
    1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
    2，新建用户后，密码被初始化为"1234"。<br />
    3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
    4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
    5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
    6，修改用户信息时，登录名不可修改。
</div>

</body>
</html>