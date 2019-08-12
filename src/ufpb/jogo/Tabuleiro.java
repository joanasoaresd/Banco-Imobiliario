package ufpb.jogo;

import ufpb.recuperaDados.RecuperaDadosDoTXT;

import java.io.IOException;

import ufpb.cartasEspeciais.SorteOuReves;
import ufpb.lougradouros.Companhia;
import ufpb.lougradouros.ImpostoDeRenda;
import ufpb.lougradouros.LucroEDividendo;
import ufpb.lougradouros.ParadaLivre;
import ufpb.lougradouros.PontoDePartida;
import ufpb.lougradouros.Posicao;
import ufpb.lougradouros.Prisao;
import ufpb.lougradouros.Terreno;

public class Tabuleiro {

	private Posicao[] posicoeDoTabuleiro;
	private RecuperaDadosDoTXT recupera = new RecuperaDadosDoTXT();

	private static final String ARQUIVO_POSICOES_ESPECIAIS = "./Arquivos/posicoesEspeciais.txt";
	private static final String ARQUIVO_COMPANHIAS = "./Arquivos/companhias.txt";
	private static final String ARQUIVO_PRISAO = "./Arquivos/prisao.txt";
	private static final String ARQUIVO_TERRENOS = "./Arquivos/terrenos.txt";
	private static final String ARQUIVO_POSICOES_DE_SORTE_OU_REVES = "./Arquivos/posicaoDeSorteOuReves.txt";

	/**
	 * Constructor from class Tabuleiro, enables initialization of position on the
	 * board attribute.
	 * 
	 * @author Clebson
	 * @param int posicoeDoTabuleiro - the player's position on the board
	 */
	public Tabuleiro() {
		this.posicoeDoTabuleiro = new Posicao[41];
		recuperaCompanhias();
		recuperaPosicoesEspeciais();
		recuperaPrisao();
		recuperaTerrenos();
		recuperaPosicoesDeSorteOuReves();

	}

	/**
	 * Method to recover companies file.
	 * 
	 * @author Clebson
	 */
	public void recuperaCompanhias() {

		try {
			for (String s : recupera.recuperaTextoDeArquivo(ARQUIVO_COMPANHIAS)) {

				String[] linha = s.split(",");

				int posicao = Integer.parseInt(linha[0]);
				String nome = linha[1];
				int preco = Integer.parseInt(linha[2]);
				String dono = "banco";
				int multiplicador = Integer.parseInt(linha[3]);

				Companhia c = new Companhia(posicao, nome, preco, dono, multiplicador);

				this.posicoeDoTabuleiro[posicao] = c;

			}
		} catch (IOException e) {
			System.out.println("Não foi possível recuperar o arquivo TXT de companhia");
		}
	}

	/**
	 * Method to recover the especial positions file
	 * 
	 * @author Amanda
	 */
	public void recuperaPosicoesEspeciais() {
		try {
			for (String s : recupera.recuperaTextoDeArquivo(ARQUIVO_POSICOES_ESPECIAIS)) {

				String[] linha = s.split(",");
				int posicao = Integer.parseInt(linha[0]);
				String nome = linha[1];
				if (nome.equals("Lucros e Dividendos")) {
					Posicao p = new LucroEDividendo(posicao);
					this.posicoeDoTabuleiro[posicao] = p;
				} else if (nome.equals("Parada Livre")) {
					Posicao p = new ParadaLivre(posicao);
					this.posicoeDoTabuleiro[posicao] = p;
				} else if (nome.equals("Imposto de Renda")) {
					Posicao p = new ImpostoDeRenda(posicao);
					this.posicoeDoTabuleiro[posicao] = p;
				} else if (nome.equals("Ponto de partida")) {
					Posicao p = new PontoDePartida(posicao);
					this.posicoeDoTabuleiro[posicao] = p;
				}

			}
		} catch (IOException e) {
			System.out.println("Não foi possível recuperar o arquivo TXT posições especiais");
		}

	}

	/**
	 * Method to recover prison file
	 * 
	 * @author Joyce
	 */
	public void recuperaPrisao() {
		try {
			for (String s : recupera.recuperaTextoDeArquivo(ARQUIVO_PRISAO)) {

				String[] linha = s.split(",");
				int posicao = Integer.parseInt(linha[0]);
				String nome = linha[1];
				Prisao p = new Prisao(posicao, nome);
				this.posicoeDoTabuleiro[posicao] = p;

			}
		} catch (IOException e) {
			System.out.println("Não foi possível recuperar o arquivo TXT de prisão");
		}

	}

	/**
	 * Method to recover lands file
	 * 
	 * @author Joana
	 */
	public void recuperaTerrenos() {

		try {
			for (String s : recupera.recuperaTextoDeArquivo(ARQUIVO_TERRENOS)) {

				String[] linha = s.split(",");

				int posicao = Integer.parseInt(linha[0]);
				String nome = linha[1];
				int preco = Integer.parseInt(linha[2]);
				int aluguel = Integer.parseInt(linha[3]);
				int aluguelComUmaCasa = Integer.parseInt(linha[4]);
				int aluguelComDuasCasas = Integer.parseInt(linha[5]);
				int aluguelComTresCasas = Integer.parseInt(linha[6]);
				int aluguelComQuatroCasas = Integer.parseInt(linha[7]);
				int aluguelComHotel = Integer.parseInt(linha[8]);
				int dono = Integer.parseInt(linha[9]);
				String cor = linha[10];

				Terreno t = new Terreno(posicao, nome, preco, aluguel, aluguelComUmaCasa, aluguelComDuasCasas,
						aluguelComTresCasas, aluguelComQuatroCasas, aluguelComHotel, dono, cor);

				this.posicoeDoTabuleiro[posicao] = t;

			}
		} catch (IOException e) {
			System.out.println("Não foi possível recuperar o arquivo TXT de terrenos");
		}

	}

	/**
	 * Method to recover luck or mischance file
	 * 
	 * @author Clebson
	 */
	public void recuperaPosicoesDeSorteOuReves() {
		try {
			for (String s : recupera.recuperaTextoDeArquivo(ARQUIVO_POSICOES_DE_SORTE_OU_REVES)) {

				int posicao = Integer.parseInt(s);
				SorteOuReves r = new SorteOuReves(posicao);

				this.posicoeDoTabuleiro[posicao] = r;

			}
		} catch (IOException e) {
			System.out.println("Não foi possível recuperar o arquivo TXT de posições de sorte ou revés");
		}

	}

	/**
	 * Method to get the position on the board
	 * 
	 * @author Clebson
	 */
	public Posicao getPosicoeDoTabuleiro(int indice) {
		return this.posicoeDoTabuleiro[indice];
	}

}
