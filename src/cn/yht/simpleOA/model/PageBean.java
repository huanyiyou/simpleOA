package cn.yht.simpleOA.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YHT on 2015/5/26.
 */
public class PageBean implements Serializable {
    //指定的或是传递的参数
    private int currentPage;  //当前页
    private int pageSize;    //每页显示多少条

    //查询出来的
    private List recordList;  //本页的数据列表
    private int recordCount;  //总记录数

    //计算出来的
    private int pageCount;   //总页数
    private int beginPageIndex;  //页码列表的开始索引
    private int endPageIndex;    //页码列表的结束索引

    private int previousPage; //上一页
    private int nextPage;  //下一页
    private boolean hasPreviousPage;  //是否有上一页
    private boolean hasNextPage;      //是否有下一页

    public PageBean() {
    }

    public PageBean(int currentPage, int pageSize, List recordList, int recordCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordList = recordList;
        this.recordCount = recordCount;

        //计算总页码
        this.pageCount = (recordCount + pageSize - 1)/pageSize;

        //计算beginPageIndex和endPageIndex
        //总页数不多于10页，则显示当前页附近的10个页码
        if(pageCount <= 10){
            beginPageIndex = 1;
            endPageIndex = pageCount;
        }
        //总页数多于10页，则显示当前页附近的10个页码
        else {
            //当前页附近的共10个页码(前4个+当前页+后5个)
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            //当前面的页码不足4个时，则显示前10个页码
            if( beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            //当后面的页码不足5个时，则显示后10个页码
            if(endPageIndex > pageCount){
                beginPageIndex = pageCount - 10 + 1;
                endPageIndex = pageCount;
            }
        }

        //判断是否有上一页
        if(currentPage <= 1){
            hasPreviousPage = false;
        }else {
            hasPreviousPage = true;
        }
        //计算上一页页码
        if(hasPreviousPage){
            previousPage = currentPage - 1;
        }else {
            previousPage = 1;
        }

        //判断是否有下一页
        if(currentPage >= pageCount){
            hasNextPage = false;
        }else {
            hasNextPage = true;
        }
        //计算下一页页码
        if(hasNextPage){
            nextPage = currentPage + 1;
        }else {
            nextPage = pageCount;
        }
    }

    public List getRecordList() {
        return recordList;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
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
}
