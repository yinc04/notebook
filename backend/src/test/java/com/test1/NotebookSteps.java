package com.test1;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertTrue;

public class NotebookSteps {

    private Notebookpage notebookPage = new Notebookpage();

    @Given("I am on the notebook page")
    public void i_am_on_the_notebook_page() {
        // 初始化或导航到笔记本页面的逻辑
    }

    @When("I click on {string} button")
    public void i_click_on_button(String buttonName) {
        if ("Add Category".equals(buttonName)) {
            notebookPage.clickAddCategoryButton();
        }
    }

    @When("I enter {string} as the category name")
    public void i_enter_as_the_category_name(String categoryName) {
        notebookPage.enterCategoryName(categoryName);
    }

    // 新增的步骤定义以处理 "Confirm" 按钮点击
    @When("I click on {string}")
    public void i_click_on(String buttonName) {
        if ("Confirm".equals(buttonName)) {
            notebookPage.clickConfirmButton();
        }
    }

    @Then("the new category {string} should be added to the list")
    public void the_new_category_should_be_added_to_the_list(String expectedCategoryName) {
        assertTrue(notebookPage.isCategoryAdded(expectedCategoryName));
    }
}
