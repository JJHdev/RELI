-- 2023.10.24 공통코드 등록
INSERT INTO SYS_CODE 
 (USE_YN, RGTR_NO, REG_YMD, CD_ORDR, UP_CD_ID, CD_ID, CD_NM) VALUES 
 ('Y','system',SYSDATE, 0,  'NONE', 'CT049', '위원회진행상태')
,('Y','system',SYSDATE, 1, 'CT049',  'CM01', '위원회개최')
,('Y','system',SYSDATE, 2, 'CT049',  'CM02', '의견서작성')
,('Y','system',SYSDATE, 3, 'CT049',  'CM03', '의결서작성')
,('Y','system',SYSDATE, 4, 'CT049',  'CM04', '수당지작성')
,('Y','system',SYSDATE, 5, 'CT049',  'CM10', '종료')
,('Y','system',SYSDATE, 0,  'NONE', 'CT050', '위원회작성구분')
,('Y','system',SYSDATE, 1, 'CT050',    'WO', '의견')
,('Y','system',SYSDATE, 2, 'CT050',    'WD', '의결')
,('Y','system',SYSDATE, 0,  'NONE', 'CT051', '위원회소득구분')
,('Y','system',SYSDATE, 1, 'CT051',    'IB', '사업소득')
,('Y','system',SYSDATE, 2, 'CT051',    'IE', '기타소득')
,('Y','system',SYSDATE, 0,  'NONE', 'CT052', '위원회의결사항')
,('Y','system',SYSDATE, 1, 'CT052',    'PO', '원안가결')
,('Y','system',SYSDATE, 2, 'CT052',    'PS', '보완가결')
,('Y','system',SYSDATE, 3, 'CT052',    'RJ', '부결')
,('Y','system',SYSDATE, 0,  'NONE', 'CT053', '위원회안건구분')
,('Y','system',SYSDATE, 1, 'CT053',    'AR', '보고안건')
,('Y','system',SYSDATE, 2, 'CT053',    'AD', '심의안건')
;

-- 2023.10.25 10:40 실행완료 RELIEF03
