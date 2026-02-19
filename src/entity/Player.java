package entity;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import principal.GamePanel;
import util.KeyboardInput;

public class Player extends GameEntity {
	
	// Objetos para interagir com outras classes
	GamePanel painel;
	KeyboardInput keyHandler;
	
	// Variáveis que armazenam a posição do jogador na tela
	private final int screenX;
	private final int screenY;
	private boolean moving = false;
	private int pixelCounter = 0;
	private int standCounter = 0;
	
	// Quantidade de chaves
	private int keys = 3;
	
	// Método construtor que conecta o painel e os inputs
	// do teclado ao objeto jogador
	public Player(GamePanel painel, KeyboardInput keyHandler) {
		
		// Instanciado os objetos
		this.painel = painel;
		this.keyHandler = keyHandler;
		
		this.solidArea = new Rectangle();
		//this.solidArea.x = 8;
		//this.solidArea.y = 16;
		this.solidArea.x = 1;
		this.solidArea.y = 1;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		//this.solidArea.width = 32;
		//this.solidArea.height = 32;
		this.solidArea.width = 46;
		this.solidArea.height = 46;
		
		
		// Nós pegamos o tamanho da janela em Pixels e dividimos pela metade
		// Mas a imagem do jogador começa a contar do canto superior direito
		// então temos que pegar o tamanho do seu desenho, dividir por dois
		// e subtrair da metade da tela, para assim deixar ele perfeitamente alinhado
		screenX = painel.getScreenWidth()/2 - (painel.getTileSize()/2);
		screenY = painel.getScreenHeight()/2 - (painel.getTileSize()/2);
		
		// Chamando os métodos
		setDefaultValues();
		getPlayerImage();
	}
	
	// Método que declara os valores iniciais do jogador
	@Override
	protected void setDefaultValues() {
		
		// Declara a posição inicial do jogador no mapa
		this.worldX = painel.getTileSize() * 23;
		this.worldY = painel.getTileSize() * 21;
		
		// Declara a velocidade do jogador
		this.speed = 1;
		
		// Declara a posição inicial do sprite do jogador
		this.direction = "down";
	}
	
	// Método que armazena a lógica de movimento do jogador
	public void playerUpdate() {
		
		if (moving == false) {
			
			// Aqui nós chamamos o objeto que verifica se o jogador pressionou alguma tecla
			// e chamamos o método que retorna a variável boolena que mostra a tecla
			// que foi pressionada.
			if (keyHandler.getUpPressed() == true || keyHandler.getDownPressed() == true ||
			    keyHandler.getLeftPressed() == true || keyHandler.getRightPressed() == true) {
				
				// Se a tecla foi para cima
				if (this.keyHandler.getUpPressed() == true) { 
					this.direction = "up";		// modifica a direção do sprite para cima
					playerMoveAnimation();
					//this.worldY -= this.speed;	// modifica a direção que o jogador se move
				}
				
				// Se a tecla foi para baixo
				else if (this.keyHandler.getDownPressed() == true) { 
					this.direction = "down";	// modifica a direção do sprite para baixo
					playerMoveAnimation();
					//this.worldY += this.speed;	// modifica a direção que o jogador se move
				}
				
				// Se a tecla foi para esquerda
				else if (this.keyHandler.getLeftPressed() == true) { 
					this.direction = "left";	// modifica a direção do sprite para esquerda
					playerMoveAnimation();
					//this.worldX -= this.speed;	// modifica a direção que o jogador se move
				}
				
				// Se a tecla foi para a direita
				else if (this.keyHandler.getRightPressed() == true) { 
					this.direction = "right";	// modifica a direção do sprite para a direita
					playerMoveAnimation();
					//this.worldX += this.speed;	// modifica a direção que o jogador se move
				}
				
				moving = true;
				
				// Checar Colisão de bloco
				this.collisionLigada = false;
				this.painel.getCollisionChecker().checkTile(this);
				
				// Checar Colisão de objetos
				int objIndex = painel.getCollisionChecker().checkObject(this, true);
				this.pickUpObject(objIndex);
				
			}
			else {
				standCounter++;
				
				if (standCounter == 20) {
					spriteNum = 1;
					standCounter = 0;
				}
			}
		}
		if (moving == true) {
			// Se colisão for false, jogador pode se mover
			if (collisionLigada == false) {
				switch(direction) {
				case "up": this.worldY -= this.speed; break;
				case "down": this.worldY += this.speed; break;
				case "left": this.worldX -= this.speed; break;
				case "right": this.worldX += this.speed; break;
				}
			}
			
			playerMoveAnimation();
			
			pixelCounter += speed;
			if (pixelCounter == 48) {
				moving = false;
				pixelCounter = 0;
			}
		}
	}
	
