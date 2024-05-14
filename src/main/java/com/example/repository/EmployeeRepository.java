package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

/**
 * EmployeeRepository クラスの作成 
 * @author yamaomarina
 */
@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER=(rs,i)->{
        Employee employee = new Employee();

        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));

        return employee;

    };

    /** 従業員⼀覧情報を⼊社⽇順(降順)で取得
        する(従業員が存在しない場合はサイズ 0
        件の従業員⼀覧を返す)。*/
    public List<Employee> findAll(){
        String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date DESC";
        try{
            List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
            return employeeList;
        }catch(Exception e){
            return null;
        }
    }

    /** 主キーから従業員情報を取得する(従業員
        が存在しない場合は Spring が⾃動的に例
        外を発⽣します)。*/
    public Employee load(Integer id){
        String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE id=:id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    /** 従業員情報を変更する(id カラムを除いた
        従業員情報の全てのカラムを更新できる
        ような SQL を発⾏する)。全⾏更新されな
        いように Where 句の指定を考える。*/
    public void update(Employee employee){
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String upadateSql = "UPDATE employees SET name=:name,image=:image,gender=:gender,hire_date=:hireDate,mail_address=:mailAddress,zip_code=:zipCode,address=:address,telephone=:telephone,salary=:salary,characteristics=:characteristics,dependents_count=:dependentsCount WHERE id=:id;";
        template.update(upadateSql,param); 
    }



}

