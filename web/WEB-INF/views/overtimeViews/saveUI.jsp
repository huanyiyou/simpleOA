<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<html>
<head>
  <title>加班记录设置</title>
  <%--<script>--%>
    <%--$().ready(function () {--%>
      <%--$("#overtime").validate({--%>
        <%--rules: {--%>
          <%--name: "required"--%>
        <%--},--%>
        <%--messages: {--%>
          <%--name: "*加班记录名称必填字段"--%>
        <%--}--%>
      <%--});--%>
    <%--});--%>
  <%--</script>--%>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
  <div id="Title_bar_Head">
    <div id="Title_Head"></div>
    <div id="Title"><!--页面标题-->
      <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 加班记录
    </div>
    <div id="Title_End"></div>
  </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
  <form:form action="${id == null ? 'add' : 'edit'}"  modelAttribute="overtime" method="post">
    <form:hidden path="id" />
    <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
    </div>

    <!-- 表单内容显示 -->
    <div class="ItemBlockBorder">
      <div class="ItemBlock">
        <table cellpadding="0" cellspacing="0" class="mainForm">
          <tr>
            <td width="100">日期</td>
            <td><form:input path="date" cssClass="InputStyle"/> *</td>
          </tr>
          <tr>
            <td>工作日、双休日、国定假日 </td>
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
          </tr>
          <tr>
            <td>结束时间</td>
            <td><form:textarea path="endTime" cssClass="InputStyle"/></td>
          </tr>
          <tr>
            <td>时长</td>
            <td><form:textarea path="timeSpan" cssClass="InputStyle"/></td>
          </tr>
          <tr>
            <td>工作描述</td>
            <td><form:textarea path="description" cssClass="TextareaStyle"/></td>
          </tr>
          <tr>
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

</body>
</html>

