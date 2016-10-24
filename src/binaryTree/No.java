package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class No {
	// Atributos
	private int valor;
	
	private No pai;
	private No esq;
	private No dir;
	
	private int nosEsq;
	private int nosDir;
	private int altura;
	
	private Boolean ehCheia;
	private Boolean ehCompleta;
	
	// Construtor
	public No(int valor) {
		this.valor = valor;
		
		pai = null;
		esq = null;
		dir = null;
		
		nosEsq = 0;
		nosDir = 0;
		altura = 0;
		
		ehCheia = true;
		ehCompleta = true;
		
		System.out.println("N� criado com o valor " + valor + ".");
	}
	
	// Metodos
	
	// Chamado dentro de adicionar() para corrigir alturas
	private void atualizaAltura() {
		if (pai == null) {
			if (esq != null && dir != null) {
				altura = max(esq.altura, dir.altura) + 1;
			}
			else if (esq == null) {
				altura = dir.altura + 1;
			}
			else if (dir == null) {
				altura = esq.altura + 1;
			}
		}
		else {
			if (esq != null && dir != null) {
				altura = max(esq.altura, dir.altura) + 1;
			}
			else if (esq == null) {
				altura = dir.altura + 1;
			}
			else if (dir == null) {
				altura = esq.altura + 1;
			}
			pai.atualizaAltura();
		}
	}
	
	// Retorna o maior de dois numeros
	private int max(int altura2, int altura3) {
		if (altura2 > altura3) {
			return altura2;
		}
		else {
			return altura3;
		}
	}

	// Atualiza ehCheia
	private void atualizaCheia() {
		
		System.out.println("\tAtualizando ehCheia do no " + this.valor);
		
		if (pai == null) {
			// No possui dois filhos
			if (dir != null && esq != null) {
				if (dir.altura == esq.altura && esq.ehCheia && dir.ehCheia) {
					ehCheia = true;
				}
				else {
					ehCheia = false;
				}
			}
			// No possui nenhum filho
			else if (dir == null && esq == null) {
				ehCheia = true;
			}
			// No possui apenas um filho
			else {
				ehCheia = false;
			}
		}
		else {
			// No possui dois filhos
			if (dir != null && esq != null) {
				if (dir.altura == esq.altura && esq.ehCheia && dir.ehCheia) {
					ehCheia = true;
				}
				else {
					ehCheia = false;
				}
			}
			// No possui nenhum filho
			else if (dir == null && esq == null) {
				ehCheia = true;
			}
			// No possui apenas um filho
			else {
				ehCheia = false;
			}
			
			pai.atualizaCheia();
		}
	}
	
	// Checa se o no tem sub-arvores vazias
	private Boolean temSubarvoreVazia() {
		if (esq == null || dir == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Checa se o no tem sub-arvores não completas
	private Boolean temSubarvoreNaoCompleta() {
		if (!temSubarvoreVazia()) {
			if (esq.ehCompleta && dir.ehCompleta) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}	
	}
	
	// Atualiza eh ehCompleta
	private void atualizaCompleta() {
		
		System.out.println("\tAtualizando ehCompleta do no " + this.valor);
		
		if (pai == null) {
			if ((altura > 1 && temSubarvoreVazia()) || temSubarvoreNaoCompleta()) {
				ehCompleta = false;
			}
			else {
				ehCompleta = true;
			}
		}
		else {
			if (altura > 1 && temSubarvoreVazia()) {
				ehCompleta = false;
			}
			else {
				ehCompleta = true;
			}
			
			pai.atualizaCompleta();
		}
	}
	
	// Adiciona novo elemento a arvore
	public Boolean adicionar(int novoValor) {
		// Novo valor maior que raiz
		if (novoValor > valor) {
			// Achou o lugar para adiciona o novo no
			if (dir == null) {
				// Atualiza numero de nos a direita
				nosDir++;
				
				//Atualiza altura de todo o caminho de insercao
				if (esq == null) {
					altura++;
					if (pai != null) {
						pai.atualizaAltura();
					}
				}
				
				// Adiciona novo no
				dir = new No(novoValor);
				dir.pai = this;
				System.out.println("N� " + novoValor + " adicionado a direita de " + valor + ".");
								
				// Atualiza ehCheia de todo o caminho de insercao
				atualizaCheia();
				
				// Atualiza ehCOmpleta de todo o caminho de insercao
				atualizaCompleta();
				
				return true;
			}
			// Nao achou lugar para adicionar o novo no e continua procurando
			else {
				this.nosDir++;
				dir.adicionar(novoValor);
			}
		}
		// Novo valor menor que raiz
		else if (novoValor < valor) {
			// Achou o lugar para adiciona o novo no
			if (esq == null) {
				// Atualiza numero de nos a esquerda
				nosEsq++;
				
				//Atualiza altura de todo o caminho de insercao
				if (dir == null) {
					altura++;
					if (pai != null) {
						pai.atualizaAltura();
					}
				}
				
				// Adiciona novo no
				esq = new No(novoValor);
				esq.pai = this;
				System.out.println("Nó " + novoValor + " adicionado a direita de " + valor + ".");
				
				// Atualiza ehCheia de todo o caminho de insercao
				atualizaCheia();
				
				// Atualiza ehCOmpleta de todo o caminho de insercao
				atualizaCompleta();
				
				return true;
			}
			// Nao achou lugar para adicionar o novo no e continua procurando
			else {
				nosEsq++;
				esq.adicionar(novoValor);
			}
		}
		// Novo valor igual a raiz
		else {
			return false;
		}
		
		return false;
	}
	
	// Remove elemento da arvore
	public Boolean remover(int valor) {
		return false;		
	}
	
	// Procura elementod na arvore
	public Boolean procurar(int valor) {
		// Nao achou
		if (valor != this.valor && esq == null && dir == null) {
			return false;
		}
		// Achou
		else if (valor == this.valor) {
			return true;
		}
		// Valor eh maior que raiz
		else if (valor > this.valor) {
			dir.procurar(valor);
		}
		else if (valor < this.valor) {
			esq.procurar(valor);
		}
		
		return false;
	}
	
	public int enesimoElemento(int valor) {
		int posicao = this.nosEsq + 1;

		if (valor > posicao){
			return dir.enesimoElemento(valor-posicao);
		}else if (valor < posicao){
			return esq.enesimoElemento(valor);
		}else{
			return this.valor;
		}
	}
	
	public int posicao(int valor) {
		return 0;
	}
	
	public int mediana() {
		return 0;
	}
	
	// DONE
	public Boolean ehCheia() {
		return this.ehCheia;
	}
	
	// DONE
	public Boolean ehCompleta() {
		return this.ehCompleta;
	}
	
	// DONE
	public String toString() {
		// Fila utilizada no percorrimento em nível da arvore
		Queue<No> fila = new LinkedList<No>();
		// String a ser retornada
		String retorno = "";
		
		// Adiciona raiz a fila
		fila.add(this);
		
		// Enquanto a fila nao esta vazia, enfila os filhos do primeiro elemento
		// da fila e depois concatena o primeiro elemento na string retorno.
		while (!fila.isEmpty()) {
			
			try {
				fila.offer(fila.peek().esq);
			}
			catch (NullPointerException e){}
			
			try {
				fila.offer(fila.peek().dir);
			}
			catch (NullPointerException e){}
			
			try {
				retorno = retorno + fila.peek().valor + " ";
			}
			catch (NullPointerException e){}
			
			fila.poll();
		}
		// Apaga o ultimo espaco
		retorno = retorno.substring(0, retorno.length()-1);
		
		return retorno;
	}

	// Imprime todos os elementos da árvore em pré-ordem detalhando seus atributos
	public void print () {
		System.out.println("> N�: " + this.valor);
		
		if (this.pai != null) {
			System.out.println("\tPai: " + this.pai.valor);
		}
		else {
			System.out.println("\tPai: null");
		}
		
		if (this.esq != null) {
			System.out.println("\tFilho esquerdo: " + this.esq.valor);
		}
		else {
			System.out.println("\tFilho esquerdo: null");
		}
		if (this.dir != null) {
			System.out.println("\tFilho direito " + this.dir.valor);
		}
		else {
			System.out.println("\tFilho direito: null");
		}
		
		System.out.println("\tAltura: " + this.altura);
		
		System.out.println("\tN�s a esquerda: " + this.nosEsq);
		System.out.println("\tN�s a direita: " + this.nosDir);
		System.out.println("\tehCheia: " + this.ehCheia);
		System.out.println("\tehCompl: " + this.ehCompleta);
		
		if (esq != null) {
			esq.print();
		}
		if (dir != null) {
			dir.print();
		}

	}
}
