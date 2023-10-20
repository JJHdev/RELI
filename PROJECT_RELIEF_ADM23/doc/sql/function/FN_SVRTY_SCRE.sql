/**
 *  2023/02/01 운영서버 반영 완료
 */
/*
CREATE OR REPLACE FUNCTION FN_SVRTY_SCRE (
    /**
     * =============================================================
     * title    : 구분자(|)로  받은 점수를 순차 계산하여 중증도 점수 산정 
     * author   : ntarget
     * date     : 2022.12.13
     * tables   : DUAL
     * -------------------------------------------------------------
     * @param   :    vi_scre    중증도점수 ('62.5|31.25|43.75|56.25|37.5')
     *          : ∑(질환별 점수)=D1+[(100-D1)/100]×{(1/2×D2)+(1/4×D3)+(1/8×D4)+(1/16×D5)+……+(1/2n-1×Dn)}]
     *                          = 62.5+(0.375)×{(56.25/2)+(43.75/4)+(37.5/8)+(31.25/16)} 
     *                          = 79.66 ⇒ 2등급 
     * =============================================================
     */
    vi_scre            VARCHAR2
)
RETURN VARCHAR2
IS    
    num_error       VARCHAR2(1);
    vo_scre         VARCHAR2(100);
    v_srce0         NUMBER;         -- D1 값
    v_srce1         NUMBER;         -- D1 산정값
    v_srce2         NUMBER;         -- D2 ~ Dn 산정 합산값
    v_seq           NUMBER;         -- 순서
    v_ratio         NUMBER;         -- 등비

    -- CURSOR 선언
    CURSOR list_cursor IS
         SELECT REGEXP_SUBSTR(A.SCRE_LIST, '[^|]+', 1, LEVEL) AS SCRE_VAL
           FROM (
                SELECT vi_scre AS SCRE_LIST
                  FROM DUAL 
                ) A
        CONNECT BY LEVEL <= LENGTH(REGEXP_REPLACE(A.SCRE_LIST, '[^|]+','')) + 1
          ORDER BY SCRE_VAL DESC;

BEGIN
    num_error   := 'N';
    vo_scre     := null;
    v_srce0     := 0;
    v_srce1     := 0;
    v_srce2     := 0;
    v_seq       := 0;
    v_ratio     := 1;
    
    -- Cursor를 FOR문에서 실행시킨다
    FOR data_list IN list_cursor LOOP
        DBMS_OUTPUT.PUT_LINE('SCRE_VAL : ' || data_list.SCRE_VAL);
        
        IF REGEXP_INSTR(data_list.SCRE_VAL,'^[+-]?\d*(\.?\d*)$') = 0 THEN
            num_error   := 'Y';
            EXIT;
        END IF;
        
        v_seq := v_seq + 1;
        
        BEGIN
            IF v_seq = 1 THEN
                v_srce0 := TO_NUMBER(data_list.SCRE_VAL);
                v_srce1 := (100 - TO_NUMBER(data_list.SCRE_VAL)) / 100;
            ELSE
                v_ratio := v_ratio * 2;  
                v_srce2 := v_srce2 + ((1/v_ratio) * TO_NUMBER(data_list.SCRE_VAL));
            END IF;
        EXCEPTION
        WHEN OTHERS THEN 
            NULL;
        END;
        
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('v_srce values : ' || v_srce0 || ', '||  v_srce1 || ', '|| v_srce2);
    
    IF num_error = 'Y' THEN
        vo_scre :=  null;   
    ELSE
        vo_scre := ROUND(v_srce0 + (v_srce1 * v_srce2), 2);
    END IF;
    
    RETURN vo_scre;

END FN_SVRTY_SCRE;
*/