package com.skeqi.mes.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PageInfoUtil<T> implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private int pageNum ;   //当前页

	private int pageSize;  //每页的数量

	private int pages;   //总页数

	private int total;  //总记录数

	private boolean isFirstPage;   //是否为第一页

	private boolean isLastPage;    //是否为最后一页

	private boolean hasPreviousPage;   //是否有上一页

	private boolean hasNextPage;    //是否有下一页

	private int navigatePages;  //导航页码数

	private int[] navigatepageNums;   //所有导航页数

	private List<T> list;


	public PageInfoUtil(List<T> list, int navigatePages,int pageSize,int pageNum){
		List<T> li = new ArrayList<T>();
		this.pageNum=pageNum;
		this.pageSize=pageSize;
		this.total=list.size();
		if(total!=0){
			this.pages=(total+pageSize-1)/pageSize;   //计算总页数
		}else{
			this.pages=1;
		}
		this.navigatePages=navigatePages;   //导航页码数
		this.isFirstPage = false;
		this.isLastPage = false;
		this.hasPreviousPage = false;
		this.hasNextPage = false;
		if(pageNum==1){
			for(int i=0;i<pageSize;i++){
				if(i+1<=total){
					li.add(list.get(i));
				}
			}
		}else{
			for(int i=(pageNum-1)*pageSize;i<pageNum*pageSize;i++){
				if(i<total){
					li.add(list.get(i));
				}
			}
		}
		calcNavigatepageNums();
		judgePageBoudary();
		this.list=li;
	}

	/**
	 * 所有导航页数
	* @author FQZ
	* @date 2020年3月10日上午10:45:36
	 */
	 private void calcNavigatepageNums(){
		if (this.pages <= this.navigatePages) {
				/* 138 */ this.navigatepageNums = new int[this.pages];
				/* 139 */ for (int i = 0; i < this.pages; ++i)
					/* 140 */ this.navigatepageNums[i] = (i + 1);
				/*     */ }
			/*     */ else {
				/* 143 */ this.navigatepageNums = new int[this.navigatePages];
				/* 144 */ int startNum = this.pageNum - (this.navigatePages / 2);
				/* 145 */ int endNum = this.pageNum + this.navigatePages / 2;
				/*     */
				/* 147 */ if (startNum < 1) {
					/* 148 */ startNum = 1;
					/*     */
					/* 150 */ for (int i = 0; i < this.navigatePages; ++i)
						/* 151 */ this.navigatepageNums[i] = (startNum++);
					/*     */ }
				/* 153 */ else if (endNum > this.pages) {
					/* 154 */ endNum = this.pages;
					/*     */
					/* 156 */ for (int i = this.navigatePages - 1; i >= 0; --i)
						/* 157 */ this.navigatepageNums[i] = (endNum--);
					/*     */ }
				/*     */ else
				/*     */ {
					/* 161 */ for (int i = 0; i < this.navigatePages; ++i)
						/* 162 */ this.navigatepageNums[i] = (startNum++);
					/*     */ }
				/*     */ }
			/*     */ }

	   private void judgePageBoudary()
		/*     */ {
			/* 188 */ this.isFirstPage = (this.pageNum == 1);
			/* 189 */ this.isLastPage = (this.pageNum == this.pages);
			/* 190 */ this.hasPreviousPage = (this.pageNum > 1);
			/* 191 */ this.hasNextPage = (this.pageNum < this.pages);
			/*     */ }
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}


	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}



	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getNavigatePages() {
		return navigatePages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int isNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}

	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public static void main(String[] args) {

	}

	@Override
	public String toString() {
		return "PageInfoUtil [pageNum=" + pageNum + ", pageSize=" + pageSize + ", pages=" + pages + ", total=" + total
				+ ", isFirstPage=" + isFirstPage + ", isLastPage=" + isLastPage + ", hasPreviousPage=" + hasPreviousPage
				+ ", hasNextPage=" + hasNextPage + ", navigatePages=" + navigatePages + ", navigatepageNums="
				+ Arrays.toString(navigatepageNums) + ", list=" + list + "]";
	}

}
