package zooAttraktionen;

import graph.Edge;
import graph.Graph;
import graph.GraphWithViewer;
import graph.Vertex;
import gui.GUI;
import linear.List;
import org.w3c.dom.Attr;


public class Besucherverwaltung {
	private GraphWithViewer lageplan;
	
	public Besucherverwaltung() {
		initGraph();
	}
	
	// Liste der Attraktionen, die von einem Startort aus NICHT in zwei Schritten zu erreichen sind
	public List<Attraktion> gibWeitentfernte(Attraktion pStart){

		class TiefenVertex {
			public Vertex weitenVertex;
			public int tiefe;

			TiefenVertex(Vertex pVertex, int tiefe){
				this.weitenVertex = pVertex;
				this.tiefe = tiefe;
			}

			public Vertex getVertex() {
				return this.weitenVertex;
			};

			public int getTiefe() {
				return this.tiefe;
			}
		}

		lageplan.setAllVertexMarks(false);
		List<Attraktion> ergList = new List<Attraktion>();
		List<TiefenVertex> testList = new List<TiefenVertex>();
		// TODO !!!
		testList.append(new TiefenVertex(pStart, 0));
		//ergList.append(pStart);
		lageplan.getVertex(pStart.getID()).setMark(true);

		testList.toFirst();
		while(testList.hasAccess()){
			// get neighbors of current
			String pId = ((TiefenVertex) testList.getContent()).getVertex().getID();
			List<Vertex> neighbors = lageplan.getNeighbours(lageplan.getVertex(pId));

			for (neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
				if (!neighbors.getContent().isMarked()){
					TiefenVertex t = new TiefenVertex(neighbors.getContent(), ((TiefenVertex) testList.getContent()).getTiefe() + 1);
					testList.append(t);
					lageplan.getVertex(neighbors.getContent().getID()).setMark(true);
				}
			}
			testList.next();
		}

		// go through list again and remove

		for (testList.toFirst(); testList.hasAccess();testList.next()){
			if (testList.getContent().getTiefe() > 2){
				ergList.append((Attraktion) testList.getContent().getVertex());
			}
		}

		return ergList;
	}

	private void initGraph() {
		lageplan = new GraphWithViewer();
		Attraktion a01 = new Attraktion(lageplan, "Toiletten", 20, 4, false, false);
		Attraktion a02 = new Attraktion(lageplan, "Spielplatz", 1000, 397, false, false);
		Attraktion a03 = new Attraktion(lageplan, "Ein-/Ausgang", Integer.MAX_VALUE, 99, false, false);
		Attraktion a04 = new Attraktion(lageplan, "Streicheltiere", 100, 87, false, false);
		Attraktion a05 = new Attraktion(lageplan, "Delfinarium", 2000, 1365, false, false);
		Attraktion a06 = new Attraktion(lageplan, "Loewen", 400, 223, false, false);
		Attraktion a07 = new Attraktion(lageplan, "Ziegen", 80, 74, false, false);
		Attraktion a08 = new Attraktion(lageplan, "Esel", 80, 55, false, false);
		Attraktion a09 = new Attraktion(lageplan, "Ausgang 1", Integer.MAX_VALUE, 71, false, false);
		Attraktion a10 = new Attraktion(lageplan, "Ausgang 2", Integer.MAX_VALUE, 211, false, false);
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
		Edge e01 = new Edge(a01, a02, 5); // Toi - Sp.pl.
		Edge e02 = new Edge(a02, a03, 500); // Sp.pl. - EinAus
		Edge e03 = new Edge(a02, a04, 150); // Sp.pl. - Str.t.
		Edge e04 = new Edge(a02, a05, 250);  // Sp.pl. - Delf.
		Edge e05 = new Edge(a04, a07, 15);  // Str.t. - Zieg
		Edge e06 = new Edge(a04, a05, 100);  // Str.t - Delf.
		Edge e07 = new Edge(a04, a08, 10);   // Str.t. - Esel
		Edge e08 = new Edge(a05, a06, 100);  // Delf - Löw
		Edge e09 = new Edge(a06, a10, 150);   // Löw - Ausg2
		Edge e10 = new Edge(a07, a08, 50);  // Zieg - Esel
		Edge e11 = new Edge(a08, a09, 80);   // Esel - Ausg1
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
		lageplan.addEdge(e11);
		
		lageplan.switchToISOMLayout();
		
	}

	public static void main(String[] args) {
		Besucherverwaltung b1 = new Besucherverwaltung();

		Attraktion a = (Attraktion) b1.lageplan.getVertex("Ziegen");

		System.out.println(a.ermittleMaxAbgabe((Attraktion) b1.lageplan.getVertex("Esel")));

		List<Attraktion> list = b1.gibWeitentfernte(a);
		System.out.println(list.isEmpty());
		for (list.toFirst();list.hasAccess();list.next()){
			System.out.print("attraktion: ");
			System.out.println(list.getContent().getID());
		}

		new GUI(b1);

	}

}
