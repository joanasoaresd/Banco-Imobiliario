package ufpb.jogo;

import ufpb.exceptions.ValorInvalidoException;
import ufpb.opcoes.JogarPrisao;
import ufpb.opcoes.Vender;

/**
 * <p>
 * Represents the option available in the game to the player.
 * </p>
 * 
 */
public class JogoFactoryVender extends JogoFactory{
	
	/**
	 * Shows the options of facade of the game to the player.
	 * @param opcao
	 * @param jogo
	 * @return boolean
	 */
	public boolean escolheOpcao(String opcao, JogoFacade jogo) {
		opcao = opcao.replaceAll("\\s+", "").toLowerCase();
		switch (opcao) {
		case "jogar":
			setOpcaoJogar();
			break;
		case "vender":
			setOpcaoVender();
			break;
		case "status":
			setOpcaoStatus();
			break;
		case "sair":
			boolean escolha;
			try {
				escolha = jogo.simOuNao("Você realmente quer sair");
				if (escolha) {
					setOpcaoSair();
				}
			} catch (ValorInvalidoException e) {
				System.err.println(e.getMessage());
				escolheOpcao(opcao, jogo);
				return false;
			}
			break;
		default:
			setOpcaoErro();
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Sets the option "Sell(Vender)".
	 * </p>
	 */
	private void setOpcaoVender() {
		this.op = new Vender();
		
	}

	/**
	 * <p>
	 * Sets the option "Play(Jogar)".
	 * </p>
	 */
	@Override
	public void setOpcaoJogar() {
		this.op = new JogarPrisao();
	}

}
