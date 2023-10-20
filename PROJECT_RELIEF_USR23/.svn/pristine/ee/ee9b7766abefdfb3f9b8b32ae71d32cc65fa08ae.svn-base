package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.CommExcel;
import business.com.cmm.service.CommService;
import business.com.exmn.service.McpDtlsService;
import business.com.exmn.service.McpDtlsVO;
import business.com.exmn.service.MnsvyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.util.CommUtils;

/**
 * [서비스클래스] - 의료비내역 Service 구현 클래스
 * 
 * @class   : McpDtlsServiceImpl
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("McpDtlsService")
@SuppressWarnings({"all"})
public class McpDtlsServiceImpl extends BaseService implements McpDtlsService {

    @Resource(name = "McpDtlsDAO")
    private McpDtlsDAO mcpDtlsDAO;

    @Resource(name = "CommService")
    private CommService commService;
	
    /**
     * 의료비내역 페이징목록조회
     */
    @Override
    public PaginatedArrayList listMcpDtls(McpDtlsVO mcpDtlsVO, int currPage, int pageSize) throws Exception {
    	return mcpDtlsDAO.listMcpDtls(mcpDtlsVO, currPage, pageSize);
    }

    /**
     * 의료비내역 목록조회
     */
    @Override
    public List listMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception {
    	return mcpDtlsDAO.listMcpDtls(mcpDtlsVO);
    }

    /**
     * 의료비내역 상세조회
     */
	@Override
	public McpDtlsVO viewMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception {
		return mcpDtlsDAO.viewMcpDtls(mcpDtlsVO);
	}

    /**
     * 2021.12.01 LSH
     * 본조사 의료비산정결과 집계 목록 조회
     */
    @Override
    public List listMcpDtlsSummary(McpDtlsVO mcpDtlsVO) throws Exception {
    	return mcpDtlsDAO.listMcpDtlsSummary(mcpDtlsVO);
    }
    
    /**
     * 2021.12.01 LSH
     * 본조사 의료비산정결과 집계 합계
     */
    @Override
    public List listMcpDtlsSummaryTotal(McpDtlsVO mcpDtlsVO) throws Exception {
        return mcpDtlsDAO.listMcpDtlsSummaryTotal(mcpDtlsVO);
    }

    /**
     * 의료비내역 등록
     */
    private int regiMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception {
		int ret = mcpDtlsDAO.regiMcpDtls(mcpDtlsVO);
		if (ret > 0) {
			// 2021.12.01 LSH 이력저장
			mcpDtlsVO.setLogTy(CommConst.INSERT);
			mcpDtlsDAO.regiMcpDtlsHst(mcpDtlsVO);
		}
		return ret;
    }

    /**
     * 의료비내역 수정
     */
    private int updtMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception {
		int ret = mcpDtlsDAO.updtMcpDtls(mcpDtlsVO);
		if (ret > 0) {
			// 2021.12.01 LSH 이력저장
			mcpDtlsVO.setLogTy(CommConst.UPDATE);
			mcpDtlsDAO.regiMcpDtlsHst(mcpDtlsVO);
		}
		return ret;
    }

    /**
     * 의료비내역 삭제 (이력을 먼저 저장후 삭제한다)
     */
    private int deltMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception {
		int ret = 0;
		// 2021.12.01 LSH 이력저장
		mcpDtlsVO.setLogTy(CommConst.DELETE);
		if (mcpDtlsDAO.regiMcpDtlsHst(mcpDtlsVO) > 0) {
	        ret = mcpDtlsDAO.deltMcpDtls(mcpDtlsVO);
		}
		return ret;
    }

    /**
     * 의료비내역 등록,수정,삭제
     */
	@Override
	public String saveMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception {
		
		if (mcpDtlsVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = mcpDtlsVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 의료비내역 수정
			ret = updtMcpDtls(mcpDtlsVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 의료비내역 등록
			ret = regiMcpDtls(mcpDtlsVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 의료비내역 삭제
			ret = deltMcpDtls(mcpDtlsVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 의료비내역 엑셀로드
     */
	@Override
	public int loadMcpDtls(McpDtlsVO mcpDtlsVO, FileInfo fileInfo) throws Exception {
		
		if (fileInfo == null)
			throw processException("error.comm.notTarget");

    	// 요양구분코드
    	Map rcperMap = commService.getMapCodeByName(CommConst.CODE_RCPER);
    	// 인정구분코드
    	Map rcognMap = commService.getMapCodeByName(CommConst.CODE_RCOGN);
    	// 피해지역코드
    	Map bizmngMap = commService.getMapCodeByName(CommConst.CODE_BIZAREA);

    	// 파일 물리적 경로 포함 명칭
    	String fullName = FileDirectory.ROOT.getTempName(fileInfo.getSaveName());
    	
    	// 엑셀로드타입
    	CommExcel ce = CommExcel.MCP;
    	
    	// 엑셀로드된 List<Map> 데이터
    	List<Map> dataList = ce.parseData(fullName);
    	
    	if (dataList == null)
    		throw processException("error.comm.notTarget");
    	
    	// 세션사용자번호
    	String gsUserNo = mcpDtlsVO.getGsUserNo();
    	
    	int sn  = 1;
    	int ret = 0;
    	
        // 검증 및 데이터 등록
    	for (Map data : dataList) {

    		// 필수값 입력 체크
    		List<String> requireErrors = ce.validateErrors(data);
    		if (requireErrors != null) {
    			throw processException("error.adm.mcpDtl.notExistExcel", new String[] { String.valueOf(sn), requireErrors.toString() });
    		}
    		// 최대길이 검증
    		List<String> lengthErrors = ce.validateLengthErrors(data);
    		if (lengthErrors != null) {
    			throw processException("error.adm.mcpDtl.overMaxLength", new String[] { String.valueOf(sn), lengthErrors.toString() });
    		}
    		
    		McpDtlsVO model = new McpDtlsVO();
    		// 데이터 담기
    		CommUtils.mapToBean(data, model);
    		// 세션사용자번호
    		model.setGsUserNo(mcpDtlsVO.getGsUserNo());
    		// 조사차수
    		model.setExmnOder(mcpDtlsVO.getExmnOder());
    		// 신청번호
    		model.setAplyNo(mcpDtlsVO.getAplyNo());
    		
    		// 피해지역 코드값 맵핑
    		model.setBizAreaCd  ((String)bizmngMap.get(CommUtils.nvlTrim(model.getBizAreaNm())));
    		// 요양구분 코드값 맵핑
    		model.setRcperSeCd  ((String)rcperMap.get(CommUtils.nvlTrim(model.getRcperSeNm())));
    		// 인정구분 코드값 맵핑
    		model.setRcognStusCd((String)rcognMap.get(CommUtils.nvlTrim(model.getRcognStusNm())));
    		// 인정구분이 없는 경우 "02"(불인정)으로 맵핑
    		if (CommUtils.isEmpty(model.getRcognStusCd()))
    			model.setRcognStusCd("02");
    		
    		try {
        		// 사업차수 : double 형의 문자열 데이터를 int 형의 문자열 데이터로 변환 (2.0 -> 2)
        		model.setBizOder(CommUtils.toIntString(model.getBizOder()));
    		} catch(Exception ex) {
        		// 사업차수가 양식에 맞지 않는 경우
    			throw processException("error.adm.mcpDtl.notValidExcel", new String[] { String.valueOf(sn), "사업차수 (기준: "+mcpDtlsVO.getBizOder()+", 현재: "+model.getBizOder()+")" });
    		}
    		try {
        		Double.parseDouble(model.getSelfAlotm());
    		} catch(Exception ex) {
        		// 본인부담금이 숫자형이 아닌 경우
    			throw processException("error.adm.mcpDtl.notValidExcel", new String[] { String.valueOf(sn), "본인부담금 ("+model.getSelfAlotm()+")" });
    		}
    		// 식별ID가 맞지 않는 경우
    		if (!CommUtils.isEqual(model.getIdntfcId(), mcpDtlsVO.getIdntfcId()))
    			throw processException("error.adm.mcpDtl.notValidExcel", new String[] { String.valueOf(sn), "식별ID (기준: "+mcpDtlsVO.getIdntfcId()+", 현재: "+model.getIdntfcId()+")" });
    		// 피해지역코드가 맞지 않는 경우
    		if (!CommUtils.isEqual(model.getBizAreaCd(), mcpDtlsVO.getBizAreaCd()))
    			throw processException("error.adm.mcpDtl.notValidExcel", new String[] { String.valueOf(sn), "피해지역 (기준: "+mcpDtlsVO.getBizAreaCd()+", 현재: "+model.getBizAreaCd()+")" });
    		// 사업차수가 맞지 않는 경우
    		if (!CommUtils.isEqual(model.getBizOder(), mcpDtlsVO.getBizOder()))
    			throw processException("error.adm.mcpDtl.notValidExcel", new String[] { String.valueOf(sn), "사업차수 (기준: "+mcpDtlsVO.getBizOder()+", 현재: "+model.getBizOder()+")" });
    		    		
			// 의료비내역 등록
			ret += regiMcpDtls(model);
        	sn++;
    	}
    	return ret;
	}
}