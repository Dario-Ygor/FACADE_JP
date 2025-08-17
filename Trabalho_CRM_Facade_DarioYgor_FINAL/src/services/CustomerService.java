import java.util.*;
public class CustomerService {
    private final Map<String, ClienteDTO> clientes = new HashMap<>();
    public String criarCliente(LeadDTO lead) {
        String id = "C" + (clientes.size()+1);
        ClienteDTO c = new ClienteDTO(id, lead.nome, lead.email);
        clientes.put(id, c);
        System.out.println("[Cliente] Cliente criado id=" + id + " (" + c.nome + ")");
        return id;
    }
    public ClienteDTO buscar(String clienteId) { return clientes.get(clienteId); }
}
