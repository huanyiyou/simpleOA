package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Overtime;
import cn.yht.simpleOA.service.OvertimeService;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Double[] getSumByUserIdAndYear(Long userId, String year) {
        List<Overtime> overtimes = getSession().createQuery(
                "FROM Overtime o WHERE o.user.id = :userId AND o.year = :year")
                .setParameter("userId", userId)
                .setParameter("year", year)
                .list();
        Double[] result = new Double[12];
        if (overtimes.size() > 0) {
            for (Overtime o : overtimes) {
                if (o.getMonth().startsWith("0")) {
                    int index = Integer.parseInt(o.getMonth().substring(1)) - 1;
                    if(null != result[index]){
                        result[index] += TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                    }else {
                        result[index] = TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                    }
                } else {
                    int index = Integer.parseInt(o.getMonth()) - 1;
                    if(null != result[index]){
                        result[index] += TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                    }else {
                        result[index] = TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Double[] getSumByYear(String year) {
        List<Overtime> overtimes = getSession().createQuery(
                "FROM Overtime o WHERE o.year = :year")
                .setParameter("year", year)
                .list();
        Double[] result = new Double[12];
        if (overtimes.size() > 0) {
            for (Overtime o : overtimes) {
                if (o.getMonth().startsWith("0")) {
                    int index = Integer.parseInt(o.getMonth().substring(1)) - 1;
                    if(null != result[index]){
                        result[index] += TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                    }else {
                        result[index] = TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                    }
                } else {
                    int index = Integer.parseInt(o.getMonth()) - 1;
                    if(null != result[index]){
                        result[index] += TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                    }else {
                        result[index] = TimeHandler.getHoursByTimeSpan(o.getTimeSpan());
                    }
                }
            }
        }
        return result;
    }
}