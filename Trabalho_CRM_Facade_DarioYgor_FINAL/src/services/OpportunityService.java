import java.util.*;
public class OpportunityService {
    private final Map<String, OportunidadeDTO> oportunidades = new HashMap<>();
    public String criar(String clienteId, String desc, double valor) {
        String id = "O" + (oportunidades.size()+1);
        OportunidadeDTO op = new OportunidadeDTO(id, clienteId, desc, valor);
        oportunidades.put(id, op);
        System.out.println("[Oportunidade] Criada id=" + id + " para cliente=" + clienteId);
        return id;
    }
    public void fechar(String oportunidadeId) {
        System.out.println("[Oportunidade] Oportunidade " + oportunidadeId + " FECHADA!");
    }
}
