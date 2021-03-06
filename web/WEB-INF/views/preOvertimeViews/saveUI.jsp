<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf" %>
<html>
<head>
    <title>申请加班记录</title>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 申请加班记录
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
    <form:form action="${id == null ? 'add' : 'edit'}" modelAttribute="preOvertime" method="post">
        <form:hidden path="id"/>
    <!-- 表单内容显示 -->
    <table>
        <tr>
            <td>日期</td>
            <td>${preOvertime.date}</td>
        </tr>
        <tr>
            <td>工作日、双休日、国定假日</td>
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
            <td>开始时间</td>
            <td>${preOvertime.startTime}</td>
        </tr>
        <tr>
            <td>结束时间</td>
            <td> ${preOvertime.endTime}</td>
        </tr>
        <tr>
            <td>时长</td>
            <td>${preOvertime.timeSpan}</td>
        </tr>
        <tr>
            <td>工作描述</td>
            <td>${preOvertime.description}</td>
        </tr>
        <tr>
            <td>审核意见</td>
            <td><form:textarea path="remark" cssClass="TextareaStyle"/></td>
        </tr>
        <!-- 表单操作 -->
        <tr>
            <td style="text-align:right"><input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/></td>
            <td>
                <a href="javascript:history.go(-1);"><img
                    src="${pageContext.request.contextPath}/style/images/goBack.png"/></a></td>
        </tr>
    </table>

    </form:form>

</body>
</html>

