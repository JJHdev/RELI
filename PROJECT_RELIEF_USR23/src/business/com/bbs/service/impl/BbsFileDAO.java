 package business.com.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.bbs.service.BbsFileVO;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 게시판 첨부파일을 관리하는 DAO 클래스
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
 * @class   : BbsFileDAO
 * @author  : LSH
 * @since   : 2021.11.18
 * @version : 1.0
 *
 *   수정일        수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("BbsFileDAO")
@SuppressWarnings({"all"})
public class BbsFileDAO extends BaseDAO {

    /**
     * 게시판 첨부파일 목록 조회
     */
    public List listBbsFile(BbsFileVO bbsFileVO) {
        return list("BbsFile.listBbsFile", bbsFileVO);
    }

    /**
     * 게시판 첨부파일 상세 조회
     */
    public BbsFileVO viewBbsFile(BbsFileVO bbsFileVO) {
        return (BbsFileVO)view("BbsFile.viewBbsFile", bbsFileVO);
    }

    /**
     * 게시판 첨부파일 등록
     */
    public int regiBbsFile(BbsFileVO bbsFileVO) {
        return insert("BbsFile.regiBbsFile", bbsFileVO);
    }

    /**
     * 게시판 첨부파일 수정
     */
    public int updtBbsFile(BbsFileVO bbsFileVO) {
        return update("BbsFile.updtBbsFile", bbsFileVO);
    }

    /**
     * 게시판 첨부파일 삭제
     */
    public int deltBbsFile(BbsFileVO bbsFileVO) {
        return delete("BbsFile.deltBbsFile", bbsFileVO);
    }

    /**
     *  게시판 첨부파일 전체삭제
     */
    public int deltBbsFileAll(BbsFileVO bbsFileVO) {
        return update("BbsFile.deltBbsFileAll", bbsFileVO);
    }

}