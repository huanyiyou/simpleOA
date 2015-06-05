<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<html>
<head>
  <title>部门加班列表</title>
</head>
<body>

<div id="Title_bar">
  <div id="Title_bar_Head">
    <div id="Title_Head"></div>
    <div id="Title"><!--页面标题-->
      <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 部门加班列表
    </div>
    <div id="Title_End"></div>
  </div>
</div>
<div>
  <form action="/overtime/list">
    <input hidden="hidden" name="pageNum" value="${pageNum}"/>
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
</div>
<div id="MainArea">
  <table cellpadding="0" cellspacing="0" >

    <!-- 表头-->
    <tr>
      <th>加班记录id</th>
      <th>日期</th>
      <th>工作日、双休日、国定假日</th>
      <th>开始时间</th>
      <th>结束时间</th>
      <th>时长</th>
      <th>工作人</th>
      <th>工作描述</th>
      <%--<td>相关操作</td>--%>
    </tr>

    <!--显示数据列表-->
    <c:forEach items="${pageBean.recordList}" var="overtime">
      <tr>
        <td>
            ${overtime.id}
        </td>
        <td>
            ${overtime.date}
        </td>
        <td>
          <c:choose>
            <c:when test="${overtime.type == 1}">
              工作日
            </c:when>
            <c:when test="${overtime.type == 2}">
              双休日
            </c:when>
            <c:when test="${overtime.type == 3}">
              国定假日
            </c:when>
          </c:choose>
        </td>
        <td>
            ${overtime.startTime}
        </td>
        <td>
            ${overtime.endTime}
        </td>
        <td>
            ${overtime.timeSpan}
        </td>
        <td>
            ${overtime.userName}
        </td>
        <td>
            ${overtime.description}
        </td>
        <%--<td>--%>
          <%--<c:if test="${sessionScope.user.hasPrivilegeByUrl('/overtime/edit')}">--%>
            <%--<a href="/overtime/editUI?id=${overtime.id}">修改</a>--%>
          <%--</c:if>--%>
          <%--<c:if test="${sessionScope.user.hasPrivilegeByUrl('/overtime/delete')}">--%>
            <%--<a href="/overtime/delete?id=${overtime.id}" onclick="return confirm('确定要删除吗?')">删除</a>--%>
          <%--</c:if>--%>
        <%--</td>--%>
      </tr>
    </c:forEach>
  </table>

  <!-- 其他功能超链接 -->
  <div id="TableTail">
    <div id="TableTail_inside">
      <%@ include file="/WEB-INF/views/public/pageView.jspf"%>
    </div>
  </div>
  <!-- 其他功能超链接 -->
  <%--<div id="TableTail">--%>
    <%--<div id="TableTail_inside">--%>
      <%--<c:if test="${sessionScope.user.hasPrivilegeByUrl('/overtime/add')}">--%>
        <%--<a href="/overtime/addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>--%>
      <%--</c:if>--%>
    <%--</div>--%>
  <%--</div>--%>

</div>
</body>
</html>

