package autenticador.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import autenticador.avl.ArvoreAVL;
import autenticador.avl.NoAvl;

public class GeradorHash {

    public static String gerar(ArvoreAVL arvore) {
        if (arvore == null || arvore.raiz == null) return "";
        return calcularHashRecursivo(arvore.raiz);
    }

    private static String calcularHashRecursivo(NoAvl no) {
        if (no == null) return "";

        String hashEsq = calcularHashRecursivo(no.esquerdo);
        String hashDir = calcularHashRecursivo(no.direito);
        
        String hashdapalavra = sha1(no.palavra);

        return sha1(hashEsq + hashDir + hashdapalavra);
    }

    public static String sha1(String input) {
        try {
            MessageDigest calcular = MessageDigest.getInstance("SHA-1");
            byte[] result = calcular.digest(input.getBytes());
            StringBuilder resultado = new StringBuilder();
            for (byte b : result) {
                resultado.append(String.format("%02x", b));
            }
            return resultado.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro SHA-1", e);
        }
    }
}