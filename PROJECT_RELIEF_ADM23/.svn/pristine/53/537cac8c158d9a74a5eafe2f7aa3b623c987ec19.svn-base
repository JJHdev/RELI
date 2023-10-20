/*****************************   컬럼 추가 오류 시   *******************************/
-- 조회
SELECT OBJECT_ID 
  FROM DBA_OBJECTS 
 WHERE OBJECT_NAME = 'TB_MNG_HST';

-- 업데이트
UPDATE SYS._DD_TBL 
   SET ROW_CNT = 0 
 WHERE OBJ_ID IN (SELECT OBJECT_ID 
                    FROM DBA_OBJECTS 
                   WHERE OBJECT_NAME = 'TB_MNG_HST' );
/**********************************************************************************/