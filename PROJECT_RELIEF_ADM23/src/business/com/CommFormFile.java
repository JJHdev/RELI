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
 *  2023.01.02  LSH        요양생활수당 지급일자등록 양식파일 추가
 */
public enum CommFormFile {
	
	MCPDTLS   ("세부의료비_산정결과_양식.xlsx", "mcpdtls.xlsx"),
	ABSTRCT   ("초본주소_등록_양식.xlsx", "abstrct.xlsx"),
	// 2023.01.02 요양생활수당 지급일자등록 양식파일 추가
	RCPERLVLH ("요양생활수당_지급일자등록_양식.xlsx", "rcperlvlh.xlsx")
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
