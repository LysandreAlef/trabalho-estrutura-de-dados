package autenticador.avl;

public class ArvoreAVL {
    
    public NoAvl raiz;
    
    private int altura(NoAvl n) {
        return (n == null) ? 0 : n.altura;
    }

    private int getFatorBalanceamento(NoAvl n) {
        return (n == null) ? 0 : altura(n.esquerdo) - altura(n.direito);
    }
    
    private NoAvl rotacionarDireita(NoAvl y) {
        NoAvl x = y.esquerdo;
        NoAvl T2 = x.direito;
        
        x.direito = y;
        y.esquerdo = T2;
        
        y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
        x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
        
        return x;
    }

    private NoAvl rotacionarEsquerda(NoAvl x) {
        NoAvl y = x.direito;
        NoAvl T2 = y.esquerdo;
        
        y.esquerdo = x;
        x.direito = T2;
        
        x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
        y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
        
        return y;
    }

    public void inserir(String palavra) {
        raiz = inserirRecursivo(raiz, palavra);
    }

    private NoAvl inserirRecursivo(NoAvl no, String palavra) {
        if (no == null) return new NoAvl(palavra);

        int comparacao = palavra.compareToIgnoreCase(no.palavra);

        if (comparacao < 0) {
            no.esquerdo = inserirRecursivo(no.esquerdo, palavra);
        } else if (comparacao > 0) {
            no.direito = inserirRecursivo(no.direito, palavra);
        } else {
            return no;
        }

        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
        int balanceamento = getFatorBalanceamento(no);
        
        if (balanceamento > 1 && palavra.compareToIgnoreCase(no.esquerdo.palavra) < 0) {
            return rotacionarDireita(no);
        }

        if (balanceamento < -1 && palavra.compareToIgnoreCase(no.direito.palavra) > 0) {
            return rotacionarEsquerda(no);
        }

        if (balanceamento > 1 && palavra.compareToIgnoreCase(no.esquerdo.palavra) > 0) {
            no.esquerdo = rotacionarEsquerda(no.esquerdo);
            return rotacionarDireita(no);
        }

        if (balanceamento < -1 && palavra.compareToIgnoreCase(no.direito.palavra) < 0) {
            no.direito = rotacionarDireita(no.direito);
            return rotacionarEsquerda(no);
        }

        return no;
    }

    public String obterTextoParaHash() {
        StringBuilder sb = new StringBuilder();
        percursoEmOrdem(raiz, sb);
        return sb.toString();
    }

    private void percursoEmOrdem(NoAvl no, StringBuilder sb) {
        if (no != null) {
            percursoEmOrdem(no.esquerdo, sb);
            sb.append(no.palavra);
            percursoEmOrdem(no.direito, sb);
        }
    }
}