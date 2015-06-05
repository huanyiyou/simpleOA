<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<html>
<head>
    <title>导航菜单</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css"/>
    <script>
        function menuClick(menu){
            $(menu).next().toggle();
        }
    </script>
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
        <c:forEach items="${applicationScope.privilegeTopList}" var="privilege">
           <c:if test="${sessionScope.user.hasPrivilegeByName(privilege.name)}">
            <li class="level1">
                <div onClick="menuClick(this)" class="level1Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/${privilege.id}.ico"
                                                                        class="Icon"/>
                        ${privilege.name}
                </div>
                <ul  class="MenuLevel2">
                    <c:forEach items="${privilege.children}" var="privilege">
                        <c:if test="${sessionScope.user.hasPrivilegeByName(privilege.name)}">
                        <li class="level2">
                            <div class="level2Style">
                                <img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif"/>
                                <a target="right" href="${pageContext.request.contextPath}${privilege.url}">${privilege.name}</a>
                            </div>
                        </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
           </c:if>
        </c:forEach>
    </ul>
</div>
</body>
</html>
