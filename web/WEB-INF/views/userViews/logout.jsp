<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/public/commons.jspf"%>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
  <title>您已退出</title>
  <link href="${pageContext.request.contextPath}/style/blue/logout.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div>
    <div align="center">
      <div id=Logout>
        <div id=AwokeMsg><img id=LogoutImg src="${pageContext.request.contextPath}/style/blue/images/logout/logout.gif" border=0 /><img id=LogoutTitle src="${pageContext.request.contextPath}/style/blue/images/logout/newlogout.jpg" border=0 /></div>
        <div id=LogoutOperate>
          <img src="${pageContext.request.contextPath}/style/blue/images/logout/logout2.gif" border=0 /> <a href="/user/loginUI">重新进入系统</a>
          <%--<img src="${pageContext.request.contextPath}/style/blue/images/logout/logout3.gif" border=0 /> <a href="javascript: window.close();">关闭当前窗口</a>--%>
        </div>
      </div>
    </div>
</div>
</body>
</html>

