package principal;

import entity.GameEntity;

public class GameCollisionChecker {
	
	private GamePanel painel;
	
	public GameCollisionChecker(GamePanel painel) {
		
		this.painel = painel;
		
	}
	
	public void checkTile(GameEntity entity) {
		
		int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
		int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
		int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
		int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;
		
		int entityLeftHorizontal = entityLeftWorldX / painel.getTileSize();
		int entityRightHorizontal = entityRightWorldX / painel.getTileSize();
		int entityTopVertical = entityTopWorldY / painel.getTileSize();
		int entityBottomVertical = entityBottomWorldY / painel.getTileSize();
		
		int tileNum1, tileNum2;
		
		switch (entity.getDirection()) {
		case "up":
			
			entityTopVertical = (entityTopWorldY - entity.getSpeed()) / painel.getTileSize();
			tileNum1 = painel.getTileManager().getMapTileNum(entityLeftHorizontal, entityTopVertical);
			tileNum2 = painel.getTileManager().getMapTileNum(entityRightHorizontal, entityTopVertical);
			
			if (painel.getTileManager().getTiles(tileNum1).getCollision() ||
				painel.getTileManager().getTiles(tileNum2).getCollision()) {
				
				entity.setCollisionLigado(true);
				
			}
			
			break;
		case "down":
			
			entityBottomVertical = (entityBottomWorldY + entity.getSpeed()) / painel.getTileSize();
			tileNum1 = painel.getTileManager().getMapTileNum(entityLeftHorizontal, entityBottomVertical);
			tileNum2 = painel.getTileManager().getMapTileNum(entityRightHorizontal, entityBottomVertical);
			if (painel.getTileManager().getTiles(tileNum1).getCollision() ||
				painel.getTileManager().getTiles(tileNum2).getCollision()) {
				
				entity.setCollisionLigado(true);
				
			}
			
			break;
		case "left":
			
			entityLeftHorizontal = (entityLeftWorldX - entity.getSpeed()) / painel.getTileSize();
			tileNum1 = painel.getTileManager().getMapTileNum(entityLeftHorizontal, entityTopVertical);
			tileNum2 = painel.getTileManager().getMapTileNum(entityLeftHorizontal, entityBottomVertical);
			if (painel.getTileManager().getTiles(tileNum1).getCollision() ||
				painel.getTileManager().getTiles(tileNum2).getCollision()) {
				
				entity.setCollisionLigado(true);
				
			}
			
			break;
		case "right":
			
			entityRightHorizontal = (entityRightWorldX + entity.getSpeed()) / painel.getTileSize();
			tileNum1 = painel.getTileManager().getMapTileNum(entityRightHorizontal, entityTopVertical);
			tileNum2 = painel.getTileManager().getMapTileNum(entityRightHorizontal, entityBottomVertical);
			if (painel.getTileManager().getTiles(tileNum1).getCollision() ||
				painel.getTileManager().getTiles(tileNum2).getCollision()) {
				
				entity.setCollisionLigado(true);
				
			}
			
			break;
		}
		
	}
	
	public int checkObject(GameEntity entity, boolean player) {
		
		int index = 999;
		
		for (int i = 0; i < painel.obj.length; i ++) {
			
			if (painel.obj[i] != null) {
				entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
				entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
				
				painel.obj[i].getSolidArea().x = painel.obj[i].getWorldX() + painel.obj[i].getSolidArea().x;
				painel.obj[i].getSolidArea().y = painel.obj[i].getWorldY() + painel.obj[i].getSolidArea().y;
				
				switch(entity.getDirection()) {
				
				case "up":
					entity.getSolidArea().y -= entity.getSpeed();
					if (entity.getSolidArea().intersects(painel.obj[i].getSolidArea())) {
						if (painel.obj[i].getCollision()) {
							//entity.getCollisionLigado();
							entity.setCollisionLigado(true);
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "down":
					entity.getSolidArea().y += entity.getSpeed();
					if (entity.getSolidArea().intersects(painel.obj[i].getSolidArea())) {
						if (painel.obj[i].getCollision()) {
							entity.setCollisionLigado(true);
							//System.out.println(i);
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "left":
					entity.getSolidArea().x -= entity.getSpeed();
					if (entity.getSolidArea().intersects(painel.obj[i].getSolidArea())) {
						if (painel.obj[i].getCollision()) {
							//entity.getCollisionLigado();
							entity.setCollisionLigado(true);
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "right":
					entity.getSolidArea().x += entity.getSpeed();
					if (entity.getSolidArea().intersects(painel.obj[i].getSolidArea())) {
						if (painel.obj[i].getCollision()) {
							//entity.getCollisionLigado();
							entity.setCollisionLigado(true);
						}
						if (player) {
							index = i;
						}
					}
					break;
				default:
					break;
				
				}
				entity.getSolidArea().x = entity.getSolidAreaDefaultX();
				entity.getSolidArea().y = entity.getSolidAreaDefaultY();
				painel.obj[i].getSolidArea().x = painel.obj[i].getSolidAreaDefaultX();
				painel.obj[i].getSolidArea().y = painel.obj[i].getSolidAreaDefaultY();
			}
		}
		return index;
	}
}
