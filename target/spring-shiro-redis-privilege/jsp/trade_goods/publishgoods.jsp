<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'index.jsp' starting page</title>
		<c:set var="ctx" value="${pageContext.request.contextPath}"/>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!-- 配置文件 -->
		<script type="text/javascript" src="http://localhost/lightmanger/ueditors/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="http://localhost/lightmanger/ueditors/ueditor.all.min.js"></script>
		<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
		<script type="text/javascript" src="http://localhost/lightmanger/ueditors/lang/zh-cn/zh-cn.js"></script>
		<!-- [my97日期时间控件] -->
<script type="text/javascript" src="./jslib/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- [jQuery] -->
<script src="./jslib/easyui1.4.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>

<!-- [EasyUI] -->
<link id="easyuiTheme" rel="stylesheet" href="./jslib/easyui1.4.2/themes/<c:out value="${cookie.easyuiThemeName.value}" default="gray"/>/easyui.css" type="text/css">
<link id="easyuiTheme" rel="stylesheet" href="./jslib/easyui1.4.2/themes/icon.css" type="text/css">
<script type="text/javascript" src="./jslib/easyui1.4.2/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="./jslib/easyui1.4.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<!-- [扩展JS] -->
<script type="text/javascript" src="./jslib/extJs.js" charset="utf-8"></script>

<!-- [扩展lightmvc样式] -->
<link rel="stylesheet" href="./style/css/lightmvc.css" type="text/css">
		
		
	</head>
	<body>
	<table class="grid" id="datalist2" >
					<tr>
					  <td style="width: 150px">支付宝交易号</td><td>
					  <input name="tradeNo" value="" id="tradeNo" style="width: 230px;height: 100%"   type="text" placeholder="请输入支付宝交易号" class="easyui-validatebox span2" data-options="required:true" >
					  </td>
					</tr>
					<tr>
					  <td style="width: 150px">退款金额</td><td><input name="totalMoney" id="totalMoney" value="" style="width: 230px;height: 100%"   type="text" placeholder="请输入退款金额 例如：0.01" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					</tr>
					<tr>
					  <td style="width: 150px">退款原因</td><td><input name="refundReason" id="refundReason" value="" style="width: 230px;height: 100%"   type="text" placeholder="退款原因 例如：协商退款" class="easyui-validatebox span2" data-options="required:true" value=""></td>
					</tr>
				</table>
		<div>
    <h1>完整demo ${ctx}     </h1><br/>
   <%=basePath %><br/>
    <%=path %><br/>
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
<div id="btns">
    <div>
        <button onclick="getAllHtml()">获得整个html的内容</button>
        <button onclick="getContent()">获得内容</button>
        <button onclick="setContent()">写入内容</button>
        <button onclick="setContent(true)">追加内容</button>
        <button onclick="getContentTxt()">获得纯文本</button>
        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
        <button onclick="hasContent()">判断是否有内容</button>
        <button onclick="setFocus()">使编辑器获得焦点</button>
        <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
        <button onmousedown="setblur(event)" >编辑器失去焦点</button>

    </div>
    <div>
        <button onclick="getText()">获得当前选中的文本</button>
        <button onclick="insertHtml()">插入给定的内容</button>
        <button id="enable" onclick="setEnabled()">可以编辑</button>
        <button onclick="setDisabled()">不可编辑</button>
        <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
        <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
        <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
    </div>

    <div>
        <button onclick="getLocalData()" >获取草稿箱内容</button>
        <button onclick="clearLocalData()" >清空草稿箱</button>
    </div>

</div>
<div>
    <button onclick="createEditor()">
    创建编辑器</button>
    <button onclick="deleteEditor()">
    删除编辑器</button>
</div>

<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');


    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        //alert(arr.join("\n"));
        document.write(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
</script>
	</body>
</html>
