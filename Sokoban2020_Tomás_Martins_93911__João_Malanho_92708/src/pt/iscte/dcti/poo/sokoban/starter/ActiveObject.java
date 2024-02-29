package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;

public interface ActiveObject {

	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject objeto);

}
