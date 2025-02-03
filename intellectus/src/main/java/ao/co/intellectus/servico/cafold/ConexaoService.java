package ao.co.intellectus.servico.cafold;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.servico.exception.ConexaoException;

@Service
public class ConexaoService {
	private String driver;
	private String URL;
	private String USER;
	private String SENHA;
	@Autowired
	private RedeService redeService;


	public Connection getConexaoLocal() throws ClassNotFoundException, SQLException {
		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		URL   = "jdbc:sqlserver://127.0.0.1:1433;databaseName=siga_intellectus";
		USER  = "sa";
		SENHA = "Brasilangola@2310"; 
		Class.forName(driver);
		return DriverManager.getConnection(URL, USER, SENHA);
	}
	
	
	public Connection getConexaoMySqlLocal() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://iberao01.ibername.com:3306/ugsedao_wp350","ugsedao_moodle", "angolaunasp13");
	}
	

	public Connection getConexaoAzure() throws ClassNotFoundException, SQLException {
		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		URL = "jdbc:sqlserver://appdb02.database.windows.net:1433;databaseName=siga_intellectus";
		USER = "dbadmin";
		SENHA = "DB@2020***";
		Class.forName(driver);
		return DriverManager.getConnection(URL, USER, SENHA);
	}
	
	/*public Connection getConexaoActual() {
		try {
			String ip = this.redeService.getIp();
			String hostname = this.redeService.getNomeHost();
			if(ip.equalsIgnoreCase("102.133.152.0") || ip.equalsIgnoreCase("102.133.154.32")) {
				return this.getConexaoAzure();
			}else if(hostname.equalsIgnoreCase("cr1.southafricanorth1-a.control.database.windows.net")) {
				return this.getConexaoAzure();
			}else if(hostname.equalsIgnoreCase("appdb02.database.windows.net")) {
				return this.getConexaoAzure();
			}else {
				return this.getConexaoLocal();				
			}
		} catch (UnknownHostException | ClassNotFoundException | SQLException e) {
			throw new ConexaoException("Erro ao se conectar com a Base de dados\nErro:  "+e.getMessage());
		}
	}*/
	
	@SuppressWarnings("unused")
	public Connection getConexaoActual() {
		try {
			String ip = this.redeService.getIp();
			String hostname = this.redeService.getNomeHost();
			/*
			if((hostname.equalsIgnoreCase("NTINT05")) || (hostname.equalsIgnoreCase("NTINT03"))) {
				return this.getConexaoLocal();				
			}else {
				return this.getConexaoAzure();
			}*/
			return this.getConexaoLocal();
			
		} catch (UnknownHostException | ClassNotFoundException | SQLException e) {
			throw new ConexaoException("Erro ao se conectar com a Base de dados\nErro:  "+e.getMessage());
		}
	}

}
