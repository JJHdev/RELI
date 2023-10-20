package business.adm.gis.service;

import java.util.List;
import java.util.Map;

import business.com.relief.service.IdntfcVO;
import commf.paging.PaginatedArrayList;
import common.file.FileInfo;

/**
 * [서비스인터페이스] - GIS Service Interface
 * 
 * @class   : GisService
 * @author  : JWH
 * @since   : 2022.11.14
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface GisService {
	
	/**
     * 초본주소 페이징목록조회
     */
	PaginatedArrayList listAbstrctAddr(AbstrctAddrVO abstrctAddrVO, int currPage, int pageSize) throws Exception;
	
	/**
     * 초본주소 목록조회
     */
	List listAbstrctAddr(AbstrctAddrVO abstrctAddrVO) throws Exception;
	
	 /**
     * 피해자 초본주소 상세조회
     */
	AbstrctAddrVO viewAbstrctAddr(AbstrctAddrVO abstrctAddrVO) throws Exception;
	
	/**
     * 피해자 초본주소 등록
     */
	String saveAbstrctAddr(AbstrctAddrVO abstrctAddrVO) throws Exception;
	
	/**
     * 의료비내역 엑셀로드
     */
    public int loadAbstrctAddr(AbstrctAddrVO abstrctAddrVO, FileInfo fileInfo) throws Exception;
    
	/**
     * 피해지역 목록 조회
     */
    public List<Map> getlistBizArea() throws Exception;
    
    /**
     * 피해지역 한개 조회
     */
    public List<Map> getOneBizArea(Map paramMap) throws Exception;
    
    /**
     * 피해자정보 페이징목록 조회
     */
    public PaginatedArrayList listIdntBizArea(Map paramMap, int currPage, int pageSize) throws Exception;

    /**
     * GIS 환경오염 영향분석 피해자정보 목록조회
     */
    public List listIdntBizArea(Map ParamMap) throws Exception;

    
    /**
     * GIS 환경오염 영향분석 피해자정보 주소 좌표데이터
     */
    public List listEnvpAffcLoc(Map ParamMap) throws Exception;
    
	/**
	 * GIS 환경 오염 영향분석 - 클릭시 가장가까운 point 가져오기
	 */
    public List<Map> getCoordinateByOnclick(Map ParamMap) throws Exception;
    
}
