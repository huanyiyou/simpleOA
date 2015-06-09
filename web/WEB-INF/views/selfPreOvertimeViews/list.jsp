<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf" %>
<html>
<head>
    <title>加班申请</title>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 加班申请
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div>
    <form action="/selfPreOvertime/list">
        <input hidden="hidden" name="pageNum"/>
        <input hidden="hidden" name="pageSize"/>
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
        状态:
        <select name="approved">
            <option value="" <c:if test="${approved == ''}">selected</c:if>>所有状态</option>
            <option value="0" <c:if test="${approved == '0'}">selected</c:if>>未通过审核</option>
            <option value="1" <c:if test="${approved == '1'}">selected</c:if>>已通过审核</option>
        </select>
        <input type="submit" value="提交" />
    </form>
</div>
<c:if test="${sessionScope.user.hasPrivilegeByUrl('/selfPreOvertime/add')}">
    <a href="/selfPreOvertime/addUI"><img
            src="${pageContext.request.contextPath}/style/images/createNew.png"/></a>
</c:if>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0">

        <!-- 表头-->

        <tr>
            <th>序号</th>
            <th>日期</th>
            <th>工作日、双休日、国定假日</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>时长</th>
            <th>工作描述</th>
            <th>审核意见</th>
            <th>相关操作</th>
        </tr>


        <!--显示数据列表-->
        <c:forEach items="${pageBean.recordList}" var="preOvertime" varStatus="status">
            <tr>
                <td>
                        ${pageBean.pageSize*(pageBean.currentPage-1)+status.count}
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
                        ${preOvertime.description}
                </td>
                <td>
                        ${preOvertime.remark}
                </td>
                <td>
                    <c:if test="${sessionScope.user.hasPrivilegeByUrl('/selfPreOvertime/edit') and preOvertime.submitted == false and preOvertime.approved == false}">
                        <a href="/selfPreOvertime/editUI?id=${preOvertime.id}">修改</a>
                    </c:if>
                    <c:if test="${sessionScope.user.hasPrivilegeByUrl('/selfPreOvertime/delete') and preOvertime.submitted == false and preOvertime.approved == false}">
                        <a href="/selfPreOvertime/delete?id=${preOvertime.id}"
                           onclick="return confirm('确定要删除吗?')">删除</a>
                    </c:if>
                    <c:if test="${preOvertime.submitted == true and preOvertime.approved == false}">
                        状态：审核中
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- 其他功能超链接 -->
<div id="TableTail">
    <div id="TableTail_inside">
        <%@ include file="/WEB-INF/views/public/pageView.jspf"%>
    </div>
</div>



</body>
</html>

