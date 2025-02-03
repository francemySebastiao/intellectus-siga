package ao.co.intellectus.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Scanner;

public class DecodeBase64File {

	public static void main(String[] args) {
        String filePath = "Registo1.b64";
        
        try {
            // Lê o conteúdo do arquivo base64
            String base64Data = readFileAsString(filePath);

            System.out.println("Assa " + LocalDateTime.now());
            

            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	private static String readFileAsString(String filePath) throws FileNotFoundException {
        StringBuilder contentBuilder = new StringBuilder();
        Scanner scanner = new Scanner(new FileInputStream(filePath));

        while (scanner.hasNextLine()) {
            contentBuilder.append(scanner.nextLine());
        }

        scanner.close();

        return contentBuilder.toString();
    }
}
