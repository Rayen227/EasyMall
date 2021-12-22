<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ pageContext.request.contextPath }/css/addOrder.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=jNQXveWMwrNx5xeSqvdaYrFYOmveq5c1"></script>
</head>
<body>
	<%@ include file = "_head.jsp" %>
	<div class="warp">
		<form action="${pageContext.request.contextPath}/order/addOrder" name="form1" method="post">
			<h3>增加订单</h3>
			<div id="forminfo">
				<span class="lf" style="vertical-align: middle;">收货地址：</span> 
				<label for="textarea"></label>
				<textarea name="receiverinfo" id="textarea" cols="35" rows="3">广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区</textarea>
				
				
	<input name="address" type="text" id="address" class="inp" style="width: 192px" value=""> <!-- 默认搜索框为空 -->
    <input type="button" value=" 搜索位置 " onclick="codeAddress()" class="ana">
    <div style="width:1400px;height:800px;border:1px solid gray" id="allmap"></div>
				
				
				
				
				
				
	
				<br> 支付方式：<input name="" type="radio" value="1" checked>&nbsp;在线支付
				  	   <input type="hidden" name="cartIds" value="${cartIds }">
			</div>
			<table width="999" height="80" border="1" cellpadding="0" cellspacing="0" bordercolor="#d8d8d8">
				<tr>
					<th width="276">商品图片</th>
					<th width="247">商品名称</th>
					<th width="231">商品单价</th>
					<th width="214">购买数量</th>
					<th width="232">总价</th>
				</tr>
				<c:set var="sum" value="0"></c:set>
				<c:forEach items="${carts }" var="cart">
				<tr>
					<td><img src="${pageContext.request.contextPath}${cart.imgurl}" 
					width="90" height="90" class="prodimg" /></td>
					<td>${cart.name }</td>
					<td>${cart.price }元</td>
					<td>${cart.num }件</td>
					<td>${cart.price*cart.num }元</td>
				</tr>
				<c:set var="sum" value="${sum+cart.price*cart.num}"></c:set>
				</c:forEach>
			</table>

			<div class="Order_price">总价：${sum }元</div>

			<div class="add_orderbox">
				<input name="" type="submit" value="增加订单" class="add_order_but">
			</div>
		</form>
	</div>
	

	    <script type="text/javascript">
        var map = new BMap.Map("allmap");
        //var map = new BMap.Map("allmap", { mapType: BMAP_SATELLITE_MAP });
        var point = new BMap.Point(109.503789, 35.860026); // 获取上方默认的经纬度
        map.centerAndZoom(point, 14); // 重置地图显示区域
        map.enableScrollWheelZoom(); // 启用滚轮放大缩小
 
        //定位
        var geolocation = new BMap.Geolocation();  // 创建地址解析器实例
        geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                var mk = new BMap.Marker(r.point);
                map.addOverlay(mk);
                map.panTo(r.point);
                //mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                mk.enableDragging();
                //alert('您的位置：' + r.point.lng + ',' + r.point.lat);
                document.getElementById("precision").value = r.point.lng; // 获取得位置的值
                document.getElementById("latitude").value = r.point.lat;
            } else {
                //alert('failed' + this.getStatus());
            }
        }, {enableHighAccuracy: true})
 
        //add city
        map.addControl(new BMap.CityListControl({
            anchor: BMAP_ANCHOR_TOP_LEFT
        }));
 
        //add click
        function showInfo(e) {
            //alert(e.point.lng + ", " + e.point.lat);
            document.getElementById("precision").value = e.point.lng;
            document.getElementById("latitude").value = e.point.lat;
            var mk = new BMap.Marker(e.point);
            map.addOverlay(mk);
            map.panTo(e.point);
            //mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            deletePoint(); //删除所有标注
        }
 
        map.addEventListener("click", showInfo);
 
        function deletePoint() {
            var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length - 1; i++) {
                map.removeOverlay(allOverlay[i]);
            }
        }
 
        //关于状态码
        //BMAP_STATUS_SUCCESS    检索成功。对应数值“0”。
        //BMAP_STATUS_CITY_LIST    城市列表。对应数值“1”。
        //BMAP_STATUS_UNKNOWN_LOCATION    位置结果未知。对应数值“2”。
        //BMAP_STATUS_UNKNOWN_ROUTE    导航结果未知。对应数值“3”。
        //BMAP_STATUS_INVALID_KEY    非法密钥。对应数值“4”。
        //BMAP_STATUS_INVALID_REQUEST    非法请求。对应数值“5”。
        //BMAP_STATUS_PERMISSION_DENIED    没有权限。对应数值“6”。(自 1.1 新增)
        //BMAP_STATUS_SERVICE_UNAVAILABLE    服务不可用。对应数值“7”。(自 1.1 新增)
        //BMAP_STATUS_TIMEOUT    超时。对应数值“8”。(自 1.1 新增)
 
        function codeAddress() { // 调用查询..查询位置
            // 创建地址解析器实例
            var myGeo = new BMap.Geocoder();
            // 将地址解析结果显示在地图上,并调整地图视野
            myGeo.getPoint(document.getElementById("address").value, function (point) {
                if (point) {
                    map.centerAndZoom(point, 14);
                    map.addOverlay(new BMap.Marker(point));
                    document.getElementById("precision").value = point.lng;
                    document.getElementById("latitude").value = point.lat;
                } else {
                    alert("您输入的地址在地图中未找到，请重新输入地址!");
                }
            }, "");
        }
 
    </script>
	<%@ include file = "_foot.jsp" %>
</body>
</html>
