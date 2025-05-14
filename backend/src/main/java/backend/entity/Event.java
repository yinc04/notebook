package backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event
{
    private int eventId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDate;
    private String content;
    private String level;
    private String state;
    private String type;
    private String username;
}
