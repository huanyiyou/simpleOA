<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>部门数据统计</title>
  <%@include file="/WEB-INF/views/public/commons.jspf" %>

</head>
<body>
<div id="Title_bar">
  <div id="Title_bar_Head">
    <div id="Title_Head"></div>
    <div id="Title"><!--页面标题-->
      <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 部门统计表
    </div>
    <div id="Title_End"></div>
  </div>
</div>
<form action="/chart/department">
  年：
  <select name="year">
    <c:forEach items="${years}" var="y">
      <option value="0">——请选择年份——</option>
      <option value="${y}" <c:if test="${y == year}">selected</c:if>>${y}</option>
    </c:forEach>
  </select>
  姓名：
  <select name="userId">
    <option value="">——请选择姓名——</option>
    <c:forEach items="${users}" var="user">
      <option value="${user.id}" <c:if test="${userId == user.id}">selected</c:if>>${user.name}</option>
    </c:forEach>
  </select>
  <input type="submit" value="提交" />
</form>
<table cellspacing="0" cellpadding="0">
  <tr>
    <th>月份</th>
    <th>加班时长(小时)</th>
    <th>休假时长(小时)</th>
    <th>加班-休假(小时)</th>
  </tr>
  <c:forEach varStatus="status" begin="0" end="11">
    <tr>
      <td>${status.index+1}</td>
      <td>${overtimes[status.index]}</td>
      <td>${breaktimes[status.index]}</td>
      <td>${overtimes[status.index]-breaktimes[status.index]}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
