package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class ParedePartida extends AbstractSObject implements ActiveObject{

	public ParedePartida(Point2D position) {
		super(position, "Parede_Partida", 2, false, false, false, false, false, false, false, false, false, true);
		
	}

	@Override
	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject movingObject) {
		ImageMatrixGUI.getInstance().removeImage(this);
		jogo.getObjetos().remove(this);
	}
}
