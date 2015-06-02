<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<html>
<head>
  <title>角色列表</title>
</head>
<body>

<div id="Title_bar">
  <div id="Title_bar_Head">
    <div id="Title_Head"></div>
    <div id="Title"><!--页面标题-->
      <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 角色管理
    </div>
    <div id="Title_End"></div>
  </div>
</div>

<div id="MainArea">
  <table cellspacing="0" cellpadding="0">

    <!-- 表头-->

    <tr>
      <th>角色id</th>
      <th>角色名称</th>
      <th>角色说明</th>
      <th>相关操作</th>
    </tr>


    <!--显示数据列表-->
    <c:forEach items="${roleList}" var="role">
      <tr>
        <td>
            ${role.id}
        </td>
        <td>
            ${role.name}
        </td>
        <td>
            ${role.description}
        </td>
        <td>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/role/edit')}">
          <a href="/role/editUI?id=${role.id}">修改</a>
          </c:if>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/role/delete')}">
          <a href="/role/delete?id=${role.id}" onclick="return confirm('确定要删除吗?')">删除</a>
          </c:if>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/role/setPrivilege')}">
          <a href="/role/setPrivilegeUI?id=${role.id}">设置权限</a>
          </c:if>
        </td>
      </tr>
    </c:forEach>
  </table>

  <!-- 其他功能超链接 -->
  <div id="TableTail">
    <div id="TableTail_inside">
      <c:if test="${sessionScope.user.hasPrivilegeByUrl('/role/add')}">
      <a href="/role/addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
      </c:if>
    </div>
  </div>
</div>
</body>
</html>

