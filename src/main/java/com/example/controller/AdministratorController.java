package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.servlet.http.HttpSession;

/**
 * AdministratorController クラスの定義
 * @author yamaomarina
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;


    /** 
    「administrator/insert.html」にフォワードする処理を記述する。
    ※フォームを引数で受け取ることで従業員登録する際のリクエストパラメータが格納される
    InsertAdministratorForm オブジェクトが Model オブジェクト(リクエストスコープ)に⾃動的に格納されます。
    */

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }

    /**
     * 
     * @param form
     * @return
     * 管理者情報を登録する。
     */

    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";

    }

    /**
     * login.htmlにフォワードする処理
     * 入力値エラー処理 
     */

    @ModelAttribute
    public LoginForm setUpForm(){
        return new LoginForm();
    }

    /**ログイン処理をする */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "administrator/login";
    }

    @Autowired
    private HttpSession session;

    
    @PostMapping("/login")
    public String login(LoginForm form,Model model){


        /**administratorServiceのload()メソッドを呼び、Administratorオブジェクトを戻り値として受け取る */
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        /**戻り値がnullの場合の処理 */
        if(administrator == null){
            model.addAttribute("errorMessage","メールアドレスまたはパスワードが不正です");
            return "administrator/login";
        }else{
            session.setAttribute("administratorName", administrator.getName());
            return "redirect:/employee/showList";
        }
        
        
    }

    /**ログアウトをする */
    @GetMapping("/logout")
    public String logout(LoginForm form){
        session.invalidate();
        return "redirect:/";
    }

}
