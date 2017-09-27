<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>

<script type="text/javascript"
	src="<c:url value="/resources/static/smarteditor/js/service/HuskyEZCreator.js"/>"
	charset="utf-8"></script>
	
<div class="container-content" style="margin-top: 120px" >
	<div class="content-body">
		<div class="section-report">
			<div class="report-header">
				<h1>업무 보고</h1>
				<p>한 주간의 업무한 내용을 보고해주세요</p>
			</div>
			
			<div class="report-content" style="padding: 20px; margin-top: 50px;">
				<div id="form-loading-view"></div>
				<form id="report-form" class="report-form" enctype="multipart/form-data" acceptcharset="UTF-8">
					<table>
						<tbody>
							<tr>
								<td class="form-menu">옵션</td>
								<td class="form-menu-content"><input type="checkbox" id="notification" ${report.type eq 1 ? 'checked': ''} >공지</td>
							</tr>
							<tr>
								<td class="form-menu">보고자</td>
								<td class="form-menu-content">${sessionUser.name}
									${sessionUser.positionCategory.name}님</td>
							</tr>
							<tr>
								<td class="form-menu">제목</td>
								<td class="form-menu-content"><input type="text" id="title"
									name="title" class="title-input" value="${report.title }" placeholder="제목을 입력하세요"></td>
							</tr>
							<tr>
								<td class="form-menu">내용</td>
								<td>
									<div id="file-loading-view"></div>
									
									<div class="menu-option">
										<div class="option-photo" id="photo-button">
											<i class="fa fa-camera-retro" aria-hidden="true"></i></i><span>포토</span>
										</div>
										<input type="file" class="photo-file" id="photo-file" />
										
										<div class="option-video" id="video-button">
											<i class="fa fa-file-video-o" aria-hidden="true"></i><span>비디오</span>
											
										</div>
										<input type="file" class="video-file" id="video-file" />
									</div>
								
								<textarea name="smarteditor" id="smarteditor" rows="10"
										cols="100" style="width: 100%; height: 412px;"></textarea>
										
								</td>
							</tr>
							<tr>
								<td class="form-menu">파일 첨부</td>
								<td class="form-menu-content">
									<div id="file-drop-zone" >
										첨부파일을 마우스로 끌어오세요 
										
										<label for="file-input">파일 선택</label>
										<input id="file-input" 
											class='file' type="file" name="file"
											style="cursor: pointer; outline: none;" />
										
									</div>
									
									<div class="file-content-zone" hidden="hidden">
										<ul id="file-list">
										</ul>
									</div>
									
								</td>
							</tr>
						</tbody>
					</table>

					<div class="form-submit">
						<input class="submit-button" id="submit" type="button" value="제출" />
					</div>
				</form>
			</div>
			
		</div>
	</div>
</div>

<script>

	var editor_object = [];

	nhn.husky.EZCreator.createInIFrame({
		oAppRef : editor_object,
		elPlaceHolder : "smarteditor",
		sSkinURI : "${contextPath}/resources/static/smarteditor/SmartEditor2Skin.jsp",
		htParams : {
			bUseToolbar : true,
			bUseVerticalResizer : true,
			bUseModeChanger : true,
			fOnBeforeUnload : function() {
	
			}
		},fOnAppLoad : function(){
			var content = '${report.content}';
			editor_object.getById["smarteditor"].exec("PASTE_HTML", [content]);
		}
	});
</script>