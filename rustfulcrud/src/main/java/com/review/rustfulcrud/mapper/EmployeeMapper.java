package com.review.rustfulcrud.mapper;

import com.review.rustfulcrud.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 通过员工ID查询一个
     * @param eid
     * @return
     */
    @Select("SELECT*FROM`employee`WHERE`eid`=#{eid}")
    public Employee getEmployeeByEid(Integer eid);

    //通过部门ID查询到一个
    @Select("SELECT*FROM`employee`WHERE`d_id`=#{did}")
    public Employee getEmployeeByDid(Integer d_id);

    //非关联查询所有
    @Select("SELECT*FROM`employee`")
    public List<Employee> getNoConeAllEmp();

    //通过关联部门ID查询一个
    @Select("SELECT*FROM`employee`WHERE`eid`=#{eid}")
    @Results({
            @Result(property = "d_id",column = "d_id"),
            @Result(property = "department", column = "d_id",
                    one = @One(select = "com.review.rustfulcrud.mapper.DepartmentMapper.getDepartmentByDid")
            )
    })
    public Employee getEmployee(Integer eid);

    //关联查询 所有
    @Select("SELECT*FROM`employee`")
    @Results(id="empMap",value = {
            @Result(id = true,property = "eid",column = "eid"),
            @Result(property = "d_id", column = "d_id"),
            @Result(property = "email",column = "email"),
            @Result( property= "department", column = "d_id",
                     one = @One(select = "com.review.rustfulcrud.mapper.DepartmentMapper.getDepartmentByDid",fetchType = FetchType.EAGER)
            )
    })
    public List<Employee> getAllEmployee();

    //添加员工 eid自增
    @Insert("INSERT INTO`employee`(`lastName`,`email`,`gender`,`d_id`)VALUES(#{lastName},#{email},#{gender},#{d_id})")
    public void addEmployee(Employee employee);
    //删除一个员工
    @Delete("DELETE FROM`employee`WHERE`eid`=#{eid}")
    void  deleteEmpByEid(Integer eid);
    //修改一个员工
    @Update("UPDATE`employee`SET`lastName` = #{lastName},`d_id`=#{d_id},`gender`=#{gender},`email`=#{email} WHERE`eid`=#{eid}")
    void updateEmp(Employee employee);
}
