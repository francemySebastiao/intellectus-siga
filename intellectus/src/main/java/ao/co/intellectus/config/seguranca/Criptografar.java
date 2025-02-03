package ao.co.intellectus.config.seguranca;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Criptografar {
	
		public static void main() {
			String senha = "a senha";
			senha = encriptografar(senha);
			System.out.println(senha);
		}

	public static String encriptografar(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
