<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/layout/bui-header.jsp"%>
<script type="text/javascript" src="${statics}/js/custom/submitJS.js"></script>
<body>
  <div class="demo-content" style="margin: 27px;">
    <div class="row">
      <div>
        <h2><a href="javascript:top.topManager.openPage({id:'${requestScope.MENUS.parentmenucode}',isClose : true});">${requestScope.MENUS.parentmenuname}</a><span class="divider"> / </span>${requestScope.MENUS.menuname }</h2>
        <hr>
            <form:form method="post" id="objForm"  class="form-horizontal">
              <input type="hidden" name="flowcode"  value="${WorkingFlowConfiguration.flowcode}" />
              <div class="control-group">
                <label class="control-label">工作流创建企业:</label>
                <div class="controls">
                     <input type="text" value="${sessionScope.ERCM_USER_SESSION.enterprisename}" disabled="disabled" class="input-normal control-text" style="width:142px;height:18px;" data-rules="{required:true}"/>
                     <input type="hidden" name="enterpeisecode" value="${sessionScope.ERCM_USER_SESSION.enterprisecode}" />
                    <div class="common_mark"></div>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">工作流描述名称:</label>
                <div class="controls">
                    <input type="text" name="flowname" value="${WorkingFlowConfiguration.flowname}" class="input-normal control-text" style="width:142px;height:18px;" data-rules="{required:true}" />
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">工作流执行顺序:</label>
                <div class="controls">
                    <input type="text" name="flowsort" value="${WorkingFlowConfiguration.flowsort}" class="input-normal control-text" style="width:142px;height:18px;" data-rules="{required:true,number:true}"/>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">工作流启动条件:</label>
                <div class="controls">
                    <input type="text" name="flowthreshold" value="${WorkingFlowConfiguration.flowthreshold}" class="input-normal control-text" style="width:142px;height:18px;" data-rules="{required:true,number:true}"/>
                    <div class="common_mark">(取款金额大于或者等于该条件则启动流程)</div>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">工作流处理时间:</label>
                <div class="controls">
                    <input type="text" class="input-normal control-text" name="handletime" value="${WorkingFlowConfiguration.handletime}"  data-rules="{required:true,number:true}" />
                    <div class="common_mark">(流程规定处理的标准时间 [单位为秒] )</div>
                </div>
              </div>
               <div class="control-group">
                <label class="control-label">工作流是否启用:</label>
                <div class="controls">
                      <label class="radio" for=""><input type="radio" name="enable" value="1" style="width:16px;" ${WorkingFlowConfiguration.enable==1?"checked='checked'":""} />启用</label>&nbsp;&nbsp;&nbsp;
                      <label class="radio" for=""><input type="radio" name="enable" value="0" style="width:16px;" ${WorkingFlowConfiguration.enable==0?"checked='checked'":""} />禁用</label>
                      <input type="hidden" value="${WorkingFlowConfiguration.enable}" id="enableHidden"/>
                      <div class="common_mark"></div>
                </div>
              </div>
              <hr>
              <div class="row actions-bar"> 
	              <div class="form-actions span5 offset3">
	                <input type="hidden" id="J_Page_Parent" value="${requestScope.MENUS.parentmenucode}" data-reload="true" >
		            <button type="button" id="J_Form_Submit" class="button button-primary"> 提 交 </button>
		            <button class="button button-danger" type="button" onclick="top.topManager.openPage({id:'${requestScope.MENUS.parentmenucode}',isClose : true});">返回列表</button>
	              </div>
 			  </div>
        </form:form>
      </div>
    </div>  
  </div>
</body>
</html>
<script type="text/javascript">
$(function(){
	BUI.use(['custom/commons','bui/overlay','bui/mask']);
	var form = new BUI.Form.HForm({
	     srcNode : '#objForm',
	}).render();
	bindClick("J_Form_Submit","${ctx}/workingFlow/updateTakeWorkingFlowConfiguration",form);
});
</script>
<%@ include file="/WEB-INF/jsps/layout/pub-click-js.jsp"%>