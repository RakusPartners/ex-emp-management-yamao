package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * EmployeeServiceの定義
 * @author yamaomarina
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**従業員情報を全取得する */
    public List<Employee> showList(){
        return employeeRepository.findAll();
    }

    /**従業員情報を取得する */

    public Employee showDetail(Integer id){
        return employeeRepository.load(id);
    }

    /**従業員情報を更新する */
    public void update(Employee employee){
        employeeRepository.update(employee);
    }

}
