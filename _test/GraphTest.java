package _test;


import java.util.*;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.GraphWithViewer;
import gui.GUI;
import linear.List;
import linear.ListWithViewer;


public class GraphTest {

	// Attribut, in dem die Karte gespeichert wird.
	public GraphWithViewer karte;

	// Rahmenmethode zum testen
	public List<Vertex> tiefendurchlaufVonKassel(){
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Kassel");
		return tiefendurchlauf(vKassel);
	}
	
	// rekursive Methode
	private List<Vertex> tiefendurchlauf(Vertex pVertex) {
		List<Vertex> ergebnis = new ListWithViewer<>();
		// TODO selber programmieren!!!

		if (pVertex.isMarked())
			return ergebnis;

		// get neighbor list
		List<Vertex> neighbors = karte.getNeighbours(pVertex);

		// add current Vertex to ergebnis
		ergebnis.append(pVertex);

		// mark current Vertex
		pVertex.setMark(true);

		// add all the Neighbors that aren`t yet marked
		for(neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
			if(!neighbors.getContent().isMarked()){
				// call method recursively
				List<Vertex> listOfNeighbor = tiefendurchlauf(neighbors.getContent());
				ergebnis.concat(listOfNeighbor);
			}
		}

		return ergebnis;
	}

	// Rahmenmethode zum testen
	public List<Vertex> breitendurchlaufVonKassel(){
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Kassel");
		return breitendurchlauf(vKassel);
	}
	
	// NICHT-rekursive Methode
	private List<Vertex> breitendurchlauf(Vertex pVertex) {
		List<Vertex> ergebnis = new ListWithViewer<>();
		// TODO selber programmieren!!!

		// add CurrVertex and mark it
		ergebnis.append(pVertex);
		pVertex.setMark(true);

		//"Levelorder"

		for(ergebnis.toFirst(); ergebnis.hasAccess();ergebnis.next()){
			List<Vertex> neighbors = karte.getNeighbours(ergebnis.getContent());
			for(neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
				if(!neighbors.getContent().isMarked()){
					neighbors.getContent().setMark(true);
					ergebnis.append(neighbors.getContent());
				}
			}
		}

		return ergebnis;
	}

	// Remove a given Vertex from a List of Vertices
	private void removeVertexFromList(List<Vertex> list, Vertex v){
		for(list.toFirst(); list.hasAccess();list.next()) {
			if (list.getContent().getID().equals(v.getID())){
				list.remove();
			}
		}
	}

	private List<Vertex> Dijkstra(Vertex pStart, Vertex pEnd){
		// Set all Vertices to unmarked
		karte.setAllVertexMarks(false);

		// Distanz Hashmap
		HashMap<String, Double> distances = new HashMap<>();

		// path Hashmap to track the previous Vertex for each one
		HashMap<Vertex, Vertex> path = new HashMap<>();

		// Set of unvisited vertices
		List<Vertex> unvisitedList = karte.getVertices();
		Set<Vertex> unvisited = new HashSet<Vertex>();
		for(unvisitedList.toFirst(); unvisitedList.hasAccess();unvisitedList.next()){
			unvisited.add(unvisitedList.getContent());
		}

		// set cost for all vertices to infinity
		for(Vertex v: unvisited){
			distances.put(v.getID(), Double.POSITIVE_INFINITY);
			path.put(v, v);
		}

		// "Visit" the start Node and remove it from Set
		distances.put(pStart.getID(), 0.0);

		Vertex current = pStart;

		while(!unvisited.isEmpty()){
			// remove current Node from unvisited Set
			unvisited.remove(current);
			current.setMark(true);
			// get all neighbors of the current Node
			List<Vertex> neighbors = karte.getNeighbours(current);
			// Update Distances for reachable Vertices
			for(neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
				if(distances.get(neighbors.getContent().getID()) > karte.getEdge(current, neighbors.getContent()).getWeight() + distances.get(current.getID())){
					distances.put(neighbors.getContent().getID(), karte.getEdge(current, neighbors.getContent()).getWeight() + distances.get(current.getID()));
					path.put(neighbors.getContent(), current);
				}
			}
			// new Vertex is now Closest vertex
			// INFO i am confused by what i am doing but it works
			//Vertex newVertex = getClosestUnmarkedNeighbor(current);
			//if(newVertex == null) {

				// currently getting the lowest cost out of the openset
				Double dist = Double.POSITIVE_INFINITY;
				Vertex best = null;
				for(Vertex v: unvisited){
					if(distances.get(v.getID()) < dist){
						dist = distances.get(v.getID());
						best = v;
					}
				}

				if (best == null)
					break;
				Vertex newVertex = best;
			//}
			current = newVertex;
		}
		// INFO Backtrack the path
		List<Vertex> ergebnis = new List<>();
		Vertex curr = pEnd;
		while(curr != pStart){
			ergebnis.append(curr);
			curr = path.get(curr);
		}
		ergebnis.append(pStart);

		return ergebnis;
		//return distances.get(pEnd.getID());
	}

