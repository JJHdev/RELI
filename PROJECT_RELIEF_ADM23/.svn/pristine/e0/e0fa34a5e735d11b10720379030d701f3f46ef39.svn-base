package business.com.cmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.cmm.service.CommService;
import business.sys.code.service.CodeService;
import business.sys.code.service.CodeVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;

/**
 * [서비스클래스] - 공통 ServiceImpl
 *
 * @class   : CommServiceImpl
 * @author  :
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일            수정자                             수정내용
 *  -------    --------    ---------------------------
 *
 */
@Service("CommService")
@SuppressWarnings("rawtypes")
public class CommServiceImpl extends BaseService implements CommService {

    @Resource(name = "CommDAO")
    private CommDAO commDAO;

    @Resource(name="CodeService")
    private CodeService codeService;

    /**
     * 코드리스트 조회
     */
    public List listCode(Map paramMap) throws Exception{
        return commDAO.listCode(paramMap);
    }

    /**
     * 코드상세 조회
     */
    public CodeVO getCode(String upCdId, String cdId) throws Exception{
    	CodeVO codeVO = CodeVO.builder()
    			.upCdId(upCdId)
    			.cdId  (cdId)
    			.build();
    	return codeService.viewCode(codeVO);
    }

    /**
     * 2021.08.20 LSH
     * 엑셀설정정보 콤보 리스트 조회
     */
    public List getListExcel(Map paramMap) throws Exception{
        return commDAO.getListExcel(paramMap);
    }
    
    /**
     * 2021.09.24 LSH
     * 피해지역 콤보 리스트 조회
     */
    public List getListBizMng(Map paramMap) throws Exception{
        return commDAO.getListBizMng(paramMap);
    }
    
    /**
     * 2021.11.17 LSH
     * 사업차수 콤보 리스트 조회
     */
    public List getListBizOder(Map paramMap) throws Exception{
        return commDAO.getListBizOder(paramMap);
    }
    
    /**
     * 2021.11.23 LSH
     * 예비조사차수 콤보 리스트 조회
     * bizAreaCd, bizOder 필수
     */
    public List getListPrptOder(Map paramMap) throws Exception{
        return commDAO.getListPrptOder(paramMap);
    }
    
    /**
     * 2021.11.23 LSH
     * 본조사차수 콤보 리스트 조회
     * bizAreaCd, bizOder 필수
     */
    public List getListMnsvyOder(Map paramMap) throws Exception{
        return commDAO.getListMnsvyOder(paramMap);
    }
    
    /**
     * 2021.11.11 LSH
     * 질병분류 콤보 리스트 조회
     */
    public List getListDissCl(Map paramMap) throws Exception{
        return commDAO.getListDissCl(paramMap);
    }
    
    /**
     * 2022.01.27 LSH
     * 상위코드ID에 해당하는 코드목록 반환
     * 피해지역/질환분류 포함
     */
    @SuppressWarnings("unchecked")
	private List<Map> _getCodeList(String upCdId) {
    	List<Map> list = null;
    	if      (CommConst.CODE_BIZAREA.equals(upCdId)) 
    		list = commDAO.getListBizMng(null);
    	else if (CommConst.CODE_DISSCL.equals(upCdId))
    		list = commDAO.getListDissCl(null);
    	else {
    		Map params = new HashMap();
    		params.put("upCdId", upCdId);
    		list = commDAO.listCode(params);
    	}
    	return list;
    }
    
    /**
     * 2022.01.27 LSH
     * 코드리스트를 맵으로 변환
     * @param list      코드리스트 (List<Map>)
     * @param keyField  맵의 KEY에 해당하는 필드명
     * @param valueField 맵의 VALUE에 해당하는 필드명
     */
    private Map<String,Object> _toMap(List<Map> list, String keyField, String valueField) {
    	Map<String,Object> ret = new HashMap<String,Object>();
    	if (list == null)
    		return ret;
    	for (Map item : list) {
    		ret.put((String)item.get(keyField), item.get(valueField));
    	}
    	return ret;
    }
    
    /**
     * 2021.11.30 LSH
     * 코드목록을 Name KEY 맵으로 변환
     */
	public Map<String,Object> getMapCodeByName(String upCdId) throws Exception{
    	List<Map> list = _getCodeList(upCdId);
    	return _toMap(list, "text", "code");
    }
    
    /**
     * 2022.01.27 LSH
     * 코드목록을 CODE KEY 맵으로 변환
     */
	public Map<String,Object> getMapCode(String upCdId) throws Exception{
    	List<Map> list = _getCodeList(upCdId);
    	return _toMap(list, "code", "text");
    }

    /**
     * 2021.12.14 LSH
     * 신청차수 콤보 리스트 조회
     */
    public List getListReliefOder(Map paramMap) throws Exception {
    	return commDAO.getListReliefOder(paramMap);
    }

    /**
     * 2021.12.23 LSH
     * 주소검색 페이징목록 조회
     */
    public PaginatedArrayList listAddress(Map paramMap, int currPage, int pageSize) throws Exception {
        return commDAO.listAddress(paramMap, currPage, pageSize);
    }

    /**
     * 2022.01.20 LSH
     * 영업일수에 해당하는 날짜 반환
     * @param  workCnt  영업일수 (숫자형)
     * @param  currDate 기준일자 (YYYYMMDD)
     * @return 영업일수에 해당하는 날짜
     */
    @SuppressWarnings("unchecked")
	public String getWorkDate(int workCnt, String currDate) throws Exception {
    	
    	Map paramMap = new HashMap();
    	paramMap.put("workCnt" , workCnt);
    	paramMap.put("currDate", currDate);
    	
    	return commDAO.getWorkDate(paramMap);
    }

    /**
     * 2022.12.06 LSH
     * 서류코드 콤보 리스트 조회
     * @param  paramMap.papeCd 상위서류코드 (ex.D00)
     */
    public List getListPapeCode(Map paramMap) throws Exception {
    	return commDAO.getListPapeCode(paramMap);
    }
    
    /**
     * 2023.01.16 LSH
     * 살생물제품 제품분류및유형 콤보 조회
     * @param paramMap.upCdId 상위코드(CT202)
     */
    public List getListBioPrduct(Map paramMap) throws Exception {
    	return commDAO.getListBioPrduct(paramMap);
    }
    
    /**
     * 2023.02.03 KSH
     * 공휴일 연도 Combo목록 조회
     */
	public List getComboHldyYear(Map paramMap) throws Exception {
		return commDAO.getComboHdlyYear(paramMap);
	}
}
