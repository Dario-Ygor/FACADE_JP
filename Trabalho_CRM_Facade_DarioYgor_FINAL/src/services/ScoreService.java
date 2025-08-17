public class ScoreService {
    public int calcularScore(LeadDTO lead) {
        int score = 50;
        if (lead.email.endsWith("@empresa.com")) score += 30;
        if (lead.telefone != null && lead.telefone.startsWith("+55")) score += 20;
        System.out.println("[Score] Score do lead " + lead.nome + ": " + score);
        return score;
    }
}
