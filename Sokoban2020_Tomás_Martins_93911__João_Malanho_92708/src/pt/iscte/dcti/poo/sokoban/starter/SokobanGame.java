// Joao Diogo Malanho  n 92708
// Tomas Martins  n 93911

package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {

	private Player player;
	private List<AbstractSObject> objetos = new ArrayList<AbstractSObject>();
	private List<AbstractSObject> alvos = new ArrayList<AbstractSObject>();
	private List<AbstractSObject> tps = new ArrayList<AbstractSObject>();

	private int level = 0;
	private final int MAXLEVEL = countLevels();
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;

	public SokobanGame() throws FileNotFoundException {

		buildlevel();

	}

	public int countLevels() {
		File files = new File("levels");
		File[] listOfFiles = files.listFiles();
		return listOfFiles.length - 1;
	}

	public void nextLevel() {
		Score score = new Score(level, player.getScore());
		score.addScore();
		level++;
		if (level < MAXLEVEL) {
			objetos.clear();
			alvos.clear();
			player.setBreaker(false);
			ImageMatrixGUI.getInstance().clearImages();
			buildlevel();
		} else {
			objetos.clear();
			alvos.clear();
			player.setBreaker(false);
			ImageMatrixGUI.getInstance().clearImages();
			ImageMatrixGUI.getInstance().update();
			level = -1;
			buildlevel();

		}
	}

	public void verifyLevel() {
		int n = 0;
		for (AbstractSObject alvo : alvos) {
			for (AbstractSObject caixote : getObjetos()) {
				if (alvo.getPosition().equals(caixote.getPosition()) && caixote.isWinner()) {
					n++;
				}
			}
		}
		if (alvos.size() == n) {
			nextLevel();
		}
	}

	public List<AbstractSObject> getObjetos() {
		return objetos;
	}

	public List<AbstractSObject> getTps() {
		return tps;
	}

	public void setNullPlayer() {
		player = null;
	}

	public void buildlevel() {
		try {
			List<ImageTile> tiles = new ArrayList<ImageTile>();
			File file = new File("levels/level" + level + ".txt");

			Scanner sc = new Scanner(file);
			int j = 0;
			while (sc.hasNextLine()) {
				String lines = sc.nextLine();
				j++;
				for (int i = 0; i != WIDTH; i++) {
					tiles.add(new Chao(new Point2D(i, j - 1)));

					if (lines.charAt(i) == '#') {
						objetos.add(new Parede(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 'C') {
						objetos.add(new Caixote(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 'E') {
						player = new Player(new Point2D(i, j - 1));
					}
					if (lines.charAt(i) == 'X') {
						objetos.add(new Alvo(new Point2D(i, j - 1)));
						alvos.add(new Alvo(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 'b') {
						objetos.add(new Bateria(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 'O') {
						objetos.add(new Buraco(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 'P') {
						objetos.add(new BigStone(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 'p') {
						objetos.add(new SmallStone(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 'm') {
						objetos.add(new Martelo(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == '%') {
						objetos.add(new ParedePartida(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 'g') {
						objetos.add(new Gelo(new Point2D(i, j - 1)));
					}
					if (lines.charAt(i) == 't') {
						objetos.add(new Portal(new Point2D(i, j - 1)));
						tps.add(new Portal(new Point2D(i, j - 1)));
					}
				}
			}
			tiles.add(player);
			for (AbstractSObject O : objetos) {
				tiles.add(O);
			}
			ImageMatrixGUI.getInstance().addImages(tiles);
			ImageMatrixGUI.getInstance().update();
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Player getPlayer() {
		return this.player;
	}

	public List<AbstractSObject> getObjectInThisPosition(Point2D position) {
		List<AbstractSObject> objectsInPosition = new ArrayList<AbstractSObject>();
		for (AbstractSObject objeto : getObjetos()) {
			if (objeto.getPosition().equals(position))
				objectsInPosition.add(objeto);
		}
		return objectsInPosition;
	}

	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI) arg0).keyPressed();
		System.out.println("Key pressed " + lastKeyPressed);
		// VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT

		if (player != null) {

			player.doSomething(Direction.directionFor(lastKeyPressed), this, null);
			if (player.getMovesLeft() <= 0)
				player = null;

		}
	}
}