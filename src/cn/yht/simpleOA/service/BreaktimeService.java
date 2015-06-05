package cn.yht.simpleOA.service;

import cn.yht.simpleOA.base.DaoSupport;
import cn.yht.simpleOA.model.Breaktime;

/**
 * Created by YHT on 2015/5/19.
 */
public interface BreaktimeService extends DaoSupport<Breaktime> {
    Double[] getSumByUserIdAndYear(Long userId, String year);

    Double[] getSumByYear(String year);
}
