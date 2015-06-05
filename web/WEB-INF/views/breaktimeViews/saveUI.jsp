<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>休假记录</title>
    <%@include file="/WEB-INF/views/public/commons.jspf" %>
    <link href="${pageContext.request.contextPath}/style/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          media="screen">
    <link href="${pageContext.request.contextPath}/style/bootstrap/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" media="screen">
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/bootstrap/bootstrap-datetimepicker.js"
            charset="UTF-8"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/script/bootstrap/locales/bootstrap-datetimepicker.zh-CN.js"
            charset="UTF-8"></script>
    <script language="javascript" type="text/javascript">
        function check(){
            if(document.getElementById("date").value == "" || document.getElementById("duration").value == ""){
                alert("日期和小时不能为空！");
                return false;
            }
            else{
                return true;
            }
        }
        function clearNoNum(obj)
        {
            //先把非数字的都替换掉，除了数字和.
            obj.value = obj.value.replace(/[^\d.]/g,"");
            //必须保证第一个为数字而不是.
            obj.value = obj.value.replace(/^\./g,"");
            //保证只有出现一个.而没有多个.
            obj.value = obj.value.replace(/\.{2,}/g,".");
            //保证.只出现一次，而不能出现两次以上
            obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        }
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 休假记录
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div id="MainArea">
    <form:form action="${id == null ? 'add' : 'edit'}" modelAttribute="breaktime" method="post" cssClass="form-horizontal" onsubmit="return check()">
        <form:hidden path="id"/>
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="form-group">
                    <label class="col-md-2 control-label">使用人</label>

                    <div class="input-group col-md-5">
                        <form:select path="userId" items="${users}" itemLabel="name" itemValue="id"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dtp_input2" class="col-md-2 control-label">日期</label>

                    <div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy/mm/dd"
                         data-link-field="dtp_input2" data-link-format="yyyy/mm/dd">
                        <form:input path="date" size="16" cssClass="form-control" readonly="true"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                    <input type="hidden" id="dtp_input2" value=""/><br/>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">小时</label>

                    <div class="input-group col-md-5">
                        <form:input path="duration" cssClass="InputStyle" onkeyup="clearNoNum(this)"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">说明</label>

                    <div class="input-group col-md-5">
                        <form:textarea path="description" cssClass="TextareaStyle"/>
                    </div>
                </div>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
                <%--<input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>--%>
                <%--<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>--%>
            <input type="submit" value="提交" title="提交"/>
            <a href="javascript:history.go(-1);"><input type="button" onclick="" value="取消"/></a>
        </div>

    </form:form>
</div>
<script>
    $('.form_date').datetimepicker({
        format: "yyyy/mm/dd",
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

</script>


</body>
</html>

