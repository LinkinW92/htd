package com.skeqi.mes.mapper.all;

import java.util.List;

import com.skeqi.mes.pojo.RegisterT;

/**
 * 程序注册
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date   : 2020年2月11日 下午3:30:26
 */
public interface SMesRegisterTDAO {

	public List<RegisterT> findAllRegisterT(RegisterT r);

	public Integer addRegisterT(RegisterT r);

	public Integer updadteRegisterT(RegisterT r);

	public Integer delRegisterT(Integer id);
}
