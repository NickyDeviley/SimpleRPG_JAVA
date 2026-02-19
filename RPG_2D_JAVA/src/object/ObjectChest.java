package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectChest extends SuperObject {

	public ObjectChest() {
		
		this.name = "chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
