/**
 *  2023/02/20 운영서버 반영 완료
 */
/*
UPDATE TB_BIZ_MNG
   SET LAT = 35.8745852603
     , LOT = 128.70378255
 WHERE BIZ_AREA_CD = 'A0001'
;  

UPDATE TB_BIZ_MNG
   SET LAT = 36.0082125
     , LOT = 126.6717434
 WHERE BIZ_AREA_CD = 'A0002'
;  

UPDATE TB_BIZ_MNG
   SET LAT = 37.66676693
     , LOT = 126.5970379
 WHERE BIZ_AREA_CD = 'A0003'
;  

UPDATE TB_BIZ_ODER
   SET AFFC_SCOPE_DSTNC = 1
     , FRST_POLLUTN_OCRN_YR = '1981'
     , LAST_POLLUTN_OCRN_YR = '2021'
 WHERE BIZ_AREA_CD = 'A0001'
   AND BIZ_ODER = 2
;

UPDATE TB_BIZ_ODER
   SET AFFC_SCOPE_DSTNC = 4
     , FRST_POLLUTN_OCRN_YR = '1936'
     , LAST_POLLUTN_OCRN_YR = '2009'   
 WHERE BIZ_AREA_CD = 'A0002'
   AND BIZ_ODER = 2
;

UPDATE TB_BIZ_ODER
   SET AFFC_SCOPE_DSTNC = 1
     , FRST_POLLUTN_OCRN_YR = '2003'
     , LAST_POLLUTN_OCRN_YR = '2019'   
 WHERE BIZ_AREA_CD = 'A0003'
   AND BIZ_ODER = 2
;

COMMIT;    
  
*/