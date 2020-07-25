package com.order.system.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.system.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;



/**
 * 分页类
 * 
 * @version 2013-3-25
 * @param <T>
 */
public class Page<T> {

	private int pageNo = 1; // 当前页码
	private int pageSize = 10; // 页面大小，设置为“-1”表示不进行分页（分页无效）

	private long count;// 总记录数，设置为“-1”表示不查询总数

	private int first;// 首页索引
	private int last;// 尾页索引
	//private int prev;// 上一页索引
	//private int next;// 下一页索引

	//private boolean firstPage;// 是否是第一页
	//private boolean lastPage;// 是否是最后一页

	private int showLength = 8;// 显示页面长度
	private int slider = 1;// 前后显示页面长度

	private List<T> list = new ArrayList<T>();

	private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc

	private String funcName = "page"; // 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。

	public Page() {
	}

	/**
	 * 构造方法
	 * 
	 * @param request
	 *            传递 repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页码
	 */
	public Page(HttpServletRequest request, HttpServletResponse response) {
		this(request, response, -2);
	}

	/**
	 * 构造方法
	 * 
	 * @param request
	 *            传递 repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页码
	 * @param pageSize
	 *            分页大小，如果传递 -1 则为不分页，返回所有数据
	 */
	public Page(HttpServletRequest request, HttpServletResponse response, int pageSize) {
		// 设置页码参数（传递repage参数，来记住页码）
		String no = request.getParameter("pageNo");
		if (StringUtils.isNumeric(no)) {
			CookieUtils.setCookie(response, "pageNo", no);
			this.pageNo = Integer.parseInt(no);
		} else if (request.getParameter("repage") != null) {
			no = CookieUtils.getCookie(request, "pageNo");
			if (StringUtils.isNumeric(no)) {
				this.pageNo = Integer.parseInt(no);
			}
		}
		// 设置页面大小参数（传递repage参数，来记住页码大小）
		String size = request.getParameter("pageSize");
		if (StringUtils.isNumeric(size)) {
			CookieUtils.setCookie(response, "pageSize", size);
			this.pageSize = Integer.parseInt(size);
		} else if (request.getParameter("repage") != null) {
			no = CookieUtils.getCookie(request, "pageSize");
			if (StringUtils.isNumeric(size)) {
				this.pageSize = Integer.parseInt(size);
			}
		}
		if (pageSize != -2) {
			this.pageSize = pageSize;
		}
		// 设置排序参数
		String orderBy = request.getParameter("orderBy");
		if (StringUtils.isNotBlank(orderBy)) {
			this.setOrderBy(orderBy);
		}
	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 */
	public Page(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0);
	}
	

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @param count
	 *            数据条数
	 */
	public Page(int pageNo, int pageSize, long count) {
		this(pageNo, pageSize, count, new ArrayList<T>());
	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @param count
	 *            数据条数
	 * @param list
	 *            本页数据对象列表
	 */
	public Page(int pageNo, int pageSize, long count, List<T> list) {
		this.setPageSize(pageSize);
		this.setCount(count);
		this.setPageNo(pageNo);
		this.setList(list);
	}

	/**
	 * 初始化参数
	 */
	private void paginate() {
		if(count <= 0 || pageSize <= 0){
			first = last = pageNo = 1;
			return;
		}
		first = 1;
		if (pageSize < 1) {
			pageSize = 20;
		}
		last = (int) (count / pageSize + (count % pageSize == 0 ? 0 : 1));
		if (last < first) {
			last = first;
		}
		if (pageNo < first) {
			pageNo = first;
		}
		if (pageNo > last) {
			pageNo = last;
		}
		/*next = pageNo + 1;
		if (next > last) {
			next = last;
		}
		prev = pageNo - 1;
		if (prev < first) {
			prev = first;
		}*/
		//firstPage = pageNo == first;
		//lastPage = pageNo == last;
	}

	/**
	 * 默认输出当前分页标签 <div class="page">${page}</div>
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (isFirstPage()) {// 如果是首页
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:" + funcName + "(" + getPrev() + "," + pageSize + ");\">&#171; 上一页</a></li>\n");
		}
		int begin = pageNo - (showLength / 2);
		if (begin < first) {
			begin = first;
		}
		int end = begin + showLength - 1;
		if (end >= last) {
			end = last;
			begin = end - showLength + 1;
			if (begin < first) {
				begin = first;
			}
		}
		if (begin > first) {
			int i = 0;
			for (i = first; i < first + slider && i < begin; i++) {
				sb.append("<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>\n");
			}
			if (i < begin) {
				sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			}
		}
		for (int i = begin; i <= end; i++) {
			if (i == pageNo) {
				sb.append("<li class=\"active\"><a href=\"javascript:\">" + (i + 1 - first) + "</a></li>\n");
			} else {
				sb.append("<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>\n");
			}
		}
		if (last - end > slider) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			end = last - slider;
		}

		for (int i = end + 1; i <= last; i++) {
			sb.append("<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>\n");
		}

		if (pageNo == last) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:" + funcName + "(" + getNext() + "," + pageSize + ");\">" + "下一页 &#187;</a></li>\n");
		}
		sb.append("<span style=\"margin-left:10px;display:inline-block;\">当前 ");
		sb.append("<input type=\"text\" style=\"width:50px;margin-left:5px;text-align:center;\" value=\"" + pageNo + "\" onkeypress=\"var e=window.event||this;var c=e.keyCode||e.which;if(c==13)");
		sb.append(funcName + "(this.value > " + last + " ? " + last + " : this.value < 1 ? 1 : this.value," + pageSize + ");\" onclick=\"this.select();\"/> / ").append(last).append("页，每页");
		sb.append("<select style=\"margin-left:5px;margin-right:5px;text-align:center;\" onchange=\"" + funcName + "(1, value)\">");
//		TreeSet<Integer> set = Sets.newTreeSet(Arrays.asList(10, 20, 30, 50, 100));
//		if(!set.contains(pageSize)) {
//			set.add(pageSize);
//		}
//		for (Integer size : set) {
//			sb.append("<option value=\"" + size + "\" " +(size == pageSize ? "selected" : "") + ">" + size + "</option>");
//		}
		sb.append("</select>");
		/*sb.append("<input type=\"text\" value=\"" + pageSize + "\" onkeypress=\"var e=window.event||this;var c=e.keyCode||e.which;if(c==13)");
		sb.append(funcName + "(" + pageNo + ",this.value);\" onclick=\"this.select();\"/> 条，");*/
		sb.append(" 条，共 " + count + " 条</span>");
		sb.insert(0, "<ul class=\"pagination\" style=\"margin-top:0px;\">\n").append("</ul>\n");
		return sb.toString();
	}

