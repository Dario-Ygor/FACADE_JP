public class ValidationService {
    public void validarLead(LeadDTO lead) {
        if (lead.nome == null || lead.nome.isBlank())
            throw new IllegalArgumentException("Nome inválido");
        if (lead.email == null || !lead.email.contains("@"))
            throw new IllegalArgumentException("Email inválido");
        System.out.println("[Validation] Lead válido: " + lead.nome);
    }
}
