<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<html>
<head>
  <title>休假记录列表</title>
</head>
<body>

<div id="Title_bar">
  <div id="Title_bar_Head">
    <div id="Title_Head"></div>
    <div id="Title"><!--页面标题-->
      <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 休假记录
    </div>
    <div id="Title_End"></div>
  </div>
</div>
<form action="/breaktime/list">
  <input hidden="hidden" name="pageNum" value="1"/>
  年：
  <select name="year">
    <option value="" <c:if test="${year == ''}">selected</c:if>>所有年份</option>
    <c:forEach items="${years}" var="y">
      <option value="${y}" <c:if test="${y == year}">selected</c:if>>${y}</option>
    </c:forEach>
  </select>
  月：
  <select name="month">
    <option value="" <c:if test="${month == ''}">selected</c:if>>所有月份</option>
    <c:forEach items="${months}" var="m">
      <option value="${m}" <c:if test="${m == month}">selected</c:if>>${m}</option>
    </c:forEach>
  </select>
  姓名：
  <select name="userId">
    <option value="0" <c:if test="${userId == 0}">selected</c:if>>所有人</option>
    <c:forEach items="${users}" var="user">
      <option value="${user.id}" <c:if test="${userId == user.id}">selected</c:if>>${user.name}</option>
    </c:forEach>
  </select>
  <input type="submit" value="提交" />
</form>
<c:if test="${sessionScope.user.hasPrivilegeByUrl('/breaktime/add')}">
  <a href="/breaktime/addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
</c:if>
<div id="MainArea">
  <table cellspacing="0" cellpadding="0">

    <!-- 表头-->
    <tr align="CENTER" valign="MIDDLE" id="TableTitle">
      <th>休假记录id</th>
      <th>使用人</th>
      <th>时长</th>
      <th>日期</th>
      <th>工作描述</th>
      <th>相关操作</th>
    </tr>

    <!--显示数据列表-->
    <c:forEach items="${pageBean.recordList}" var="breaktime">
      <tr>
        <td>
            ${breaktime.id}
        </td>
        <td>
            ${breaktime.userName}
        </td>
        <td>
            ${breaktime.duration}
        </td>
        <td>
            ${breaktime.date}
        </td>
        <td>
            ${breaktime.description}
        </td>
        <td>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/breaktime/edit')}">
            <a href="/breaktime/editUI?id=${breaktime.id}">修改</a>
          </c:if>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/breaktime/delete')}">
            <a href="/breaktime/delete?id=${breaktime.id}" onclick="return confirm('确定要删除吗?')">删除</a>
          </c:if>
        </td>
      </tr>
    </c:forEach>
  </table>

  <!-- 其他功能超链接 -->
  <div id="TableTail">
    <div id="TableTail_inside">
      <%@ include file="/WEB-INF/views/public/pageView.jspf"%>
    </div>
  </div>
</div>
</body>
</html>

