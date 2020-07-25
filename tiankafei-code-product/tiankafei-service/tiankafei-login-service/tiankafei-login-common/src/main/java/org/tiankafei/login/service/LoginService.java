package org.tiankafei.login.service;

import org.tiankafei.login.model.LoginResultDto;
import org.tiankafei.user.param.LoginParamVo;
import org.tiankafei.web.common.exception.LoginException;

/**
 * @author tiankafei
 * @since 1.0
 **/
public interface LoginService {

    /**
     * 登录
     *
     * @param loginParamVo
     * @return
     * @throws Exception
     */
    LoginResultDto login(LoginParamVo loginParamVo) throws Exception;

    /**
     * 注销
     * @param userId
     * @throws LoginException
     */
    void logout(String userId) throws LoginException;

}
