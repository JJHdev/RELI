package business.bio.relief.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.relief.service.BioMngHstService;
import business.bio.relief.service.BioMngHstVO;
import business.com.CommConst;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;

/**
 * [서비스클래스] - 살생물제품 관리이력정보 Service 구현 클래스
 * 
 * @class   : BioMngHstServiceImpl
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("BioMngHstService")
@SuppressWarnings({"all"})
public class BioMngHstServiceImpl extends BaseService implements BioMngHstService {

    @Resource(name = "BioMngHstDAO")
    private BioMngHstDAO mngHstDAO;
	
    /**
     * 관리이력정보 페이징목록조회
     */
    @Override
    public PaginatedArrayList listBioMngHst(BioMngHstVO bioMngHstVO, int currPage, int pageSize) throws Exception {
    	return mngHstDAO.listBioMngHst(bioMngHstVO, currPage, pageSize);
    }

    /**
     * 관리이력정보 목록조회
     */
    @Override
    public List listBioMngHst(BioMngHstVO bioMngHstVO) throws Exception {
    	return mngHstDAO.listBioMngHst(bioMngHstVO);
    }

    /**
     * 관리이력정보 상세조회
     */
	@Override
	public BioMngHstVO viewBioMngHst(String sn) throws Exception {
		return mngHstDAO.viewBioMngHst(sn);
	}

    /**
     * 관리이력정보 등록
     */
    private int regiBioMngHst(BioMngHstVO bioMngHstVO) throws Exception {
        return mngHstDAO.regiBioMngHst(bioMngHstVO);
    }

    /**
     * 관리이력정보 수정
     */
    private int updtBioMngHst(BioMngHstVO bioMngHstVO) throws Exception {
        return mngHstDAO.updtBioMngHst(bioMngHstVO);
    }

    /**
     * 관리이력정보 삭제
     */
    private int deltBioMngHst(String sn) throws Exception {
        return mngHstDAO.deltBioMngHst(sn);
    }

    /**
     * 관리이력정보 등록,수정,삭제
     */
	@Override
	public String saveBioMngHst(BioMngHstVO bioMngHstVO) throws Exception {
		
		if (bioMngHstVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = bioMngHstVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 관리이력정보 수정
			ret = updtBioMngHst(bioMngHstVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 관리이력정보 등록
			ret = regiBioMngHst(bioMngHstVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 관리이력정보 삭제
			ret = deltBioMngHst(bioMngHstVO.getSn());
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}