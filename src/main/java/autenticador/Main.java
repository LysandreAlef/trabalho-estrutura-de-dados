package autenticador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import autenticador.avl.ArvoreAVL;
import autenticador.hash.GeradorHash;
import autenticador.lista.ListaDinamica;
import autenticador.pilha.PilhaArvores;

public class Main {

    public static void main(String[] args) {
        String caminhoArquivo = "arquivo.txt";
        PilhaArvores pilha = new PilhaArvores();
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {

            String linhaAtual;
            while ((linhaAtual = leitor.readLine()) != null) {
                if (linhaAtual.trim().isEmpty()) {
                    continue;
                }
                String[] palavrasArray = linhaAtual.trim().split("\\s+");
                ListaDinamica listaDePalavras = new ListaDinamica();
                for (String palavra : palavrasArray) {
                    listaDePalavras.adicionar(palavra);
                }
                ArvoreAVL arvore = new ArvoreAVL();
                for (int i = listaDePalavras.tamanho() - 1; i >= 0; i--) {
                    arvore.inserir(listaDePalavras.obter(i));
                }
                pilha.empilhar(arvore);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            System.exit(1);
        }
        
        StringBuilder resultado = new StringBuilder();
        while (!pilha.vazia()) {
            ArvoreAVL arvoreDesempilhada = pilha.desempilhar();
            String hash = GeradorHash.gerar(arvoreDesempilhada);

            resultado.append(hash).append("\n");
        }
        
        String saida = resultado.toString();
        if (saida.endsWith("\n")) {
            saida = saida.substring(0, saida.length() - 1);
        }
        System.out.println(saida);
    }
}