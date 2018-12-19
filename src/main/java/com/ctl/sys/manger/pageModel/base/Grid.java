package com.ctl.sys.manger.pageModel.base;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("rawtypes")
public class Grid implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long total = 0L;
	private List rows = new ArrayList();
	private List footer = new ArrayList();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
