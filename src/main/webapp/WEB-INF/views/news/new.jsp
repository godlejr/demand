<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>
<c:set var="newsFileUrl"
   value="http://static.demand.co.kr/homepage/files/news"></c:set>

<script type="text/javascript"
   src="<c:url value="/resources/static/smarteditor/js/service/HuskyEZCreator.js"/>"
   charset="utf-8"></script>

<div class="container-content" style="margin-top: 120px">
   <jsp:include page="../question/banner.jsp" flush="false">
      <jsp:param name="title" value="보도자료" />
   </jsp:include>

   <div class="content-body">
      <div class="section-news">
         <div class="news-content" style="padding: 20px; margin-top: 50px;">
            <div id="form-loading-view"></div>
            <form id="news-form" class="news-form" enctype="multipart/form-data"
               acceptcharset="UTF-8">
               <table>
                  <tbody>
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
                        <td class="form-menu">대표 이미지</td>
                        <td class="form-menu-content">
                           <div id="avatar-file-drop-zone">

                              <div class="avatar-file-input">
                                 <label for="avatar-file-input">대표이미지 선택</label> <input
                                    id="avatar-file-input" class='file' type="file" name="file"
                                    style="cursor: pointer; outline: none;" />
                              </div>

                              <div class="avatar-file-image" data-id="0"></div>

                           </div>

                        </td>
                     </tr>

                     <tr>
                        <td class="form-menu">내용</td>
                        <td>
                           <div id="file-loading-view"></div>

                           <div class="menu-option">
                              <div class="option-photo" id="photo-button">
                                 <i class="fa fa-camera-retro" aria-hidden="true"></i><span>포토</span>
                              </div>
                              <input type="file" class="photo-file" id="photo-file" />

                              <div class="option-video" id="video-button">
                                 <i class="fa fa-file-video-o" aria-hidden="true"></i><span>비디오</span>

                              </div>
                              <input type="file" class="video-file" id="video-file" />
                           </div> <textarea name="smarteditor" id="smarteditor" rows="10"
                              cols="100" style="width: 100%; height: 412px;"></textarea>

                        </td>
                     </tr>
                     <tr>
                        <td class="form-menu">파일 첨부</td>
                        <td class="form-menu-content">
                           <div id="file-drop-zone">
                              첨부파일을 마우스로 끌어오세요 <label for="file-input">파일 선택</label> <input
                                 id="file-input" class='file' type="file" name="file"
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
   var avatarFileZone = $("#avatar-file-drop-zone");
   var fileZone = $("#file-drop-zone");
   
   var $avatarFile = $("#avatar-file-input");
   var $photoFile = $('#photo-file'),
      $photoButton = $('#photo-button');

   var   $videoFile = $('#video-file'),
      $videoButton = $('#video-button');

   var $fileUploadingView = $('#file-loading-view'),
      $formUploadingView = $('#form-loading-view');
   
   var $submit = $('#submit');   
   
   var fileSize = 0,
      isFileListShown = false,
      autoFileCount = 0;

   var newsFileTempForm;

   avatarFileZone.on("dragenter", function(event){
      event.stopPropagation();
      event.preventDefault();
      $(this).css('border', '2px solid #c0a179');
   });

   avatarFileZone.on("dragleave", function(event){
      event.stopPropagation();
      event.preventDefault();
      $(this).css('border', '0');
   });
   
   avatarFileZone.on("dragover", function(event){
      event.stopPropagation();
      event.preventDefault();
   });
   
   avatarFileZone.on("drop", function(event){
      event.preventDefault();
      $(this).css('border', '2px solid #c0a179');
      
      var files = event.originalEvent.dataTransfer.files;
      var fileItemHolder = $(".avatar-file-image");
      var dataId = fileItemHolder.attr("data-id");

      if(dataId != "0"){
         fileItemHolder.children(".image-item").remove();
         deleteFileById(dataId);
      }
      
      if(files.length > 0){
         var file = files[0];
         var formData = new FormData();
         
         formData.append("file", file);
         updateAvatarFile(formData);
      }
   });

   document.getElementById('avatar-file-input').addEventListener('change', prepareAvatarFileUpload, false);
   document.getElementById('file-input').addEventListener('change',prepareFileUpload,false);
   
   function onClickAvatarImage(){
      $avatarFile.click();
   }
   
   function prepareAvatarFileUpload(event) {
       var files = event.target.files;   
       if(files.length != 0){  
            var ext = $(this).val().split('.').pop().toLowerCase();
            if(ext.length > 0){
                if ($.inArray(ext, ['jpg', 'jpeg', 'png','ico']) == -1) {
                     $(this).val('');
                     alert('사진 파일(jpg, png, ico)만 업로드 할수 있습니다.');
                     return ;
                 }
            }
            
            var formData = new FormData();
             var fileItemHolder = $(this).parent().siblings(".avatar-file-image");
             var dataId = fileItemHolder.attr("data-id");
             
             if(dataId != "0"){
               fileItemHolder.children(".image-item").remove();
               deleteFileById(dataId);
             }
           event.preventDefault();
           formData.append("file", files[0]);
           updateAvatarFile(formData);
         }
   }
   
   function updateAvatarFile(formData){
      $.ajax({
         type: "POST",
         url: "${contextPath}/news/avatar",
         enctype: 'multipart/form-data',
         processData: false,  
         contentType: false,
         data: formData,
         success: function(data){
            updateAvatarFileView(data);
         }
      });
   }

   function updateAvatarFileView(data){
      var fileItemHolder = $(".avatar-file-image");
      var fileItemTemplate = "<div class='image-item'> <div class='image-delete' onclick='javascript:deleteFile(this)'>"
         +"<i class='fa fa-times-circle' aria-hidden='true'></i></div><img class='image-preview' id='image-preview' src='"
         +"${newsFileUrl}/"+ data.storageName +"' style='max-width:100%;' onclick='javascript:onClickAvatarImage()'></div>";

      $(".avatar-file-input").hide();
      fileItemHolder.attr("data-id", data.id);
      fileItemHolder.append(fileItemTemplate);
   }
      
   function deleteFile(self){
      var fileItemHolder = $(".avatar-file-image");
      var fileId = fileItemHolder.attr("data-id");
      
      fileItemHolder.attr("data-id", "0");
      $(self).parent().remove();
      deleteFileById(fileId);
   }

   function deleteFileById(id){
      $.ajax({
         type: "POST",
         url: "${contextPath}/files/"+id+"/delete",
         success: function(data){
            var fileItemHolder = $(".avatar-file-image");
            var fileId = fileItemHolder.attr("data-id");
            if(fileId == "0"){
               $(".avatar-file-input").show();
            }
            
            avatarFileZone.css('border', '0');
         }
      });
   }

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
           formdata.append("fileFlag",3);
      
           singleFileUploader(formdata, 1);
       }
   });


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
               formdata.append("fileFlag",4);
          
               singleFileUploader(formdata, 2);
           }else{
               alert('비디오 파일 크기는 20MB 미만으로 업로드 할수 있습니다.');
           }
       }
   });

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

   function attachFileToEditor(fileUrl, sortOfFile){
      
      var fileTemplate = "";
      
      if(sortOfFile == 1){  //photo == 1
         fileTemplate = "<img style='max-width:100%;' src='" + fileUrl + "'/>";
      }else{
         fileTemplate =   "<video style='max-width:100%;' controls >" +
                     "<source src='" + fileUrl + "'>" +
                     "</video>";
      }
   
      editor_object.getById["smarteditor"].exec("PASTE_HTML", [fileTemplate]);
   }

   function updateFileListView(fileSize, file){
      if(fileSize > 0 ){
         if(!isFileListShown){
            $('.file-content-zone').show();
            isFileListShown = true;
            newsFileTempForm = new FormData();
         }
         newsFileTempForm.append('files' + autoFileCount , file);
         
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
      console.log(newsFileTempForm.get("files"+ fileIndex).name);
      newsFileTempForm.delete("files"+ fileIndex);
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

   function navigateToNewsListPage(){
       document.location.href = "${contextPath}/news";
   }

   function checkFileSizeLimitFiveMB(originalfileSize){
      var fileSize = originalfileSize/1024/1024;
      if(fileSize > 20){
         return false;
      }else{
         return true;
      }
   }
   
   $submit.click(function(){
      window.onunload = window.onbeforeunload = undefined;
      
      editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
      var content =  $("#smarteditor").val();
      var title = $("#title").val();
      var avatarId = $(".avatar-file-image").attr("data-id");

      if(avatarId == 0){
         alert('대표이미지를 등록하세요.');
         return;
      }
      
      if(title == '' || title == null){
         alert('제목을 입력하세요.');
         $("#title").focus();
         return;
      }else{
         if(title.legth > 40){
            alert('제목은 40자 이내로 작성해주세요.');
            $("#title").focus();
            return;
         }
      }
      
      if(content == '' || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>' ||  content == '<p><br></p>'){
         alert('내용을 입력하세요.');
         return;
      }
      
      var newsForm = new FormData();
      newsForm.append("title",title);
      newsForm.append("content",content);
      newsForm.append("avatarId",avatarId);
      
      for(var i =0 ; i < autoFileCount ; i++ ){
         if(newsFileTempForm.has('files'+ i)){
            newsForm.append("files", newsFileTempForm.get('files'+ i));
         }
      }
      
      sendNews(newsForm);
   });
   
   function sendNews(newsForm){
      $.ajax({
         type: "POST",
         url: "${contextPath}/news/",
         processData: false,  
         contentType: false,
         enctype: 'multipart/form-data',
         data: newsForm,
           success: function(data) {
            navigateToNewsListPage();
           },
           beforeSend: function(){
            $formUploadingView.show();
         },
         complete: function(){
            $formUploadingView.hide();
         }
       });
   }
</script>
