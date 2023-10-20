package business.com.file.service;

import java.util.List;
import java.util.Map;

import business.com.relief.service.ReliefVO;
import business.com.support.service.LwstVO;
import business.sys.user.service.InfoIntrlckVO;
import commf.paging.PaginatedArrayList;
import common.file.FileInfo;

/**
 * [서비스인터페이스] - 신청첨부파일 Service Interface
 *
 * @class   : AplyFileService
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface AplyFileService {

    /**
     * 신청첨부파일 페이징목록 조회
     */
    public PaginatedArrayList listAplyFile(AplyFileVO aplyFileVO, int currPage, int pageSize) throws Exception;

    /**
     * 신청첨부파일 목록조회
     */
    public List listAplyFile(AplyFileVO aplyFileVO) throws Exception;

    /**
     * 신청첨부파일 상세조회
     */
    public AplyFileVO viewAplyFile(AplyFileVO aplyFileVO) throws Exception;

    /**
     * 2021.12.10 CSLEE
     * 다중 신청첨부파일 상세 조회
     */
    public List viewAplyFiles(AplyFileVO aplyFileVO) throws Exception;

    /**
     * 신청첨부파일 수정
     */
    public int updtAplyFile(AplyFileVO aplyFileVO) throws Exception;

    /**
     * 신청첨부파일 등록,수정,삭제
     */
    public String saveAplyFile(AplyFileVO aplyFileVO) throws Exception;

    /**
     * 2021.10.08 LSH
     * 서류기준 신청파일 조회
     */
    public List listAplyFileByPape(Map paramMap) throws Exception;

    /**
     * 2021.10.08 LSH
     * 신청파일 임시저장처리
     */
    public AplyFileVO saveTempFile(FileInfo fileInfo) throws Exception;

    /**
     * 2021.10.12 LSH
     * 구제급여 신청파일 실제저장처리
     */
    public int saveReliefFile(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.12.11 LSH
     * 정보연동 신청파일 실제저장처리
     */
    public int saveIntrlckFile(InfoIntrlckVO infoIntrlckVO) throws Exception;

    /**
     * 2021.12.11 LSH
     * 취약계층소송지원 신청파일 실제저장처리
     */
    public int saveLwstFile(LwstVO lwstVO) throws Exception;

    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    public PaginatedArrayList listAplySubmitFile(AplyFileVO aplyFileVO, int currPage, int pageSize) throws Exception;
    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류목록조회
     */
    public List listAplySubmitFile(AplyFileVO aplyFileVO) throws Exception;
    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류 상세 조회
     */
    public AplyFileVO viewAplySubmitFile(String sn) throws Exception;
    /**
     * 2022.01.11 LSH
     * 일정시간이 지난 임시저장된 신청파일 삭제
     */
    public int deltAplyTempFiles(int diffDay) throws Exception;

    /**
     * 2022.12.06 LSH
     * 구제급여신청 관리자 제출서류 추가등록 처리 (단일파일)
     * @return 저장한 파일번호
     */
    public String saveReliefAddfile(ReliefVO reliefVO, FileInfo fileInfo) throws Exception;    

    /**
     * 2023.01.27 LSH
     * 취약계층소송지원신청 관리자 제출서류 추가등록 처리 (단일파일)
     * @return 저장한 파일번호
     */
    public String saveLwstAddfile(LwstVO lwstVO, FileInfo fileInfo) throws Exception;    
}