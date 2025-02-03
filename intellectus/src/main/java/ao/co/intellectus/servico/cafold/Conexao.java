package ao.co.intellectus.servico.cafold;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=siga_intellectus";
	//private String URL = "jdbc:sqlserver://172.16.10.216:1433;databaseName=siga_intellectus";
//    private String URL = "jdbc:sqlserver://100.96.1.3:1433;databaseName=siga_intellectus";
    private String USER = "sa";
//    private String SENHA = "Aktotvs@@angola!";
    private String SENHA = "lazaro4321DCBA:_";
    private Connection conn;
 
    public Conexao() {
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConn() {
        return conn;
    }
}