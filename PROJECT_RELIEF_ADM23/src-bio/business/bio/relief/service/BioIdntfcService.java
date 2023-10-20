package business.bio.relief.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 살생물제품 피해자정보 Service Interface
 * 
 * @class   : BioIdntfcService
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface BioIdntfcService {

    /**
     * 피해자정보 상세조회
     */
    public BioIdntfcVO viewBioIdntfc(String sufrerNo) throws Exception;

    /**
     * 피해자정보 등록
     */
	public int regiBioIdntfc(BioIdntfcVO bioIdntfcVO) throws Exception;

    /**
     * 피해자정보 수정
     */
	public int updtBioIdntfc(BioIdntfcVO bioIdntfcVO) throws Exception;

    /**
     * 성명/생년월일 기준 피해자정보 ID 조회
     */
    public String getBioIdntfcExistNo(BioIdntfcVO bioIdntfcVO) throws Exception;

    /**
     * 구제급여 신청접수 처리 (식별ID 생성 반환)
     */
    public String saveBioIdntfcReceipt(BioIdntfcVO bioIdntfcVO) throws Exception;
}