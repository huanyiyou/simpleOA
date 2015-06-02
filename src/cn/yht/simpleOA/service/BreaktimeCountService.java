package cn.yht.simpleOA.service;

import cn.yht.simpleOA.base.DaoSupport;
import cn.yht.simpleOA.model.BreaktimeCount;

/**
 * Created by YHT on 2015/5/29.
 */
public interface BreaktimeCountService extends DaoSupport<BreaktimeCount> {

    Double[] getTimeByUserIdAndYear(Long userId, String year);

    Long getSameUYMId(Long userId, String year, String month);

    Double[] getTimeByYear(String year);
}
