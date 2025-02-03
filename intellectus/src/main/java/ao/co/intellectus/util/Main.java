package ao.co.intellectus.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ao.co.intellectus.model.AuditFile;

public class Main {

	public static void main(String[] args) {
        // Criar uma instância da classe AuditFileDTO e definir os valores
        AuditFile auditFile = new AuditFile();
        // Definir os valores dos campos header, masterFiles, sourceDocuments, etc.

        try {
            // Inicializar o contexto JAXB para a classe AuditFileDTO
            JAXBContext context = JAXBContext.newInstance(AuditFile.class);

            // Criar um Marshaller para transformar a instância em XML
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Transformar a instância em XML e imprimir na saída padrão
            marshaller.marshal(auditFile, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