	public List<Vertex> shortestPath(String pStart, String pEnd){
		return Dijkstra(karte.getVertex(pStart), karte.getVertex(pEnd));
	}


	public GraphTest(){
		karte = new GraphWithViewer();
		Vertex dortmund = new Vertex("Dortmund");
		karte.addVertex(dortmund);
		Vertex koeln = new Vertex("Koeln");
		karte.addVertex(koeln);
		Vertex frankfurt = new Vertex("Frankfurt");
		karte.addVertex(frankfurt);
		Vertex kassel = new Vertex("Kassel");
		karte.addVertex(kassel);
		Vertex wuerzburg = new Vertex("Wuerzburg");
		karte.addVertex(wuerzburg);

		Edge kassel_dortmund = new Edge(kassel, dortmund, 160);
		karte.addEdge(kassel_dortmund);
		Edge dortmund_koeln = new Edge(dortmund, koeln, 93);
		karte.addEdge(dortmund_koeln);
		Edge frankfurt_kassel = new Edge(frankfurt, kassel, 193);
		karte.addEdge(frankfurt_kassel);
		Edge kassel_wuerzburg = new Edge(kassel, wuerzburg, 209);
		karte.addEdge(kassel_wuerzburg);
		Edge wuerzburg_frankfurt = new Edge(wuerzburg, frankfurt, 119);
		karte.addEdge(wuerzburg_frankfurt);
		Edge frankfurt_koeln = new Edge(frankfurt, koeln, 189);
		karte.addEdge(frankfurt_koeln);

		// *** weitere Vertices und Edges! ***
		
		Vertex hamburg = new Vertex("Hamburg");
		karte.addVertex(hamburg);
		Vertex berlin = new Vertex("Berlin");
		karte.addVertex(berlin);
		Vertex bremen = new Vertex("Bremen");
		karte.addVertex(bremen);
		Vertex hannover = new Vertex("Hannover");
		karte.addVertex(hannover);
		Vertex leipzig = new Vertex("Leipzig");
		karte.addVertex(leipzig);
		Vertex nuernberg = new Vertex("Nuernberg");
		karte.addVertex(nuernberg);
		Vertex stuttgart = new Vertex("Stuttgart");
		karte.addVertex(stuttgart);
		Vertex muenchen = new Vertex("Muenchen");
		karte.addVertex(muenchen);
		Vertex karlsruhe = new Vertex("Karlsruhe");
		karte.addVertex(karlsruhe);
		Vertex aachen = new Vertex("Aachen");
		karte.addVertex(aachen);

		Edge e = new Edge(berlin, hamburg, 289);
		karte.addEdge(e);
		Edge hamburg_bremen = new Edge(hamburg, bremen, 119);
		karte.addEdge(hamburg_bremen);
		Edge bremen_hannover = new Edge(bremen, hannover, 122);
		karte.addEdge(bremen_hannover);
		Edge hannover_hamburg = new Edge(hannover, hamburg, 150);
		karte.addEdge(hannover_hamburg);
		Edge berlin_hannover = new Edge(berlin, hannover, 290);
		karte.addEdge(berlin_hannover);
		Edge berlin_leipzig = new Edge(berlin, leipzig, 188);
		karte.addEdge(berlin_leipzig);
		Edge hannover_kassel = new Edge(hannover, kassel, 167);
		karte.addEdge(hannover_kassel);
		Edge leipzig_kassel = new Edge(leipzig, kassel, 250);
		karte.addEdge(leipzig_kassel);
		Edge dortmund_bremen = new Edge(dortmund, bremen, 234);
		karte.addEdge(dortmund_bremen);
		Edge dortmund_hannover = new Edge(dortmund, hannover, 210);
		karte.addEdge(dortmund_hannover);
		Edge leipzig_nuernberg = new Edge(leipzig, nuernberg, 278);
		karte.addEdge(leipzig_nuernberg);
		Edge wuerzburg_nuernberg = new Edge(wuerzburg, nuernberg, 110);
		karte.addEdge(wuerzburg_nuernberg);
		Edge nuernberg_muenchen = new Edge(nuernberg, muenchen, 166);
		karte.addEdge(nuernberg_muenchen);
		Edge muenchen_stuttgart = new Edge(muenchen, stuttgart, 223);
		karte.addEdge(muenchen_stuttgart);
		Edge nuernberg_stuttgart = new Edge(nuernberg, stuttgart, 208);
		karte.addEdge(nuernberg_stuttgart);
		Edge stuttgart_karlsruhe = new Edge(stuttgart, karlsruhe, 82);
		karte.addEdge(stuttgart_karlsruhe);
		Edge karlsruhe_frankfurt = new Edge(karlsruhe, frankfurt, 147);
		karte.addEdge(karlsruhe_frankfurt);
		Edge aachen_koeln = new Edge(aachen, koeln, 68);
		karte.addEdge(aachen_koeln);

		// auf ein geeignetes Layout umstellen
		karte.switchToISOMLayout();
	}
	
