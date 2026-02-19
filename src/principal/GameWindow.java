package principal;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	/*
		A função da classe GameWindow é criar a janela do jogo
		Com todas as informações necessárias já definidas e com
		o painel aonde tudo do jogo será desenhado adicionado
		dentro dela.
	*/
	
	// Método construtor da classe GameWindow
	public GameWindow(GamePanel painel) {
		
		this.setTitle("RPG_2D_JAVA");								// Cria um título para a janela
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Determina que o X fecha a janela
		this.add(painel);											// Adiciona o Painel ao contentPane
		this.setResizable(false);									// Impede que a janela possa ter seu tamanho alterado
		this.pack();												// Define que a janela terá o tamanho do maior objeto do contentPane
		this.setLocationRelativeTo(null);							// Faz a janela iniciar no centro do monitor
		this.setVisible(true);										// Torna a Janela visível
	}
	
	// Método para resolver bugs de textura
	public void resolverBugs() {
		
		/*
			Caso ocorra algum erro de textura durante textes, basta chamar
			este método, ele reavalia a posição de todos os itens da janela
			e depois pinta todos novamente.
		*/
		this.revalidate();
		this.repaint();
	}
	
}
