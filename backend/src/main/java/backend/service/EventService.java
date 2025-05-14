package backend.service;

import backend.entity.Category;
import backend.entity.Event;
import backend.entity.User;

import java.util.List;

public interface EventService
{
    // 新增事件
    int addEvent(Event event);

    // 查询所有事件
    List<Event> selectAllEvent(User user);

    // 删除事件
    int deleteEvent(Event event);

    // 修改事件
    int updateEvent(Event event);

    // 根据分类查询事件
    List<Event> selectEventByType(Category category);
}
