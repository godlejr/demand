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

			<div class="report-content">
				<div id="form-loading-view"></div>
				<form id="report-form" class="report-form" enctype="multipart/form-data" acceptcharset="UTF-8">
					<table>
						<tbody>
							<tr>
								<td class="form-menu">옵션</td>
								<td class="form-menu-content"><input type="checkbox" id="notification">공지</td>
							</tr>
							<tr>
								<td class="form-menu">보고자</td>
								<td class="form-menu-content">${sessionUser.name}
									${sessionUser.positionCategory.name}님</td>
							</tr>
							<tr>
								<td class="form-menu">제목</td>
								<td class="form-menu-content"><input type="text" id="title"
									name="title" class="title-input" placeholder="제목을 입력하세요"></td>
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
	var fileZone = $("#file-drop-zone");

	var $photoFile = $('#photo-file'),
		$photoButton = $('#photo-button');
	
	var	$videoFile = $('#video-file'),
		$videoButton = $('#video-button');
	
	var $fileUploadingView = $('#file-loading-view'),
		$formUploadingView = $('#form-loading-view');
	
	var $submit=$('#submit');
	
	var fileSize = 0,
		isFileListShown = false,
		autoFileCount = 0;
	
	var reportFileTempForm;
	
	$videoButton.click(function(event){
		event.preventDefault();
		$videoFile.click();
	});
	
	
	$videoFile.change(function(event){
		var ext = $(this).val().split('.').pop().toLowerCase();
		
		if (ext.length > 0) {
	        if ($.inArray(ext, ['avi', 'mpeg4', 'mp4']) == -1) {
	            $(this).val('');
	            alert('비디오 파일(avi, mpeg4, mp4)만 업로드 할수 있습니다.');
	            return false;
	        }
	        var file = $videoFile[0].files[0];
	        
	        if(checkFileSizeLimitFiveMB(file.size)){
	        	var formdata = new FormData();
	 	        formdata.append("file",file );
	 	        formdata.append("fileFlag",2);
	       
	 	        singleFileUploader(formdata, 2);
	        }else{
	            alert('비디오 파일 크기는 20MB 미만으로 업로드 할수 있습니다.');
	        }
	    }
	});
	
	
	$photoButton.click(function(event){
		event.preventDefault();
		$photoFile.click();
	});
	
	$photoFile.change(function(){
		var ext = $(this).val().split('.').pop().toLowerCase();
		
		if (ext.length > 0) {
	        if ($.inArray(ext, ['jpg', 'jpeg', 'png','ico']) == -1) {
	            $(this).val('');
	            alert('사진 파일(jpg, png, ico)만 업로드 할수 있습니다.');
	            return false;
	        }

	        var file = $photoFile[0].files[0];
	       
	        var formdata = new FormData();
	        formdata.append("file",file );
	        formdata.append("fileFlag",1);
      
	        singleFileUploader(formdata, 1);
	    }
	});
	
	function attachFileToEditor(fileUrl,sortOfFile){
		
		var fileTemplate = "";
		
		if(sortOfFile == 1){  //photo == 1
			fileTemplate = "<img style='max-width:100%;' src='" + fileUrl + "'/>";
		}else{
			fileTemplate =	"<video style='max-width:100%;' controls >" +
							"<source src='" + fileUrl + "'>" +
							"</video>";
		}
	
		editor_object.getById["smarteditor"].exec("PASTE_HTML", [fileTemplate]);
	}
	
	function singleFileUploader(formdata, fileFlag){
		$.ajax({
			type: "POST",
			url: "${contextPath}/smarteditor/uploader",
			processData: false,  
			contentType: false,
			enctype: 'multipart/form-data',
			data: formdata,
            success: function(data) {
            	attachFileToEditor(data, fileFlag);
            },
            beforeSend: function(){
            	$fileUploadingView.show();
	        },
	        complete: function(){
	        	$fileUploadingView.hide();
	        }
	    });
	}
	
	document.getElementById('file-input').addEventListener('change',prepareFileUpload,false);
	
	function updateFileListView(fileSize, file){
		if(fileSize > 0 ){
			if(!isFileListShown){
				$('.file-content-zone').show();
				isFileListShown = true;
				reportFileTempForm = new FormData();
			}
			reportFileTempForm.append('files' + autoFileCount , file);
			
			var fileIndex = fileSize - 1;
			
			var fileItemTemplate = "<li class='list-item'>"+
									"<i class='fa fa-file-code-o' aria-hidden='true'></i>" +
									"<span class='item-name'>"+ file.name + "</span>" + 
									"<i class='fa fa-times' style='cursor:pointer;' onClick='deleteFileList(this," + autoFileCount + ");' aria-hidden='true'></i>" + 
									"</li>";
			
			$("#file-list").append(fileItemTemplate);
			
			++autoFileCount;
			
		}else{
			if(isFileListShown){
				$('.file-content-zone').hide();
				isFileListShown = false;
			}
		}
	}
	
	function deleteFileList(self, fileIndex){
		--fileSize;
		console.log(reportFileTempForm.get("files"+ fileIndex).name);
		reportFileTempForm.delete("files"+ fileIndex);
		$(self).parent().remove();
		
		if(fileSize == 0){
			if(isFileListShown){
				$('.file-content-zone').hide();
				isFileListShown = false;
			}
		}
	}
	
	function prepareFileUpload(event) {
	 	var files = event.target.files;
		 
	 	if (files.length < 1)
			return;
	 	
	 	for (var i = 0; i < files.length; i++) {
			++fileSize;
			updateFileListView(fileSize, files[i]);
     	}
	}
	

	fileZone.on('dragenter', function(event) {
		event.stopPropagation();
		event.preventDefault();
		$(this).css('border', '2px solid #c0a179');
	});

	fileZone.on('dragleave', function(event) {
		event.stopPropagation();
		event.preventDefault();
		$(this).css('border', '2px dotted #c0a179');
	});

	fileZone.on('dragover', function(event) {
		event.stopPropagation();
		event.preventDefault();
	});

	fileZone.on('drop', function(event) {
		event.preventDefault();
		$(this).css('border', '2px dotted #c0a179');

		var files = event.originalEvent.dataTransfer.files;
		if (files.length < 1)
			return;
		
		for (var i = 0; i < files.length; i++) {
			++fileSize;
			updateFileListView(fileSize, files[i]);
        }
	});


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
		}
	});

	
	
	$submit.click(function(){
		window.onunload = window.onbeforeunload = undefined;
		
		editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
		var content =  $("#smarteditor").val();
		var title = $("#title").val();
		var isNotification = $('input:checkbox[id="notification"]').is(":checked");
		
		if(title == '' || title == null){
			alert('제목을 입력하세요.');
			$title.focus();
			return;
		}else{
			if(title.legth > 40){
				alert('제목은 40자 이내로 작성해주세요.');
				$title.focus();
				return;
			}
		}
		if(content == '' || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>' ||  content == '<p><br></p>'){
			alert('내용을 입력하세요.');
			$content.focus();
			return;
		}
		
		
		var reportForm = new FormData();
		reportForm.append("title",title);
		reportForm.append("content",content);
		reportForm.append("isNotification",isNotification);
		
		for(var i =0 ; i < autoFileCount ; i++ ){
			if(reportFileTempForm.has('files'+ i)){
				reportForm.append("files", reportFileTempForm.get('files'+ i));
			}
		}
		
		sendReport(reportForm);
	});
	
	function navigateToReportListPage(){
		 document.location.href = "${contextPath}/reports";
	}
	
	function sendReport(reportForm){
		$.ajax({
			type: "POST",
			url: "${contextPath}/reports/",
			processData: false,  
			contentType: false,
			enctype: 'multipart/form-data',
			data: reportForm,
            success: function(data) {
            	navigateToReportListPage();
            },
            beforeSend: function(){
            	$formUploadingView.show();
	        },
	        complete: function(){
	        	$formUploadingView.hide();
	        }
	    });
	}
	
	function checkFileSizeLimitFiveMB(originalfileSize){
		var fileSize = originalfileSize/1024/1024;
		if(fileSize > 20){
			return false;
		}else{
			return true;
		}
	}
	


	
</script>
