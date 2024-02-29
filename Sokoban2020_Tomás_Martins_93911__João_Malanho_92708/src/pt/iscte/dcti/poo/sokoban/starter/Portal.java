package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Portal extends AbstractSObject implements ActiveObject {

	public Portal(Point2D position) {
		super(position, "Portal_Azul", 1, false, true, false, false, false, false, false, true, false, false);
	}

	@Override
	public void doSomething(Direction direction, SokobanGame jogo, AbstractSObject objeto) {
		boolean tp = true;
		Point2D portalPosition = new Point2D(0, 0);
		for (AbstractSObject zzz : jogo.getObjetos())
			if (zzz.getPosition().equals(this.getPosition()) && !zzz.isTransposable()) {
				tp = false;
				return;
			}
		for (AbstractSObject portal : jogo.getTps()) {
			if (!portal.getPosition().equals(objeto.getPosition().plus(direction.asVector()))) {
				portalPosition = portal.getPosition();
				for (AbstractSObject zzz : jogo.getObjetos())
					if (zzz.getPosition().equals(portal.getPosition()))
						if (!zzz.isTransposable()) {
							tp = false;
						}
			}
		}
		if (tp) {
			objeto.setPosition(portalPosition);
		}
	}

}
