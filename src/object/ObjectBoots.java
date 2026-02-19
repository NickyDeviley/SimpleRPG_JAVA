package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectBoots extends SuperObject {

	public ObjectBoots() {
		
		this.name = "boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
