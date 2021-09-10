package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.RegisterT;

public interface SMesRegisterTService {

	public List<RegisterT> findAllRegisterT(RegisterT r) throws ServicesException;

	public Integer addRegisterT(RegisterT r) throws ServicesException;

	public Integer updadteRegisterT(RegisterT r) throws ServicesException;

	public Integer delRegisterT(Integer id) throws ServicesException;
}
