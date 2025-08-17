public class OportunidadeDTO {
    public String id; public String clienteId; public String descricao; public double valor;
    public OportunidadeDTO(String id, String clienteId, String descricao, double valor) {
        this.id = id; this.clienteId = clienteId; this.descricao = descricao; this.valor = valor;
    }
}
