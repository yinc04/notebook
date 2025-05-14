package backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    private String username;
    private String trueName;
    private String password;
    private int age;
    private String phone;
    private String email;
    private List<Category> category;
}
