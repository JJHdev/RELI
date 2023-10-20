package business.bio.cmit.service;

import java.util.List;

import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 살생물제품 위원회관리 Service Interface
 * 
 * @class   : BioCmitMngService
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface BioCmitMngService {

    /**
     * 위원회 페이징목록 조회
     */
    public PaginatedArrayList listBioCmitDmge(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 위원회 목록조회
     */
    public List listBioCmitDmge(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원회 상세조회
     */
    public BioCmitMngVO viewBioCmitDmge(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원회 등록,수정,삭제
     */
    public String saveBioCmitDmge(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원회 소속 위원 페이징목록 조회
     */
    public PaginatedArrayList listBioCmitMng(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 위원회관리 목록조회
     */
    public List listBioCmitMng(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원회관리 등록,수정,삭제
     */
    public String saveBioCmitMng(BioCmitMngVO bioCmitMngVO) throws Exception;
    
    /**
     * 2023.02.13 LSH
     * 임기번호가 속한 위원회가 있는지 체크
     */
    public boolean existBioCmitMngForTenure(BioCmitMngVO bioCmitMngVO) throws Exception;
}