public class CRMFacade {
    private final ValidationService validation;
    private final ScoreService score;
    private final LeadService lead;
    private final CustomerService customer;
    private final OpportunityService opportunity;
    private final CommunicationService comms;
    private final AuditService audit;

    public CRMFacade(ValidationService validation, ScoreService score,
                     LeadService lead, CustomerService customer,
                     OpportunityService opportunity, CommunicationService comms,
                     AuditService audit) {
        this.validation = validation;
        this.score = score;
        this.lead = lead;
        this.customer = customer;
        this.opportunity = opportunity;
        this.comms = comms;
        this.audit = audit;
    }

    public String cadastrarLead(LeadDTO novoLead) {
        validation.validarLead(novoLead);
        int sc = score.calcularScore(novoLead);
        String leadId = lead.salvarLead(novoLead);
        audit.registrar("CADASTRAR_LEAD", "leadId=" + leadId + ", score=" + sc);
        comms.enviarEmail(novoLead.email, "Bem-vindo!",
            "Olá " + novoLead.nome + ", recebemos seu contato. Em breve falaremos com você.");
        return leadId;
    }

    public boolean qualificarLead(String leadId, int minimoScore) {
        LeadDTO l = lead.buscar(leadId);
        if (l == null) return false;
        int sc = score.calcularScore(l);
        boolean qualificado = sc >= minimoScore;
        audit.registrar("QUALIFICAR_LEAD", "leadId=" + leadId + ", score=" + sc +
                ", qualificado=" + qualificado);
        return qualificado;
    }

    public String converterLeadEmCliente(String leadId) {
        LeadDTO l = lead.buscar(leadId);
        if (l == null) throw new IllegalArgumentException("Lead não encontrado");
        String clienteId = customer.criarCliente(l);
        audit.registrar("CONVERTER_LEAD", "leadId=" + leadId + " -> clienteId=" + clienteId);
        comms.enviarEmail(l.email, "Conta criada",
            "Olá " + l.nome + ", criamos sua conta de cliente. Obrigado!");
        lead.remover(leadId);
        return clienteId;
    }

    public void registrarInteracao(String clienteId, String canal, String conteudo) {
        ClienteDTO c = customer.buscar(clienteId);
        if (c == null) throw new IllegalArgumentException("Cliente inválido");
        audit.registrar("INTERACAO", "clienteId=" + clienteId + ", canal=" + canal);
        if ("EMAIL".equalsIgnoreCase(canal)) {
            comms.enviarEmail(c.email, "Resposta do Suporte", conteudo);
        } else if ("SMS".equalsIgnoreCase(canal)) {
            comms.enviarSms(c.email, conteudo); // simulando
        } else {
            System.out.println("[Interação] Canal: " + canal + " | " + conteudo);
        }
    }

    public String criarOportunidade(String clienteId, String descricao, double valor) {
        String opId = opportunity.criar(clienteId, descricao, valor);
        audit.registrar("CRIAR_OPORTUNIDADE", "clienteId=" + clienteId + ", opId=" + opId);
        return opId;
    }

    public void fecharVenda(String oportunidadeId) {
        opportunity.fechar(oportunidadeId);
        audit.registrar("FECHAR_VENDA", "opId=" + oportunidadeId);
    }
}
