package backend.service;

import backend.entity.User;

public interface UserService
{
    // 登录账户
    User login(User user);

    // 注册账户
    int register(User user);

    // 修改用户信息
    int updateUser(User user);

    // 查询用户
    User select(User user);

    // 修改密码
    int updatePassword(User user);

    // 根据用户名查询用户
    User selectByUsername(User user);
}
