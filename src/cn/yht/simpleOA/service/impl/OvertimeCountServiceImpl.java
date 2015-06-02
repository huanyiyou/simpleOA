package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Overtime;
import cn.yht.simpleOA.model.OvertimeCount;
import cn.yht.simpleOA.service.OvertimeCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by YHT on 2015/5/28.
 */
@Transactional
@Service
public class OvertimeCountServiceImpl extends DaoSupportImpl<OvertimeCount> implements OvertimeCountService {
    @Override
    public Long getSameUYMId(Long userId, String year, String month) {
        return (Long) getSession().createQuery("SELECT o.id FROM OvertimeCount o WHERE o.user.id = :userId AND o.year = :year AND  o.month = :month")
                .setParameter("userId",userId)
                .setParameter("year", year)
                .setParameter("month", month)
                .uniqueResult();
    }

    @Override
    public Double[] getTimeByUserIdAndYear(Long userId, String year) {
        List<OvertimeCount> overtimeCounts = getSession().createQuery("FROM OvertimeCount o WHERE  o.user.id = :userId AND o.year = :year")
                .setParameter("userId", userId)
                .setParameter("year", year)
                .list();
        Double[] result = new Double[12];
        if(overtimeCounts.size() > 0){
            for(OvertimeCount oc : overtimeCounts){
                if(oc.getMonth().startsWith("0")){
                    result[Integer.parseInt(oc.getMonth().substring(1)) - 1] = oc.getHours();
                }
                else {
                    result[Integer.parseInt(oc.getMonth()) - 1] = oc.getHours();
                }
            }
        }
        return result;
    }

    @Override
    public Double[] getTimeByYear(String year) {
        List<OvertimeCount> overtimeCounts = getSession().createQuery("FROM OvertimeCount o WHERE  o.year = :year")
                .setParameter("year", year)
                .list();
        Double[] result = new Double[12];
        if(overtimeCounts.size() > 0){
            for(OvertimeCount oc : overtimeCounts){
                if(oc.getMonth().startsWith("0")){
                    if(null == result[Integer.parseInt(oc.getMonth().substring(1)) - 1]){
                        result[Integer.parseInt(oc.getMonth().substring(1)) - 1] = oc.getHours();
                    }
                    else {
                        result[Integer.parseInt(oc.getMonth().substring(1)) - 1] += oc.getHours();
                    }
                }
                else {
                    if(null == result[Integer.parseInt(oc.getMonth()) - 1]){
                        result[Integer.parseInt(oc.getMonth()) - 1] = oc.getHours();
                    }else {
                        result[Integer.parseInt(oc.getMonth()) - 1] += oc.getHours();
                    }
                }
            }
        }
        return result;
    }
}
