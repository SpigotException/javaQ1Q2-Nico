package StadtplanBukarest;

import graph.GraphWithViewer;
import graph.Vertex;
import linear.List;

public class Attraktion extends Vertex {
	private GraphWithViewer lageplan;
	private boolean istGesperrt;

	private boolean istBarriereferi;
	
	public Attraktion( GraphWithViewer lageplan, String pID, boolean istBarriereferi) {
		super(pID);
		this.lageplan = lageplan;

		this.istBarriereferi = istBarriereferi;
	}

	public List<Attraktion> wasLiefereIch(){
		return null;
	}

	public boolean isIstBarriereferi() {
		return istBarriereferi;
	}
}
