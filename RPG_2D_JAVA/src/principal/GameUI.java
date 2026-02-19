package principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.ObjectKey;

public class GameUI {
	
	private GamePanel painel;
	private Font arial_40, arial_80B;
	private ObjectKey key;
	private BufferedImage keyImage;
	//private GameLoop gameLoop;
	
	private String message;
	private boolean messageOn;
	private boolean gameFinished;
	private int messageCounter;
	
	public GameUI (GamePanel painel) {
		this.painel = painel;
		this.arial_40 = new Font("Arial", Font.PLAIN, 40);
		this.arial_80B = new Font("Arial", Font.BOLD, 80);
		this.key = new ObjectKey();
		this.keyImage = key.getImage();
		
		this.messageOn = false;
		this.message = "";
		
		this.messageCounter = 0;
		this.gameFinished = false;
	}
	
	public void showMessage(String text) {
		
		this.message = text;
		this.messageOn = true;
		
	}
	
	public void draw(Graphics2D g2) {
		
		if (this.gameFinished == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "Você encontrou o tesouro!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = painel.getScreenWidth()/2 - textLength/2;
			y = painel.getScreenHeight()/2 - (painel.getTileSize()*3);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.YELLOW);
			
			text = "Parabéns!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = painel.getScreenWidth()/2 - textLength/2;
			y = painel.getScreenHeight()/2 + (painel.getTileSize()*2);
			g2.drawString(text, x, y);
			
			//this.gameLoop.killThread();
		}
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			g2.drawImage(keyImage, (painel.getTileSize()/2), (painel.getTileSize()/2), painel.getTileSize(), painel.getTileSize(), null);
			g2.drawString("x = " + painel.getJogador().getKeyNumber(), 74, 65);
			
			if (this.messageOn) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, (painel.getTileSize()/2), (painel.getTileSize()*5));
				
				messageCounter++;
				
				if (this.messageCounter > 120) {
					this.messageCounter = 0;
					this.messageOn = false;
				}
			}
		}
	}
	
	public boolean getGameFinished() { return this.gameFinished; }
	
	public void setGameFinished(boolean gameFinished) { this.gameFinished = gameFinished; }
	//public void setGameThread(Thread thread) { this.thread = thread; }
	//public void setGameLoop(GameLoop gameLoop) { this.gameLoop = gameLoop; }
	
}
