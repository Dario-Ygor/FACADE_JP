import java.util.*;
public class LeadService {
    private final Map<String, LeadDTO> leads = new HashMap<>();
    public String salvarLead(LeadDTO lead) {
        String id = "L" + (leads.size()+1);
        leads.put(id, lead);
        System.out.println("[Lead] Lead salvo com id=" + id);
        return id;
    }
    public LeadDTO buscar(String leadId) { return leads.get(leadId); }
    public void remover(String leadId) { leads.remove(leadId); }
}
