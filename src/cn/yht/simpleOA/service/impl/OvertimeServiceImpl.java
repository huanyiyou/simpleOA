package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Overtime;
import cn.yht.simpleOA.service.OvertimeService;
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
}
