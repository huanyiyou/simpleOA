<%@include file="/WEB-INF/views/public/commons.jspf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>加班系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="60,*" framespacing="0" border="0" frameborder="0">
  <frame src="/home/top" name="TopMenu"  scrolling="no" noresize />
  <frameset cols="180,*" id="resize">
    <frame noresize name="menu" src="/home/left" scrolling="yes" />
    <frame noresize name="right" src="/home/right" scrolling="yes" />
  </frameset>
  <%--<frame noresize name="status_bar" scrolling="no" src="/home/bottom" />--%>
</frameset>
<noframes>
  <body>
  </body>
</noframes>
</html>
