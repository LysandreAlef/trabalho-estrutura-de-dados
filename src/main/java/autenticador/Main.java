import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String caminhoArquivo = "documento.txt";
        Stack<ArvoreAVL> pilha = new Stack<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {

            String linhaAtual;
            while ((linhaAtual = leitor.readLine()) != null) {
                if (linhaAtual.trim().isEmpty()) {
                    continue;
                }
                String[] palavrasArray = linhaAtual.trim().split("\\s+");
                ArrayList<String> listaDePalavras = new ArrayList<>();
                for (String palavra : palavrasArray) {
                    listaDePalavras.add(palavra);
                }
                ArvoreAVL arvore = new ArvoreAVL();
                for (int i = listaDePalavras.size() - 1; i >= 0; i--) {
                    arvore.inserir(listaDePalavras.get(i));
                }
                pilha.push(arvore);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            System.exit(1);
        }
        StringBuilder resultado = new StringBuilder();
        while (!pilha.isEmpty()) {
            ArvoreAVL arvoreDesempilhada = pilha.pop();
            String hash = arvoreDesempilhada.computarHash();

            resultado.append(hash).append("\n");
        }
        String saida = resultado.toString();
        if (saida.endsWith("\n")) {
            saida = saida.substring(0, saida.length() - 1);
        }
        System.out.println(saida);
    }
}
