 package business.com.bbs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.bbs.service.BbsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 게시판을 관리하는 DAO 클래스
 * - [게시판] 공지사항
 * - [게시판] 질의응답
 * - [자료실] 법ㆍ규정관리
 * - [자료실] 연구보고서관리
 * - [자료실] 위원회관리
 * 
 * 사용 가능한  DAO Statement Method
 * 1. list          : 리스트 조회시 사용함.
 * 2. pageListOra   : 페이징처리용 리스트조회시 사용함. for Oracle, Tibero
 * 3. view          : 단건조회, 상세조회시 사용함.
 * 4. save          : INSERT, UPDATE, DELETE 모두 사용가능. (Return Type : Integer)
 * 5. insert        : INSERT (Return String : Key 채번 사용함.)
 * 6. update        : UPDATE (Return Type : Integer)
 * 7. delete        : DELETE (Return Type : Integer)
 * 
 *
 * @class   : BbsDAO
 * @author  : 김주호
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *  2021.10.29  LSH        검색/상세조회를 하나의 메서드로 통일함
 */
@Repository("BbsDAO")
@SuppressWarnings({"all"})
public class BbsDAO extends BaseDAO {
	


	/**
	 * 게시판 공통 페이징 목록 조회
	 * */
	public PaginatedArrayList listBbs(BbsVO bbsVO, int currPage, int pageSize) {
		return pageList("Bbs.listBbs", bbsVO, currPage, pageSize);
	}
	
	/**
	 * 게시판 공통 목록 조회
	 * */
	public List listBbs(BbsVO bbsVO) {
		return list("Bbs.listBbs", bbsVO);
	}
	/**
	 * 나의 질의응답 목록 조회
	 * */
	public List mylistBbs(BbsVO bbsVO) {
		return list("Bbs.mylistBbs", bbsVO);
	}
	/**
	 * 나의 질의응답 페이징 조회
	 * */
	public PaginatedArrayList mylistBbs(BbsVO bbsVO, int currPage, int pageSize) {
		return pageList("Bbs.mylistBbs", bbsVO, currPage, pageSize);
	}
	
	/**
	 * 게시판 공통 상세 조회
	 * */
	public BbsVO viewBbs(BbsVO bbsVO) {
		return (BbsVO)view("Bbs.viewBbs", bbsVO);
	}
	
	/**
	 * 게시판 공통 비밀번호 확인 (true / false)
	 * @param  (필수) bbsVO.nttNo   : 게시글번호
	 * @param  (필수) bbsVO.nttPswd : 입력비밀번호
	 * */
	public boolean checkBbsPassword(BbsVO bbsVO) {
		return (Boolean)view("Bbs.checkBbsPassword", bbsVO);
	}
	/**
	 * 질의응답 답변 조회
	 * */
	public BbsVO viewBbsN(BbsVO bbsA) {
		
		return(BbsVO)view("Bbs.viewBbsN", bbsA);
	}
	/*
	 * QNA 등록 (사용자)
	 */
	public Integer qnaSave(BbsVO bbsVO) {
		
		return insert("Bbs.qnaSave",bbsVO);
	}
    /*
     * 게시판 수정 공통
     */
	public BbsVO modifyBbs(BbsVO bbsVO) {
		return (BbsVO) view("Bbs.modifyBbs",bbsVO);
	}

	public Integer updateBbs(BbsVO bbsVO) {
		return update("Bbs.updateBbs",bbsVO);
	}

	public Integer inqCntUp(int nttNo) {
		return update("Bbs.inqCntUp",nttNo);
	}

	public Integer deleteBbs(BbsVO bbsVO) {
		return update("Bbs.deleteBbs",bbsVO);
	}
	
    /*
     * [관리자] Qna 답변 등록 
     */
	public Integer saveAnswer(BbsVO bbsVO) {
		return insert("Bbs.saveAnswer",bbsVO);
	}
	
	/*
     * [관리자] Qna 답변 수정 
     */
	public Integer updateAnswer(BbsVO bbsVO) {
		return update("Bbs.updateAnswer",bbsVO);
	}
	  /*
	   *[관리자] Qna 삭제  
	   */
	public Integer deleteQna(BbsVO bbsVO) {
		return update("Bbs.deleteQna",bbsVO);
	}

	public BbsVO checkQnaWriter(BbsVO bbsVO) {
		// TODO Auto-generated method stub
		return (BbsVO) view("Bbs.checkQnaWriter",bbsVO);
	}


	
}