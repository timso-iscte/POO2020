package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Martelo extends AbstractSObject implements ActiveObject{

	public Martelo(Point2D position) {
		super(position, "Martelo", 2, false, true, true, true, false, false, false, false, false, false);
	}

	@Override
	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject movingObject) {
		ImageMatrixGUI.getInstance().removeImage(this);
		jogo.getPlayer().setBreaker(true);	
	}
}
