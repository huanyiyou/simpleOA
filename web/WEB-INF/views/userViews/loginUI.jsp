<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/public/commons.jspf" %>
<html>
<head>
  <title>加班系统</title>
  <link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet>
  <script type="text/javascript">
    $(function(){
      document.forms[0].loginName.focus();
    });

    // 在被嵌套时就刷新上级窗口
    if(window.parent != window){
      window.parent.location.reload(true);
    }
  </script>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody >



<!-- 显示表单 -->
<form:form action="/user/login"  focusElement="loginNameInput">
  <div id="CenterAreaBg">
    <div id="CenterArea">
      <div id="LogoImg"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/logo.png" /></div>
      <div id="LoginInfo">
        <div>
          <label for="loginNameInput"> <img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif"/></label>
          <input type="text" name="loginName" size="20" tabindex="1" class="TextField required" id="loginNameInput"/>
        </div>
        <div>
          <label for="aa" ><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></label>
          <input type="password" name="password" id="aa" size="20" tabindex="2"  class="TextField required"/>
        </div>
        <input type="image" tabindex="3" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif" />
        <a style="color: yellow">${message}</a>
      </div>
      <div id="CopyRight"><a href="javascript:void(0)">&copy; 2015 版权所有 </a></div>
    </div>
  </div>
</form:form>
</body>

</html>

