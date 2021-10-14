package cn.raphuscucullatus.java.web.foundational.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/9/13 3:25
 * @since JDK8
 */
public class MyBatisGeneratorUtil {
    public static void main(String[] args) {
        ArrayList<String> warnings = new ArrayList<>();
        boolean overwrite = true;

        try {
            File generatorConfig = new File("D:\\JavaDevelop\\IDE\\workspace\\java\\java-web-foundational\\src\\main\\resources\\generatorConfig.xml");
            ConfigurationParser configurationParser = new ConfigurationParser(warnings);
            Configuration configuration = configurationParser.parseConfiguration(generatorConfig);
            DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, warnings);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
