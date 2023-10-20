package business.com.bbs.service;

import java.util.List;

import common.base.BaseModel;
import common.file.FileInfo;
import common.util.CommUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 게시판 모델 클래스
 * - [게시판] 공지사항
 * - [게시판] 질의응답
 * - [자료실] 법ㆍ규정관리
 * - [자료실] 연구보고서관리
 * - [자료실] 위원회관리
 *
 * @class   : BbsVO
 * @author  : 김주호
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class BbsVO extends BaseModel {
    
	// 검색조건
    private String srchText;
    
	// 세션사용자번호
	private String gsUserNo;


    
    private int nttNo;          // 게시물 번호
    private int upNttNo;        // 상위게시물 번호
    private String bbsSeCd;     // 게시판구분코드
    private String nttClCd;     // 게시물분류코드
    private String nttSj;       // 게시물제목
    private String nttCn;       // 게시물내용
    private String pstgBgngYmd; // 게시시작일자
    private String pstgEndYmg;  // 게시종료일자
    private String nttLinkUrl;  // 게시물연계URL
    private String emlAddr;     // 이메일주소
    private int inqCnt;         // 조회수
    private String nttPswd;     // 게시물 비밀번호
    private char rlsYN;         // 공개여부
    private char delYN;         // 삭제여부
    private String rgtrNo;      // 등록자번호
    private String rgtrNm;      // 등록자명(작성자명으로 사용)
    private String regYmd;      // 등록일자
    private String mdfrnNo;     // 수정자번호 
    private String mdfcnYmd;    // 수정일자
    private String status;      // 게시글 상태
    
    // 22.11.24 JDY 팝업관련 추가  
    private String popupYn;     // 팝업여부
    private int popupHght;      // 높이
    private int popupSqr;      	// 너비
    
    private String userNo;
    
   // 12.03 김주호  첨부파일 유무 추가 
    private String fileYN;
    
    
    
    private String srchType;
    private String srchUserId;
    private String srchUserNm;         
    private String srchKeyword;        
    private String userId;
    private List<FileInfo> files;
    
    
}