	// public static void main(String[] args) {
	// Page<String> p = new Page<String>(3, 3);
	// System.out.println(p);
	// System.out.println("首页："+p.getFirst());
	// System.out.println("尾页："+p.getLast());
	// System.out.println("上页："+p.getPrev());
	// System.out.println("下页："+p.getNext());
	// }

	/**
	 * 获取设置总数
	 * 
	 * @return
	 */
	public long getCount() {
		return count;
	}

	/**
	 * 设置数据总数
	 * 
	 * @param count
	 */
	public void setCount(long count) {
		this.count = count;
		paginate();
	}

	/**
	 * 获取当前页码
	 * 
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页码
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		/*if(pageNo > last){
			pageNo = last;
		}*/
		this.pageNo = pageNo;
	}
	
	public void initPageNoAndSize(int pageNo, int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	/**
	 * 获取页面大小
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页面大小（最大500）
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		paginate();
	}

	/**
	 * 首页索引
	 * 
	 * @return
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * 尾页索引
	 * 
	 * @return
	 */
	public int getLast() {
		return last;
	}

	/**
	 * 获取页面总数
	 * 
	 * @return
	 */
	public int getTotalPage() {
		return last;
	}

	/**
	 * 是否为第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return pageNo == first;
	}

	/**
	 * 是否为最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage() {
		return pageNo == last;
	}

	/**
	 * 上一页索引值
	 * 
	 * @return
	 */
	public int getPrev() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * 下一页索引值
	 * 
	 * @return
	 */
	public int getNext() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * if( pageNo> totalPage) return 返回随机页 else pageNo
	 * 
	 * @return
	 */
//	@JSONField(serialize = false)
//	public int getPageNoRandom() {
//		int i = last - 1 > 0 ? last - 1 : 1;
//		return new Random().nextInt(i) + 1;
//	}

	/**
	 * 获取本页数据对象列表
	 * 
	 * @return List<T>
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置本页数据对象列表
	 * 
	 * @param list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * 获取查询排序字符串
	 * 
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置查询排序，标准查询有效， 实例： updatedate desc, name asc
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 获取点击页码调用的js函数名称 function ${page.funcName}(pageNo){location=
	 * "${ctx}/list-${category.id}${urlSuffix}?pageNo="+i;}
	 * 
	 * @return
	 */
	public String getFuncName() {
		return funcName;
	}

	/**
	 * 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。
	 * 
	 * @param funcName
	 *            默认为page
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	/**
	 * 分页是否有效
	 * 
	 * @return this.pageSize==-1
	 */
	public boolean isDisabled() {
		return this.pageSize == -1;
	}

	/**
	 * 是否进行总数统计
	 * 
	 * @return this.count==-1
	 */
	public boolean isNotCount() {
		return this.count == -1;
	}

	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult() {
		int firstResult = (getPageNo() - 1) * getPageSize();
		if (firstResult >= getCount()) {
			firstResult = 0;
		}
		return firstResult;
	}

//	@JSONField(serialize = false)
//	public int getFirstResultRendom() {
//		int firstResult = (getPageNoRandom() - 1) * getPageSize();
//		if (firstResult >= getCount()) {
//			firstResult = 0;
//		}
//		return firstResult;
//	}

	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults() {
		return getPageSize();
	}

	/**
	 * 获取 Spring data JPA 分页对象
	 */
/*	@JSONField(serialize = false)
	public Pageable getSpringPage() {
		List<Order> orders = new ArrayList<Order>();
		if (orderBy != null) {
			for (String order : StringUtils.split(orderBy, ",")) {
				String[] o = StringUtils.split(order, " ");
				if (o.length == 1) {
					orders.add(new Order(Direction.ASC, o[0]));
				} else if (o.length == 2) {
					if ("DESC".equals(o[1].toUpperCase())) {
						orders.add(new Order(Direction.DESC, o[0]));
					} else {
						orders.add(new Order(Direction.ASC, o[0]));
					}
				}
			}
		}
		if (orders.isEmpty()) {
			return new PageRequest(this.pageNo - 1, this.pageSize);
		}
		return new PageRequest(this.pageNo - 1, this.pageSize, new Sort(orders));
	}*/

	/**
	 * 设置 Spring data JPA 分页对象，转换为本系统分页对象
	 */
//	public void setSpringPage(org.springframework.data.domain.Page<T> page) {
//		setPageSize(page.getSize());
//		setCount(page.getTotalElements());
//		setPageNo(page.getNumber() + 1);
//		setList( page.getContent());
//	}
	
	public long getOffset() {
		return (pageNo - 1) * pageSize;
	}

}