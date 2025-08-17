public class AuditService {
    public void registrar(String evento, String detalhe) {
        System.out.println("[Audit] " + evento + " -> " + detalhe);
    }
}
