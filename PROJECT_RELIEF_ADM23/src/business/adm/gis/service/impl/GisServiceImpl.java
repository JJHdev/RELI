package business.adm.gis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.adm.gis.service.AbstrctAddrVO;
import business.adm.gis.service.GisService;
import business.com.CommConst;
import business.com.CommExcel;
import business.com.relief.service.IdntfcService;
import business.com.relief.service.IdntfcVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.util.CommUtils;

/**
 * [서비스클래스] - GIS Service 구현 클래스
 * 
 * @class   : GisServiceImpl
 * @author  : JWH
 * @since   : 2022.11.14
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("GisService")
@SuppressWarnings({"all"})
public class GisServiceImpl extends BaseService implements GisService {
	
	@Resource(name = "GisDAO")
    private GisDAO gisDAO;
	
	@Resource(name = "IdntfcService")
    private IdntfcService idntfcService;
	
	/**
     * 초본주소 페이징목록조회
     */
    @Override
    public PaginatedArrayList listAbstrctAddr(AbstrctAddrVO abstrctAddrVO, int currPage, int pageSize) throws Exception {
    	return gisDAO.listAbstrctAddr(abstrctAddrVO, currPage, pageSize);
    }

    /**
     * 초본주소 목록조회
     */
    @Override
    public List listAbstrctAddr(AbstrctAddrVO abstrctAddrVO) throws Exception {
    	return gisDAO.listAbstrctAddr(abstrctAddrVO);
    }
    
    /**
     * 피해자 초본주소 상세조회
     */
	@Override
	public AbstrctAddrVO viewAbstrctAddr(AbstrctAddrVO abstrctAddrVO) throws Exception {
		return gisDAO.viewAbstrctAddr(abstrctAddrVO);
	}
    
	/**
     * 피해자 초본주소 등록
     */
	@Override
	public String saveAbstrctAddr(AbstrctAddrVO abstrctAddrVO) throws Exception {
		if (abstrctAddrVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = abstrctAddrVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 초본주소 수정
			ret = updtAbstrctAddr(abstrctAddrVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 초본주소 등록
			ret = regiAbstrctAddr(abstrctAddrVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			List<AbstrctAddrVO> list = abstrctAddrVO.getAbstrctAddrList();
			for (AbstrctAddrVO data : list) {
				data.setGsUserNo(abstrctAddrVO.getGsUserNo());
				// 초본주소 삭제
				ret += deltAbstrctAddr(data);
			}
		}
		if (ret > 0) {
			// 2023.02.21 LSH 초본주소 변경시 피해지역 거주기간 업데이트
			idntfcService.updtIdntfcResiWhlCn(
				IdntfcVO.builder()
				.gsUserNo(abstrctAddrVO.getGsUserNo())
				.idntfcId(abstrctAddrVO.getIdntfcId())
				.build()
			);
		}
		// 저장결과를 반환한다.
		if (ret > 0)
			return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
	/**
     * 초본주소 등록
     */
    private int regiAbstrctAddr(AbstrctAddrVO abstrctAddrVO) throws Exception {
		int ret = gisDAO.regiAbstrctAddr(abstrctAddrVO);
		return ret;
    }
    
	/**
     * 피해자 초본주소 수정
     */
	private int updtAbstrctAddr(AbstrctAddrVO abstrctAddrVO) {
		return gisDAO.updtAbstrctAddr(abstrctAddrVO);
	}
	
	/**
     * 피해자 초본주소 삭제
     */
	private int deltAbstrctAddr(AbstrctAddrVO abstrctAddrVO) {
		return gisDAO.deltAbstrctAddr(abstrctAddrVO);
	}
	
	/**
     * 초본주소 엑셀로드
     */
	@Override
	public int loadAbstrctAddr(AbstrctAddrVO abstrctAddrVO, FileInfo fileInfo) throws Exception {
		
		if (fileInfo == null)
			throw processException("error.comm.notTarget");

    	// 파일 물리적 경로 포함 명칭
    	String fullName = FileDirectory.ROOT.getTempName(fileInfo.getSaveName());
    	
    	// 엑셀로드타입
    	CommExcel ce = CommExcel.ABSTRCT;
    	
    	// 엑셀로드된 List<Map> 데이터
    	List<Map> dataList = ce.parseData(fullName);
    	
    	if (dataList == null)
    		throw processException("error.comm.notTarget");
    	
    	// 세션사용자번호
    	String gsUserNo = abstrctAddrVO.getGsUserNo();
    	
    	// 피해자정보 객체
    	IdntfcVO idntfcVO = IdntfcVO.builder().gsUserNo(gsUserNo).build();
    	
    	int sn  = 1;
    	int ret = 0;
    	
        // 검증 및 데이터 등록
    	for (Map data : dataList) {

    		// 필수값 입력 체크
    		List<String> requireErrors = ce.validateErrors(data);
    		if (requireErrors != null) {
    			throw processException("error.adm.abstrctAddr.notExistExcel", new String[] { String.valueOf(sn), requireErrors.toString() });
    		}
    		// 최대길이 검증
    		List<String> lengthErrors = ce.validateLengthErrors(data);
    		if (lengthErrors != null) {
    			throw processException("error.adm.abstrctAddr.overMaxLength", new String[] { String.valueOf(sn), lengthErrors.toString() });
    		}
    		// 타입 검증
    		List<String> typeErrors = ce.validateTypeErrors(data);
    		if (lengthErrors != null) {
    			throw processException("error.adm.abstrctAddr.notVaildType", new String[] { String.valueOf(sn), typeErrors.toString() });
    		}
    		
    		AbstrctAddrVO model = new AbstrctAddrVO();
    		// 데이터 담기
    		CommUtils.mapToBean(data, model);
    		// 세션사용자번호
    		model.setGsUserNo(gsUserNo);
    		
			// 초본주소 등록
    		if (regiAbstrctAddr(model) > 0) {
    			idntfcVO.setIdntfcId(model.getIdntfcId());
    			// 2023.02.21 LSH 초본주소 변경시 피해지역 거주기간 업데이트
    			idntfcService.updtIdntfcResiWhlCn(idntfcVO);
    			ret++;
    		}
        	sn++;
    	}
    	return ret;
	}
	
	/**
     * 피해지역 목록 조회
     */
	public List<Map> getlistBizArea() throws Exception {
		List<Map> area = gisDAO.getlistBizArea();
		return area;
	}

	/**
     * 피해지역 한개 조회
     */
	public List<Map> getOneBizArea(Map paramMap) throws Exception {
		List<Map> area = gisDAO.getOneBizArea(paramMap);
		return area;
	}
	
    /**
     * 피해자정보 페이징목록조회
     */
	@Override
	public PaginatedArrayList listIdntBizArea(Map paramMap, int currPage, int pageSize) throws Exception {
		return gisDAO.listIdntBizArea(paramMap, currPage, pageSize);
	}

    /**
     * GIS 환경오염 영향분석 피해자정보 목록조회
     */
	@Override
	public List listIdntBizArea(Map ParamMap) throws Exception {
		return gisDAO.listIdntBizArea(ParamMap);
	}
	
    /**
     * GIS 환경오염 영향분석 피해자정보 주소 좌표데이터
     */
	@Override
	public List listEnvpAffcLoc(Map ParamMap) throws Exception {
		return gisDAO.listEnvpAffcLoc(ParamMap);
	}
	
	/**
	 * GIS 환경 오염 영향분석 - 클릭시 가장가까운 point 가져오기
	 */
	@Override
	public List<Map> getCoordinateByOnclick(Map ParamMap) throws Exception {
		return gisDAO.getCoordinateByOnclick(ParamMap);
	}


	
}
