<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
<div id="MainArea">
    <form:form action="${id == null ? 'add' : 'edit'}" modelAttribute="preOvertime" method="post"
               cssClass="form-horizontal" onsubmit="return check()">
        <form:hidden path="id"/>
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
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
                    <label class="col-md-2 control-label">工作日、双休日、国定假日</label>

                    <div class="input-group col-md-5">
                        <form:radiobutton path="type" value="1"/>工作日
                        <form:radiobutton path="type" value="2"/>双休日
                        <form:radiobutton path="type" value="3"/>国定假日
                    </div>
                </div>
                <div class="form-group">
                    <label for="dtp_input3" class="col-md-2 control-label">开始时间</label>

                    <div class="input-group date form_startTime col-md-5" data-date="" data-date-format="hh:ii"
                         data-link-field="dtp_input3" data-link-format="hh:ii">
                        <form:input path="startTime" size="16" cssClass="form-control" readonly="true"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                    </div>
                    <input type="hidden" id="dtp_input3" value=""/><br/>
                </div>
                <div class="form-group">
                    <label for="dtp_input4" class="col-md-2 control-label">结束时间</label>

                    <div class="input-group date form_endTime col-md-5" data-date="" data-date-format="hh:ii"
                         data-link-field="dtp_input4" data-link-format="hh:ii">
                        <form:input path="endTime" size="16" cssClass="form-control" readonly="true"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                    </div>
                    <input type="hidden" id="dtp_input4" value=""/><br/>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">时长</label>

                    <div class="input-group col-md-5">
                        <form:input path="timeSpan" readonly="true"/>
                        <input type="button" onclick="getVal()" title="计算时长" value="计算时长"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">工作描述</label>

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
    $('.form_startTime').datetimepicker({
        language: 'zh-CN',
        autoclose: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
//        minuteStep: 1
    }).on('changeDate', function (ev) {
        var startTime = ev.date.valueOf();
        start = startTime;
        $("#endTime").val("");
        $("#timeSpan").val("");
    });
    $('.form_endTime').datetimepicker({
        language: 'zh-CN',
        autoclose: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
//        minuteStep: 1
    }).on('changeDate', function (ev) {
        var endTime = ev.date.valueOf();
        end = endTime;
        if (end < start) {
            alert("“结束时间 ”不能早于“开始时间 ” ！");
            $("#endTime").val("");
        } else {

        }
        $("#timeSpan").val("");
    });
</script>
<script>
    function getVal() {
        var date = $("#date").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if (date == "" || startTime == "" || endTime == "") {

        }
        else {
            var date1 = date + " " + startTime + ":00";
            var date2 = date + " " + endTime + ":00";
            var date3 = Date.parse(date2) - Date.parse(date1);

            //计算出小时数
            var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
            var hours = Math.floor(leave1 / (3600 * 1000))
            //计算相差分钟数
            var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
            var minutes = Math.floor(leave2 / (60 * 1000))
            $("#timeSpan").val(hours + "小时" + minutes + "分钟");
        }
    }
</script>

<script>
    function check() {
        var date = $("#date").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var timeSpan = $("#timeSpan").val();
        if (date == null || date == '' ||
                startTime == null || startTime == '' ||
                endTime == null || endTime == '' ||
                timeSpan == null || timeSpan == '') {
            alert("日期或时间有空值!");
            return false;
        }
        return true;
    }
</script>

</body>
</html>

