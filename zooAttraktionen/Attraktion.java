package zooAttraktionen;

import graph.Graph;
import graph.GraphWithViewer;
import graph.Vertex;
import linear.List;

public class Attraktion extends Vertex {
	private GraphWithViewer lageplan;
	private int maxBesucherzahl;
	private int aktBesucherzahl;
	private boolean wirdGeraeumt;
	private boolean istGesperrt;
	
	public Attraktion( GraphWithViewer lageplan, String pID, int maxBesucherzahl, int aktBesucherzahl, boolean wirdGeraeumt,
			boolean istGesperrt) {
		super(pID);
		this.lageplan = lageplan;
		this.maxBesucherzahl = maxBesucherzahl;
		this.aktBesucherzahl = aktBesucherzahl;
		this.wirdGeraeumt = wirdGeraeumt;
		this.istGesperrt = istGesperrt;
	}

	public int getAktBesucherzahl() {
		return aktBesucherzahl;
	}

	public void setAktBesucherzahl(int aktBesucherzahl) {
		this.aktBesucherzahl = aktBesucherzahl;
	}

	public boolean wirdGeraeumt() {
		return wirdGeraeumt;
	}

	public void setzeWirdGeraeumt(boolean wirdGeraeumt) {
		this.wirdGeraeumt = wirdGeraeumt;
	}

	public boolean istGesperrt() {
		return istGesperrt;
	}

	public void setzeIstGesperrt(boolean istGesperrt) {
		this.istGesperrt = istGesperrt;
	}

	public int getMaxBesucherzahl() {
		return maxBesucherzahl;
	}
	
	public int ermittleMaxAbgabe(Attraktion pNachbar) {
		// Check that neighbor is Valid

		List<Vertex> neighbors = lageplan.getNeighbours(this);
		Attraktion neighbor = null;
		for(neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
			if (((Attraktion)neighbors.getContent()).equals(pNachbar)){
				neighbor = (Attraktion) neighbors.getContent();
				break;
			}
		}
		// Neighbor Invalid
		if (neighbor == null) return -1;
		// Neighbor gesperrt
		if (neighbor.istGesperrt()) return -1;

		int aktuelleBesucher = pNachbar.getAktBesucherzahl();
		int weight = (int) lageplan.getEdge(this, pNachbar).getWeight();
		int spaceAtNeighbor = pNachbar.getMaxBesucherzahl() - pNachbar.getAktBesucherzahl();
		// get minimum
		int min = Integer.min(aktuelleBesucher, weight);
		min = Integer.min(min, spaceAtNeighbor);
		return min;
	}
	
	public List<Attraktion> wasLiefereIch(){
		return null;
	}

	@Override
	public String toString() {
		return "max=" + maxBesucherzahl + ", akt=" + aktBesucherzahl
				+ ", wirdGeraeumt=" + wirdGeraeumt + ", istGesperrt=" + istGesperrt + "]";
	}
	
	
	
	

	

}
