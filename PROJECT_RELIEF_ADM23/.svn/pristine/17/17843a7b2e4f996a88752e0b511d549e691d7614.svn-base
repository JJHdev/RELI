package business.adm.bbs.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.bbs.service.BbsFileVO;
import business.com.bbs.service.BbsService;
import business.com.bbs.service.BbsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 게시판 관리 Controller - [게시판] 공지사항 - [게시판] 질의응답 - [자료실] 법ㆍ규정관리 -
 * [자료실] 연구보고서관리 - [자료실] 위원회관리
 *
 * @class : BbsController
 * @author : 김주호
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */
@Controller
@SuppressWarnings({ "all" })
public class BbsController extends BaseController {

	@Resource(name = "BbsService")
	protected BbsService bbsService;

    @Resource(name="fileManager")
    protected FileManager fileManager;

	/**
	 * [게시판] 관리자 공지사항 화면
	 */
	@RequestMapping("/adm/bbs/listNotice.do")
	public String listNotice(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_NOTICE);

		int page = CommUtils.strToInt(bbsVO.getPage(), 1);

		// 2021.10.29 게시판 공통 목록 검색으로 통일
		List list = bbsService.listBbs(bbsVO, page, basePageSize);

		model.addAttribute("model", bbsVO);
		model.addAttribute("rows", list);
		model.addAttribute("startNo", ((PaginatedArrayList) list).getStartNo()); // 순서
		model.addAttribute("startRevNo", ((PaginatedArrayList) list).getStartRevNo());
		// 전체건수
		model.addAttribute("totalSize", ((PaginatedArrayList) list).getTotalSize());

