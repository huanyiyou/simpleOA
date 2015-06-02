package cn.yht.simpleOA.service;

import cn.yht.simpleOA.base.DaoSupport;
import cn.yht.simpleOA.model.Privilege;

import java.util.Collection;
import java.util.List;

/**
 * Created by YHT on 2015/5/16.
 */
public interface PrivilegeService extends DaoSupport<Privilege> {
    List<Privilege> getTopList();

    Collection<String> getAllUrls();
}
