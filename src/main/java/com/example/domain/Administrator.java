package com.example.domain;

/**
 * 管理者情報を表すドメイン 
 * @author yamaomarina
*/

public class Administrator {

    /**administrators テーブルのID */
    private Integer id;

    /**administrators テーブルの名前 */
    private String name;

    /**administrators テーブルのメールアドレス */
    private String mailAddress;

    /**administrators テーブルのパスワード */
    private String password;

    public Administrator(Integer id, String name, String mailAddress, String password) {
        this.id = id;
        this.name = name;
        this.mailAddress = mailAddress;
        this.password = password;
    }


    public Administrator() {
    }


    @Override
    public String toString() {
        return "Administrator [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    

}

