<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>用户信息</title>
  <%@include file="/WEB-INF/views/public/commons.jspf"%>
  <script language="javascript" type="text/javascript">
    function check()
    {
      if (document.form1.currentPassword.value==""){
        alert("请输入当前登录密码!");
        return false;
      }
      if (document.form1.newPassword.value==""){
        alert("请输入新的登录密码!");
        return false;
      }
      if (document.form1.confirmPassword.value==""){
        alert("请输入重复密码!");
        return false;
      }
      if (document.form1.newPassword.value!=document.form1.confirmPassword.value){
        alert("对不起!重复密码不等于新登录密码");
        return false;
      }

      return true;

    }
  </script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
  <div id="Title_bar_Head">
    <div id="Title_Head"></div>
    <div id="Title"><!--页面标题-->
      <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人设置
    </div>
    <div id="Title_End"></div>
  </div>
</div>
<div>
  <h1>更改密码</h1>

  <form name="form1" action="/user/setting" onsubmit="return check()">
    <fieldset>
      <table>
        <tr>
          <td></td>
          <td>${message}</td>
        </tr>
        <tr>
          <td><label for="currentPassword">当前密码</label></td>
          <td><input type="password" name="currentPassword" id="currentPassword"></td>
        </tr>
        <tr>
          <td><label for="newPassword">新密码</label></td>
          <td><input type="password" name="newPassword" id="newPassword"></td>
        </tr>
        <tr>
          <td> <label for="confirmPassword">确认新密码</label></td>
          <td><input type="password" name="confirmPassword" id="confirmPassword"></td>
        </tr>
        <tr>
          <td></td>
          <td><input type="submit" value="更改密码" /></td>
        </tr>
      </table>
    </fieldset>
  </form>

</div>
</body>
</html>
