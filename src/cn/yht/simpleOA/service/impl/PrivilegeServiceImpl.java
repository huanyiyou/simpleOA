package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Privilege;
import cn.yht.simpleOA.service.PrivilegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by YHT on 2015/5/16.
 */
@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {
    @Override
    public List<Privilege> getTopList() {
        return getSession().createQuery(
                "FROM Privilege p WHERE p.parent IS NULL ORDER BY p.id")
                .list();
    }

    @Override
    public Collection<String> getAllUrls() {
        return getSession().createQuery(
                "SELECT distinct p.url FROM Privilege p WHERE p.url IS NOT NULL")
                .list();
    }
}
