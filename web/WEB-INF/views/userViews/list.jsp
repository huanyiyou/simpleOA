<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<%@include file="/WEB-INF/views/public/tablecloth.jspf" %>
<html>
<head>
  <title>用户列表</title>
</head>
<body>
<div id="Title_bar">
  <div id="Title_bar_Head">
    <div id="Title_Head"></div>
    <div id="Title"><!--页面标题-->
      <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户管理
    </div>
    <div id="Title_End"></div>
  </div>
</div>

<div id="MainArea">
  <table cellspacing="0" cellpadding="0">

    <!-- 表头-->
    <tr>
      <th>用户id</th>
      <th>登录名</th>
      <th>显示名称</th>
      <th>相关操作</th>
    </tr>

    <!--显示数据列表-->
    <c:forEach items="${userList}" var="user">
      <tr class="TableDetail1 template">
        <td> ${user.id}&nbsp;</td>
        <td> ${user.loginName}&nbsp;</td>
        <td>${user.name}&nbsp;</td>
        <td>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/user/edit')}">
          <a href="/user/editUI?id=${user.id}">修改</a>
          </c:if>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/user/delete')}">
          <a href="/user/delete?id=${user.id}" onclick="return confirm('确定要删除吗?')">删除</a>
          </c:if>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/user/initPassword')}">
          <a href="/user/initPassword?id=${user.id}" onclick="return confirm('确定要初始化密码吗?')">初始化密码</a>
          </c:if>
        </td>
      </tr>
    </c:forEach>
  </table>

  <!-- 其他功能超链接 -->
  <div id="TableTail">
    <div id="TableTail_inside">
    <c:if test="${sessionScope.user.hasPrivilegeByUrl('/user/add')}">
      <a href="/user/addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
    </div>
    </c:if>
  </div>
</div>

</body>
</html>

