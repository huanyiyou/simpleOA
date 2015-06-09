package cn.yht.simpleOA.service;

import cn.yht.simpleOA.base.DaoSupport;
import cn.yht.simpleOA.model.Overtime;

import java.util.List;

/**
 * Created by YHT on 2015/5/19.
 */
public interface OvertimeService extends DaoSupport<Overtime> {
    List<Overtime> findAllByUserId(Long user);

    double[] getSumByUserIdAndYear(Long userId, String year);

    double[] getSumByYear(String year);

    double[] getSumByUserId(Long userId);
}
