package _test;
import linear.List;
import linear.ListWithViewer;
import gui.GUI;
import baeume.BinaryTree;
import baeume.TreeViewer;


public class BinaryTreeTest {
	public BinaryTree<Integer> suchbaum;
	
	public BinaryTreeTest(){
        suchbaum = new BinaryTree<Integer>(59);
        BinaryTree<Integer> b4= new BinaryTree<Integer>(4);
        BinaryTree<Integer> b34 = new BinaryTree<Integer>(34);
        BinaryTree<Integer> b16 = new BinaryTree<Integer>(16);
        BinaryTree<Integer> b7= new BinaryTree<Integer>(7);
        BinaryTree<Integer> b6 = new BinaryTree<Integer>(6);
        BinaryTree<Integer> b12 = new BinaryTree<Integer>(12);
        BinaryTree<Integer> b18= new BinaryTree<Integer>(18);
        BinaryTree<Integer> b17= new BinaryTree<Integer>(17);
        BinaryTree<Integer> b53 = new BinaryTree<Integer>(53);
        BinaryTree<Integer> b45 = new BinaryTree<Integer>(45);
        BinaryTree<Integer> b78 = new BinaryTree<Integer>(78);
        BinaryTree<Integer> b62 = new BinaryTree<Integer>(62);
        BinaryTree<Integer> b61= new BinaryTree<Integer>(61);
        BinaryTree<Integer> b71 = new BinaryTree<Integer>(71);
        BinaryTree<Integer> b73 = new BinaryTree<Integer>(73);
        
        suchbaum.setLeftTree(b4);
        b4.setRightTree(b34);
        b7.setLeftTree(b6);
        b7.setRightTree(b12);
        b16.setRightTree(b18);
        b18.setLeftTree(b17);
        b16.setLeftTree(b7);
        b34.setLeftTree(b16);
        b34.setRightTree(b53);
        b53.setLeftTree(b45);
        suchbaum.setRightTree(b78);
        b78.setLeftTree(b62);
        b62.setLeftTree(b61);
        b62.setRightTree(b71);
        b71.setRightTree(b73);
        TreeViewer.showTree(suchbaum, 600, 400);
	}
	public int gibAnzahlKnoten(){
        return gibAnzahlKnoten(suchbaum);
    }
	// Rahmenmethode
	public int summe(){
		return summe(suchbaum);
	}

    public int gibTiefe(){return gibTiefe(suchbaum);}

    public void preOrder(){
        List<Integer> list = preOrder(suchbaum);
        System.out.println("Pre-Order Traversierung:");
        for(list.toFirst();list.hasAccess();list.next()){
            System.out.println(list.getContent());
        }
    }

    public void inOrder(){
        List<Integer> list = inOrder(suchbaum);
        System.out.println("In-Order Traversierung:");
        for(list.toFirst();list.hasAccess();list.next()){
            System.out.println(list.getContent());
        }
    }

    private int gibAnzahlKnoten(BinaryTree<Integer> pBaum){
        if(pBaum.isEmpty()){
            return 0;
        }
        int lAnzahl = gibAnzahlKnoten(pBaum.getLeftTree());
        int rAnzahl = gibAnzahlKnoten(pBaum.getRightTree());
        return (lAnzahl + 1 + rAnzahl);
    }

	private int summe(BinaryTree<Integer> pTree) {
		int ergebnis = 0;
		// TODO programmieren:
		// Abbruchbedingung, Wurzelbehandlung, 2 rekursive Aufrufe, Sachlogik
        if(pTree.isEmpty()){
            return 0;
        }
        ergebnis += pTree.getContent();
        ergebnis += summe(pTree.getLeftTree());
        ergebnis += summe(pTree.getRightTree());
		return ergebnis;
	}

    private int gibTiefe(BinaryTree<Integer> pTree){
        if(pTree.isEmpty()){
            return -1;
        }
        if(gibTiefe(pTree.getLeftTree()) < 0 && gibTiefe(pTree.getRightTree()) < 0){
            return 0;
        }
        return gibTiefe(pTree.getLeftTree()) > gibTiefe(pTree.getRightTree()) ? gibTiefe(pTree.getLeftTree()) + 1 : gibTiefe(pTree.getRightTree()) + 1;
    }

    private List<Integer> preOrder(BinaryTree<Integer> pTree){
        List<Integer> ergebnis = new List<>();
        if (pTree.isEmpty()){
            return ergebnis;
        }
        ergebnis.append(pTree.getContent());
        ergebnis.concat(preOrder(pTree.getLeftTree()));
        ergebnis.concat(preOrder(pTree.getRightTree()));

        return ergebnis;
    }

    private List<Integer> inOrder(BinaryTree<Integer> pTree){
        List<Integer> ergebnis = new List<>();
        if (pTree.isEmpty()){
            return ergebnis;
        }
        ergebnis.concat(inOrder(pTree.getLeftTree()));
        ergebnis.append(pTree.getContent());
        ergebnis.concat(inOrder(pTree.getRightTree()));

        return ergebnis;
    }

	public static void main(String[] args) {
		new GUI(new BinaryTreeTest());
	}
}
