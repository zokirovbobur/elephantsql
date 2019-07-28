package com.bbro.online_pg_test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

@RestController
@RequestMapping
public class PgService {
    private static final String HOST = "raja.db.elephantsql.com";
    private static final String USER = "azhbcnce";
    private static final String PASSWORD = "vVIVpFDggQ8XdjTZMiTSYuVDZ0c6DDP4";
    private static final String URL =
            "jdbc:postgresql://raja.db.elephantsql.com:5432/azhbcnce";
    public static final String DB = USER;

    @GetMapping
    public String connection(){
        StringBuilder result = new StringBuilder();
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(URL, USER, PASSWORD);
            String sql = "select * from test";
            ResultSet resultSet = c.createStatement().executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String string = resultSet.getString(2);
                result.append(id).append(" : ").append(string);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return result.toString();
    }
}
