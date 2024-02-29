package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Gelo extends AbstractSObject implements ActiveObject {

	public Gelo(Point2D position) {
		super(position, "Gelo", 1, false, true, false, false, false, false, true, false, false, false);

	}

	@Override
	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject movingObject) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ImageMatrixGUI.getInstance().update();
		for (AbstractSObject objeto : jogo
				.getObjectInThisPosition(movingObject.getPosition().plus(direction.asVector()))) {
			System.out.println(objeto);
			if (!objeto.isTransposable()) {
				break;
			} else if (objeto.isWet()) {
				movingObject.move(direction);
				ImageMatrixGUI.getInstance().update();
				this.doSomething(direction, jogo, movingObject);
				ImageMatrixGUI.getInstance().update();
			}
		}
	}
}
