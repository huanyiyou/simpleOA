<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/public/commons.jspf" %>
<html>
<head>
  <title>simpleOA</title>
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
        <table BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
          <tr>
            <td colspan="3"><!-- 显示错误 -->
              <font color="red"><form:errors/></font>
            </td>
          </tr>
          <tr>
            <td width=45 class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" /></td>
            <td>
              <input type="text" name="loginName" size="20" tabindex="1" cssClass="TextField required" id="loginNameInput"/>
            </td>
            <td rowspan="2" style="padding-left:10px;">
              <input type="image" tabindex="3" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif" />
            </td>
          </tr>
          <tr>
            <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></td>
            <td><input type="password" name="password" id="aa" size="20" tabindex="2" showPassword="false" cssClass="TextField required"/></td>
          </tr>
        </table>
      </div>
      <div id="CopyRight"><a href="javascript:void(0)">&copy; 2015 版权所有 </a></div>
    </div>
  </div>
</form:form>
</body>

</html>
