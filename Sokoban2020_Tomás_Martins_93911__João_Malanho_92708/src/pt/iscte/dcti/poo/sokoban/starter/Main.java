// Jo�o Diogo Malanho  n 92708
// Tom�s Martins  n 93911
package pt.iscte.dcti.poo.sokoban.starter;

import java.io.FileNotFoundException;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		ImageMatrixGUI.setSize(10, 10);
		SokobanGame s = new SokobanGame();
		ImageMatrixGUI.getInstance().registerObserver(s);
		ImageMatrixGUI.getInstance().go();

	}
}
