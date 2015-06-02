package cn.yht.simpleOA.service;

import cn.yht.simpleOA.base.DaoSupport;
import cn.yht.simpleOA.model.User;

/**
 * Created by admin on 2015/5/13.
 */
public interface UserService extends DaoSupport<User>{
    User findByLoginNameAndPassword(String loginName, String password);
}
