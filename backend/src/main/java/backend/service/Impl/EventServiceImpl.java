package backend.service.Impl;


import backend.entity.Category;
import backend.entity.Event;
import backend.entity.User;
import backend.mapper.EventMapper;
import backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService
{
    @Autowired
    private EventMapper eventMapper;

    @Override
    public int addEvent(Event event)
    {
        return eventMapper.addEvent(event);
    }

    @Override
    public List<Event> selectAllEvent(User user)
    {
        return eventMapper.selectAllEvent(user);
    }

    @Override
    public int deleteEvent(Event event)
    {
        return eventMapper.deleteEvent(event);
    }

    @Override
    public int updateEvent(Event event)
    {
        return eventMapper.updateEvent(event);
    }

    @Override
    public List<Event> selectEventByType(Category category)
    {
        return eventMapper.selectEventByType(category);
    }
}
