package business.com.bbs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import business.com.CommConst;
import business.com.bbs.service.BbsFileVO;
import business.com.bbs.service.BbsService;
import business.com.bbs.service.BbsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [서비스클래스] - 게시판을 관리하는 Service 구현 클래스
 * - [게시판] 공지사항
 * - [게시판] 질의응답
 * - [자료실] 법ㆍ규정관리
 * - [자료실] 연구보고서관리
 * - [자료실] 위원회관리
 * 
 * @class   : BbsServiceImpl
 * @author  : 김주호
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  ----------  --------    ---------------------------
 *  2022.11.24   JDY         메인화면 공지사항 팝업창
 */

@Service("BbsService")
@SuppressWarnings({"all"})
public class BbsServiceImpl extends BaseService implements BbsService {

    @Resource(name = "BbsDAO")
    private BbsDAO bbsDAO;

    @Resource(name = "BbsFileDAO")
    private BbsFileDAO bbsFileDAO;

    @Resource(name="fileManager")
    protected FileManager fileManager;


	/**
	 * 게시판 공통 페이징 목록 조회
	 */
	@Override
	public PaginatedArrayList listBbs(BbsVO bbsVO,int currPage, int pageSize) throws Exception {
		return bbsDAO.listBbs(bbsVO, currPage, pageSize);
	}

	/**
	 * 게시판 공통 목록 조회 
	 */
	@Override
	public List listBbs(BbsVO bbsVO) throws Exception {
		return bbsDAO.listBbs(bbsVO);
	}
	
	/**
	 * 나의질의응답 공통 목록 조회 
	 * @throws Exception 
	 */
	@Override
	public List mylistBbs(BbsVO bbsVO) throws Exception {
		return bbsDAO.mylistBbs(bbsVO);
	}
	/**
	 * 나의질의응답 공통 페이징 
	 * @throws Exception 
	 */
	@Override
	public PaginatedArrayList mylistBbs(BbsVO bbsVO,int currPage, int pageSize) throws Exception {
		return bbsDAO.mylistBbs(bbsVO, currPage, pageSize);
	}
	/**
	 * 게시판 공통 상세 조회 
	 */
	@Override
	public BbsVO viewBbs(BbsVO bbsVO) throws Exception {
		return bbsDAO.viewBbs(bbsVO);
	}

	/**
	 * 게시판 공통 비밀번호 확인 (true / false)
	 * @param  (필수) bbsVO.nttNo   : 게시글번호
	 * @param  (필수) bbsVO.nttPswd : 입력비밀번호
	 */
	@Override
	public boolean checkBbsPassword(BbsVO bbsVO) throws Exception {
		return bbsDAO.checkBbsPassword(bbsVO);
	}
	
	/**
	 * 질의응답 답변 조회
	 */
	@Override
	public BbsVO viewBbsN(BbsVO bbsA) throws Exception {
		return bbsDAO.viewBbsN(bbsA);
	}
	/**
	 * 질의응답 문의 등록
	 */

	@Override
	public Integer qnaSave(BbsVO bbsVO) throws Exception {
		return bbsDAO.qnaSave(bbsVO);
	}

    /* 게시판 수정화면 표출 공통*/
	@Override
	public BbsVO modifyBbs(BbsVO bbsVO) throws Exception {
		return bbsDAO.modifyBbs(bbsVO);
	}

	/* 게시판 수정 공통*/
	@Override
	public Integer updateBbs(BbsVO bbsVO)throws Exception {
		return bbsDAO.updateBbs(bbsVO);
	}

	/* 조회수 증가*/
	@Override
	public Integer inqCntUp(int nttNo) throws Exception {
		
		return bbsDAO.inqCntUp(nttNo);
	}

