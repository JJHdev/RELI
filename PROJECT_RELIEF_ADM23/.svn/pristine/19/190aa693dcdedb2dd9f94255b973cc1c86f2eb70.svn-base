 package business.bio.relief.service.impl;

import org.springframework.stereotype.Repository;

import business.bio.relief.service.BioIdntfcVO;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 살생물제품 피해자정보를 관리하는 DAO 클래스
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
 * @class   : BioIdntfcDAO
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("BioIdntfcDAO")
@SuppressWarnings({"all"})
public class BioIdntfcDAO extends BaseDAO {

    /**
     * 피해자정보 상세 조회
     */
    public BioIdntfcVO viewBioIdntfc(String sufrerNo) {
        return (BioIdntfcVO)view("BioIdntfc.viewBioIdntfc", sufrerNo);
    }

    /**
     * 피해자정보 등록
     */
    public int regiBioIdntfc(BioIdntfcVO bioIdntfcVO) {
        return insert("BioIdntfc.regiBioIdntfc", bioIdntfcVO);
    }

    /**
     * 2021.10.14 LSH
     * 피해자정보 이력저장
     */
    public int regiBioIdntfcHst(BioIdntfcVO bioIdntfcVO) {
        return insert("BioIdntfc.regiBioIdntfcHst", bioIdntfcVO);
    }

    /**
     * 피해자정보 수정
     */
    public int updtBioIdntfc(BioIdntfcVO bioIdntfcVO) {
        return update("BioIdntfc.updtBioIdntfc", bioIdntfcVO);
    }

    /**
     * 2021.10.19 LSH
     * 성명/생년월일 기준 피해자정보 KEY 조회
     */
    public String getBioIdntfcExistNo(BioIdntfcVO bioIdntfcVO) {
        return (String)view("BioIdntfc.getBioIdntfcExistNo", bioIdntfcVO);
    }

    /**
     * 2021.11.01 LSH
     * 식별ID 신규생성
     */
    public String getCreateBioIdntfcId() {
        return (String)view("BioIdntfc.getCreateBioIdntfcId", null);
    }
}