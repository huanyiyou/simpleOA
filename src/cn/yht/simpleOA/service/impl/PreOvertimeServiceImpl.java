package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.PageBean;
import cn.yht.simpleOA.model.PreOvertime;
import cn.yht.simpleOA.service.PreOvertimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YHT on 2015/5/19.
 */
@Service
@Transactional
public class PreOvertimeServiceImpl extends DaoSupportImpl<PreOvertime> implements PreOvertimeService {
    @Override
    public List<PreOvertime> findAllByUserId(Long userId) {
        return getSession().createQuery(
                "FROM PreOvertime p WHERE p.user.id = :id")
                .setParameter("id", userId)
                .list();
    }

    @Override
    public List<PreOvertime> findAllSubmitted() {
        return getSession().createQuery(
                "FROM PreOvertime p WHERE p.submitted = True")
                .list();
    }

    @Deprecated
    @Override
    public PageBean getPageBeanByUserId(int pageNum, int pageSize, Long userId) {
        List list = getSession().createQuery(
                "FROM PreOvertime p WHERE p.user.id = :userId")
                .setParameter("userId", userId)
                .setFirstResult((pageNum-1)*pageSize)
                .setMaxResults(pageSize)
                .list();

        Long count = (Long) getSession().createQuery(
                "SELECT COUNT(*) FROM PreOvertime p WHERE p.user.id = :userId")
                .setParameter("userId", userId)
                .uniqueResult();

        return new PageBean(pageNum, pageSize, list, count.intValue());
    }
}
