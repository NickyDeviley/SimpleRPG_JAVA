package principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.GameTileManager;
import util.KeyboardInput;

public class GamePanel extends JPanel {
	/*
	  	A classe GamePanel serve para gerenciar o painel e todos
	  	os objetos que estão desenhados nele.
	*/
	
	// Objetos declarados para utilizar no jogo
	private KeyboardInput keyHandler;							// Declarando o objeto que recebe as teclas
	private Player jogador;										// Declarando o objeto jogador
	private GameTileManager gtm;								// Declarando o objeto que gerencia os blocos
	private GameCollisionChecker cChecker;
	private AssetSetter aSetter;
	private Sound sound;
	private Sound music;
	private GameUI userInterface;
	public SuperObject obj[];
	
	// Variáveis para mapa do jogo
	private final int originalTileSize = 16;					// Tamanho original dos desenhos na tela 16x16
	private final int scale = 3;								// A escala que nós utilizaremos
	private final int tileSize = originalTileSize * scale;		// O tamanho dos desenhos que serão desenhados 48x48
	
	// Variáveis gerenciar tamanho do painel
	private final int maxScreenH = 16;							// Quantos blocos podem ser desenhados Horizontalmente na tela
	private final int maxScreenV = 12;							// Quantos blocos podem ser desenhados Verticalmente na tela
	private final int screenWidth = tileSize * maxScreenH;		// Largura da tela	768 pixels
	private final int screenHeight = tileSize * maxScreenV;		// Altura da tela	576 pixels
	
	// Configurações do mundo
	private final int maxWorldHorizontal = 50;						// Tamanho máximo do mapa Horizontal
	private final int maxWorldVertical = 50;						// Tamanho máximo do mapa Vertical
	//private final int worldWidth = tileSize * maxWorldHorizontal;	// Tamanho do mundo em pixels
	//private final int worldHeight = tileSize * maxWorldVertical;	// Tamanho do mundo em pixels

	// Método construtor aonde os objetos são instanciados
	// e o painel tem seu tamanho definido.
	public GamePanel() {
		
		// Instanciando Objetos
		this.keyHandler = new KeyboardInput();
		this.jogador = new Player(this, this.keyHandler);
		this.gtm = new GameTileManager(this);
		this.cChecker = new GameCollisionChecker(this);
		this.aSetter = new AssetSetter(this);
		this.sound = new Sound();
		this.music = new Sound();
		this.userInterface = new GameUI(this);
		this.obj = new SuperObject[10];
		
		// Chamando métodos do painel
		this.setGamePanelSize();
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setDoubleBuffered(true);			// Método para dobrar o buffer e melhorar a renderização de imagens
		this.addKeyListener(this.keyHandler);
		
		setupGame();
		
	}
	
	public void setupGame() { 
	
		aSetter.setObject();
		playMusic(0);
		
	}
	
	// Método que atualiza a lógica do jogador, mapa e inimigos
	public void update() { jogador.playerUpdate(); }
	
	// Método sobreescrito da classe JPanel
	// Que tem a função de desenhar as coisas na tela.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);				// Chamando o método da super classe para apagar os desenhos anteriores.
		Graphics2D g2 = (Graphics2D)g;			// Convertendo o Graphics para Graphics2D, uma classe que fornece
		
		// DEBUG
		long drawStart = 0;
		if (keyHandler.checkDrawTime == true) {
			drawStart = System.nanoTime();
		}
		
		// Métodos melhores para se trabalhar com gráficos 2d.
		this.gtm.draw(g2);						// Chamando o método que desenha o mapa do jogo
		this.jogador.draw(g2);					// Chamando o método que desenha o jogador
		this.userInterface.draw(g2);
		
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}	
		}
		
		/*
		// DEBUG
		if (keyHandler.checkDrawTime == true) {
		long drawEnd = System.nanoTime();
		long passed = drawEnd - drawStart;
		g2.setColor(Color.WHITE);
		g2.drawString("Draw Time: " + passed, 10, 400);
		System.out.println("Draw Time: " + passed);
		}
		*/
		
		g2.dispose();						// Método que limpa todos os objetos criados pelo g2
	}
	
	// MUSICA
	public void playMusic(int index) {
		
		this.music.setFile(index);
		this.music.play();
		this.music.loop();
		
	}
	public void stopMusic() { this.music.stop(); };
	public void playSE(int index) {
		this.sound.setFile(index);
		this.sound.play();
	}
	
	// Método que determina o tamanho do painel
	private void setGamePanelSize() {
		//Dimension tamanho = new Dimension(64, 64);	// Declarando e instanciando um método Dimension
		Dimension tamanho = new Dimension(screenWidth, screenHeight);
		setMinimumSize(tamanho);
		setPreferredSize(tamanho);
		setMaximumSize(tamanho);
	}
	
	// Métodos Get informações do painel
	public int getTileSize() { return this.tileSize; }
	public int getMaxScreenH() { return this.maxScreenH; }
	public int getMaxScreenV() { return this.maxScreenV; }
	public int getScreenWidth() { return this.screenWidth; }
	public int getScreenHeight() { return this.screenHeight; }
	
	// Métodos Get informações do mundo
	public int getMaxWorldHorizontal() { return this.maxWorldHorizontal; }
	public int getMaxWorldVertical() { return this.maxWorldVertical; }
	//public int getWorldWidth() { return this.worldWidth; }
	//public int getWorldHeight() { return this.worldHeight; }
	
	// Métodos get objetos
	public Player getJogador() { return this.jogador; }
	public GameCollisionChecker getCollisionChecker() { return this.cChecker; }
	public GameTileManager getTileManager() { return this.gtm; }
	public GameUI getUserInterface() { return this.userInterface; }

	//public Object getObj(int index) { return this.obj[index]; }
	
	// Métodos set
	//public void setObj(int index, Object obj) { this.obj[index] = obj; }
	
}
