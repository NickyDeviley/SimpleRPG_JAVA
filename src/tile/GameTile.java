package tile;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameTile {

	/*
		Classe base para todos os blocos
	*/
	
	private BufferedImage image;			// Objeto que armazena a imagem do bloco
	private boolean collision = false;	// variável que armazena se é possível colidir com o bloco
	
	// Método construtor padrão
	public GameTile() {
		this.image = null;
		this.collision = false;
	}
	
	// Método construtor 1
	public GameTile(Image image) {
		this.image = (BufferedImage)image;
	}
	
	// Método construtor 2
	public GameTile(Image image, boolean collision) {
		this.image = (BufferedImage)image;
		this.collision = collision;
	}
	
	public BufferedImage getImage() { return this.image; }
	public boolean getCollision() { return this.collision; }
	
	public void setImage(BufferedImage image) { this.image = image; }
	public void setCollision(boolean collision) { this.collision = collision; }
	
}
