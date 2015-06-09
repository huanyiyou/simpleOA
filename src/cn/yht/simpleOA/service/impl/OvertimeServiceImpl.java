package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Overtime;
import cn.yht.simpleOA.service.OvertimeService;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by YHT on 2015/5/19.
 */
@Service
@Transactional
public class OvertimeServiceImpl extends DaoSupportImpl<Overtime>  implements OvertimeService {
    @Override
    public List<Overtime> findAllByUserId(Long userId) {
        return getSession().createQuery(
                "FROM Overtime o WHERE o.user.id = :id")
                .setParameter("id", userId)
                .list();
    }

    @Override
    public double[] getSumByUserIdAndYear(Long userId, String year) {
        List<Overtime> overtimes = getSession().createQuery(
                "FROM Overtime o WHERE o.user.id = :userId AND o.year = :year")
                .setParameter("userId", userId)
                .setParameter("year", year)
                .list();
        return getArrayByListMonthly(overtimes);
    }

    @Override
    public double[] getSumByYear(String year) {
        List<Overtime> overtimes = getSession().createQuery(
                "FROM Overtime o WHERE o.year = :year")
                .setParameter("year", year)
                .list();
        return getArrayByListMonthly(overtimes);
    }

    @Override
    public double[] getSumByUserId(Long userId) {
        List<Overtime> overtimes = getSession().createQuery(
                "FROM Overtime o WHERE o.user.id = :userId")
                .setParameter("userId", userId)
                .list();
        return getArrayByListMonthly(overtimes);
    }

    protected double[] getArrayByListMonthly(List<Overtime> overtimes){
        double[] result = new double[12];
        if (overtimes.size() > 0) {
            for (Overtime o : overtimes) {
                double val = TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                if (o.getMonth().startsWith("0")) {
                    int index = Integer.parseInt(o.getMonth().substring(1)) - 1;
                    result[index] += val;
                } else {
                    int index = Integer.parseInt(o.getMonth()) - 1;
                    result[index] += val;
                }
            }
        }
        return TimeHandler.getRoundArray(result);
    }

}