package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class BigStone extends AbstractSObject implements ActiveObject {

	public BigStone(Point2D position) {
		super(position, "BigStone", 2, true, false, false, false, false, false, false, false, false, false);
	}

	@Override
	public void move(Direction direction) {
		Point2D newPosition = getPosition().plus(direction.asVector());
			this.setPosition(newPosition);
	}

	@Override
	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject movingObject) {
		Point2D newPosition = this.getPosition().plus(direction.asVector());
		ArrayList<AbstractSObject> samePosition = new ArrayList<AbstractSObject>();
		for (AbstractSObject objeto : jogo.getObjetos())
			if (objeto.getPosition().equals(newPosition))
				samePosition.add(objeto);
		boolean canMove = true;
		for (AbstractSObject objeto : samePosition) {
			if (!objeto.isTransposable()) {
				canMove = false;
			} else if (objeto.isTp()) {
				((ActiveObject) objeto).doSomething(direction, jogo, this);
				canMove = false;
			}
			if (objeto.isDeleter())
				((ActiveObject) objeto).doSomething(direction, jogo, this);
			if (objeto.isWet()) {
				((ActiveObject) objeto).doSomething(direction, jogo, this);
			}
		}
		if (canMove)
			move(direction);
	}

}
