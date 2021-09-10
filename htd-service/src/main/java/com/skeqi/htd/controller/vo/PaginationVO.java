package com.skeqi.htd.controller.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页对象
 *
 * @author linkin
 */
@Data
public class PaginationVO<T> {
	private List<T> list;
	private Integer total;

	public static <T> PaginationVO with(List<T> page, Integer total) {
		PaginationVO vo = new PaginationVO();
		vo.setList(page);
		vo.setTotal(total);
		return vo;
	}
}