		return "adm/bbs/listNotice";
	}

	/**
	 * [게시판] 공지사항 상세보기
	 */
	@RequestMapping("/adm/bbs/viewNotice.do")
	public String viewNotice(HttpServletRequest request,HttpServletResponse response, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 조회수
		// chkinqCnt(data.getNttNo(), request, response);
		bbsService.inqCntUp(data.getNttNo());
		// 리턴 (ModelMap)
		model.addAttribute("model", data);

		return "adm/bbs/viewNotice";
	}


	/**
	 * [게시판] 공지사항 수정표출 화면
	 */
	@RequestMapping("/adm/bbs/modifyNotice.do")
	public String modifyNotice(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 수정모드
		data.setMode(CommConst.UPDATE);
		// 폼데이터 정의
		model.addAttribute("form", data);
		return "adm/bbs/modifyNotice";
	}
	/**
	 * [게시판] 공지사항 등록표출 화면
	 */
	@RequestMapping("/adm/bbs/writeNotice.do")
	public String writeNotice(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 등록모드
		bbsVO.setMode(CommConst.INSERT);
		// 작성자명을 "관리자"로 DEFAULT 정의
		bbsVO.setRgtrNm(CommConst.ADMIN_NAME);
		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_NOTICE);
		// 폼데이터 정의
		model.addAttribute("form", bbsVO);
		return "adm/bbs/writeNotice";
	}


	/**
	 * [게시판] 질의응답 화면
	 */
	@RequestMapping("/adm/bbs/listBbs.do")
	public String listBbs(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		// 게시판구분 값이 없을 때 기본값 : 질의 응답
		if( CommUtils.isEmpty(bbsVO.getBbsSeCd()) ) {
		    // 게시판구분 정의 (질의응답)
		    bbsVO.setBbsSeCd(CommConst.BBS_QNA);
		}

		model.addAttribute("model", bbsVO);

		return "adm/bbs/listBbs";
	}

	/**
	 * [게시판] 질의응답 상세보기
	 *
	 */
	@RequestMapping("/adm/bbs/viewQna.do")
	public String viewBbs(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		Map paramMap = getParameterMap(request, true);
		// 게시판구분 정의 (질의응답)
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);
		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO bbsQ = bbsService.viewBbs(bbsVO);
		BbsVO bbsA = new BbsVO();

		bbsQ.setNttSj("[" + bbsQ.getNttClCd()+"] "+bbsQ.getNttSj());
		if ( CommConst.isAdminRole(bbsVO.getUserInfo().getRoleId())) {

			model.addAttribute("question", bbsQ);

			if (bbsQ.getStatus() != null) {
				bbsA.setUpNttNo(bbsQ.getNttNo());
				bbsA = bbsService.viewBbsN(bbsA);
				model.addAttribute("answer", bbsA);

			} else {

			}

			return "adm/bbs/viewQna";

		} else {

			throw new EgovBizException(message.getMessage("error.comm.notAccess"));

		}
	}

	/**
	 * [게시판] 질의응답 수정표출 화면
	 */
	@RequestMapping("/adm/bbs/writeQna.do")
	public String writeQna(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 수정모드
		data.setMode(CommConst.UPDATE);
		// 폼데이터 정의
		BbsVO bbsQ = bbsService.viewBbs(bbsVO);
		BbsVO bbsA = new BbsVO();


		bbsQ.setNttSj("[" + bbsQ.getNttClCd()+"] "+bbsQ.getNttSj());
		if ( CommConst.isAdminRole(bbsVO.getUserInfo().getRoleId())) {



			if (bbsQ.getStatus() != null) {
				bbsA.setUpNttNo(bbsQ.getNttNo());
				bbsA = bbsService.viewBbsN(bbsA);
				model.addAttribute("question", bbsQ);
				model.addAttribute("answer", bbsA);

			} else {

				bbsA.setUpNttNo(bbsQ.getNttNo());
				bbsA.setBbsSeCd(CommConst.BBS_QNA);
				bbsA.setNttNo(bbsQ.getNttNo());
				bbsA.setNttCn("");
				model.addAttribute("question", bbsQ);
				model.addAttribute("answer", bbsA);


			}

			return "adm/bbs/writeQna";

		} else {

			throw new EgovBizException(message.getMessage("error.comm.notAccess"));

		}
	}

	/**
	 * [게시판] QNA 답변 저장
	 */
	@RequestMapping("/adm/bbs/saveAnswer.do")
	@ResponseBody
	public Map saveAnswer(
			HttpServletRequest request,
			ModelMap model,
			@ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 세션 사용자번호 맵핑
		bbsVO.setGsUserNo(bbsVO.getUserInfo().getUserNo());
		bbsVO.setMdfrnNo (bbsVO.getUserInfo().getUserNo());
		bbsVO.setRgtrNo  (bbsVO.getUserInfo().getUserNo());
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);


	    // 게시글을 등록/수정한다.
	    bbsService.saveAnswer(bbsVO);

		return getSuccess();
	}

	/**
	 * [게시판] 질의응답 QNA 삭제
	 */
	@RequestMapping("/adm/bbs/deleteQna.do")
	@ResponseBody
	public Map deleteQna(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		// 세션 사용자번호 맵핑
		  bbsVO.setGsUserNo(bbsVO.getUserInfo().getUserNo());
		  bbsVO.setMdfrnNo(bbsVO.getUserInfo().getUserNo());
		  bbsVO.setRgtrNo(bbsVO.getUserInfo().getUserNo());
		  if(bbsVO.getStatus()=="null"|| bbsVO.getStatus().equals("null")|| bbsVO.getStatus() == "") {
			// 게시글을 삭제한다
			  bbsService.deleteBbs(bbsVO);
		  }else {
			// 게시글을 삭제한다
			  bbsService.deleteBbs(bbsVO);
			  bbsService.deleteQna(bbsVO);
		  }


		return getSuccess();
	}

	/**
	 * [게시판] 질의응답 ajax 리스트 조회
	 */
	@RequestMapping("/adm/bbs/listBbsJson.do")
	@ResponseBody
	public Map listBbsJson(HttpServletRequest request, @ModelAttribute BbsVO bbsVO) throws Exception {

		// 페이징정보를 가져오기 위한 조건맵
		Map paramMap = getParameterMap(request, true);
		// 세션사용자정보를 모델객체에 담는다
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 화면에서 넘어온 페이지번호 (없으면 디폴트 1)
		int page = CommUtils.getInt(paramMap.get("page"), 1);
		// 화면에서 넘어온 화면출력수 (없으면 디폴트 10)
		int size = CommUtils.getInt(paramMap.get("rows"), basePageSize);
		// 페이징으로 검색한다.
		List list = bbsService.listBbs(bbsVO, page, size);
		list =	bbsListComfirm(list);
		// 페이징정보가 담긴 데이터맵을 반환한다.
		// (BaseController에 정의되어 있음)
		return getPaginatedResult(list);
	}

	/**
	 * [게시판] 2021.12.09 질의응답  말머리
	 */
	public List getprolog(List<BbsVO> list) {


		for(int i = 0; i<list.size(); i++) {
		    // 말머리 처리
			if(list.get(i).getNttClCd() != null && !"".equals(list.get(i).getNttClCd())) {
				String totNttSj = "[" + list.get(i).getNttClCd() + "] "  + list.get(i).getNttSj();
				list.get(i).setNttSj(totNttSj);
			}


		}
		return list;
	}

	public List bbsListComfirm(List<BbsVO> list) {


		for(int i = 0; i<list.size(); i++) {
			// 마스킹처리
			String maskName = maskName(list.get(i).getRgtrNm());
			list.get(i).setRgtrNm(maskName);

		    // 말머리 처리
			if(list.get(i).getNttClCd() != null && !"".equals(list.get(i).getNttClCd())) {
				String totNttSj = "[" + list.get(i).getNttClCd() + "] "  + list.get(i).getNttSj();
				list.get(i).setNttSj(totNttSj);
			}


		}
		return list;
	}
	/**
	 * [게시판] 2021.12.08 질의응답  사용자 이름 마스킹 처리
	 */
	public String maskName(String name) {

		if(name != null && !"".equals(name)) {

			String middleMask = "";

			if(name.length() > 2) {
				middleMask  = name.substring(1, name.length()-1);
			}else {
				middleMask  = name.substring(1, name.length());
			}

			String masking ="";

			for(int i =0; i<middleMask.length(); i++) {
				masking += "*";
			}

			if(name.length() >2 ) {

				name = name.replace(middleMask, masking);
			}else {
				name = name.replace(middleMask, masking);
			}

		}


		return name;
	}



	/**
	 * [게시판] 자주하는 질문 상세보기
	 */
	@RequestMapping("/adm/bbs/viewFaq.do")
	public String viewFaq(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 리턴 (ModelMap)
		model.addAttribute("model", data);

		return "adm/bbs/viewFaq";
	}

	/**
	 * [게시판] 자주하는 질문 수정 표출 화면
	 */
	@RequestMapping("/adm/bbs/modifyFaq.do")
	public String modifyFaq(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 수정모드
		data.setMode(CommConst.UPDATE);
		// 폼데이터 정의
		model.addAttribute("form", data);
		return "adm/bbs/modifyFaq";
	}

	/**
	 * [게시판] 자주하는 질문 등록 표출 화면
	 */
	@RequestMapping("/adm/bbs/writeFaq.do")
	public String writeFaq(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 등록모드
		bbsVO.setMode(CommConst.INSERT);
		// 작성자명을 "관리자"로 DEFAULT 정의
		bbsVO.setRgtrNm(CommConst.ADMIN_NAME);
		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_FAQ);
		// 폼데이터 정의
		model.addAttribute("form", bbsVO);
		return "adm/bbs/writeFaq";
	}

	/**
	 * [게시판] 자주하는 질문 등록
	 */

	@RequestMapping("/adm/bbs/saveFaq.do")
	@ResponseBody
	public Map saveFaq(HttpServletRequest request, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);

		bbsVO.setBbsSeCd(CommConst.BBS_FAQ);
		bbsVO.setRgtrNo(bbsVO.getUserInfo().getUserNo());
		int result = bbsService.qnaSave(bbsVO);

		if (result > 0) {
			return getSuccess();
		}
		return getFailure();

	}



	/**
	 * [자료실] 법ㆍ규정관리 화면
	 */
	@RequestMapping("/adm/bbs/listStatute.do")
	public String listStatute(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO)
			throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		// 게시판구분 정의 (법규정)
		bbsVO.setBbsSeCd(CommConst.BBS_STATUTE);

		bbsVO.setPage(CommUtils.nvlTrim(bbsVO.getPage(), "1"));
		bbsVO.setUserId(CommUtils.nvlTrim(bbsVO.getUserId(), "test"));
		int page = CommUtils.strToInt(bbsVO.getPage(), 1);

		// 2021.10.29 게시판 공통 목록 검색으로 통일
		List list = bbsService.listBbs(bbsVO, page, basePageSize);
		// List list = bbsService.listStatute(bbsVO, page, basePageSize);

		model.addAttribute("model", bbsVO);
		model.addAttribute("rows", list);
		model.addAttribute("startNo", ((PaginatedArrayList) list).getStartNo());
		model.addAttribute("startRevNo", ((PaginatedArrayList) list).getStartRevNo());
		model.addAttribute("totalSize", ((PaginatedArrayList) list).getTotalSize());


		return "adm/bbs/listStatute";
	}

	/**
	 * [자료실] 법규정목록 상세화면
	 */
	@RequestMapping("/adm/bbs/viewStatute.do")
	public String viewStatute(HttpServletRequest request,HttpServletResponse response, ModelMap model, @ModelAttribute BbsVO bbsVO)
			throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		Map paramMap = getParameterMap(request, true);

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 조회수
		// chkinqCnt(data.getNttNo(), request, response);
		bbsService.inqCntUp(data.getNttNo());
		// 리턴 (ModelMap)
		model.addAttribute("model", data);

		return "adm/bbs/viewStatute";
	}

	/**
	 * [게시판] 법규정목록 수정표출 화면
	 */
	@RequestMapping("/adm/bbs/modifyStatute.do")
	public String modifyStatute(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 수정모드
		data.setMode(CommConst.UPDATE);
		// 폼데이터 정의
		model.addAttribute("form", data);
		return "adm/bbs/modifyStatute";
	}
	/**
	 * [게시판] 법규정목록 등록표출 화면
	 */
	@RequestMapping("/adm/bbs/writeStatute.do")
	public String writeStatute(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 등록모드
		bbsVO.setMode(CommConst.INSERT);
		// 작성자명을 "관리자"로 DEFAULT 정의
		bbsVO.setRgtrNm(CommConst.ADMIN_NAME);
		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_STATUTE);
		// 폼데이터 정의
		model.addAttribute("form", bbsVO);
		return "adm/bbs/writeStatute";
	}


	/**
	 * [자료실] 연구보고서관리 화면
	 */
	@RequestMapping("/adm/bbs/listResearch.do")
	public String listResearch(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO)
			throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_RESEARCH);

		int page = CommUtils.strToInt(bbsVO.getPage(), 1);

		// 2021.10.29 게시판 공통 목록 검색으로 통일
		List list = bbsService.listBbs(bbsVO, page, basePageSize);

		model.addAttribute("model", bbsVO);
		model.addAttribute("rows", list);
		model.addAttribute("startNo", ((PaginatedArrayList) list).getStartNo()); // 순서
		model.addAttribute("startRevNo", ((PaginatedArrayList) list).getStartRevNo());
		model.addAttribute("totalSize", ((PaginatedArrayList) list).getTotalSize());

		return "adm/bbs/listResearch";
	}
	/**
	 * [자료실] 연구보고서 상세화면
	 */
	@RequestMapping("/adm/bbs/viewResearch.do")
	public String viewResearch(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute BbsVO bbsVO)
			throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		Map paramMap = getParameterMap(request, true);

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 조회수
		// chkinqCnt(data.getNttNo(), request, response);
		bbsService.inqCntUp(data.getNttNo());
		// 리턴 (ModelMap)
		model.addAttribute("model", data);


		return "adm/bbs/viewResearch";
	}

	/**
	 *
	 * [게시판] 연구보고서 수정표출 화면
	 */
	@RequestMapping("/adm/bbs/modifyResearch.do")
	public String modifyResearch(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 수정모드
		data.setMode(CommConst.UPDATE);
		// 폼데이터 정의
		model.addAttribute("form", data);
		return "adm/bbs/modifyResearch";
	}

	/**
	 * [게시판] 연구보고서 등록표출 화면
	 */
	@RequestMapping("/adm/bbs/writeResearch.do")
	public String writeResearch(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 등록모드
		bbsVO.setMode(CommConst.INSERT);
		// 작성자명을 "관리자"로 DEFAULT 정의
		bbsVO.setRgtrNm(CommConst.ADMIN_NAME);
		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_RESEARCH);
		// 폼데이터 정의
		model.addAttribute("form", bbsVO);

		return "adm/bbs/writeResearch";
	}
	/**
	 * [자료실] 위원회관리 화면
	 */
	@RequestMapping("/adm/bbs/listCommit.do")
	public String listCommit(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_COMMIT);

		int page = CommUtils.strToInt(bbsVO.getPage(), 1);

		// 2021.10.29 게시판 공통 목록 검색으로 통일
		List list = bbsService.listBbs(bbsVO, page, basePageSize);

		model.addAttribute("model", bbsVO);
		model.addAttribute("rows", list);
		model.addAttribute("startNo", ((PaginatedArrayList) list).getStartNo()); // 순서
		model.addAttribute("startRevNo", ((PaginatedArrayList) list).getStartRevNo());
		model.addAttribute("totalSize", ((PaginatedArrayList) list).getTotalSize());

		return "adm/bbs/listCommit";
	}
	/**
	 * [자료실] 위원회 상세화면
	 */
	@RequestMapping("/adm/bbs/viewCommit.do")
	public String viewCommit(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute BbsVO bbsVO)
			throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		Map paramMap = getParameterMap(request, true);

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 조회수
		// chkinqCnt(data.getNttNo(), request, response);
		bbsService.inqCntUp(data.getNttNo());
		// 리턴 (ModelMap)
		model.addAttribute("model", data);


		return "adm/bbs/viewCommit";
	}

	@RequestMapping("/adm/bbs/modifyCommit.do")
	public String modifyCommit(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 수정모드
		data.setMode(CommConst.UPDATE);
		// 폼데이터 정의
		model.addAttribute("form", data);
		return "adm/bbs/modifyCommit";
	}

	/**
	 * [게시판] 위원회 등록표출 화면
	 */
	@RequestMapping("/adm/bbs/writeCommit.do")
	public String writeCommit(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 등록모드
		bbsVO.setMode(CommConst.INSERT);
		// 작성자명을 "관리자"로 DEFAULT 정의
		bbsVO.setRgtrNm(CommConst.ADMIN_NAME);
		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_COMMIT);
		// 폼데이터 정의
		model.addAttribute("form", bbsVO);

		return "adm/bbs/writeCommit";
	}
