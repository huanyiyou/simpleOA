package cn.yht.simpleOA.service;

import cn.yht.simpleOA.base.DaoSupport;
import cn.yht.simpleOA.model.PageBean;
import cn.yht.simpleOA.model.PreOvertime;

import java.util.List;

/**
 * Created by YHT on 2015/5/19.
 */
public interface PreOvertimeService extends DaoSupport<PreOvertime> {
    List<PreOvertime> findAllByUserId(Long user);

    List<PreOvertime> findAllSubmitted();

    @Deprecated
    PageBean getPageBeanByUserId(int pageNum, int pageSize, Long user);
}
