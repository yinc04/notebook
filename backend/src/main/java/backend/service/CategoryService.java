package backend.service;

import backend.entity.Category;
import backend.entity.User;

import java.util.List;
import java.util.Map;

public interface CategoryService
{
    // 新增分类
    int addCategory(Category category);

    // 根据 type 获取分类，并判断是否存在该分类
    Category selectCategoryByType(Category category);

    // 修改分类
    int updateCategory(Map<String, String> map);

    // 删除分类
    int deleteCategory(Category category);

    // 根据账户名查询所有分类
    List<Category> selectAllCategory(User user);
}
