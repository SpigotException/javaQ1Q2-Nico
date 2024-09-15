package StadtplanBukarest;

import graph.Edge;
import graph.GraphWithViewer;
import graph.Vertex;
import gui.GUI;
import linear.List;
import linear.ListWithViewer;


public class Besucherverwaltung {
	private GraphWithViewer lageplan;

	public Besucherverwaltung() {
		initGraph();
	}

	private void initGraph() {
		lageplan = new GraphWithViewer();
		Attraktion a01 = new Attraktion(lageplan, "G",false);
		Attraktion a02 = new Attraktion(lageplan, "P",  false);
		Attraktion a03 = new Attraktion(lageplan, "M", true);
		Attraktion a04 = new Attraktion(lageplan, "n", true);
		Attraktion a05 = new Attraktion(lageplan, "E", false);
		Attraktion a06 = new Attraktion(lageplan, "V", false);
		Attraktion a07 = new Attraktion(lageplan, "U", true);
		Attraktion a08 = new Attraktion(lageplan, "I", false);
		Attraktion a09 = new Attraktion(lageplan, "D",  false);
		Attraktion a10 = new Attraktion(lageplan, "G", false);
		lageplan.addVertex(a01);
		lageplan.addVertex(a02);
		lageplan.addVertex(a03);
		lageplan.addVertex(a04);
		lageplan.addVertex(a05);
		lageplan.addVertex(a06);
		lageplan.addVertex(a07);
		lageplan.addVertex(a08);
		lageplan.addVertex(a09);
		lageplan.addVertex(a10);
		Edge e01 = new Edge(a01, a02, 5);//G P
		Edge e02 = new Edge(a02, a05, 2); //P E
		Edge e03 = new Edge(a05, a07, 7); // E u
		Edge e04 = new Edge(a05, a04, 9);  // Sp.pl. - Delf.
		Edge e05 = new Edge(a04, a03, 4);  // Str.t. - Zieg
		Edge e06 = new Edge(a04, a06, 2);  // Str.t - Delf.
		Edge e07 = new Edge(a06, a08, 5);   // Str.t. - Esel
		Edge e08 = new Edge(a06, a07, 5);  // Delf - Löw
		Edge e09 = new Edge(a07, a09, 7);   // Löw - Ausg2
		Edge e10 = new Edge(a09, a08, 4);  // Zieg - Esel
		//Edge e11 = new Edge(a08, a09, 80);   // Esel - Ausg1
		lageplan.addEdge(e01);
		lageplan.addEdge(e02);
		lageplan.addEdge(e03);
		lageplan.addEdge(e04);
		lageplan.addEdge(e05);
		lageplan.addEdge(e06);
		lageplan.addEdge(e07);
		lageplan.addEdge(e08);
		lageplan.addEdge(e09);
		lageplan.addEdge(e10);
		//lageplan.addEdge(e11);

		lageplan.switchToISOMLayout();

	}
	public List<Vertex> gibNaheBarriereferie(String pStart){
		ListWithViewer<Vertex> ergbnis = new ListWithViewer<>();
		Vertex v = lageplan.getVertex(pStart);
		ergbnis.append(v);
		v.setMark(true);
		for (ergbnis.toFirst();ergbnis.hasAccess();ergbnis.next()){
			List<Vertex> n = lageplan.getNeighbours(ergbnis.getContent());

			for (n.toFirst();n.hasAccess();n.next()){
				if (!n.getContent().isMarked()){
					n.getContent().setMark(true);
					ergbnis.append(n.getContent());
				}
			}
		}
		return ergbnis;
	}
	public List<Vertex> Breitensuche3(){
		List<Vertex> erg= new ListWithViewer<>();
		List<Vertex> erg1 = new ListWithViewer<>();
		Vertex v = lageplan.getVertex("D");
		erg.append(v);
		v.setMark(true);
		for (erg.toFirst();erg.hasAccess();erg.next()){
			List<Vertex> n1 = lageplan.getNeighbours(erg.getContent());
			for (n1.toFirst();n1.hasAccess(); n1.next()){
				if (!n1.getContent().isMarked()){
					erg1.append(n1.getContent());
				}
			}
		}
		//weiter
		return erg;
	}
	public double gibFahrzeit(){

		List<Attraktion> pStrecke = new ListWithViewer<>();
		pStrecke.append((Attraktion) lageplan.getVertex("G"));
		pStrecke.append((Attraktion) lageplan.getVertex("P"));
		pStrecke.append((Attraktion) lageplan.getVertex("E"));
		pStrecke.append((Attraktion) lageplan.getVertex("n"));
		pStrecke.append((Attraktion) lageplan.getVertex("M"));

		double result = 0;
		pStrecke.toFirst();
		Vertex current = pStrecke.getContent();
		pStrecke.next();


		while (pStrecke.hasAccess()){
			Vertex v1 = pStrecke.getContent();
			result  += lageplan.getEdge(current,v1).getWeight();
			current = v1;
			pStrecke.next();
		}
	return result;
	}


public List<Vertex> gibAlleKonoten(){
	List<Vertex> g = lageplan.getVertices();
	for (g.toFirst();g.hasAccess(); g.next()){
		System.out.println(g.getContent());
	}

return g;
}

	public static void main(String[] args) {
		Besucherverwaltung b1 = new Besucherverwaltung();


		new GUI(b1);

	}

}
