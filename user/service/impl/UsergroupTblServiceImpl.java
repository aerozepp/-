package kr.co.esjee.sjcms.admin.user.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import kr.co.esjee.sjcms.BaseController;
import kr.co.esjee.sjcms.FormConstant;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblDefaultVO;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblService;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblVO;
import kr.co.esjee.sjcms.common.ValidatorMessenger;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : UsergroupTblServiceImpl.java
 * @Description : UsergroupTbl Business Implement class
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("usergroupTblService")
public class UsergroupTblServiceImpl extends AbstractServiceImpl implements
        UsergroupTblService, FormConstant{

    @Resource(name="usergroupTblDAO")
    private UsergroupTblDAO usergroupTblDAO;
    
	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/** ID Generation */
    //@Resource(name="{egovUsergroupTblIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * USERGROUP_TBL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UsergroupTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertUsergroupTbl(UsergroupTblVO vo) throws Exception {
    	log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());
    	
    	usergroupTblDAO.insertUsergroupTbl(vo);
    	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
        
    }

    /**
	 * USERGROUP_TBL을 수정한다.
	 * @param vo - 수정할 정보가 담긴 UsergroupTblVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateUsergroupTbl(UsergroupTblVO vo) throws Exception {
        usergroupTblDAO.updateUsergroupTbl(vo);
    }

    /**
	 * USERGROUP_TBL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 UsergroupTblVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteUsergroupTbl(UsergroupTblVO vo) throws Exception {
        usergroupTblDAO.deleteUsergroupTbl(vo);
    }

    /**
	 * USERGROUP_TBL을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UsergroupTblVO
	 * @return 조회한 USERGROUP_TBL
	 * @exception Exception
	 */
    public UsergroupTblVO selectUsergroupTbl(UsergroupTblVO vo) throws Exception {
        UsergroupTblVO resultVO = usergroupTblDAO.selectUsergroupTbl(vo);
        return resultVO;
    }

    /**
	 * USERGROUP_TBL 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USERGROUP_TBL 목록
	 * @exception Exception
	 */
    public List selectUsergroupTblList(UsergroupTblDefaultVO searchVO) throws Exception {
        return usergroupTblDAO.selectUsergroupTblList(searchVO);
    }
    
    public List<EgovMap> selectUsergroupTblAllList(HashMap<String, Object> param) throws Exception {
    	param.put("isAdmin", BaseController.isAdmin());
    	return usergroupTblDAO.selectUsergroupTblAllList(param);
    }

    public List selectMemoUsergroupTblList() throws Exception {
    	return usergroupTblDAO.selectMemoUsergroupTblList();
    }

    
    /**
	 * USERGROUP_TBL 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USERGROUP_TBL 총 갯수
	 * @exception
	 */
    public int selectUsergroupTblListTotCnt(UsergroupTblDefaultVO searchVO) {
		return usergroupTblDAO.selectUsergroupTblListTotCnt(searchVO);
	}
    
    /**
	 * USERGROUP_TBL 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USERGROUP_TBL 목록
	 * @exception Exception
	 */
    public List selectUsergroupTblListByUserId(String userId) throws Exception {
        return usergroupTblDAO.selectUsergroupTblListByUserId(userId);
    }
    
	public boolean checkPk(UsergroupTblVO vo, ValidatorMessenger vm) throws Exception {
    	String flagPk = usergroupTblDAO.selectUsergroupTblPk(vo);
    	if (PK_T.equals(flagPk)) {
    		vm.append(messageSource.getMessage("errors.id.used", null, LocaleContextHolder.getLocale()));
    	} else if (PK_D.equals(flagPk)) {
    		vm.append(messageSource.getMessage("errors.id.delete", null, LocaleContextHolder.getLocale()));
    	}
    	return true;
	}

	public void checkDefault(UsergroupTblVO vo, ValidatorMessenger vm) throws Exception {
		UsergroupTblVO usergroupTblVO = this.selectUsergroupTbl(vo);
		String defaultTf = usergroupTblVO.getDefaultTf();
    	if (FLAG_T.equals(defaultTf)) {
    		vm.append(messageSource.getMessage("errors.id.default", null, LocaleContextHolder.getLocale()));
    	} 
	}
	
	@Override
	public ValidatorMessenger validate(UsergroupTblVO vo, String flag) throws Exception {
		ValidatorMessenger vm = new ValidatorMessenger();
		if (SUBMIT_INSERT.equals(flag)) {
			checkPk(vo, vm);
		}else if (SUBMIT_DELETE.equals(flag)) {
			checkDefault(vo, vm);
		}
		vm.complete();
		return vm;
	}
}
