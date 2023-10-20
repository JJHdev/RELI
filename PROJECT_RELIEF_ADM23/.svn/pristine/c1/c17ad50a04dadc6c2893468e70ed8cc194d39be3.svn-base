package business.bio.relief.service;

import java.util.List;
import java.util.Map;

import commf.paging.PaginatedArrayList;
import common.file.FileInfo;

/**
 * [서비스인터페이스] - 살생물제품 구제급여신청 Service Interface
 * 
 * @class   : BioReliefService
 * @author  : LSH
 * @since   : 2023.01.25
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
     * 구제급여신청 상세조회
     */
    public BioReliefVO viewBioRelief(BioReliefVO bioReliefVO) throws Exception;

    /**
     * 구제급여신청 임시저장/제출하기
     */
    public String saveBioRelief(BioReliefVO bioReliefVO) throws Exception;

    /**
     * 구제급여신청 상세조회 (피해자정보 포함)
     */
    public BioReliefVO viewBioReliefWidhIdntfc(BioReliefVO bioReliefVO) throws Exception;
    
    /**
     * 구제급여 접수처리
     */
	public String saveBioReceipt(BioReliefVO bioReliefVO) throws Exception;
    
    /**
     * 구제급여접수 - 피해자정보 수정처리
     * 구제급여접수 - 신청인정보 수정처리
     * 구제급여접수 - 피해내용 수정처리
     */
	public String saveBioReliefModify(BioReliefVO bioReliefVO) throws Exception;
	
    /**
     * 관리자 제출서류 추가등록 업로드 및 저장처리
     */
	public String saveBioReliefAddfile(BioReliefVO bioReliefVO, FileInfo fileInfo) throws Exception;
    
}
