package com.review.rustfulcrud;

import com.review.rustfulcrud.bean.Department;
import com.review.rustfulcrud.bean.Employee;
import com.review.rustfulcrud.mapper.DepartmentMapper;
import com.review.rustfulcrud.mapper.EmployeeMapper;
import com.review.rustfulcrud.servlet.DepartmentServlet;
import com.review.rustfulcrud.servlet.EmployeeServlet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class RustfulcrudApplicationTests {
    @Autowired
    EmployeeServlet employeeServlet;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentServlet departmentServlet;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    DataSource dataSource;

    //redis
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    void contextLoads() {
    }
    @Test
    public void test01(){

        List<Department> allDepartment = departmentMapper.getAllDepartment();
        for(Department department:allDepartment){
            System.out.println(department);
            System.out.println("______________________");
            for (Employee employee: department.getEmployees()){
                System.out.println(employee);
            }
        }
    }
    //数据源测试
    @Test
    public void test02() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println("______________");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    public void test03(){
        Employee employee = employeeMapper.getEmployee(31);
        System.out.println("取得的对象");
        System.out.println(employee);
        redisTemplate.opsForValue().set("employee",employee);
        employee = (Employee) redisTemplate.opsForValue().get("employee");
        System.out.println("huanchundeduix");
        System.out.println(employee);

    }

}
