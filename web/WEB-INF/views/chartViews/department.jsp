<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门统计图表</title>
    <%@include file="/WEB-INF/views/public/commons.jspf" %>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/script/fusioncharts/js/fusioncharts.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/script/fusioncharts/js/themes/fusioncharts.theme.fint.js"></script>
    <script type="text/javascript">
        FusionCharts.ready(function () {
            var multiseriesChart = new FusionCharts({
                "type": "mscolumn2d",
                "renderAt": "chartContainer",
                "width": "1200",
                "height": "500",
                "dataFormat": "json",
                "dataSource":  {
                    "chart": {
                        "caption": "加班与休假统计",
                        "subCaption": "${year} 年 ${userName}",
                        "xAxisname": "月份",
                        "yAxisName": "市场 (小时)",
//                        "numberPrefix": "h",
                        "numberSuffix":"小时",
                        "theme": "fint"
                    },
                    "categories": [
                        {
                            "category": [
                                {
                                    "label": "1月份"
                                },
                                {
                                    "label": "2月份"
                                },
                                {
                                    "label": "3月份"
                                },
                                {
                                    "label": "4月份"
                                },
                                {
                                    "label": "5月份"
                                },
                                {
                                    "label": "6月份"
                                },
                                {
                                    "label": "7月份"
                                },
                                {
                                    "label": "8月份"
                                },
                                {
                                    "label": "9月份"
                                },
                                {
                                    "label": "10月份"
                                },
                                {
                                    "label": "11月份"
                                },
                                {
                                    "label": "12月份"
                                }
                            ]
                        }
                    ],
                    "dataset": [
                        {
                            "seriesname": "休假",
                            "data": [
                                {
                                    "value": "${breaktimes[0]}"
                                },
                                {
                                    "value": "${breaktimes[1]}"
                                },
                                {
                                    "value": "${breaktimes[2]}"
                                },
                                {
                                    "value": "${breaktimes[3]}"
                                },
                                {
                                    "value": "${breaktimes[4]}"
                                },
                                {
                                    "value": "${breaktimes[5]}"
                                },
                                {
                                    "value": "${breaktimes[6]}"
                                },
                                {
                                    "value": "${breaktimes[7]}"
                                },
                                {
                                    "value": "${breaktimes[8]}"
                                },
                                {
                                    "value": "${breaktimes[9]}"
                                },
                                {
                                    "value": "${breaktimes[10]}"
                                },
                                {
                                    "value": "${breaktimes[11]}"
                                }
                            ]
                        },
                        {
                            "seriesname": "加班",
                            "data": [
                                {
                                    "value": "${overtimes[0]}"
                                },
                                {
                                    "value": "${overtimes[1]}"
                                },
                                {
                                    "value": "${overtimes[2]}"
                                },
                                {
                                    "value": "${overtimes[3]}"
                                },
                                {
                                    "value": "${overtimes[4]}"
                                },
                                {
                                    "value": "${overtimes[5]}"
                                },
                                {
                                    "value": "${overtimes[6]}"
                                },
                                {
                                    "value": "${overtimes[7]}"
                                },
                                {
                                    "value": "${overtimes[8]}"
                                },
                                {
                                    "value": "${overtimes[9]}"
                                },
                                {
                                    "value": "${overtimes[10]}"
                                },
                                {
                                    "value": "${overtimes[11]}"
                                }
                            ]
                        }
                    ]
                }
            });

            multiseriesChart.render();
        });
    </script>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 部门统计图表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<form action="/chart/department">
    年：
    <select name="year">
        <c:forEach items="${years}" var="y">
            <option value="${y}" <c:if test="${y == year}">selected</c:if>>${y}</option>
        </c:forEach>
    </select>
    姓名：
    <select name="userId">
        <option value="0" <c:if test="${userId == 0}">selected</c:if>>所有人</option>
        <c:forEach items="${users}" var="user">
            <option value="${user.id}" <c:if test="${userId == user.id}">selected</c:if>>${user.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="提交" />
</form>
<div id="chartContainer">FusionCharts XT will load here!</div>

</body>
</html>
