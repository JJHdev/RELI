package business.bio.relief.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 살생물제품 구제급여신청 Service Interface
 * 
 * @class   : BioReliefService
 * @author  : LSH
 * @since   : 2023.01.16
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface BioReliefService {

    /**
     * 구제급여신청 페이징목록 조회
     */
    public PaginatedArrayList listBioRelief(BioReliefVO bioReliefVO, int currPage, int pageSize) throws Exception;

    /**
     * 구제급여신청 목록조회
     */
    public List listBioRelief(BioReliefVO bioReliefVO) throws Exception;

    /**
     * 마이페이지 구제급여신청 목록조회
     */
    public List listBioReliefMypage(BioReliefVO bioReliefVO) throws Exception;

    /**
     * 구제급여신청 상세조회
     */
    public BioReliefVO viewBioRelief(BioReliefVO bioReliefVO) throws Exception;

    /**
     * 구제급여신청 본인이 작성한 임시저장 KEY 조회 
     */
    public BioReliefVO viewBioReliefAplyLast(BioReliefVO bioReliefVO) throws Exception;

    /**
     * 관리자대행으로 구제급여신청 임시저장한 최종 신청정보 KEY 조회
     */
    public BioReliefVO viewBioReliefAplyLastAdmn(BioReliefVO bioReliefVO) throws Exception;

    /**
     * 구제급여신청 상세조회 (피해자정보 포함)
     */
    public BioReliefVO viewBioReliefWidhIdntfc(BioReliefVO bioReliefVO) throws Exception;
    
    /**
     * 마이페이지 - 식별아이디기준 최종 KEY 조회
     */
    public BioReliefVO viewBioReliefByIdntfcId(String idntfcId) throws Exception;

    /**
     * 구제급여신청 임시저장/제출하기
     */
    public String saveBioRelief(BioReliefVO bioReliefVO) throws Exception;
}
