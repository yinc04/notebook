package com.test1;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources", // 指定 feature 文件的位置
        glue = {"com.test1"}, // 指定步骤定义所在的包
        plugin = {"pretty", "html:target/cucumber-reports.html"} // 输出报告格式
)
public class RunCucumberTest {
}
