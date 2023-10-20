package business.sys.user.service;

import java.util.List;

import business.com.file.service.AplyFileVO;
import business.com.file.service.PapeMngVO;
import common.base.BaseModel;
import common.util.CommUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 정보연동신청 모델 클래스
 *
 * @class   : InfoIntrlckVO
 * @author  : 한금주
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class InfoIntrlckVO extends BaseModel {
	
	//	신청 번호
	private String aplyNo;
	//	연동구분코드
	private String intrlckSeCd;
	//	신청자번호
	private String aplcntNo;
	//	대상자명
	private String trprNm;
	private String trprNmR;
	private String userNmR;
	//	대상자 식별id
	private String trprIdntfcId;
	//	대상자생년월일
	private String trprBrdt;
	//	전화번호
	private String telNo;
	//	휴대전화번호
	private String mbtelNo;
	//	신청일자
	private String aplyYmd;
	//	접수일자
	private String rcptYmd;
	//	진행상태코드
	private String prgreStusCd;
	//	등록자번호
	private String rgtrNo;
	//	등록일자
	private String regYmd;
	//	수정자번호
	private String mdfrNo;
	//	수정일자
	private String mdfcnYmd;    
	// 검색조건
    private String srchText;
    
    //식별아이디
    private String idntfcId;
    //피해자명
    private String sufrerNm;
    //피해자생년월일
    private String sufrerBrdt;
    //피해자주민등록번호
    private String sufrerRrno;
    //피해자성별
    private String sufrerSxdst;
    //피해자 우편번호
    private String sufrerZip;
    //피해자 주소
    private String sufrerAddr;
    //피해자 상세주소
    private String sufrerDaddr;
    //피해자 이메일주소
    private String sufrerEmlAddr;
    //피해자 전화번호
    private String sufrerTelno;
    //피해자 휴대전화번호
    private String sufrerMbtelNo;
    //피해자 연령
    private String sufrerAge;
    
    // 사용자번호
    private String userNo;
    
    //신청자 이름
    private String userNm;
    
	private String trprbryy;
	private String trprbrmm;
	private String trprbrdd;
        
    
    /* ========== 신청파일 ========== */
    // 저장대상 신청파일목록
    private List<AplyFileVO> saveFiles;
    // 삭제대상 신청파일목록
    private List<AplyFileVO> removeFiles;
    // 신청양식 서류목록
    private List<PapeMngVO> papeList;
    // 신청양식 업무구분
    private String papeDtySeCd;
    private String aplySeCd;
    
    // 세션사용자번호
    private String gsUserNo;
    
    /* ========== 검색조건 ========== */
	// 신청일자
    private String srchAplyStdt;
    private String srchAplyEndt;
    
	// 연동상태(다중선택)
    private List<String> prgreStusList;
    // 연동 대상
    private List<String> intrlckSeList;
    
	// 신청자명
    private String srchAplcntNm;
    // 식별아이디
    private String srchIdntfcId;
    
   
 	public void rebuildProperties() {

 		// 세션사용자번호 정의
 		if (CommUtils.isNotEmpty(getUserInfo().getUserNo())) {
 			setGsUserNo(getUserInfo().getUserNo());
 			
 		// 신청구분
 		String _intrlckSeCd = getIntrlckSeCd();
 		}
 	}

 }
