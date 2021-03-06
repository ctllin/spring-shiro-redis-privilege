<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/statusTran.tld" prefix="payStatus"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="jspfmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmtjsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<table class="grid">
			<tr>
			    <td width="50px">序号</td>
			    <td width="50px">商品编号</td>
				<td width="50px">供应商编号</td>
				<td width="60px">省</td>
				<td width="60px">城市</td>
				<td width="150px">地址</td>
				<td width="70px">坐标</td>
				<td width="150px">创建时间</td>
			</tr>
			<c:forEach items="${beanData }" var="data" varStatus="status">
				<tr>
				    <td>${status.index+1}</td>
				    <td>${data.goods_info_id}</td>
				    <td>${data.supplier_id}</td>
				    <td>${data.province_name}</td>
				    <td>${data.city_name}</td>
				    <td>${data.address}</td>
				    <td>${data.lng_lat}</td>
				    <td>${data.savetime}</td>
					<%-- <td>${data.goodsId }</td>
					<td>${data.goodsName }</td>
					<td>${data.goodsSize }</td>
					<td>${data.quantity}</td>
					<td>${data.price  }</td>
				    <td>${data.supplierguid }</td> 
				    <td>${data.isDelete }</td> 
					<td><fmtjsp:formatDate value="${data.savetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
