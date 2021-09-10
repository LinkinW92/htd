package com.skeqi.common.exception.user;

import com.skeqi.common.exception.BaseException;

/**
 * 用户信息异常类
 *
 * @author skeqi
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
