package business.com.file.service;

import java.util.List;

import common.file.FileDirectory;

/**
 * [서비스인터페이스] - 위원회첨부파일 Service Interface
 * 
 * @class   : CmitFileService
 * @author  : LSH
 * @since   : 2023.10.26
 * @version : 3.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface CmitFileService {

    /**
     * 위원회첨부파일 목록조회
     */
    public List listCmitFile(CmitFileVO cmitFileVO) throws Exception;
    /**
     * 위원회첨부파일 상세조회
     */
    public CmitFileVO viewCmitFile(String sn) throws Exception;
    /**
     * 위원회첨부파일 등록
     */
    public int regiCmitFile(CmitFileVO cmitFileVO) throws Exception;
    /**
     * 위원회첨부파일 수정
     */
    public int updtCmitFile(CmitFileVO cmitFileVO) throws Exception;
    /**
     * 위원회첨부파일 삭제
     */
    public int deltCmitFile(CmitFileVO cmitFileVO) throws Exception;
    /**
     * 위원회첨부파일 다중저장처리
     */
    public int saveCmitFile(List<CmitFileVO> files, FileDirectory d) throws Exception;
}