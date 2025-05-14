package backend.service.Impl;


import backend.entity.Category;
import backend.entity.User;
import backend.mapper.CategoryMapper;
import backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int addCategory(Category category)
    {
        return categoryMapper.addCategory(category);
    }

    @Override
    public Category selectCategoryByType(Category category)
    {
        return categoryMapper.selectCategoryByType(category);
    }

    @Override
    public int updateCategory(Map<String, String> map)
    {
        return categoryMapper.updateCategory(map);
    }

    @Override
    public int deleteCategory(Category category)
    {
        return categoryMapper.deleteCategory(category);
    }

    @Override
    public List<Category> selectAllCategory(User user)
    {
        return categoryMapper.selectAllCategory(user);
    }
}
