package ao.co.intellectus.servico.cafold;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private String URL = "jdbc:sqlserver://localhost:1433;databaseName=siga_intellectus";
	private String URL = "jdbc:sqlserver://192.168.16.8:1433;databaseName=siga_intellectus";
//    private String URL = "jdbc:sqlserver://100.96.1.3:1433;databaseName=siga_intellectus";
    private String USER = "sa";
    //private String SENHA = "Aktotvs@@angolanb!";
    private String SENHA = "Aktotvs@@angola!";
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