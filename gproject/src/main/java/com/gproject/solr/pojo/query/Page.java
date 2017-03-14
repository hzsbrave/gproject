package com.gproject.solr.pojo.query;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


/**
 * @author laudin
 * @date 2012-12-27
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int maxPageSize = 60;
    public static final int middlePageSize = 40;
    public static final int defaultPageSize = 20;

    private int pageGap = 7; // 页面显示分页的页码数
    private int pageNo = 1;
    private int pageSize = 20;
    private long totalCount = 0;
    private long totalPages = 0; // 总页数
    private List<T> resultList = Collections.emptyList();
    //private FacetResult facetResult = null;

    public Page() {
    }

    public Page(int pageGap) {
        setPageGap(pageGap);
    }

    public Page(int pageNo, int pageSize) {
        setPageNo(pageNo);
        setPageSize(pageSize);
    }

//	public FacetResult getFacetResult() {
//		return facetResult;
//	}
//
//	public Page<T> setFacetResult(FacetResult facetResult) {
//		this.facetResult = facetResult;
//		return this;
//	}

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     * @return
     */

    public Page<T> setPageNo(int pageNo) {
        this.pageNo = pageNo;
        if (this.pageNo < 1) {
            this.pageNo = 1;
        }

        return this;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     * @return
     */

    public Page<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (this.pageSize <= 0) {
            this.pageSize = defaultPageSize;
        }
        return this;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public Page<T> setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        setTotalPages();
        return this;
    }

    /**
     * @return the result
     */
    public List<T> getResultList() {
        return resultList;
    }

    /**
     * @param result the result to set
     * @return
     */
    public Page<T> setResultList(List<T> result) {
        this.resultList = result;
        return this;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public Page<T> setTotalPages() {
        if (totalCount < 0) {
            totalPages = 0;
        }

        totalPages = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPages++;
        }
        return this;
    }

    public int getFirst() {
        return ((pageNo - 1) * pageSize) + 1;
    }

    public boolean getIsHasNext() {
        return (pageNo + 1 <= getTotalPages());
    }

    public int getNextPage() {
        if (getIsHasNext()) {
            return pageNo + 1;
        }
        return 0;
    }

    public boolean getIsHasPre() {
        return (pageNo - 1 >= 1);
    }

    public int getPrePage() {
        if (getIsHasPre()) {
            return pageNo - 1;
        }
        return pageNo;
    }

    public int getPageGap() {
        return pageGap;
    }

    public void setPageGap(int pageGap) {
        this.pageGap = pageGap;
    }

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    // 页面分页起始页码数
    public long getBeginPageNo() {
        long totalPage = getTotalPages();
        if (totalPage <= pageGap) {
            return 1;
        }

        int begin = pageGap % 2 == 0 ? (pageNo - pageGap / 2) + 1 : pageNo - pageGap / 2;
        if (begin <= 0) {
            return 1;
        }
        if (begin > (totalPage - (pageGap - 1))) {
            return totalPage - (pageGap - 1);
        }
        return begin;
    }

    // 页面分页结束页码数
    public long getEndPageNo() {
        long totalPage = getTotalPages();
        if (totalPage <= pageGap) {
            return totalPage;
        }

        long end = pageNo + pageGap / 2;
        if (end >= totalPage) {
            return totalPage;
        }

        if (end < pageGap) {
            return pageGap;
        }

        return end;
    }

    public void limitPageSize() {
        if (pageSize > maxPageSize) {
            pageSize = maxPageSize;
        }
    }

}
