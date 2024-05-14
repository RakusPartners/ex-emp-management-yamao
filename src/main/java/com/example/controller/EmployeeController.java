package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

/**
 * EmployeeControllerクラスの定義
 * @author yamaomarina
 */

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧を出力する
     * @param model
     * @return
     */
    @GetMapping("/showList")
    public String showList(Model model){
        model.addAttribute("employeeList", employeeService.showList());
        return "employee/list";
    }

    @GetMapping("/showDetail")
    public String showDetail(String id,Model model,UpdateEmployeeForm form){
        int numId = Integer.valueOf(id);
        Employee employee = employeeService.showDetail(numId);

        model.addAttribute("employee", employee);
        return "employee/detail";
    }

}