	// Método que armazena o sprite do jogador
	// e o desenha na tela.
	@Override
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;		// Criando um objeto BufferedImage
		
		// Switch para verificar a posição do jogador
		switch(direction) {
		case "up":					// Caso seja para cima
			if(spriteNum == 1) {	// Se o número for 1
				image = up1;		// Carrega imagem 1
			}
			if(spriteNum == 2) {	// Se o número for 2
				image = up2;		// Carrega imagem 2
			}
			break;
		case "down":				// Caso seja para baixo
			if(spriteNum == 1) {	// Se o número for 1
				image = down1;		// Carrega imagem 1
			}
			if(spriteNum == 2) {	// Se o número for 2
				image = down2;		// Carrega imagem 2
			}
			break;
		case "left":				// Caso seja para esquerda
			if(spriteNum == 1) {	// Se o número for 1
				image = left1;		// Carrega imagem 1
			}
			if(spriteNum == 2) {	// Se o número for 2
				image = left2;		// Carrega imagem 2
			}
			break;
		case "right":				// Caso seja para direita
			if(spriteNum == 1) {	// Se o número for 1
				image = right1;		// Carrega imagem 1
			}
			if(spriteNum == 2) {	// Se o número for 2
				image = right2;		// Carrega imagem 2
			}
			break;
		default:					// Método Default
			break;
		}
		// Chama o método para desenhar o sprite do jogador
		g2.drawImage(image, this.screenX, this.screenY, painel.getTileSize(), painel.getTileSize(), null);
		
		// Desenha a hitbox
		g2.setColor(Color.RED);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
	
	public void pickUpObject(int index) {
		
		if (index != 999) {
			
			String objectName = painel.obj[index].getName();
			
			switch (objectName) {
			case "key":
				this.painel.playSE(1);
				this.keys++;
				this.painel.obj[index] = null;
				this.painel.getUserInterface().showMessage("Você pegou uma chave!");
				break;
			case "door":
				if (this.keys > 0) {
					this.painel.playSE(3);
					this.painel.obj[index] = null;
					this.keys--;
					this.painel.getUserInterface().showMessage("Você abriu uma porta!");
				}
				else {	
					this.painel.getUserInterface().showMessage("Você precisa de uma chave!");
				}
				break;
			case "boots":
				this.painel.playSE(2);
				this.speed *= 4;
				this.painel.obj[index] = null;
				this.painel.getUserInterface().showMessage("Você aumentou sua velocidade!");
				break;
			case "chest":
				this.painel.getUserInterface().setGameFinished(true);
				this.painel.stopMusic();
				this.painel.playSE(4);
				break;
			}
			
		}
		
	}
	
	// Método que pega os sprites do jogador
	private void getPlayerImage() {
		try {
			// Carrega todas as imagens do jogador
			up1 = ImageIO.read(getClass().getResourceAsStream("/playerMove/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/playerMove/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/playerMove/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/playerMove/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/playerMove/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/playerMove/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/playerMove/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/playerMove/boy_right_2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();	// Verifica se as imagens existe
		}
		
	}
	
	private void playerMoveAnimation() {
		// Soma a quantidade de sprites
		spriteCounter++;
			
		// Se a quantidade de sprites for maior que 20
		if (spriteCounter > 20) {
				
			// Se o número do sprite for igual a 1
			if (spriteNum == 1) {
				spriteNum = 2;		// Troca para o segundo sprite
			}
				
			// Se o número do sprite for igual a 2
			else if (spriteNum == 2) {
				spriteNum = 1;		// Troca para o primeiro sprite
			}
			spriteCounter = 0;		// Zera a quantidade de sprites
		}
	}
	
	// Métodos Get
	public int getScreenX() { return this.screenX; }
	public int getScreenY() { return this.screenY; }
	public int getKeyNumber() { return this.keys; }
	//public int getWorldX() { return this.worldX; }
	//public int getWorldY() { return this.worldY; }
	//public Rectangle getSolidArea() { return this.solidArea; }
	//public boolean getCollisionLigado() { return this.collisionLigada; }
}
