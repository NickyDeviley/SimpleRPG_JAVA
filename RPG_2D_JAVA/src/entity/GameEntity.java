package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameEntity {

	/*
		Classe base para todas as entidades
	*/
	
	// Atributos comuns de todas as entidades
	protected int worldX, worldY;		// variável que armazena a posição X e Y
	protected int speed;				// Variável que armazena a posição velocidade
	protected int spriteCounter = 0;	// Variável que armazena a quantidade de sprites 
	protected int spriteNum = 1;		// Variável que armazena o número do sprite
	protected boolean collisionLigada = false;
	protected int solidAreaDefaultX, solidAreaDefaultY;
	
	// Objetos comuns de todas as entidades
	protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	protected String direction;
	protected Rectangle solidArea;
	
	public abstract void draw(Graphics2D g2);		// Método para desenhar os sprites
	protected abstract void setDefaultValues();		// Método para declarar os valores padrões
	
	public int getWorldX() { return this.worldX; }
	public int getWorldY() { return this.worldY; }
	public int getSpeed() { return this.speed; }
	public int getSolidAreaDefaultX() { return this.solidAreaDefaultX; }
	public int getSolidAreaDefaultY() { return this.solidAreaDefaultY; }
	public boolean getCollisionLigado() { return this.collisionLigada; }
	public String getDirection() { return this.direction; }
	public Rectangle getSolidArea() { return this.solidArea; }
	
	public void setCollisionLigado(boolean collisionLigada) { this.collisionLigada = collisionLigada; }
	
}
