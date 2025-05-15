package com.test1;

import java.util.ArrayList;
import java.util.List;

public class Notebookpage {
    private List<String> categories = new ArrayList<>();

    public void clickAddCategoryButton() {
        // 模拟点击添加分类按钮的逻辑
    }

    public void enterCategoryName(String categoryName) {
        // 模拟输入分类名称的逻辑
    }

    public void clickConfirmButton() {
        // 模拟点击确认按钮的逻辑，并添加分类到列表中
        categories.add("Work");
    }

    public boolean isCategoryAdded(String categoryName) {
        return categories.contains(categoryName);
    }
}
