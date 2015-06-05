package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Breaktime;
import cn.yht.simpleOA.service.BreaktimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YHT on 2015/5/19.
 */
@Service
@Transactional
public class BreaktimeServiceImpl extends DaoSupportImpl<Breaktime> implements BreaktimeService {
    @Override
    public double[] getSumByUserIdAndYear(Long userId, String year) {
        List<Breaktime> breaktimes = getSession().createQuery(
                "FROM Breaktime b WHERE b.user.id = :userId AND b.year = :year")
                .setParameter("userId", userId)
                .setParameter("year", year)
                .list();
        double[] result = new double[12];
        if(breaktimes.size() > 0){
            for(Breaktime b : breaktimes){
                if(b.getMonth().startsWith("0")){
                    int index = Integer.parseInt(b.getMonth().substring(1)) - 1;
                    result[index] += b.getDuration();
                }
                else {
                    int index = Integer.parseInt(b.getMonth()) - 1;
                    result[index] += b.getDuration();
                }
            }
        }
        return result;
    }

    @Override
    public double[] getSumByYear(String year) {
        List<Breaktime> breaktimes = getSession().createQuery(
                "FROM Breaktime b WHERE b.year = :year")
                .setParameter("year", year)
                .list();
        double[] result = new double[12];
        if(breaktimes.size() > 0){
            for(Breaktime b : breaktimes){
                if(b.getMonth().startsWith("0")){
                    int index = Integer.parseInt(b.getMonth().substring(1)) - 1;
                    result[index] += b.getDuration();
                }
                else {
                    int index = Integer.parseInt(b.getMonth()) - 1;
                    result[index] += b.getDuration();
                }
            }
        }
        return result;
    }
}
