package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.GamePanel;

public abstract class SuperObject {

	protected BufferedImage image;
	protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	protected String name;
	protected boolean collision;
	protected int worldX, worldY;
	protected int solidAreaDefaultX = 0;
	protected int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel painel) {
		
		int screenX = worldX - painel.getJogador().getWorldX() + painel.getJogador().getScreenX();
		int screenY = worldY - painel.getJogador().getWorldY() + painel.getJogador().getScreenY();
		
		
		if ((worldX + painel.getTileSize()) > painel.getJogador().getWorldX() - painel.getJogador().getScreenX() &&
			(worldX - painel.getTileSize()) < painel.getJogador().getWorldX() + painel.getJogador().getScreenX() &&
			(worldY + painel.getTileSize()) > painel.getJogador().getWorldY() - painel.getJogador().getScreenY() &&
			(worldY - painel.getTileSize()) < painel.getJogador().getWorldY() + painel.getJogador().getScreenY()) {
			
			// método que desenha os bloco
			g2.drawImage(image, screenX, screenY, painel.getTileSize(), painel.getTileSize(), null);
		}
		
	}
	
	public BufferedImage getImage() { return this.image; }
	public Rectangle getSolidArea() { return this.solidArea; }
	public String getName() { return this.name; }
	public boolean getCollision() { return this.collision; }
	public int getWorldX() { return this.worldX; }
	public int getWorldY() { return this.worldY; }
	public int getSolidAreaDefaultX() { return this.solidAreaDefaultX; }
	public int getSolidAreaDefaultY() { return this.solidAreaDefaultY; }
	
	public void setCollision(boolean collision) { this.collision = collision; }
	public void setWorldX(int worldX) { this.worldX = worldX; }
	public void setWorldY(int worldY) { this.worldY = worldY; }
	
}
