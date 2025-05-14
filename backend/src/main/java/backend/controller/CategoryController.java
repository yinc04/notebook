package backend.controller;


import backend.entity.Category;
import backend.entity.Result;
import backend.entity.User;
import backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result addCategory(@RequestBody Category category)
    {
        return Result.success(categoryService.addCategory(category));
    }

    @PostMapping("/selectCategoryByType")
    public Result selectCategoryByType(@RequestBody Category category)
    {
        return Result.success(categoryService.selectCategoryByType(category));
    }

    @PostMapping("/update")
    public Result updateCategory(@RequestBody Map<String, String> map)
    {
        // 参数示例： { "oldType": "美食", "newType": "日记", "username": "小天" }
        return Result.success(categoryService.updateCategory(map));
    }

    @PostMapping("/delete")
    public Result deleteCategory(@RequestBody Category category)
    {
        return Result.success(categoryService.deleteCategory(category));
    }

    @PostMapping("/selectAllCategory")
    public Result getAllCategory(@RequestBody User user)
    {
        return Result.success(categoryService.selectAllCategory(user));
    }
}
