package backend.controller;


import backend.entity.Result;
import backend.entity.User;
import backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController
{
    @Autowired
    private UserService userService;

    // 登录账户
    @PostMapping("/login")
    public Result login(@RequestBody User user)
    {
        return Result.success(userService.login(user));
    }

    // 注册账户
    @PostMapping("/register")
    public Result register(@RequestBody User user)
    {
        return Result.success(userService.register(user));
    }

    // 更新账户信息
    @PostMapping("/update")
    public Result update(@RequestBody User user)
    {
        return Result.success(userService.updateUser(user));
    }

    // 查询账户信息
    @PostMapping("/select")
    public Result select(@RequestBody User user)
    {
        return Result.success(userService.select(user));
    }

    // 修改密码
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody User user)
    {
        return Result.success(userService.updatePassword(user));
    }

    // 根据用户名查询用户
    @PostMapping("/selectByUsername")
    public Result selectByUsername(@RequestBody User user)
    {
        return Result.success(userService.selectByUsername(user));
    }
}
