package backend.mapper;


import backend.entity.Category;
import backend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper
{
    // 根据 type 获取分类，并判断是否存在该分类
    @Select("select * from category where type = #{type} and username = #{username}")
    Category selectCategoryByType(Category category);

    // 新增分类
    @Insert("insert into category(type, username) values(#{type},#{username})")
    int addCategory(Category category);

    // 删除分类
    @Delete("delete from category where type = #{type} and username = #{username}")
    int deleteCategory(Category category);

    // 更新分类
    @Update("update category set type = #{newType} where type = #{oldType} and username = #{username}")
    int updateCategory(Map<String, String> map);

    // 获取所有分类
    @Select("select * from category where username = #{username}")
    List<Category> selectAllCategory(User user);
}
