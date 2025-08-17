public class CommunicationService {
    public void enviarEmail(String para, String assunto, String corpo) {
        System.out.println("[Email] Para: " + para + " | Assunto: " + assunto + " | Corpo: " + corpo);
    }
    public void enviarSms(String para, String texto) {
        System.out.println("[SMS] Para: " + para + " | " + texto);
    }
}
