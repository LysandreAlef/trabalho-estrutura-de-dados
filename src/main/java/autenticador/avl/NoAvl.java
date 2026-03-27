package autenticador.avl;

public class NoAvl {
public String palavra;
public NoAvl esquerdo, direito;
public int altura;

public NoAvl(String d) {
this.palavra = d;
this.altura = 1;
    }
}