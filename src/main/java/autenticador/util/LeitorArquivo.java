package autenticador.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class LeitorArquivo {

    public static String[] lerLinhas(String caminho) {
        int qtd = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));
            while (br.readLine() != null) {
                qtd++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("não foi possível localizar o arquivo " + e.getMessage());
            return new String[0]; 
        }

        String[] linhas = new String[qtd];

        try {
            BufferedReader br2 = new BufferedReader(new FileReader(caminho));
            String l;
            int i = 0;
            
            while ((l = br2.readLine()) != null) {
                linhas[i] = l;
                i++;
            }
            br2.close();
        } catch (Exception e) {
            System.out.println("não foi possível ler o arquivo novamente " + e.getMessage());
        }

        return linhas;
    }
}