package business.com.bbs.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 게시판을 관리하는 Service Interface
 * - [게시판] 공지사항
 * - [게시판] 질의응답
 * - [자료실] 법ㆍ규정관리
 * - [자료실] 연구보고서관리
 * - [자료실] 위원회관리
 * 
 * @class   : BbsService
 * @author  : 김주호
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *  2021.10.29  LSH        검색/상세조회를 하나의 메서드로 통일함
 */
@SuppressWarnings("all")
public interface BbsService {
	

	
	/* 게시판 공통 페이징 목록 조회 */
	public PaginatedArrayList listBbs(BbsVO bbsVO, int page, int basePageSize) throws Exception;
	
	/* 게시판 공통 목록 조회  */
	public List listBbs(BbsVO bbsVO)throws Exception;
	
	/* 나의 질의응답 목록 조회*/
	public List mylistBbs(BbsVO bbsVO)throws Exception;
	/* 게시판 공통 페이징 목록 조회 */
	public PaginatedArrayList mylistBbs(BbsVO bbsVO, int page, int basePageSize) throws Exception;

	/* 게시판 공통 상세 조회  */
	public BbsVO viewBbs(BbsVO bbsVO)throws Exception;

	/* 게시판 공통 비밀번호 확인  */
	public boolean checkBbsPassword(BbsVO bbsVO)throws Exception;
	
    /* 게시판 수정 화면 표출 (공통)*/
	public BbsVO modifyBbs(BbsVO bbsVO) throws Exception;
	
	/* 게시판 수정 (공통)*/
	public Integer updateBbs(BbsVO bbsVO) throws Exception;
	
	/* 질의응답 답변 조회*/
	public BbsVO viewBbsN(BbsVO bbsA)throws Exception;
	
    /* 질의응답 문의 등록*/
	public Integer qnaSave(BbsVO bbsVO)throws Exception;
	
	/*조회수 증가 */
	public Integer inqCntUp(int nttNo)throws Exception;
  
    /* 게시판 저장처리 (공통)*/
	public int saveBbs(BbsVO bbsVO) throws Exception;

    /* 게시판 삭제처리 (공통)*/
	public int deleteBbs(BbsVO bbsVO) throws Exception;
	
    /* 게시글 첨부파일 목록조회 */
    public List listBbsFile(BbsFileVO bbsFileVO) throws Exception;

    /* 게시글 첨부파일 상세조회 */
    public BbsFileVO viewBbsFile(BbsFileVO bbsFileVO) throws Exception;

    /* 게시글 첨부파일 단일삭제 */
    public int deleteBbsFile(BbsFileVO bbsFileVO) throws Exception;
    
    /* Qna 답변 등록 */
	public Integer saveAnswer(BbsVO bbsVO) throws Exception;

	/* Qna 답변 삭제*/
	public Integer deleteQna(BbsVO bbsVO)throws Exception;

	/* 사용자 QNA 비밀번호 권한 체크 */
	public BbsVO checkQnaWriter(BbsVO bbsVO) throws Exception;

}