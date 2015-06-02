package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Breaktime;
import cn.yht.simpleOA.model.BreaktimeCount;
import cn.yht.simpleOA.service.BreaktimeCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by YHT on 2015/5/29.
 */
@Service
@Transactional
public class BreaktimeCountServiceImpl extends DaoSupportImpl<BreaktimeCount> implements BreaktimeCountService {
    @Override
    public Long getSameUYMId(Long userId, String year, String month) {
        return (Long) getSession().createQuery("SELECT b.id FROM BreaktimeCount b WHERE b.user.id = :userId AND b.year = :year AND  b.month = :month")
                .setParameter("userId", userId)
                .setParameter("year", year)
                .setParameter("month", month)
                .uniqueResult();
    }
    @Override
    public Double[] getTimeByUserIdAndYear(Long userId, String year) {
        List<BreaktimeCount> breaktimeCounts = getSession().createQuery("FROM BreaktimeCount b WHERE  b.user.id = :userId AND b.year = :year")
                .setParameter("userId", userId)
                .setParameter("year", year)
                .list();
        Double[] result = new Double[12];
        if(breaktimeCounts.size() > 0){
            for(BreaktimeCount bc : breaktimeCounts){
                if(bc.getMonth().startsWith("0")){
                    result[Integer.parseInt(bc.getMonth().substring(1)) - 1] = bc.getHours();
                }else {
                    result[Integer.parseInt(bc.getMonth()) - 1] = bc.getHours();
                }
            }
        }
        return result;
    }



    @Override
    public Double[] getTimeByYear(String year) {
        List<BreaktimeCount> breaktimeCounts = getSession().createQuery("FROM BreaktimeCount b WHERE  b.year = :year")
                .setParameter("year", year)
                .list();
        Double[] result = new Double[12];
        if(breaktimeCounts.size() > 0){
            for(BreaktimeCount bc : breaktimeCounts){
                if(bc.getMonth().startsWith("0")){
                    if(null == result[Integer.parseInt(bc.getMonth().substring(1)) - 1]){
                        result[Integer.parseInt(bc.getMonth().substring(1)) - 1] = bc.getHours();
                    }
                    else {
                        result[Integer.parseInt(bc.getMonth().substring(1)) - 1] += bc.getHours();
                    }

                }else {
                    if(null == result[Integer.parseInt(bc.getMonth()) - 1]){
                        result[Integer.parseInt(bc.getMonth()) - 1] = bc.getHours();
                    }else {
                        result[Integer.parseInt(bc.getMonth()) - 1] += bc.getHours();
                    }
                }
            }
        }
        return result;
    }
}
