package business.com.support.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.biz.service.MngHstService;
import business.com.biz.service.MngHstVO;
import business.com.file.service.AplyFileService;
import business.com.support.service.DtlService;
import business.com.support.service.LwstService;
import business.com.support.service.LwstVO;
import business.com.support.service.SprtAplyService;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileInfo;

/**
 * [서비스클래스] - 취약계층소송지원을 관리하는 Service 구현 클래스
 *
 * @class : LwstServiceImpl
 * @author : 한금주
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */

@Service("LwstService")
@SuppressWarnings({ "all" })
public class LwstServiceImpl extends BaseService implements LwstService {

	@Resource(name = "LwstDAO")
	private LwstDAO lwstDAO;

	@Resource(name = "DtlService")
	private DtlService dtlService;

	@Resource(name = "SprtAplyService")
	private SprtAplyService sprtAplyService;

    @Resource(name = "AplyFileService")
    private AplyFileService aplyFileService;

    // 2023.01.27 LSH 관리이력서비스 (관리자 제출서류 추가등록에 필요함)
    @Resource(name = "MngHstService")
    private MngHstService mngHstService;

	@Override
	public LwstVO openLwst(String aplcntNo) throws Exception {
		return lwstDAO.openLwst(aplcntNo);
	}

	@Override
	public Integer saveUserLwst(LwstVO lwstVO) throws Exception {
		if (lwstDAO.saveUserLwst(lwstVO) > 0) {
			aplyFileService.saveLwstFile(lwstVO);
			return 1;
		}
		return 0;
	}

	@Override
	public PaginatedArrayList listLwst(LwstVO lwstVO, int currPage, int pageSize) throws Exception {
		return lwstDAO.listLwst(lwstVO, currPage, pageSize);
	}

	@Override
	public List listLwst(LwstVO lwstVO) throws Exception {
		return lwstDAO.listLwst(lwstVO);
	}

	@Override
	public LwstVO viewListLwst(String aplyNo) throws Exception {
		return lwstDAO.viewListLwst(aplyNo);
	}

	@Override
	public Integer updateLwstIncdnt(LwstVO lwstVO) throws Exception {
		if (lwstVO == null)
			throw processException("error.comm.notTarget");
		int ret = lwstDAO.updateLwstIncdnt(lwstVO);
		return ret;
	}

	@Override
	public Integer cancelLwstIncdnt(LwstVO lwstVO) throws Exception {
		if (lwstVO == null)
			throw processException("error.comm.notTarget");
		int ret = 0;
		return lwstDAO.cancelLwstIncdnt(lwstVO);
	}

