package com.datehoer.bookapi.common;

import cn.hutool.core.convert.Convert;
import com.datehoer.bookapi.model.PageModel;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class TableSupport {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 分页参数合理化
     */
    public static final String REASONABLE = "reasonable";

    /**
     * 封装分页对象
     */
    public static PageModel getPageDomain() {
        PageModel pageDomain = new PageModel();
        HttpServletRequest request = getRequest();
        if (request != null) {
            pageDomain.setPageNum(Convert.toInt(request.getParameter(PAGE_NUM), 1));
            pageDomain.setPageSize(Convert.toInt(request.getParameter(PAGE_SIZE), 10));
            pageDomain.setOrderByColumn(Convert.toStr(request.getParameter(ORDER_BY_COLUMN), "id"));
            pageDomain.setIsAsc(Convert.toStr(request.getParameter(IS_ASC), "asc"));
        }else{
            pageDomain.setPageNum(1);
            pageDomain.setPageSize(10);
            pageDomain.setOrderByColumn("id");
            pageDomain.setIsAsc("asc");
        }
        return pageDomain;
    }

    public static PageModel buildPageRequest() {
        return getPageDomain();
    }
    private static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes == null ? null : attributes.getRequest();
    }
}