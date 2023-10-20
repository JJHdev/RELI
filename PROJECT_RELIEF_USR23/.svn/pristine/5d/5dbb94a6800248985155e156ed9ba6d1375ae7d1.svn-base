package business.com.file.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;
import common.file.FileDirectory;

/**
 * [서비스인터페이스] - 피해조사첨부파일 Service Interface
 * 
 * @class   : ExmnFileService
 * @author  : LSH
 * @since   : 2021.11.23
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface ExmnFileService {

    /**
     * 피해조사첨부파일 목록조회
     */
    public List listExmnFile(ExmnFileVO exmnFileVO) throws Exception;

    /**
     * 피해조사첨부파일 상세조회
     */
    public ExmnFileVO viewExmnFile(ExmnFileVO exmnFileVO) throws Exception;

    /**
     * 피해조사첨부파일 등록
     */
    public int regiExmnFile(ExmnFileVO exmnFileVO) throws Exception;
    /**
     * 피해조사첨부파일 수정
     */
    public int updtExmnFile(ExmnFileVO exmnFileVO) throws Exception;
    /**
     * 피해조사첨부파일 삭제
     */
    public int deltExmnFile(ExmnFileVO exmnFileVO) throws Exception;
    /**
     * 피해조사첨부파일 다중저장처리
     */
    public int saveExmnFile(List<ExmnFileVO> files, FileDirectory d) throws Exception;
}