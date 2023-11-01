 package business.com.file.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.file.service.CmitFileVO;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 위원회첨부파일 관리하는 DAO 클래스
 * 
 * 사용 가능한  DAO Statement Method
 * 1. list          : 리스트 조회시 사용함.
 * 2. pageListOra   : 페이징처리용 리스트조회시 사용함. for Oracle, Tibero
 * 3. view          : 단건조회, 상세조회시 사용함.
 * 4. save          : INSERT, UPDATE, DELETE 모두 사용가능. (Return Type : Integer)
 * 5. insert        : INSERT (Return String : Key 채번 사용함.)
 * 6. update        : UPDATE (Return Type : Integer)
 * 7. delete        : DELETE (Return Type : Integer)
 * 
 *
 * @class   : CmitFileDAO
 * @author  : LSH
 * @since   : 2023.10.26
 * @version : 3.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("CmitFileDAO")
@SuppressWarnings({"all"})
public class CmitFileDAO extends BaseDAO {

    /**
     * 2023.10.26 LSH
     * 위원회첨부파일 목록 조회
     */
    public List listCmitFile(CmitFileVO cmitFileVO) {
        return list("CmitFile.listCmitFile", cmitFileVO);
    }

    /**
     * 2023.10.26 LSH
     * 위원회첨부파일 상세 조회
     */
    public CmitFileVO viewCmitFile(CmitFileVO cmitFileVO) {
        return (CmitFileVO)view("CmitFile.viewCmitFile", cmitFileVO);
    }

    /**
     * 위원회첨부파일 등록
     */
    public int regiCmitFile(CmitFileVO cmitFileVO) {
        return insert("CmitFile.regiCmitFile", cmitFileVO);
    }

    /**
     * 위원회첨부파일 수정
     */
    public int updtCmitFile(CmitFileVO cmitFileVO) {
        return update("CmitFile.updtCmitFile", cmitFileVO);
    }

    /**
     * 위원회첨부파일 삭제
     */
    public int deltCmitFile(CmitFileVO cmitFileVO) {
        return delete("CmitFile.deltCmitFile", cmitFileVO);
    }

}