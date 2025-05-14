package backend.controller;


import backend.entity.Category;
import backend.entity.Event;
import backend.entity.Result;
import backend.entity.User;
import backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "*")
public class EventController
{
    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public Result addEvent(@RequestBody Event event)
    {
        return Result.success(eventService.addEvent(event));
    }

    @PostMapping("/selectAll")
    public Result selectAllEvent(@RequestBody User user)
    {
        return Result.success(eventService.selectAllEvent(user));
    }

    @PostMapping("/delete")
    public Result deleteEvent(@RequestBody Event event)
    {
        return Result.success(eventService.deleteEvent(event));
    }

    @PostMapping("/update")
    public Result updateEvent(@RequestBody Event event)
    {
        return Result.success(eventService.updateEvent(event));
    }

    @PostMapping("/selectEventByType")
    public Result selectEventByType(@RequestBody Category category)
    {
        return Result.success(eventService.selectEventByType(category));
    }
}
