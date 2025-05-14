package backend.mapper;


import backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper
{
    // 登录账户
    User login(User user);

    // 注册账户
    @Insert("insert into user values(#{username},#{trueName},#{password},#{age},#{phone},#{email})")
    int register(User user);

    // 修改用户信息
    @Update("update user set trueName = #{trueName}, age = #{age},phone = #{phone}, email = #{email} where username = #{username}")
    int updateUser(User user);

    // 查询用户
    @Select("select * from user where username = #{username}")
    User select(User user);

    // 修改密码
    @Update("update user set password = #{password} where username = #{username}")
    int updatePassword(User user);

    // 根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User selectByUsername(User user);
}
