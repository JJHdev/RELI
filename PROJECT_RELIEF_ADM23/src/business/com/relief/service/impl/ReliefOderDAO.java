 package business.com.relief.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.relief.service.ReliefVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 구제급여 의료비추가신청을 관리하는 DAO 클래스
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
 * @class   : ReliefDAO
 * @author  : LSH
 * @since   : 2021.10.05
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("ReliefOderDAO")
@SuppressWarnings({"all"})
public class ReliefOderDAO extends BaseDAO {

    /**
     * 구제급여신청 페이징목록 조회
     */
    public PaginatedArrayList listReliefOder(ReliefVO reliefVO, int currPage, int pageSize) {
        return pageList("ReliefOder.listReliefOder", reliefVO, currPage, pageSize);
    }

    /**
     * 구제급여신청 목록 조회
     */
    public List listReliefOder(ReliefVO reliefVO) {
        return list("ReliefOder.listReliefOder", reliefVO);
    }

    /**
     * 구제급여신청 상세 조회
     */
    public ReliefVO viewReliefOder(ReliefVO reliefVO) {
        return (ReliefVO)view("ReliefOder.viewReliefOder", reliefVO);
    }

    /**
     * 2021.10.29
     * 구제급여신청차수 등록
     */
    public int regiReliefOder(ReliefVO reliefVO) {
        return insert("ReliefOder.regiReliefOder", reliefVO);
    }

    /**
     * 2021.10.29
     * 구제급여신청차수 수정
     */
    public int updtReliefOder(ReliefVO reliefVO) {
        return update("ReliefOder.updtReliefOder", reliefVO);
    }

    /**
     * 2021.10.29
     * 구제급여신청차수 삭제
     */
    public int deltReliefOder(ReliefVO reliefVO) {
        return delete("ReliefOder.deltReliefOder", reliefVO);
    }

    /**
     * 2021.12.13
     * 의료비 추가신청 최종차수 가져오기
     * 본조사인 경우 접수(04)/본조사(06)건의 최종차수
     * 지급인 경우 본조사(06)/지급(07)건의 최종차수
     */
    public String getReliefLastOder(ReliefVO reliefVO) {
        return (String)view("ReliefOder.getReliefLastOder", reliefVO);
    }
}