	public void printMatrix() {
		int anzahlStaedte = 0;
		Vector<String> staedte = new Vector<String>();
		String staedteString = "staedte = [";
		List<Vertex> vertices = karte.getVertices();
		for(vertices.toFirst(); vertices.hasAccess(); vertices.next()) {
			String stadt = vertices.getContent().getID();
			staedteString += "'"+stadt+"'"+",";
			staedte.add(stadt);
			anzahlStaedte++;
		}
		staedteString = staedteString.substring(0,staedteString.length()-1);
		staedteString += "]";
		System.out.println(staedteString);
		int[][] entfernungen = new int[anzahlStaedte][anzahlStaedte];
		List<Edge> edges = karte.getEdges();
		for(edges.toFirst(); edges.hasAccess(); edges.next()) {
			Edge e = edges.getContent();
			double weight = e.getWeight();
			Vertex v0 = e.getVertices()[0];
			Vertex v1 = e.getVertices()[1];
			int index0 = staedte.indexOf(v0.getID());		
			int index1 = staedte.indexOf(v1.getID());
			entfernungen[index0][index1] = (int)weight;
			entfernungen[index1][index0] = (int)weight;
		}
		System.out.println();
		System.out.println("entfernungen = []");
		for(int[] zeile:entfernungen) {
			String z = "entfernungen.add([";
			for(int zelle:zeile) {
				z += zelle + ",";
			}
			z = z.substring(0,z.length()-1);
			z += "])";
			System.out.println(z);
			
		}
		
	}

	public String getClosestNeighbor(String p1){
		Vertex v = karte.getVertex(p1);
		List<Vertex> neighbors = karte.getNeighbours(v);
		neighbors.toFirst();
		Vertex nearest = neighbors.getContent();
		for(neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
			Vertex currNeighbor = neighbors.getContent();
			Edge e = karte.getEdge(v, currNeighbor);
			if(e.getWeight() < karte.getEdge(v, nearest).getWeight()){
				nearest = currNeighbor;
			}
		}
		return nearest.getID();
	}

	public Vertex getClosestUnmarkedNeighbor(Vertex v){
		List<Vertex> neighbors = karte.getNeighbours(v);
		neighbors.toFirst();
		// get first unmarked Vertex
		Vertex nearest = null;
		for(neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
			if(!neighbors.getContent().isMarked()) {
				nearest = neighbors.getContent();
			}
		}
		if (nearest == null){
			return null;
		}
		for(neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
			if(!neighbors.getContent().isMarked()) {
				Vertex currNeighbor = neighbors.getContent();
				Edge e = karte.getEdge(v, currNeighbor);
				if (e.getWeight() < karte.getEdge(v, nearest).getWeight()) {
					nearest = currNeighbor;
				}
			}
		}
		return nearest;
	}

	public Vertex getClosestNeighbor(Vertex v){
		List<Vertex> neighbors = karte.getNeighbours(v);
		neighbors.toFirst();
		Vertex nearest = neighbors.getContent();
		for(neighbors.toFirst(); neighbors.hasAccess();neighbors.next()){
			Vertex currNeighbor = neighbors.getContent();
			Edge e = karte.getEdge(v, currNeighbor);
			if(e.getWeight() < karte.getEdge(v, nearest).getWeight()){
				nearest = currNeighbor;
			}
		}
		return nearest;
	}
	

	
	public static void main(String[] args) {
		GraphTest gt = new GraphTest();
		new GUI(gt);


		// MEthods
		System.out.println("Muenchen to Bremen:");
		List<Vertex> umgedreht = gt.shortestPath("Muenchen", "Bremen");
		for(umgedreht.toFirst(); umgedreht.hasAccess();umgedreht.next()){
			System.out.println(umgedreht.getContent().getID());
		}

	}

}
