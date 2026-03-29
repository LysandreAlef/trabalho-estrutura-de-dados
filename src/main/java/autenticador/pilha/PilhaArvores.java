package autenticador.pilha;

import autenticador.avl.ArvoreAVL;

public class PilhaArvores {
    
    private class No {
        ArvoreAVL arvore;
        No proximo;

        public No(ArvoreAVL arvore) {
            this.arvore = arvore;
            this.proximo = null;
        }
    }

    private No topo;

    public PilhaArvores() {
        this.topo = null;
    }

    public void empilhar(ArvoreAVL arvore) {
        No novoNo = new No(arvore);
        novoNo.proximo = topo;
        topo = novoNo;
    }

    public ArvoreAVL desempilhar() {
        if (vazia()) {
            throw new IllegalStateException("Erro: Tentativa de desempilhar de uma pilha vazia.");
        }
        ArvoreAVL arvoreDesempilhada = topo.arvore;
        topo = topo.proximo;
        return arvoreDesempilhada;
    }

    public boolean vazia() {
        return topo == null;
    }
}