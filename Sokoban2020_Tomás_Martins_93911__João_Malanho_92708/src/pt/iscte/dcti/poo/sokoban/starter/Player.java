package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends AbstractSObject implements ActiveObject {

	private int movesLeft;
	private int score;

	public Player(Point2D position) {
		super(position, "Empilhadora_U", 2, false, false, true, false, false, false, false, false, false, false);
		movesLeft = 100;
		ImageMatrixGUI.getInstance().setStatusMessage("Bateria: " + movesLeft);
	}

	public int getScore() {
		return this.score;
	}

	public int getMovesLeft() {
		return movesLeft;
	}

	public void addMovesLeft(int moves) {
		movesLeft += moves;
	}

	@Override
	public void move(Direction direction) {
		setImageName("Empilhadora_" + direction.toString().charAt(0));
		Point2D newPosition = getPosition().plus(direction.asVector());
		setPosition(newPosition);
		movesLeft--;
		score++;
		ImageMatrixGUI.getInstance().update();
		ImageMatrixGUI.getInstance().setStatusMessage("Bateria: " + movesLeft + "         Score:" + getScore());
		if (movesLeft == 0)
			ImageMatrixGUI.getInstance().setMessage("");
	}

	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject movingObject) {
		Point2D newPosition = getPosition().plus(direction.asVector());
		ArrayList<AbstractSObject> samePosition = new ArrayList<AbstractSObject>();
		for (AbstractSObject objeto : jogo.getObjetos())
			if (objeto.getPosition().equals(newPosition))
				samePosition.add(objeto);
		boolean canMove = true;
		for (AbstractSObject objeto : samePosition) {
			if (!objeto.isTransposable()) {
				if (objeto.isBreakable() && this.isBreaker()) {
					((ActiveObject) objeto).doSomething(direction, jogo, objeto);
				} else if (objeto.isMovable()) {
					((ActiveObject) objeto).doSomething(direction, jogo, objeto);
					if (objeto.getPosition().equals(newPosition))
						canMove = false;
				} else
					canMove = false;
			} else {
				if (objeto.isTp()) {
					canMove = false;
				}
				((ActiveObject) objeto).doSomething(direction, jogo, this);
			}
		}
		if (canMove) {
			move(direction);
		}
		ImageMatrixGUI.getInstance().update();
	}

}
