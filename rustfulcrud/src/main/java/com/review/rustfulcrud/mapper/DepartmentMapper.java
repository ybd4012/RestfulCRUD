package com.review.rustfulcrud.mapper;

import com.review.rustfulcrud.bean.Department;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface DepartmentMapper {
    /**
     * 通过部门ID查询到一个部门
     * @param d_id
     * @return
     */
    @Select("SELECT*FROM`department`WHERE`d_id`=#{d_id}")
    public Department getDepartmentByDid(Integer d_id);

//    查询所有
    @Select("SELECT*FROM`department`")
    public List<Department> getNCAllDepartment();
    /**
     * 联表查询所有
     * 一对多关系
     * fetchType取出的类型 一对一 立即加载   一对多采用 懒加载
     */
    @Select("SELECT*FROM`department`")
    //id作为映射表的id 这里的id和value可以省略
    @Results(id = "departmentMap",value = {
            //id为true表示主键
            @Result(id = true, property = "d_id",column = "d_id"),
            @Result(property = "departmentName",column = "departmentName"),
            //employees是department内的一个属性,column作为select这个方法的查询参数
            @Result(property = "employees",column = "d_id",
            many = @Many(select = "com.review.rustfulcrud.mapper.EmployeeMapper.getEmployeeByDid",fetchType = FetchType.LAZY)
            )
    })
    public List<Department> getAllDepartment();

}
