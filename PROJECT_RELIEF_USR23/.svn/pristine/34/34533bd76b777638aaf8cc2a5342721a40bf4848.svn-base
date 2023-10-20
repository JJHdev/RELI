package business.usr.mypage.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.bbs.service.BbsService;
import business.com.bbs.service.BbsVO;
import business.com.relief.service.ReliefService;
import business.com.relief.service.ReliefVO;
import business.com.support.service.LwstService;
import business.com.support.service.LwstVO;
import business.sys.user.service.InfoIntrlckService;
import business.sys.user.service.InfoIntrlckVO;
import business.sys.user.service.UserInfoService;
import business.sys.user.service.UserInfoVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseController;
import common.user.UserInfo;
import common.util.CommUtils;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 마이페이지 Controller
 *
 * @class : MypageController
 * @author : LSH
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */
@Controller
@SuppressWarnings({ "all" })
public class MypageController extends BaseController {

	// 사용자정보 Service
	@Resource(name = "UserInfoService")
	protected UserInfoService userInfoService;

	// 정보연동 Service
	@Resource(name = "InfoIntrlckService")
	protected InfoIntrlckService infoIntrlckService;

	// 구제급여 Service
	@Resource(name = "ReliefService")
	protected ReliefService reliefService;

	// 취약계층소송지원 Service
	@Resource(name = "LwstService")
	protected LwstService lwstService;

	// 게시판 Service
	@Resource(name = "BbsService")
	protected BbsService bbsService;

	/**
	 * 마이페이지 - 나의정보 (회원정보수정) 화면
	 */
	@RequestMapping("/usr/mypage/openMypage.do")
	public String openMypage(HttpServletRequest request,
			@ModelAttribute UserInfoVO userInfoVO,
			ModelMap model)
			throws Exception {

		setGlobalSession(userInfoVO);
		String userNo = userInfoVO.getUserInfo().getUserNo();
		model.addAttribute("model", userInfoService.openMypage(userNo));
		return "usr/mypage/openMypage";
	}
	/**
	 * 마이페이지 - 나의정보 (회원정보수정) 처리
	 */
	@RequestMapping("/usr/mypage/updateMyInfo.do")
	@ResponseBody
	public Map updateMyInfo(@ModelAttribute UserInfoVO userInfoVO) throws Exception {

		setGlobalSession(userInfoVO);

		String userNo = userInfoVO.getUserInfo().getUserNo();
		String pswd   = userInfoVO.getPswd();

		if (!CommUtils.isEqual(userInfoVO.getUserNo(),userNo)) {
			return getFailure("입력하신 회원정보가 일치하지 않습니다.");
		}

		// 2022.01.04 CSLEE 추가
		// 비밀번호 변경시 이전 비밀번호와 동일한 비밀번호입력할 수 없게 확인
		if(!CommUtils.isEmpty(pswd)) {
    		Map paramMap = new HashMap();
            paramMap.put("userNo" , userNo);
            paramMap.put("pswd"   , pswd);
            if(userInfoService.checkUserPswdDupl(paramMap)) {
                // 기존과 동일한 비밀번호로 변경할 수 없습니다.
                return getFailure(message.getMessage("error.user.duplPassword"));
            }
		}

        // 수정 처리
		userInfoService.updateMyInfo(userInfoVO);

		return getSuccess();
	}

	/**
	 * 마이페이지 - 정보연동 화면
	 */
	@RequestMapping("/usr/mypage/openInfoIntrlck.do")
	public String openInfoIntrlck(HttpServletRequest request,
			ModelMap model,
			@ModelAttribute InfoIntrlckVO infoIntrlckVO) throws Exception {

		setGlobalSession(infoIntrlckVO);
		infoIntrlckVO.setPapeDtySeCd(CommConst.PAPE_DTY_INTRLCK);
		infoIntrlckVO.setGsUserNo(infoIntrlckVO.getUserInfo().getUserNo());
		infoIntrlckVO.setAplcntNo(infoIntrlckVO.getGsUserNo()); // 신청인번호
		// -------------------- Default Setting End -----------------------/
		model.addAttribute("model", infoIntrlckVO);
		return "usr/mypage/openInfoIntrlck";
	}

	/**
	 * 마이페이지 - 정보연동 - 식별아이디 조회
	 */
	@RequestMapping("/usr/mypage/searchInfoIntrlck.do")
	@ResponseBody
	public Map searchInfoIntrlck(HttpServletRequest request, ModelMap model, @ModelAttribute InfoIntrlckVO infoIntrlckVO)
			throws Exception {

		setGlobalSession(infoIntrlckVO);
		ArrayList ListInfoIntrlck = infoIntrlckService.searchInfoIntrlck(infoIntrlckVO);

		return getSuccess(ListInfoIntrlck);
	}
	/**
	 * 마이페이지 - 정보연동 - 신청
	 * 2021.12.11 LSH 파일처리로직 추가
	 * - @ModelAttribute를 @RequestBody로 변경
	 */
	@RequestMapping("/usr/mypage/regiInfoIntrlck.do")
	@ResponseBody
	public Map regiInfoIntrlck(HttpServletRequest request,
			@RequestBody InfoIntrlckVO infoIntrlckVO)
			throws Exception {

		setGlobalSession(infoIntrlckVO);
		infoIntrlckVO.setIntrlckSeCd(infoIntrlckVO.getIntrlckSeCd());
		int result = infoIntrlckService.regiInfoIntrlck(infoIntrlckVO);
		if (result > 0) {
			return getSuccess();
		}
		return getFailure();
	}

