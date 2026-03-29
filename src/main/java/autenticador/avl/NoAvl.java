package autenticador.avl;

public class NoAvl {
    
    public String palavra;
    public NoAvl esquerdo;
    public NoAvl direito;
    public int altura;

    public NoAvl(String palavra) {
        this.palavra = palavra;
        this.altura = 1;
        this.esquerdo = null;
        this.direito = null;
    }
}