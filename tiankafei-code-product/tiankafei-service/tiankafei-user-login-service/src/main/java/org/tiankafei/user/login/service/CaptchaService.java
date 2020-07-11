package org.tiankafei.user.login.service;

import org.tiankafei.user.login.param.CaptchaParamVo;
import org.tiankafei.web.common.exception.VerificationException;

/**
 * @author tiankafei
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @return
     * @throws VerificationException
     */
    CaptchaParamVo createCaptcha() throws VerificationException;

    /**
     * 校验验证码
     *
     * @param uuid
     * @param captcha
     * @return
     * @throws VerificationException
     */
    boolean verifyCaptcha(String uuid, String captcha) throws VerificationException;

}
