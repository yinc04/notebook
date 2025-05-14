package backend.mapper;


import backend.entity.Category;
import backend.entity.Event;
import backend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper
{
    // 新增事件
    @Insert("insert into event (title, startDate, endDate, content, level, state,type,username) " +
            "values (#{title}, #{startDate}, #{endDate}, #{content}, #{level}, #{state},#{type},#{username})")
    int addEvent(Event event);

    // 查询用户所有事件
    @Select("select * from event where username = #{username}")
    List<Event> selectAllEvent(User user);

    // 删除事件
    @Delete("delete from event where eventId = #{eventId}")
    int deleteEvent(Event event);

    // 修改事件
    @Update("update event set title = #{title}, startDate = #{startDate}, endDate = #{endDate}, " +
            "content = #{content}, level = #{level}, state = #{state},type=#{type} where eventId = #{eventId}")
    int updateEvent(Event event);

    // 根据分类查询事件
    @Select("select * from event where type = #{type}")
    List<Event> selectEventByType(Category category);
}
