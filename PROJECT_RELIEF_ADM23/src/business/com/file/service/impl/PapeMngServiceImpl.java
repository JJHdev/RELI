package business.com.file.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.file.service.PapeMngService;
import business.com.file.service.PapeMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;

/**
 * [서비스클래스] - 서류양식관리 Service 구현 클래스
 *
 * @class   : PapeMngServiceImpl
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("PapeMngService")
@SuppressWarnings({"all"})
public class PapeMngServiceImpl extends BaseService implements PapeMngService {

    @Resource(name = "PapeMngDAO")
    private PapeMngDAO papeMngDAO;

    /**
     * 서류양식관리 페이징목록조회
     */
    @Override
    public PaginatedArrayList listPapeMng(PapeMngVO papeMngVO, int currPage, int pageSize) throws Exception {
    	return papeMngDAO.listPapeMng(papeMngVO, currPage, pageSize);
    }

    /**
     * 서류양식관리 목록조회
     */
    @Override
    public List listPapeMng(PapeMngVO papeMngVO) throws Exception {
    	return papeMngDAO.listPapeMng(papeMngVO);
    }

    /**
     * 서류양식관리 상세조회
     */
	@Override
	public PapeMngVO viewPapeMng(PapeMngVO papeMngVO) throws Exception {
		return papeMngDAO.viewPapeMng(papeMngVO);
	}

    /**
     * 서류양식관리 등록
     */
    private int regiPapeMng(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.regiPapeMng(papeMngVO);
    }

    /**
     * 서류양식관리 수정
     */
    private int updtPapeMng(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.updtPapeMng(papeMngVO);
    }

    /**
     * 서류양식관리 삭제
     */
    private int deltPapeMng(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.deltPapeMng(papeMngVO);
    }

    /**
     * 서류양식관리 등록,수정,삭제
     */
	@Override
	public String savePapeMng(PapeMngVO papeMngVO) throws Exception {

		if (papeMngVO == null)
			throw processException("error.comm.notTarget");

		int ret = 0;
		String mode = papeMngVO.getMode();

		try {
		    if (CommConst.UPDATE.equals(mode)) {
	            // 서류양식관리 수정
	            ret = updtPapeMng(papeMngVO);
	        }
	        else if (CommConst.INSERT.equals(mode)) {
	            // 서류양식관리 등록
	            ret = regiPapeMng(papeMngVO);
	        }
	        else if (CommConst.DELETE.equals(mode)) {
	            // 서류양식관리 삭제
	            ret = deltPapeMng(papeMngVO);
	        }
	        // 저장결과를 반환한다.
	        if (ret > 0)
	            return message.getMessage("prompt.success");
	        else
	            throw processException("error.comm.notProcess");
		}catch(Exception ex) {
		    if(ex.getMessage().contains("JDBC-10007")) {
		        throw processException("error.adm.file.duplPapeMngInfo");
		    }
		    else throw ex;
		}

	}

    /**
     * 2021.10.08 LSH
     * 신청서류 리스트 조회
     */
    public List getListPape(Map paramMap) throws Exception{
        return papeMngDAO.getListPape(paramMap);
    }

    /**
     * 2021.10.08 LSH
     * 신청서류그룹 리스트 조회
     */
    public List getListPapeGroup(Map paramMap) throws Exception{
        return papeMngDAO.getListPapeGroup(paramMap);
    }

    /**
     * 2021.12.06 CSLEE
     * [구제급여] 모든(All) 급여종류 목록 조회
     */
    public List listPapeMngUpPapeAll(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.listPapeMngUpPapeAll(papeMngVO);
    }

    /**
     * UpPape(급여종류:공통서류/의료비...)에 속하는 모든 제출서류 코드 목록 조회
     */
    public List listPapeMngPapeCodeByUpPape(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.listPapeMngPapeCodeByUpPape(papeMngVO);
    }

    /**
     * ===============================
     * * [USR] 신청서류 양식 화면 관련
     * 2021.12.03 CSLEE 추가
     * ===============================
     */

    /**
     * [구제급여] 신청구분 목록 조회
     */
    public List listPapeMngAplySe(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.listPapeMngAplySe(papeMngVO);
    }

    /**
     * [구제급여]급여 종류 목록 조회
     */
    public List listPapeMngUpPape(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.listPapeMngUpPape(papeMngVO);
    }

    /**
     * [구제급여] 제출서류 (공통서류/추가서류 모두 포함)
     */
    public List listPapeMngPape(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.listPapeMngPape(papeMngVO);
    }

    /**
     * 다운로드 할 양식 정보 목록 조회
     */
    public List listPapeMngDownFile(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.listPapeMngDownFile(papeMngVO);
    }

    /**
     * 2022.01.11 CSLEE 추가
     * 다운로드 카운트 증가 저장
     */
    public int updtPapeMngDownCount(PapeMngVO papeMngVO) throws Exception {
        return papeMngDAO.updtPapeMngDownCount(papeMngVO);
    }

}