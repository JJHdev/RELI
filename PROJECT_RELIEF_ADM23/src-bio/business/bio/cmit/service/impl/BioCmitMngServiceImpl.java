package business.bio.cmit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.cmit.service.BioCmitMngService;
import business.bio.cmit.service.BioCmitMngVO;
import business.com.CommConst;
import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 살생물제품 위원회관리 Service 구현 클래스
 * 
 * @class   : BioCmitMngServiceImpl
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@Service("BioCmitMngService")
@SuppressWarnings({"all"})
public class BioCmitMngServiceImpl extends BaseService implements BioCmitMngService {

    @Resource(name = "BioCmitMngDAO")
    private BioCmitMngDAO cmitMngDAO;

    @Resource(name = "BioCmitDmgeDAO")
    private BioCmitDmgeDAO cmitDmgeDAO;
	
    /**
     * 위원회 페이징목록조회
     */
	@Override
	public PaginatedArrayList listBioCmitDmge(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) throws Exception {
    	return cmitDmgeDAO.listBioCmitDmge(bioCmitMngVO, currPage, pageSize);
	}

    /**
     * 위원회 목록조회
     */
	@Override
	public List listBioCmitDmge(BioCmitMngVO bioCmitMngVO) throws Exception {
    	return cmitDmgeDAO.listBioCmitDmge(bioCmitMngVO);
	}

    /**
     * 위원회 상세조회
     */
	@Override
	public BioCmitMngVO viewBioCmitDmge(BioCmitMngVO bioCmitMngVO) throws Exception {
    	return cmitDmgeDAO.viewBioCmitDmge(bioCmitMngVO);
	}

    /**
     * 위원회 저장처리
     */
	@Override
	public String saveBioCmitDmge(BioCmitMngVO bioCmitMngVO) throws Exception {
		
		if (bioCmitMngVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = bioCmitMngVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 위원회 수정
			ret = cmitDmgeDAO.updtBioCmitDmge(bioCmitMngVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 위원회 등록
			ret = cmitDmgeDAO.regiBioCmitDmge(bioCmitMngVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 위원회 소속위원을 전체 삭제한다.
			cmitMngDAO.deltBioCmitMngAll(bioCmitMngVO);
			// 위원회 삭제
			ret = cmitDmgeDAO.deltBioCmitDmge(bioCmitMngVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 위원회 소속 위원 페이징목록조회
     */
    @Override
    public PaginatedArrayList listBioCmitMng(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) throws Exception {
    	return cmitMngDAO.listBioCmitMng(bioCmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회 소속 위원 목록조회
     */
    @Override
    public List listBioCmitMng(BioCmitMngVO bioCmitMngVO) throws Exception {
    	return cmitMngDAO.listBioCmitMng(bioCmitMngVO);
    }

    /**
     * 위원회 소속 위원 등록,수정,삭제
     */
	@Override
	public String saveBioCmitMng(BioCmitMngVO bioCmitMngVO) throws Exception {

		if (bioCmitMngVO == null)
			throw processException("error.comm.notTarget");

		// 처리 모드
		String mode = bioCmitMngVO.getMode();
		// 결과 건수
		int ret = 0;
		
		// 선택삭제인 경우
		if (CommConst.DELETE.equals(mode)) {
			// 삭제 목록
			List<BioCmitMngVO> removeList = bioCmitMngVO.getRemoveList();

			if (CommUtils.isEmptyList(removeList))
				throw processException("error.comm.notTarget");
			
			// 위원회 소속 위원 삭제를 수행한다.
			for (BioCmitMngVO data : removeList) {
				ret += cmitMngDAO.deltBioCmitMng(data);
			}
		}
		else {
			// 등록 목록
			List<BioCmitMngVO> saveList = bioCmitMngVO.getSaveList();

			if (CommUtils.isEmptyList(saveList))
				throw processException("error.comm.notTarget");
			
			// 위원회 소속 위원 등록을 수행한다.
			for (BioCmitMngVO data : saveList) {
				data.setGsUserNo (bioCmitMngVO.getGsUserNo ());
				data.setCmitMngNo(bioCmitMngVO.getCmitMngNo());
				
				// 해당 위원회에 동일한 위원이 존재하는지 체크
				if (cmitMngDAO.existBioCmitMng(data)) {
					// 해당 위원회에 이미 등록된 위원입니다.
					throw processException("error.adm.cmitMng.duplBioMfcmm", 
						new String[] {data.getMfcmmOgdpNm() + " - " + data.getMfcmmNm()} 
					);
				}
				ret += cmitMngDAO.regiBioCmitMng(data);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 2023.02.13 LSH
     * 임기번호가 속한 위원회가 있는지 체크
     */
	@Override
    public boolean existBioCmitMngForTenure(BioCmitMngVO bioCmitMngVO) throws Exception {
    	return (cmitMngDAO.getBioCmitMngCountForTenure(bioCmitMngVO) > 0);
	}
}