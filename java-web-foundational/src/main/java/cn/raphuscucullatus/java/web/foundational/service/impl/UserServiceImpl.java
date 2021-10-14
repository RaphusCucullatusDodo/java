package cn.raphuscucullatus.java.web.foundational.service.impl;


import cn.raphuscucullatus.java.web.foundational.bean.vo.UserVO;
import cn.raphuscucullatus.java.web.foundational.dao.UserDao;
import cn.raphuscucullatus.java.web.foundational.dao.impl.CustomQueryRunnerUserDaoImpl;
import cn.raphuscucullatus.java.web.foundational.dao.impl.StatementUserDaoImpl;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.exception.BusinessException;
import cn.raphuscucullatus.java.web.foundational.service.UserService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * 登入业务逻辑实现
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/211:51
 * @since JDK8
 */
public class UserServiceImpl implements UserService {
    /**
     * Service依赖Dao
     */
    private final UserDao userDao = new CustomQueryRunnerUserDaoImpl();

    @Override
    public boolean login(User user) {
        if (user != null && user.getName() != null && user.getPassword() != null) {
            List<User> userList = userDao.select(user);
//            System.out.println("满足条件的用户人数为:"+userList.size());
            if (userList != null && userList.size() == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean register(User user) {
        int row = userDao.insert(user);
        if (1 == row) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserVO> findAllUser() {
        List<UserVO> userVOList = null;
        List<User> userList = userDao.select(null);
        if (null != userList) {
            userVOList = new ArrayList<>();
            for (User user : userList) {
//                将user对象装换为uservo对象
                UserVO userVO = new UserVO(user.getId(), user.getName(), user.getPassword()
                        , user.getCreateDate().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd hh:mm:ss")))
                        , user.getUpdateDate().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd hh:mm:ss"))));
                userVOList.add(userVO);
            }
        }
        return userVOList;
    }

    @Override
    public boolean findByName(String name) {
        try {
            List<User> userList = userDao.select(new User(name));
            if (userList == null) {
                return false;
            } else {
                throw new BusinessException("用户名已存在");
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
