package business.bio.relief.service;

import java.util.List;
import java.util.Map;

import commf.paging.PaginatedArrayList;
import common.file.FileInfo;

/**
 * [서비스인터페이스] - 살생물제품 신청첨부파일 Service Interface
 *
 * @class   : BioAplyFileService
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface BioAplyFileService {

    /**
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    public PaginatedArrayList listBioAplySubmitFile(BioAplyFileVO bioAplyFileVO, int currPage, int pageSize) throws Exception;
    /**
     * 공통사용 - 제출서류목록조회
     */
    public List listBioAplySubmitFile(BioAplyFileVO bioAplyFileVO) throws Exception;
    /**
     * 공통사용 - 제출서류 상세 조회
     */
    public BioAplyFileVO viewBioAplySubmitFile(String sn) throws Exception;

    /**
     * 서류기준 신청파일 조회
     */
    public List listBioAplyFileByPape(Map paramMap) throws Exception;

    /**
     * 신청파일 임시저장처리
     */
    public BioAplyFileVO saveBioTempFile(FileInfo fileInfo) throws Exception;

    /**
     * 구제급여 신청파일 실제저장처리
     */
    public int saveBioReliefFile(BioReliefVO bioReliefVO) throws Exception;

    /**
     * 구제급여신청 관리자 제출서류 추가등록 처리 (단일파일)
     * @return 저장한 파일번호
     */
    public String saveBioReliefAddfile(BioReliefVO bioReliefVO, FileInfo fileInfo) throws Exception;    
}