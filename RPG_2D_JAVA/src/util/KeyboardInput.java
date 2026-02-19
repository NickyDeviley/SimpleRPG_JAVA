package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

	private boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean checkDrawTime = false;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// Verifica as teclas seguradas
		
		int code = e.getKeyCode();	// Armazena o código da tecla
		
		if (code == KeyEvent.VK_W) { upPressed = true; }
		if (code == KeyEvent.VK_S) { downPressed = true; }
		if (code == KeyEvent.VK_A) { leftPressed = true; }
		if (code == KeyEvent.VK_D) { rightPressed = true; }
		
		// DEBUG
		if (code == KeyEvent.VK_T) { 
			if (checkDrawTime == false) {
				checkDrawTime = true;
			}
			else if (checkDrawTime == true) {
				checkDrawTime = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Verifica as teclas que foram soltas
		
		int code = e.getKeyCode();	// Armazena o código da tecla
		
		if (code == KeyEvent.VK_W) { upPressed = false; }
		if (code == KeyEvent.VK_S) { downPressed = false; }
		if (code == KeyEvent.VK_A) { leftPressed = false; }
		if (code == KeyEvent.VK_D) { rightPressed = false; }
		
	}

	// Métodos Get
	public boolean getUpPressed() { return this.upPressed; }
	public boolean getDownPressed() { return this.downPressed; }
	public boolean getLeftPressed() { return this.leftPressed; }
	public boolean getRightPressed() { return this.rightPressed; }

}
