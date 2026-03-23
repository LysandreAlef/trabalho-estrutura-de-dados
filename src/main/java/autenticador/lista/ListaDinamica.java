package autenticador.lista;

public class ListaDinamica {
    
    // classe interna para nó da lista
    class No {
        String info;
        No prox;
        
        public No(String info) {
            this.info = info;
            this.prox = null;
        }
    }
    
    No inicio;
    No fim;
    int tam;
    
    public ListaDinamica() {
        this.inicio = null;
        this.fim = null;
        this.tam = 0;
    }
    
    public void adicionar(String palavra) {
        No novo = new No(palavra);
        
        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.prox = novo;
            fim = novo;
        }
        tam++;
    }
    
    public int tamanho() {
        return tam;
    }
    
    public String obter(int index) {
        
        if (index < 0 || index >= tam) {
            return null; 
        }
        
        No aux = inicio;
        for (int i = 0; i < index; i++) {
            aux = aux.prox;
        }
        
        return aux.info;
    }
}