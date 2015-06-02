package cn.yht.simpleOA.service;

import cn.yht.simpleOA.base.DaoSupport;
import cn.yht.simpleOA.model.OvertimeCount;

import java.util.List;

/**
 * Created by YHT on 2015/5/28.
 */
public interface OvertimeCountService extends DaoSupport<OvertimeCount>{

    Long getSameUYMId(Long userId, String year, String month);

    Double[] getTimeByUserIdAndYear(Long userId, String year);

    Double[] getTimeByYear(String year);
}
