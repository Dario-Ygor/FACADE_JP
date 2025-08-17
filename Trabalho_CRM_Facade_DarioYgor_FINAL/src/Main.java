public class Main {
    public static void main(String[] args) {
        CRMFacade facade = new CRMFacade(
            new ValidationService(),
            new ScoreService(),
            new LeadService(),
            new CustomerService(),
            new OpportunityService(),
            new CommunicationService(),
            new AuditService()
        );

        String leadId = facade.cadastrarLead(new LeadDTO("Alex", "alex@empresa.com", "+5599999999"));

        if (facade.qualificarLead(leadId, 80)) {
            String clienteId = facade.converterLeadEmCliente(leadId);
            facade.registrarInteracao(clienteId, "EMAIL", "Olá! Podemos agendar uma demonstração?");
            String opId = facade.criarOportunidade(clienteId, "Plano Anual CRM", 4999.90);
            facade.fecharVenda(opId);
        } else {
            System.out.println("[Main] Lead não alcançou score mínimo.");
        }
    }
}
