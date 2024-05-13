package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;


/** 
 * AdministratorRepository クラスの作成
 * @author yamaomarina
*/
@Repository
public class AdministratorRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i)->{
        Administrator administrator = new Administrator();

        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mailAddress"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**管理者情報を挿⼊する。 */
    public void insert(Administrator administrator){
        String insertSql="INSERT INTO administrators(id,name,mail_address,passowrd) VALUES(:id,:name,:mailAddress,:password);";
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        template.update(insertSql,param);
    }


    /**メールアドレスとパスワードから管理者情報を取得する(1 件も存在しない場合は null を返す※)。 */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        String sql = "SELECT id, name, mail_address, password FROM administrators WHERE mail_address= :mailAddress AND password= :password;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
        try {
            // １件も検索されなかったら例外が発⽣する
            return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
            } catch (Exception e) {
            // 例外が発⽣したら null を返す
            return null;
            }

    }

}

