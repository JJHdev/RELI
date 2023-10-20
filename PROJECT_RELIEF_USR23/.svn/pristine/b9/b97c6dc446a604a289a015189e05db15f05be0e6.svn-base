package business.bio.file.service;

import java.util.List;
import java.util.Map;

import business.bio.relief.service.BioReliefVO;
import commf.paging.PaginatedArrayList;
import common.file.FileInfo;

/**
 * [서비스인터페이스] - 살생물제품 신청첨부파일 Service Interface
 *
 * @class   : BioAplyFileService
 * @author  : LSH
 * @since   : 2023.01.16
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface BioAplyFileService {

    /**
     * 신청첨부파일 페이징목록 조회
     */
    public PaginatedArrayList listBioAplyFile(BioAplyFileVO bioAplyFileVO, int currPage, int pageSize) throws Exception;

    /**
     * 신청첨부파일 목록조회
     */
    public List listBioAplyFile(BioAplyFileVO bioAplyFileVO) throws Exception;

    /**
     * 서류기준 신청파일 조회
     */
    public List listBioAplyFileByPape(Map paramMap) throws Exception;

    /**
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    public PaginatedArrayList listBioAplySubmitFile(BioAplyFileVO bioAplyFileVO, int currPage, int pageSize) throws Exception;

    /**
     * 공통사용 - 제출서류목록조회
     */
    public List listBioAplySubmitFile(BioAplyFileVO bioAplyFileVO) throws Exception;

    /**
     * 신청첨부파일 상세조회
     */
    public BioAplyFileVO viewBioAplyFile(BioAplyFileVO bioAplyFileVO) throws Exception;
    /**
     * 공통사용 - 제출서류상세조회
     */
    public BioAplyFileVO viewBioAplySubmitFile(String sn) throws Exception;

    /**
     * 신청첨부파일 등록
     */
	public int regiBioAplyFile(BioAplyFileVO aplyFileVO) throws Exception;
	
    /**
     * 신청첨부파일 수정
     */
    public int updtBioAplyFile(BioAplyFileVO bioAplyFileVO) throws Exception;

    /**
     * 신청첨부파일 등록,수정,삭제
     */
    public String saveBioAplyFile(BioAplyFileVO bioAplyFileVO) throws Exception;

    /**
     * 신청파일 임시저장처리
     */
    public BioAplyFileVO saveBioTempFile(FileInfo fileInfo) throws Exception;

    /**
     * 구제급여 신청파일 실제저장처리
     */
    public int saveBioReliefFile(BioReliefVO reliefVO) throws Exception;
}