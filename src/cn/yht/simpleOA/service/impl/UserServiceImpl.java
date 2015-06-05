package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2015/5/13.
 */
@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {
    @Override
    public User findByLoginNameAndPassword(String loginName, String password) {
        String md5Digest = DigestUtils.md5Hex(password);
        return (User) getSession().createQuery("FROM User u WHERE u.loginName = :loginName AND u.password = :password")
                .setParameter("loginName", loginName)
                .setParameter("password", md5Digest)
                .uniqueResult();
    }

    @Override
    public boolean hasSameLoginName(User user) {
        List<User> users = getSession().createQuery("FROM User u WHERE u.loginName = :loginName").setParameter("loginName", user.getLoginName()).list();
        if(users.size() > 0){
            return true;
        }else {
            return false;
        }
    }
}
