<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<html>
<head>
  <title>申请加班列表</title>
</head>
<body>

<div id="Title_bar">
  <div id="Title_bar_Head">
    <div id="Title_Head"></div>
    <div id="Title"><!--页面标题-->
      <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 申请加班列表
    </div>
    <div id="Title_End"></div>
  </div>
</div>
<div>
  <form action="/preOvertime/list">
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
  <table cellspacing="0" cellpadding="0" >

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
      <th>审核意见</th>
      <th>相关操作</th>
    </tr>

    <!--显示数据列表-->
    <c:forEach items="${pageBean.recordList}" var="preOvertime">
      <tr>
        <td>
            ${preOvertime.id}
        </td>
        <td>
            ${preOvertime.date}
        </td>
        <td>
            <c:choose>
              <c:when test="${preOvertime.type == 1}">
                工作日
              </c:when>
              <c:when test="${preOvertime.type == 2}">
                双休日
              </c:when>
              <c:when test="${preOvertime.type == 3}">
                国定假日
              </c:when>
            </c:choose>
        </td>
        <td>
            ${preOvertime.startTime}
        </td>
        <td>
            ${preOvertime.endTime}
        </td>
        <td>
            ${preOvertime.timeSpan}
        </td>
        <td>
            ${preOvertime.userName}
        </td>
        <td>
            ${preOvertime.description}
        </td>
        <td>
            ${preOvertime.remark}
        </td>
        <td>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/preOvertime/edit')}">
            <a href="/preOvertime/editUI?id=${preOvertime.id}">编辑审核意见</a><br/>
          </c:if>
          <%--<c:if test="${sessionScope.user.hasPrivilegeByUrl('/preOvertime/delete')}">--%>
            <%--<a href="/preOvertime/delete?id=${preOvertime.id}" onclick="return confirm('确定要删除吗?')">删除</a>--%>
          <%--</c:if>--%>
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/preOvertime/approve')}">
            <a href="/preOvertime/approve?id=${preOvertime.id}" onclick="return confirm('确定要通过吗?')">通过</a>
          </c:if>
          ||
          <c:if test="${sessionScope.user.hasPrivilegeByUrl('/preOvertime/deny')}">
            <a href="/preOvertime/deny?id=${preOvertime.id}" onclick="return confirm('确定要拒绝吗?')">拒绝</a>
          </c:if>
        </td>
      </tr>
    </c:forEach>
  </table>

  <!-- 其他功能超链接 -->
  <%--<div id="TableTail">--%>
    <%--<div id="TableTail_inside">--%>
      <%--<c:if test="${sessionScope.user.hasPrivilegeByUrl('/preOvertime/add')}">--%>
        <%--<a href="/preOvertime/addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>--%>
      <%--</c:if>--%>
    <%--</div>--%>
  <%--</div>--%>
  <!-- 其他功能超链接 -->
  <div id="TableTail">
    <div id="TableTail_inside">
      <%@ include file="/WEB-INF/views/public/pageView.jspf"%>
    </div>
  </div>
</div>
</body>
</html>

