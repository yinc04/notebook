package controllertest;

import backend.controller.CategoryController;
import backend.entity.Category;
import backend.entity.Result;
import backend.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private Category testCategory;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        testCategory = new Category("Work", "user1");
    }

    @Test
    public void testAddCategory_ShouldReturnSuccess() throws Exception {
        // 给定：当调用 addCategory 返回 1
        when(categoryService.addCategory(any(Category.class))).thenReturn(1);

        // 当：发送 POST 请求 /category/add
        mockMvc.perform(post("/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCategory)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(Result.success(1).getCode()))
                .andExpect(jsonPath("$.message").value(Result.success(1).getMessage()))
                .andExpect(jsonPath("$.data").value(1));

        // 验证：addCategory 是否被调用了一次
        verify(categoryService, times(1)).addCategory(any(Category.class));
    }
}