	/**
     * 게시글 등록/수정
     */
	@Override
	public int saveBbs(BbsVO bbsVO) throws Exception {
		
		String mode = bbsVO.getMode();
		
		int ret = 0;
        // 이전 파일 목록
        List<BbsFileVO> files = null;
		
		if (CommConst.INSERT.equals(mode)) {
			// 게시글 등록 처리
			ret = bbsDAO.qnaSave(bbsVO);
		}
		else if (CommConst.UPDATE.equals(mode)) {
			// 게시글 수정 처리
			ret = bbsDAO.updateBbs(bbsVO);
			
			// 이전 파일 목록 조회
	        files = bbsFileDAO.listBbsFile(BbsFileVO.builder().dcmtNo(bbsVO.getNttNo()+"").build());
		}
		// 처리모드가 없는 경우
		else {
			throw processException("error.comm.notTarget");
		}
        
        // 게시판 첨부파일 경로
        FileDirectory d = FileDirectory.BOARD;
        
        // 임시경로에 저장된 첨부파일 목록
        List<FileInfo> tempFiles = bbsVO.getFiles();
        
        // 첨부파일이 있는 경우
        if (!tempFiles.isEmpty()) {
        	for (FileInfo f : tempFiles) {
    			// 파일이 첨부된 경우
    			if ("Y".equals(f.getFileYn())) {
    				// 파일 경로 정의 (/board/게시판코드/등록일자/)
    				String filePath = d.getPath()+"/"+bbsVO.getBbsSeCd()+"/"+CommUtils.getCurDateString();
	        		// 첨부파일 실제경로로 이동처리
	        		fileManager.moveFile(f.getSaveName(), d, filePath);
	        		
	        		// Lombok의 Builder를 사용한 객체 생성
	        		BbsFileVO vo = BbsFileVO.builder().dcmtNo(String.valueOf(bbsVO.getNttNo()))
	        							.filePath(filePath)
	        							.fileName(f.getFileName())
	        							.saveName(f.getSaveName())
	        							.fileSize(f.getFileSize())
	        							.fileIdx (f.getFileIdx())
	        							.gsUserNo(bbsVO.getGsUserNo())
	        							.build();
	                
	        		bbsFileDAO.regiBbsFile(vo);
    			}
    			else {
    				if (files != null) {
    					// 삭제대상 파일이 있는지 체크
	    				for (BbsFileVO fileVO : files) {
	    					if (CommUtils.isEqual(fileVO.getSn(), f.getFileNo())) {
	    						files.remove(fileVO);
	    						break;
	    					}
	    				}    				
    				}
    			}
        	}
        }
        if (files != null && files.size() > 0) {
			for (BbsFileVO fileVO : files) {
				// 파일 삭제
				bbsFileDAO.deltBbsFile(fileVO);
			}    				
        }
		return ret;
	}

    /**
     * 게시글 삭제 처리
     */
	@Override
	public int deleteBbs(BbsVO bbsVO) throws Exception {
	    // 게시글 상세조회
		BbsVO vo = bbsDAO.viewBbs(bbsVO);
		// 삭제할 자료가 없을 경우
		if (vo == null)
			throw new EgovBizException(message.getMessage("exception.NoResult"));
		
		// 게시글 데이터 삭제
		int ret = bbsDAO.deleteBbs(vo);
		
		if (ret > 0) {
			// 게시글 첨부파일 조회조건 객체 생성
			BbsFileVO fileVO = BbsFileVO.builder()
									.dcmtNo  (String.valueOf(vo.getNttNo()))
									.gsUserNo(bbsVO.getGsUserNo())
									.build();

			// 게시글 첨부파일 데이터 일괄 삭제
			bbsFileDAO.deltBbsFileAll(fileVO);
		}
		return ret;
	}

	/**
	 * 게시글 첨부파일 목록 조회
	 */
	@Override
	public List listBbsFile(BbsFileVO bbsFileVO) throws Exception {
		return bbsFileDAO.listBbsFile(bbsFileVO);
	}

	/**
	 * 게시글 첨부파일 상세정보 조회
	 */
	@Override
	public BbsFileVO viewBbsFile(BbsFileVO bbsFileVO) throws Exception {
		return bbsFileDAO.viewBbsFile(bbsFileVO);
	}


	/**
	 * 게시글 첨부파일 단일삭제 처리
	 */
	@Override
	public int deleteBbsFile(BbsFileVO bbsFileVO) throws Exception {
		BbsFileVO vo = bbsFileDAO.viewBbsFile(bbsFileVO);
		if (vo == null)
			throw new EgovBizException(message.getMessage("exception.NoResult"));
		// 첨부파일 데이터 삭제
		return bbsFileDAO.deltBbsFile(vo);
	}
	
	/**
	 *[관리자] Qna 답변 등록 
	 */
	@Override
	public Integer saveAnswer(BbsVO bbsVO) throws Exception {
		
		if(bbsVO.getStatus()=="" || bbsVO.getStatus().equals("") || bbsVO.getStatus().equals("null")) {
			
			
			return bbsDAO.saveAnswer(bbsVO);
		}else {
			return bbsDAO.updateAnswer(bbsVO);
		}
		
		
	}
	/*
	 *[관리자] Qna 답변 삭제  
	 */
	@Override
	public Integer deleteQna(BbsVO bbsVO) throws Exception {

		return bbsDAO.deleteQna(bbsVO);
	}
	
	 /* [사용자] 비빌번호 권한 체크 
	 * 
	 */
	@Override
	public BbsVO checkQnaWriter(BbsVO bbsVO) throws Exception {
		return bbsDAO.checkQnaWriter(bbsVO);
	}
	
	
   /* 
    * 메인화면 공지사항 팝업창
    */
	@Override 
	public ArrayList<BbsVO> popupNotice(BbsVO bbsVO) throws Exception { 
		return bbsDAO.popupNotice(bbsVO); 
	}
	
	/* 
	 * 메인화면 공지사항 팝업창
	 */
	@Override 
	public BbsVO viewPopupNotice(BbsVO bbsVO) throws Exception { 
		return bbsDAO.viewPopupNotice(bbsVO); 
	}
	
}
