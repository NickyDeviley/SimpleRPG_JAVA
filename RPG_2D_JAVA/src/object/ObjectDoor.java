package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectDoor extends SuperObject{

	public ObjectDoor() {
		
		this.name = "door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		this.collision = true;
		
	}
	
}