//==========================================================++
//                         공통코드
//==========================================================++
	/**
	 * [게시판] 공통 게시판 수정
	 */
	@RequestMapping("/adm/bbs/updateBbs.do")
	@ResponseBody
	public Map updateBbs(HttpServletRequest request, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		bbsVO.setMdfrnNo(bbsVO.getUserInfo().getUserNo());
		int result = bbsService.updateBbs(bbsVO);

		if (result > 0) {
			return getSuccess();
		}

		return getFailure();
	}
	/**
	 * [게시판] 게시물 저장
	 */
	@RequestMapping("/adm/bbs/saveBbs.do")
	@ResponseBody
	public Map saveBbs(
			HttpServletRequest request,
			ModelMap model,
			@ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		// 세션 사용자번호 맵핑
		bbsVO.setGsUserNo(bbsVO.getUserInfo().getUserNo());
		bbsVO.setMdfrnNo (bbsVO.getUserInfo().getUserNo());
		bbsVO.setRgtrNo  (bbsVO.getUserInfo().getUserNo());

        // 다중파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    bbsVO.setFiles(files);

	    // 게시글을 등록/수정한다.
	    bbsService.saveBbs(bbsVO);

		return getSuccess();
	}

	/**
	 * [게시판] 공통 게시판 삭제
	 */
	@RequestMapping("/adm/bbs/deleteBbs.do")
	@ResponseBody
	public Map deleteBbs(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 세션 사용자번호 맵핑
		bbsVO.setGsUserNo(bbsVO.getUserInfo().getUserNo());
		bbsVO.setMdfrnNo (bbsVO.getUserInfo().getUserNo());
		bbsVO.setRgtrNo  (bbsVO.getUserInfo().getUserNo());

	    // 게시글을 삭제한다
	    bbsService.deleteBbs(bbsVO);

		return getSuccess();
	}
    /**
     * 게시글 첨부파일 목록조회 JSON 반환
     */
    @RequestMapping("/adm/bbs/listBbsFile.do")
    @ResponseBody
    public Map listBbsFile(HttpServletRequest request
    		, @RequestParam Map<String,String> reqMap
            , @ModelAttribute BbsFileVO bbsFileVO
            , ModelMap model) throws Exception {

        // -------------------- Default Setting End -----------------------//
        Map paramMap = getParameterMap(request, true);
        // 게시글의 첨부파일 목록조회
        List list = bbsService.listBbsFile(bbsFileVO);

        return getSuccess("rows", list);
    }

    /**
     * 2021.07.22
     * 첨부파일 다운로드
     * 임시생성함
     * 추후엔 공통영역으로 변경되어야할 항목임
     */
    @RequestMapping("/adm/bbs/downBbsFile.do")
    public void downBbsFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute BbsFileVO bbsFileVO
    		) throws Exception {
        // -------------------- Default Setting Start ---------------------//
        setGlobalSession(bbsFileVO);
        // -------------------- Default Setting End -----------------------//


        // 다운로드할 파일 정보 조회
        BbsFileVO data = bbsService.viewBbsFile(bbsFileVO);

        // 파일문서타입
        FileDirectory fdir = FileDirectory.BOARD;

        // 파일 다운로드 공통함수 사용
        FileInfo fileInfo = FileInfo.builder()
							.fullPath(fdir.getRealPath(data.getFilePath()))
							.filePath(data.getFilePath())
							.saveName(data.getSaveName())
							.fileName(data.getFileName())
							.build();

        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }

    /**
     * 2021.07.22
     * 첨부파일 단일파일 삭제처리
     */
    @RequestMapping("/adm/bbs/deleteBbsFile.do")
    @ResponseBody
    public Map deleteBbsFile(HttpServletRequest request,
    		@ModelAttribute BbsFileVO bbsFileVO
    		) throws Exception {

        setGlobalSession(bbsFileVO);
        // -------------------- Default Setting End -----------------------//

        bbsFileVO.setGsUserNo(bbsFileVO.getUserInfo().getUserNo());

    	// 첨부파일 KEY가 없을 경우
    	if (CommUtils.isEmpty(bbsFileVO.getDcmtNo()))
    		throw new EgovBizException(message.getMessage("exception.notKey"));

	    // 첨부파일을 삭제한다.
	    bbsService.deleteBbsFile(bbsFileVO);

	    return getSuccess();
    }

    /**
     * 2021.08.03
     * 첨부파일 압축 다운로드
     * 임시생성함
     * 추후엔 공통영역으로 변경되어야할 항목임
     */
    @RequestMapping("/adm/bbs/downBbsFileZip.do")
    public void downBbsFileZip(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute BbsFileVO bbsFileVO
    		) throws Exception {

    	// -------------------- Default Setting Start ---------------------//
        setGlobalSession(bbsFileVO);
        // -------------------- Default Setting End -----------------------//

        // 파일타입
        FileDirectory fd = FileDirectory.BOARD;

        // 다운로드할 파일 목록 조회
        List<BbsFileVO> list = bbsService.listBbsFile(bbsFileVO);

        if (list == null ||
        	list.size() == 0)
        	throw new EgovBizException("다운로드할 첨부파일이 없습니다.");

        List<FileInfo> files = new ArrayList<FileInfo>();

        for (BbsFileVO vo : list) {

            // 파일 다운로드 공통함수 사용
        	FileInfo fileInfo = FileInfo.builder()
            		.saveName(vo.getSaveName())
            		.fileName(vo.getFileName())
            		.filePath(vo.getFilePath())
            		.fullPath(fd.getRealPath(vo.getFilePath()))
            		.fileType(fd.getType())
            		.build();
        	files.add(fileInfo);
        }
        // 실제 파일 압축 다운로드 처리
        fileManager.procFileZipDownload(request, response, files, fd.getType());
    }


