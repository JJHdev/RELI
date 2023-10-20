/**
******************************************************************************************
*** 파일명    : comm_ckeditor.js
*** 설명      : CKEditor 관련 공통 설정 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-07-21              LSH
*** 1.1         2022-10-26              ntarget		     CkEditorImageUpload에서 처리 (URL)
******************************************************************************************
**/
const CKEDITOR_CONFIG = {
	// 파일 업로드를 처리 할 경로 설정(CK필터).
	//filebrowserImageUploadUrl: ROOT_PATH+'/images/ckimage' 
	
	filebrowserUploadUrl: ROOT_PATH+'/com/cmm/ckupload.do'
};
