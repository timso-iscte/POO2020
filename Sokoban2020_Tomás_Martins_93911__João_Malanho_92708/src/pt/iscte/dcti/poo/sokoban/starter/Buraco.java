package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractSObject implements ActiveObject {

	public Buraco(Point2D position) {
		super(position, "Buraco", 1, false, true, false, false, true, false, false, false, false, false);

	}



	@Override
	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject movingObject) {		
		if (getPosition().equals(jogo.getPlayer().getPosition().plus(direction.asVector())) && this.isDeleter()) {
			ImageMatrixGUI.getInstance().dispose();
		} else if (movingObject.isDeletable() && this.isDeleter()) {
			ImageMatrixGUI.getInstance().removeImage(movingObject);
			jogo.getObjetos().remove(movingObject);
		} else if (!movingObject.isDeletable() && !movingObject.isDeleter()) {
			movingObject.setImovable();
			this.setNotDeleter();
		}
	}

}
