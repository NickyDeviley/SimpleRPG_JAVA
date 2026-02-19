package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import principal.GamePanel;

public class GameTileManager {

	/*
		classe que gerencia os blocos e os mapas do jogo
	*/
	
	// Declarando objetos para conversar com outras classes
	private GamePanel painel;
	private GameTile[] tiles;
	
	// Matriz que armazena o mapa
	private int mapTileNum[][];		
	
	// Método construtor
	public GameTileManager(GamePanel painel) {
		
		// instanciando objetos
		this.painel = painel;
		tiles = new GameTile[10];
		mapTileNum = new int[painel.getMaxWorldHorizontal()][painel.getMaxWorldVertical()];
	
		// Chamando métodos
		getTileImage();					// Pega as imagens do bloco
		loadMap("/maps/world01.txt");	// Carrega o mapa
		
	}
	
	// método que lê as imagens dos blocos
	private void getTileImage() {
		
		// Carrega todas as imagens dos blocos
		try {
			tiles[0] = new GameTile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")));
			tiles[0].setCollision(false);
			
			tiles[1] = new GameTile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")));
			tiles[1].setCollision(true);
			
			tiles[2] = new GameTile(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")));
			tiles[2].setCollision(true);

			tiles[3] = new GameTile(ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png")));
			tiles[3].setCollision(false);
			
			tiles[4] = new GameTile(ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")));
			tiles[4].setCollision(true);
			
			tiles[5] = new GameTile(ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png")));
			tiles[5].setCollision(false);
			
			//tiles[0] = new GameTile();
			//tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")));
			//tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			//tiles[1] = new GameTile();
			//tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			//tiles[2] = new GameTile();
			//tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			//tiles[3] = new GameTile();
			//tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			//tiles[4] = new GameTile();
			//tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			
			//tiles[5] = new GameTile();
			//tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		}
		catch(IOException e) {
			e.printStackTrace();	// Retorna um erro dos arquivos que não existam
		}
		
	}
	
	// Método que desenha o mapa
	public void draw(Graphics2D g2) {
		
		// Variáveis
		int qtdMundoHorizontal = 0;
		int qtdMundoVertical = 0;
		//int qtdHorizontal = 0;	// Armazena a quantidade de blocos na horizontal
		//int qtdVertical = 0;		// Armazena a quantidade de blocos na vertical
		//int x = 0;				// Armazena a posição X
		//int y = 0;				// Armazena a posição Y
		
		// Laço While
		while(qtdMundoHorizontal < painel.getMaxWorldHorizontal() && qtdMundoVertical < painel.getMaxWorldVertical()) {
			/*
				enquanto a quantidade de blocos horizontais
				for menor que
				a quantidade máxima de blocos na horizontal
				e
				enquanto a quantidade de blocos verticais
				for menor que
				a quantidade máxima de blocos na vertical
			*/
			
			// Matriz que armazena o número do bloco em cada posição
			int tileNum = mapTileNum[qtdMundoHorizontal][qtdMundoVertical];
			
			int worldX = qtdMundoHorizontal * painel.getTileSize();
			int worldY = qtdMundoVertical * painel.getTileSize();
			int screenX = worldX - painel.getJogador().getWorldX() + painel.getJogador().getScreenX();
			int screenY = worldY - painel.getJogador().getWorldY() + painel.getJogador().getScreenY();
			
			if ((worldX + painel.getTileSize()) > painel.getJogador().getWorldX() - painel.getJogador().getScreenX() &&
				(worldX - painel.getTileSize()) < painel.getJogador().getWorldX() + painel.getJogador().getScreenX() &&
				(worldY + painel.getTileSize()) > painel.getJogador().getWorldY() - painel.getJogador().getScreenY() &&
				(worldY - painel.getTileSize()) < painel.getJogador().getWorldY() + painel.getJogador().getScreenY()) {
				
				// método que desenha os bloco
				g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, painel.getTileSize(), painel.getTileSize(), null);

			}
			
			qtdMundoHorizontal++;			// Somando a quantidade de bloco horizontal
			// x += painel.getTileSize();	// Mudando a posição X do bloco
			
			// Se a quantidade de blocos na horizontal
			// for igual a quantidade máxima de blocos na horizontal
			if (qtdMundoHorizontal == painel.getMaxWorldHorizontal()) {
				qtdMundoHorizontal = 0;			// limpa a quantidade de blocos horizontais
				//x = 0;						// limpa a posição X
				qtdMundoVertical++;				// aumenta a quantidade de blocos verticais
				//y += painel.getTileSize();	// aumenta a posição Y
			}
		}
		
	}
	
	// Este método recebe o caminho do arquivo.txt
	// aonde está desenhado o mapa com números
	public void loadMap(String mapPath) {
		
		try {
			// Lê o arquivo como Stream e armazena em um objeto "InputStream"
			InputStream is = getClass().getResourceAsStream(mapPath);
			
			// Cria um objeto "leitor" e passa como parâmetro um objeto "InputStreamReader"
			// Que recebe o objeto criando anteriormente como parâmetro
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int horizontal = 0;	// Modifica a posição horizontal
			int vertical = 0;	// Modifica a posição vertical
			
			// Laço while que verifica se a variável horizontal é menor que o tamanho do mundo em horizontal
			// e verifica se a variável vertical é menor que o tamanho do mundo em vertical
			while(horizontal < painel.getMaxWorldHorizontal() && vertical < painel.getMaxWorldVertical()) {
				
				// Variável que armazena a linha do arquivo
				String linha = br.readLine();
				
				// Laço While que lê toda a linha
				while(horizontal < painel.getMaxWorldHorizontal()) {
					
					// Aqui a linha é lida até encontrar um espaço,
					// assim que encontra um espaço ela pula para o próximo
					// valor após o espaço e lê novamente
					String numbers[] = linha.split(" ");
					
					// Aqui o array é convertido de string para inteiro
					// índice por índice
					int num = Integer.parseInt(numbers[horizontal]);
					
					// Aqui nós armazenamos na matriz do mapa
					// o número que é representado no arquivo.txt
					mapTileNum[horizontal][vertical] = num;
					horizontal++;	// Soma 1  quantidade de blocos já armazenados na horizontal
					
				}
				
				// Verificamos se a quantidade de blocos na horizontal
				// atingiu o tamanho máximo do mundo na horizontal
				if (horizontal == painel.getMaxWorldHorizontal()) {
					horizontal = 0;	// A quantidade de blocos na horizontal é zerada
					vertical++;		// A quantidade de blocos na vertical é somada em 1
				}
				
			}
			br.close();	// Fecha a variável de leitura para limpar a memória mais rápido
		
		}
		catch (Exception e) {
			e.getCause();
		}	
	}
	
	public int getMapTileNum(int index_X, int index_Y) {
		return this.mapTileNum[index_X][index_Y];
	}
	public GameTile getTiles(int index) {
		return this.tiles[index];
	}
	
}
