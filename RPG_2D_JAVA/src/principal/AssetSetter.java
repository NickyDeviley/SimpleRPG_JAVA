package principal;

import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;

public class AssetSetter {

	GamePanel painel;
	
	public AssetSetter(GamePanel painel) {
		
		this.painel = painel;
		
	}
	
	public void setObject() {
		
		//painel.setObj(0, new ObjectKey());
		//painel.getObj(0).getWorldX = 23 * painel.getTileSize();
		painel.obj[0] = new ObjectKey();
		painel.obj[0].setWorldX(23 * painel.getTileSize());
		painel.obj[0].setWorldY(7 * painel.getTileSize());
		
		painel.obj[1] = new ObjectKey();
		painel.obj[1].setWorldX(23 * painel.getTileSize());
		painel.obj[1].setWorldY(40 * painel.getTileSize());
		
		painel.obj[2] = new ObjectKey();
		painel.obj[2].setWorldX(38 * painel.getTileSize());
		painel.obj[2].setWorldY(8 * painel.getTileSize());
		
		painel.obj[3] = new ObjectDoor();
		painel.obj[3].setWorldX(10 * painel.getTileSize());
		painel.obj[3].setWorldY(11 * painel.getTileSize());
		
		painel.obj[4] = new ObjectDoor();
		painel.obj[4].setWorldX(8 * painel.getTileSize());
		painel.obj[4].setWorldY(28 * painel.getTileSize());
		
		painel.obj[5] = new ObjectDoor();
		painel.obj[5].setWorldX(12 * painel.getTileSize());
		painel.obj[5].setWorldY(22 * painel.getTileSize());
		
		painel.obj[6] = new ObjectChest();
		painel.obj[6].setWorldX(10 * painel.getTileSize());
		painel.obj[6].setWorldY(7 * painel.getTileSize());
		
		painel.obj[7] = new ObjectBoots();
		painel.obj[7].setWorldX(37 * painel.getTileSize());
		painel.obj[7].setWorldY(42 * painel.getTileSize());
		//painel.obj[7].setWorldX(22 * painel.getTileSize());
		//painel.obj[7].setWorldY(22 * painel.getTileSize());
	}
}
