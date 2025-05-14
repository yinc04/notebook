package backend.service.Impl;


import backend.entity.User;
import backend.mapper.UserMapper;
import backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user)
    {
        return userMapper.login(user);
    }

    @Override
    public int register(User user)
    {
        return userMapper.register(user);
    }

    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    @Override
    public User select(User user)
    {
        return userMapper.select(user);
    }

    @Override
    public int updatePassword(User user)
    {
        return userMapper.updatePassword(user);
    }

    @Override
    public User selectByUsername(User user)
    {
        return userMapper.selectByUsername(user);
    }
}