//  	/**
//  	 * 2021.11.13  게시판 조회수 중복 방지
//  	 * @throws Exception
//  	 */
//  	public void chkinqCnt(int nttNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
//  		  Cookie oldCookie = null;
//  		    Cookie[] cookies = request.getCookies();
//  		    if (cookies != null) {
//  		        for (Cookie cookie : cookies) {
//  		            if (cookie.getName().equals("postView")) {
//  		                oldCookie = cookie;
//  		            }
//  		        }
//  		    }
//
//  		    if (oldCookie != null) {
//  		        if (!oldCookie.getValue().contains("[" + nttNo + "]")) {
//  		        	bbsService.inqCntUp(nttNo);
//  		            oldCookie.setValue(oldCookie.getValue() + "_[" + nttNo + "]");
//  		            oldCookie.setPath("/");
//  		            oldCookie.setMaxAge(60 * 60 * 24);
//  		            response.addCookie(oldCookie);
//  		        }
//  		    } else {
//  		    	bbsService.inqCntUp(nttNo);
//  		        Cookie newCookie = new Cookie("postView","[" + nttNo + "]");
//  		        newCookie.setPath("/");
//  		        newCookie.setMaxAge(60 * 60 * 24);
//  		        response.addCookie(newCookie);
//  		    }
//  	}

}