	/**
	 * 마이페이지 - 구제급여현황 화면
	 */
	@RequestMapping("/usr/mypage/viewRelief.do")
	public String viewRelief(HttpServletRequest request,
			@ModelAttribute ReliefVO reliefVO,
			ModelMap model)
			throws Exception {

		setGlobalSession(reliefVO);
		// -------------------- Default Setting End -----------------------//

		// 신청차수 기본값 정의
		reliefVO.setAplyOder(CommConst.APLY_ODER_RELIEF);
		// 서류업무구분 정의
		reliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_RELIEF);

		// -------------------- Default Setting End -----------------------//
		model.addAttribute("model", reliefVO);

		return "usr/mypage/viewRelief";
	}

	/**
	 * 2012.12.08 LSH 구제급여현황 구비서류조회
	 * 마이페이지 - 구제급여현황 - 구비서류조회 화면
	 */
	@RequestMapping("/usr/mypage/viewReliefPape.do")
	public String viewReliefPape(HttpServletRequest request,
			@ModelAttribute ReliefVO reliefVO,
			ModelMap model)
			throws Exception {

		setGlobalSession(reliefVO);
		// -------------------- Default Setting End -----------------------//

		// 신청차수 기본값 정의
		reliefVO.setAplyOder(CommConst.APLY_ODER_RELIEF);
		// 서류업무구분 정의
		reliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_RELIEF);

		// -------------------- Default Setting End -----------------------//
		model.addAttribute("model", reliefVO);

		return "usr/mypage/viewReliefPape";
	}

	/**
	 * 2012.12.08 LSH 구제급여현황 구제급여지급현황
	 * 마이페이지 - 구제급여현황 - 구제급여지급현황 화면
	 * - 해당 신청자번호 또는 식별ID 기준 SUMMARY
	 */
	@RequestMapping("/usr/mypage/viewReliefGive.do")
	public String viewReliefGive(HttpServletRequest request,
			@ModelAttribute ReliefVO reliefVO,
			ModelMap model)
			throws Exception {

		setGlobalSession(reliefVO);
		// -------------------- Default Setting End -----------------------//
		// 최종신청정보
		ReliefVO keyObj = null;
		// 2021.11.08 LSH
		// 식별아이디 사용자인 경우 식별ID기준으로 조회한다.
		if (reliefVO.getUserInfo().isIdntfc()) {
			String idntfcId = reliefVO.getUserInfo().getUserId();
			reliefVO.setIdntfcId(idntfcId);
			// 최종신청정보 KEY 조회
			keyObj = reliefService.viewReliefByIdntfcId(idntfcId);
		}
		// 로그인회원 사용자인 경우 신청자번호기준으로 조회한다.
		else {
			String aplcntNo = reliefVO.getUserInfo().getUserNo();
			reliefVO.setAplcntNo(aplcntNo);
			// 최종신청정보 KEY 조회
			keyObj = reliefService.viewReliefAplyLast(reliefVO);
		}
		if (keyObj != null) {
			reliefVO.setAplyNo(keyObj.getAplyNo());
			// 신청정보 상세조회
			ReliefVO viewObj = reliefService.viewRelief(reliefVO);
			if (viewObj != null) {
				// 신청구분 정의
				reliefVO.setAplySeCd(viewObj.getAplySeCd());
			}
		}
		// 서류업무구분 정의
		reliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_RELIEF);

		// -------------------- Default Setting End -----------------------//
		model.addAttribute("model", reliefVO);

		return "usr/mypage/viewReliefGive";
	}

	/**
     * 마이페이지 - 취약계층소송지원현황 목록 화면
     */
    @RequestMapping("/usr/mypage/listLwst.do")
    public String listLwst(HttpServletRequest request,
            ModelMap model,
            @ModelAttribute LwstVO lwstVO) throws Exception {

        setGlobalSession(lwstVO);
        // -------------------- Default Setting End -----------------------//

        UserInfo userInfo = lwstVO.getUserInfo();

        lwstVO.setAplcntNo(userInfo.getUserNo());
        List list = lwstService.listLwstAplyIncdnt(lwstVO);

        model.addAttribute("model", lwstVO);
        model.addAttribute("rows", list);

        return "usr/mypage/listLwst";
    }

	/**
	 * 마이페이지 - 취약계층소송지원현황 상세 화면
	 */
	@RequestMapping("/usr/mypage/viewLwst.do")
	public String viewLwst(HttpServletRequest request,
			ModelMap model,
			@ModelAttribute LwstVO lwstVO) throws Exception {

		setGlobalSession(lwstVO);
		// -------------------- Default Setting End -----------------------//

		LwstVO row              = lwstService.viewLwstAplyIncdnt(lwstVO);
		List   incdntDetailList = lwstService.listLwstAplyIncdntDetail(lwstVO);

		model.addAttribute("model",            lwstVO);
		model.addAttribute("row",              row);
		model.addAttribute("incdntDetailList", incdntDetailList);

		return "usr/mypage/viewLwst";
	}

	/**
	 * 마이페이지 - 나의질의현황 화면
	 *
	 */
	@RequestMapping("/usr/mypage/listMyBbs.do")
	public String listMyBbs(HttpServletRequest request,
			ModelMap model,
			@ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);

		// -------------------- Default Setting End -----------------------//
		String userNo = bbsVO.getUserInfo().getUserNo();
		bbsVO.setRgtrNo(userNo);
		bbsVO.setPage(CommUtils.nvlTrim(bbsVO.getPage(), "1"));
		// 게시판 구분 QNA
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);

		int page = CommUtils.strToInt(bbsVO.getPage(), 1);
		List list = bbsService.mylistBbs(bbsVO, page, basePageSize);
		list = getprolog(list);
		model.addAttribute("model", bbsVO);
		model.addAttribute("rows", list);
		model.addAttribute("startNo", ((PaginatedArrayList) list).getStartNo());
		model.addAttribute("startRevNo", ((PaginatedArrayList) list).getStartRevNo());
		model.addAttribute("totalSize", ((PaginatedArrayList) list).getTotalSize());

		return "usr/mypage/listMyBbs";
	}

	/**
	 * 마이페이지 -  질의응답 상세보기
	 *
	 */
	@RequestMapping("/usr/mypage/viewMyQna.do")
	public String viewMyBbs(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		String userNo = bbsVO.getUserInfo().getUserNo();

		Map paramMap = getParameterMap(request, true);
		// 게시판구분 정의 (질의응답)
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);

		// TODO. 세션사용자와 글의 작성자가 일치하는지 확인할것
		// 세션사용자번호는 다음과같이 가져온다.
		// 해당 nttNO에 해당하는 객체 가져오기
		BbsVO bbsQ = bbsService.viewBbs(bbsVO);
		BbsVO bbsA = new BbsVO();
		// 말머리 처리
		bbsQ.setNttSj("[" + bbsQ.getNttClCd()+"] "+bbsQ.getNttSj());
		if (userNo.equals(bbsQ.getRgtrNo()) || CommConst.isAdminRole(bbsVO.getUserInfo().getRoleId())) {
			model.addAttribute("model", bbsQ);
			model.addAttribute("backToList", "/usr/mypage/viewMyBbs.do");

			if (bbsQ.getStatus() != null) {
				bbsA.setUpNttNo(bbsQ.getNttNo());
				bbsA = bbsService.viewBbsN(bbsA);
				model.addAttribute("answer", bbsA);

				return "usr/mypage/viewMyQna";

			}
		} else {

			throw new EgovBizException(message.getMessage("error.comm.notAccess"));

		}
		return "usr/mypage/viewMyQna";
	}

	/**
	 * 마이페이지 - 나의질의현황 질의응답 등록페이지
	 */
	@RequestMapping("/usr/mypage/writeMyQna.do")
	public String writeQna(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		// 게시판구분 정의 (질의응답)
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);
		String userNo = bbsVO.getUserInfo().getUserNo();

		if (userNo == null) {
			// 로그인 페이지로 보내야함
			 throw new EgovBizException(message.getMessage("error.comm.notAccess"));

		} else {
			return "usr/mypage/writeMyQna";

		}
	}

	/**
	 * [마이페이지] 질의응답 문의등록
	 *
	 * @param request
	 * @param bbsVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/usr/mypage/saveQna.do")
	@ResponseBody
	public Map saveQna(HttpServletRequest request, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);

		bbsVO.setBbsSeCd(CommConst.BBS_QNA);
		bbsVO.setRgtrNo(bbsVO.getUserInfo().getUserNo());
		int result = bbsService.qnaSave(bbsVO);

		if (result > 0) {
			return getSuccess();
		}
		return getFailure();

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

	/**
	 * ntarget : 2022.11.09
	 * 회원탈퇴 처리
	 */
	@RequestMapping("/usr/mypage/updtMembWithdraw.do")
	@ResponseBody
	public Map updtMembWithdraw(HttpServletRequest request, ModelMap model, @ModelAttribute UserInfoVO userInfoVO) throws Exception {

		setGlobalSession(userInfoVO);

		String userNo = userInfoVO.getUserInfo().getUserNo();

		if (CommUtils.isEmpty(userNo)) {
			return getFailure("회원정보를 알수가 없습니다. 관리자에게 문의바랍니다.");
		}

        // 수정 처리
		userInfoService.updtMembWithdraw(userInfoVO);

		return getSuccess();
	}
}
