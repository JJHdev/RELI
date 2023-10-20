package business.com;

import common.file.FileDirectory;

/**
 * 다운로드 양식 파일 정보 클래스
 * 
 * @class   : CommFormFile
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *  2021.11.30  LSH        세부의료비 산정결과 양식파일 추가
 */
public enum CommFormFile {
	
	MCPDTLS ("세부의료비_산정결과_양식.xlsx", "mcpdtls.xlsx"),
	PERSINFO01 ("[개인정보보호법 시행규칙 별지 제8호] 개인정보 열람정정삭제등 요구서.hwp", "PERSINFO01.hwp"),
	PERSINFO02 ("[개인정보보호법 시행규칙 별지 제11호] 위임장.hwp", "PERSINFO02.hwp"),
	PERSINFO03 ("개인정보 열람·정정·삭제·처리정지 요구서.hwp", "PERSINFO03.hwp"),
	PERSINFO04 ("개인정보 처리방침에 관한 고시 별지 제11호 위임장.hwp", "PERSINFO04.hwp"),
	PERSINFO05 ("[개인정보처리방법에 관한 고시 별지 제8호] 개인정보열람 요구서.hwp", "PERSINFO05.hwp"),
	PERSINFO06 ("[개인정보처리방법에 관한 고시 별지 제11호] 위임장.hwp", "PERSINFO06.hwp")
	;

	private String fileName;
	private String saveName;
	
	private CommFormFile(String fileName, String saveName) {
		this.fileName = fileName;
		this.saveName = saveName;
	}

	public String getSaveName() {
		return saveName;
	}

	public String getFileName() {
		return fileName;
	}
	public String getFullPath() {
		return FileDirectory.FORMFILE.getRealPath();
	}
	
	public String getFullName() {
		return FileDirectory.FORMFILE.getRealName(saveName);
	}
	
}
