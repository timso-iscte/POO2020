package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class AbstractSObject implements ImageTile{

	private Point2D position;
	private String imageName;
	private int layer;
	private boolean movable;
	private boolean transposable;
	private boolean deletable;
	private boolean consumable;
	private boolean deleter;
	private boolean winner;
	private boolean wet;
	private boolean tp;
	private boolean breaker;
	private boolean breakable;

	public AbstractSObject(Point2D position, String imageName, int layer, boolean movable, boolean transposable,
			boolean deletable, boolean consumable, boolean deleter, boolean winner, boolean wet, boolean tp,boolean breaker, boolean breakable) {
		this.position = position;
		this.imageName = imageName;
		this.layer = layer;
		this.movable = movable;
		this.transposable = transposable;
		this.deletable = deletable;
		this.consumable = consumable;
		this.deleter = deleter;
		this.winner = winner;
		this.wet = wet;
		this.tp = tp;
		this.breaker = breaker;
		this.breakable = breakable;

	}

	public int getLayer() {
		return layer;
	}

	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public void setBreaker(boolean b) {
		this.breaker = b;
	}
	
	public void setImovable() {
		this.movable = false;
	}
	
	public void setNotDeleter() {
		this.deleter = false;
	}

	public String getName() {
		return imageName;
	}

//	public String getImageName() {
//		return imageName;
//	}

	public boolean isMovable() {
		return movable;
	}

	public boolean isTransposable() {
		return transposable;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public boolean isConsumable() {
		return consumable;
	}

	public boolean isDeleter() {
		return deleter;
	}

	public boolean isWinner() {
		return winner;
	}

	public boolean isWet() {
		return wet;
	}

	public boolean isTp() {
		return tp;
	}

	public boolean isBreaker() {
		return breaker;
	}
	
	public boolean isBreakable() {
		return breakable;
	}

	public void move(Direction direction) {
	}

	public void consume(SokobanGame jogo) {
	}

//	public void delete(SokobanGame jogo) {
//
//	}
	
}
