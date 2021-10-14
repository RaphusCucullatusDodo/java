package cn.raphuscucullatus.mybatis_plus;

import cn.raphuscucullatus.mybatis_plus.mapper.EmployeeMapper;
import cn.raphuscucullatus.mybatis_plus.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        Employee employee = employeeMapper.selectById(1);
        System.out.println(employee);
    }

}
