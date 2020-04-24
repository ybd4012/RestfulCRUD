package com.review.rustfulcrud.controller;

import com.review.rustfulcrud.bean.Department;
import com.review.rustfulcrud.bean.Employee;
import com.review.rustfulcrud.servlet.DepartmentServlet;
import com.review.rustfulcrud.servlet.EmployeeServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeServlet employeeServlet;
    @Autowired
    DepartmentServlet departmentServlet;

    //员工列表
    @GetMapping(path = "/emp")
    public String getAllEmployee(HttpSession session, HttpServletRequest request){
        List<Employee> allEmployee = employeeServlet.getAllEmployee();
        for(Employee employee:allEmployee){
        System.out.println(employee+""+employee.getDepartment());
        }
        request.setAttribute("Allemps",allEmployee);
        session.setAttribute("mai_body_name","main_body_emp");
        return "main.html";
    }


    /**
     * 添加员工----1.跳转添加页面----2.点击添加发生Post请求完成添加
     */
    //1.跳转添加页面
    @GetMapping(path = "/addEmpPage")
    public String addEmpPage(HttpSession session,HttpServletRequest request){
        //查询所有部门
        List<Department> allDepartment = departmentServlet.getNCAllDepartment();
        request.setAttribute("allDepartment",allDepartment);
        session.setAttribute("mai_body_name","main_body_addEdit");
        return "main.html";
    }
    //2.发送Post请求添加
    @PostMapping(path = "/EmpSubmit")
    public String addEmpSubmit(HttpSession session,Employee employee){
        employeeServlet.addEpmployee(employee);
//        session.setAttribute("mai_body_name","main_body_addEdit");
        session.setAttribute("empMethod","emp_add");
        return "redirect:/emp";
    }
    //3.发送delete请求删除员工 并且返回员工列表...........................
    @DeleteMapping("/deleteEmpByEid")
    public String deleteEmpByEid(Employee employee){
        System.out.println(employee);
        employeeServlet.deleteEmpByEid(employee.getEid());
        return "redirect:/emp";
    }
    //4.来到empEdit页面
    @GetMapping("/toEmpPageEdit/{eid}")
    public String toEmpEdit(HttpSession session, @PathVariable("eid") Integer eid, Model model){
        List<Department> allDepartment = departmentServlet.getNCAllDepartment();
        model.addAttribute("allDepartment",allDepartment);
        Employee employee = employeeServlet.getEmployee(eid);
        model.addAttribute("employee",employee);
        session.setAttribute("mai_body_name","main_body_addEdit");
        return "main.html";
    }

    //5发送put请求完成添加
    @PutMapping(path = "/EmpSubmit")
    public String editEmp( Employee employee){
        employeeServlet.updateEmp(employee);
        return "redirect:/emp";
    }

    @GetMapping(path = "/project")
    public String getProject(){
        return "/component/github";
    }
}
