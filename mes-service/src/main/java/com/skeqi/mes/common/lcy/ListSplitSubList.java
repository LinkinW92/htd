package com.skeqi.mes.common.lcy;

import java.util.List;


public class ListSplitSubList {

	@SuppressWarnings("rawtypes")
	public List getSplitList(List list, Integer perCount, Integer queryPage, Integer queryTotalNumber){

		//需要分割的集合  list
		//分割后每个集合要有的条数   perCount
	    //需要给的第几个集合 queryPage
		//总共需要查询几次  queryTotalNumber

	        List listTemp = null;
	        int index = 0;
	        for (int i = 0; i < queryTotalNumber; i++) {
	        	index++;

				if(index!=queryTotalNumber){//如果index 不等于最后一次查询
					listTemp = list.subList(i*perCount, (i+1)*perCount);
					if(queryPage==i+1){
						return listTemp;

					}
				}else{//如果index 等于最后一次分割，那就让他分到总条数，否则有可能报数组下标越界
					listTemp = list.subList(i*perCount, list.size());
					return listTemp;
				}



			}
			return listTemp;

	}

	}

