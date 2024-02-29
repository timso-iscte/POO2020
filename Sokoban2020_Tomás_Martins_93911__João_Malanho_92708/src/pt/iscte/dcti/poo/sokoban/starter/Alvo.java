package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Alvo extends AbstractSObject implements ActiveObject {

	public Alvo(Point2D position) {
		super(position, "Alvo", 1, false, true, false, false, false, false, false, false, false, false);
	}

	@Override
	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject objeto) {
	}

}
