package business.bio.relief.service;

/**
 * [서비스인터페이스] - 살생물제품 피해자정보 Service Interface
 * 
 * @class   : BioIdntfcService
 * @author  : LSH
 * @since   : 2023.01.16
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
    public BioIdntfcVO viewBioIdntfc(String idntfcId) throws Exception;

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
}