package com.skeqi.mes.service.project;

import java.util.List;

import com.skeqi.mes.pojo.project.AndonMessage;

public interface AndonMessageService {

	public List<AndonMessage> findAndonMessageList();

	public AndonMessage findAndonMessage();

	public Integer addAndonMessage(AndonMessage dx) throws Exception ;

	public Integer deleteAndonMessage(Integer id);

	public Integer updateAndonMessage(AndonMessage dx) throws Exception;

}