	@Override
	public String regiLwstIncdnt(LwstVO lwstVO) throws Exception {
		if (lwstVO == null)
			throw processException("error.comm.notTarget");

		int ret = 0;
		String mode = lwstVO.getMode();
		// 사건목록
		List<LwstVO> list = lwstVO.getLwstList();

		// 수정시 (?? 수정처리가 있는지 확인 필요)
		if(CommConst.UPDATE.equals(mode)) {
			ret = udtLwstIncdnt(lwstVO);
			if (ret > 0) {
				// 향후기일목록
				List<LwstVO> dtlsList = lwstVO.getDtlsList();
				if (dtlsList != null &&
						dtlsList.size() > 0) {
					dtlService.saveDtlsList(dtlsList, lwstVO);
//					aplyFileService.saveLwstFile(lwstVO);
					ret = 1;
				}
			}

		}
		// 신규등록시
		else if (CommConst.INSERT.equals(mode)) {
			// 1) 먼저 사건정보를 저장한다. (KEY를 받기 위해서)
			ret = saveLwstIncdnt(lwstVO);
			// 2) 저장이 성공이면
			if (ret > 0) {
				// 3) 향후기일 목록을 저장한다.
				List<LwstVO> dtlsList = lwstVO.getDtlsList();
				dtlService.saveDtlsList(dtlsList, lwstVO);

			}
		}
		// 삭제시
		else if (CommConst.DELETE.equals(mode)) {

			// 1) 향후기일 목록을 사건관리번호 기준으로 삭제한다.
			dtlService.deltDtlsListByMngNo(lwstVO);
			// 2) 사건정보를 삭제한다.
			ret = deleteLwstIncdnt(lwstVO);
		}
		if (ret > 0)
			return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

	private int saveLwstIncdnt(LwstVO lwstVO) throws Exception {
		if(lwstDAO.saveLwstIncdnt(lwstVO) > 0) {
			aplyFileService.saveLwstFile(lwstVO);
			return 1;
		}
		return 0;
	}

	private int udtLwstIncdnt(LwstVO lwstVO) {
		int ret = lwstDAO.udtLwstIncdnt(lwstVO);
		return ret;
	}

	private int deleteLwstIncdnt(LwstVO lwstVO) {
		int ret = 0;
		ret = lwstDAO.deleteLwstIncdnt(lwstVO);
		return ret;
	}

	@Override
	public List listLwstIncdnt(LwstVO lwstVO, int currPage, int pageSize) throws Exception {
		return lwstDAO.listLwstIncdnt(lwstVO, currPage, pageSize);
	}

	@Override
	public List listLwstIncdnt(LwstVO lwstVO) throws Exception {
		return lwstDAO.listLwstIncdnt(lwstVO);
	}

	@Override
	public LwstVO viewListLwstIncdnt(int incdntMngNo) throws Exception {
		return lwstDAO.viewListLwstIncdnt(incdntMngNo);
	}

	@Override
	public PaginatedArrayList listLwstRtrcn(LwstVO lwstVO, int currPage, int pageSize) throws Exception {
		return lwstDAO.listLwstRtrcn(lwstVO, currPage, pageSize);
	}

	@Override
	public List listLwstRtrcn(LwstVO lwstVO) throws Exception {
		return lwstDAO.listLwstRtrcn(lwstVO);
	}

	@Override
	public LwstVO viewlistLwstRtrcn(String aplyNo) throws Exception {
		return lwstDAO.viewlistLwstRtrcn(aplyNo);
	}

	@Override
	public PaginatedArrayList listLwstPrgre(LwstVO lwstVO, int currPage, int pageSize) throws Exception {
		return lwstDAO.listLwstPrgre(lwstVO, currPage, pageSize);
	}

	@Override
	public List listLwstPrgre(LwstVO lwstVO) throws Exception {
		return lwstDAO.listLwstPrgre(lwstVO);
	}

	@Override
	public LwstVO viewlistLwstPrgre(String aplyNo) throws Exception {
		return lwstDAO.viewlistLwstPrgre(aplyNo);
	}

	@Override
	public ArrayList searchLwstNo(HashMap paramMap) throws Exception {
		return (ArrayList)lwstDAO.searchLwstNo(paramMap);
	}

	@Override
	public ArrayList listAllLwst(HashMap paramMap) throws Exception {
		return (ArrayList)lwstDAO.listAllLwst(paramMap);
	}

	@Override
	public String regiLwstAply(LwstVO lwstVO) throws Exception {
		if (lwstVO == null)
			throw processException("error.comm.notTarget");

		int ret = 0;
		String mode = lwstVO.getMode();
		// 소송별신청 목록
		List<LwstVO> list = lwstVO.getLwstAplyList();

		// 수정시 (수정처리가 있는지 확인 필요)
		if(CommConst.UPDATE.equals(mode)) {
			ret = udtAplyLwstList(lwstVO);
			if (ret > 0) {
				// 소송지원정보 목록
				List<LwstVO> sprtList = lwstVO.getLwstSprtList();
				if (sprtList != null &&
						sprtList.size() > 0) {
					sprtAplyService.saveSprtList(sprtList, lwstVO);
				}
			}

		}
		// 신규등록시
		else if (CommConst.INSERT.equals(mode)) {
			// 1) 먼저 사건정보를 저장한다. (KEY를 받기 위해서)
			ret = saveAplyLwstList(lwstVO);
			// 2) 저장이 성공이면
			if (ret > 0) {
				// 소송지원정보 목록
				List<LwstVO> sprtList = lwstVO.getLwstSprtList();
				sprtAplyService.saveSprtList(sprtList, lwstVO);
			}
		}
		// 삭제시
		else if (CommConst.DELETE.equals(mode)) {

			// 1) 소송지원정보 목록을 사건관리번호 기준으로 삭제한다.
			sprtAplyService.delSprtListAplyNo(lwstVO);
			// 2) 사건정보를 삭제한다.
			ret = deleteAplyList(lwstVO);
		}
		if (ret > 0)
			return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

	private int deleteAplyList(LwstVO lwstVO) {
		int ret = lwstDAO.deleteAplyList(lwstVO);
		return ret;
	}

	private int saveAplyLwstList(LwstVO lwstVO) {
		int ret = lwstDAO.saveAplyLwstList(lwstVO);
		return ret;
	}

	private int udtAplyLwstList(LwstVO lwstVO) {
		int ret = lwstDAO.udtAplyLwstList(lwstVO);
		return ret;
	}

	/**
     * 2021.12.16 CSLEE
     *  신청정보와 소송사건 정보 목록 조회
     */
    public List listLwstAplyIncdnt(LwstVO lwstVO) throws Exception {
        return lwstDAO.listLwstAplyIncdnt(lwstVO);
    }

	/**
     * 2021.12.16 CSLEE
     *  신청정보와 소송사건 정보 조회 (향후기일 정보는 최신 정보 1건 기준
     *  [USR>마이페이지>취약계층 소송지원 현황] 화면을 위해 추가
     */
	@Override
    public LwstVO viewLwstAplyIncdnt(LwstVO lwstVO) throws Exception {
        return (LwstVO)lwstDAO.viewLwstAplyIncdnt(lwstVO);
    }

	/**
     * 2021.12.17 CSLEE
     *  특정 신청정보의  향후일자 목록 정보를 향후기일 최신 일자 순으로 조회
     */
	@Override
    public List listLwstAplyIncdntDetail(LwstVO lwstVO) throws Exception {
        return lwstDAO.listLwstAplyIncdntDetail(lwstVO);
    }

    /**
     * 2023.01.27 LSH
     * 관리자 제출서류 추가등록 업로드 및 저장처리
     */
	@Override
	public String saveLwstAddfile(LwstVO lwstVO, FileInfo fileInfo) throws Exception {
		
		int ret = 0;
		
		if (fileInfo == null)
			throw processException("error.comm.notTarget");
		
    	// 신청번호 기준 신청정보 조회
		LwstVO vo = viewListLwst(lwstVO.getAplyNo());
    	if (vo == null)
			throw processException("error.comm.notTarget");
		
    	fileInfo.setGsUserNo(lwstVO.getGsUserNo());
    	
	    // 업로드한 파일정보를 저장한다.
    	String sn = aplyFileService.saveLwstAddfile(lwstVO, fileInfo);
    	
		if (sn != null) {
			// 관리이력 저장처리
			MngHstVO mngHstVO = MngHstVO.builder ()
					.dtySeCd  (CommConst.DTY_LWST )
					.hstSeCd  (CommConst.HST_PAPER)
					.relKeyNo (sn                 )
					.aplyNo   (lwstVO.getAplyNo  ())
					.hstCn    (lwstVO.getHstCn   ())
					.gsUserNo (lwstVO.getGsUserNo())
					.build    ();
			mngHstVO.setMode(CommConst.INSERT);
			mngHstService.saveMngHst(mngHstVO);
			ret = 1;
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}