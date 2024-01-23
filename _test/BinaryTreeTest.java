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

    // Rahmenmethoden
	public int gibAnzahlKnoten(){
        return gibAnzahlKnoten(suchbaum);
    }

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

    public void postOrder(){
        List<Integer> list = postOrder(suchbaum);
        System.out.println("Post-Order Traversierung:");
        for(list.toFirst();list.hasAccess();list.next()){
            System.out.println(list.getContent());
        }
    }

    public boolean istEnthalten(int pNumber){
        return istEnthalten(suchbaum, pNumber);
    }

    public int mostRightElement(){
        return mostRightElement(suchbaum);
    }

    public void einfuegen(int pNumber){
        einfuegen(suchbaum, pNumber);
    }

    public void gibBlaetterList(){
        List<Integer> ergebnis = gibBlaetterList(suchbaum);
        for(ergebnis.toFirst(); ergebnis.hasAccess();ergebnis.next()){
            System.out.println(ergebnis.getContent());
        }
    }

    public void remove(int pNumber){
        remove(suchbaum, pNumber);
    }

    //Implementierungen

    private boolean remove(BinaryTree<Integer> pTree, int pNumber){
        //TODO: Schöner machen? / verständlicher machen mit hilfsmethoden!
        if(!istEnthalten(suchbaum, pNumber)){
            return false;
        }
        BinaryTree<Integer> rtb = pTree.getRightTree();
        BinaryTree<Integer> ltb = pTree.getLeftTree();
        BinaryTree<Integer> ltbr = ltb.getRightTree();
        BinaryTree<Integer> rtbr = rtb.getRightTree();
        BinaryTree<Integer> core;

        //Vor die gefundene Zahl gehen
        if(pNumber > pTree.getContent()){
        if(rtb.getContent() != pNumber){
            remove(rtb, pNumber);
        }else{
            //wenn gefunden:
            if(rtb.getRightTree().isEmpty() && rtb.getLeftTree().isEmpty()){
                pTree.setRightTree(rtb.getRightTree());
            }else if(rtb.getRightTree().isEmpty()){
                pTree.setRightTree(rtb.getLeftTree());
            }else if(rtb.getLeftTree().isEmpty()){
                pTree.setRightTree(rtb.getRightTree());
            }else{
                core = mostLeftElement(rtbr);
                remove(core.getContent());
                core.setLeftTree(rtb.getLeftTree());
                core.setRightTree(rtb.getRightTree());
                pTree.setRightTree(core);
            }
            return true;
        }
        }
        //Vor die gefundene Zahl gehen
        if(pNumber < pTree.getContent()){
        if(ltb.getContent() != pNumber){
            remove(ltb, pNumber);
        }else{
            //wenn gefunden:
            if(ltb.getRightTree().isEmpty() && ltb.getLeftTree().isEmpty()){
                pTree.setLeftTree(ltb.getRightTree());
            }else if(ltb.getRightTree().isEmpty()){
                pTree.setLeftTree(ltb.getLeftTree());
            }else if(ltb.getLeftTree().isEmpty()){
                pTree.setLeftTree(ltb.getRightTree());
            }else{
                core = mostLeftElement(ltbr);
                remove(core.getContent());
                core.setLeftTree(ltb.getLeftTree());
                core.setRightTree(ltb.getRightTree());
                pTree.setLeftTree(core);
            }
            return true;
        }
            return true;
        }
        if(pTree.getContent() == pNumber){
            //Wurzel entfernen
            if(pTree.getRightTree().isEmpty() && pTree.getLeftTree().isEmpty()){
                pTree.setLeftTree(pTree.getRightTree());
            }else if(pTree.getRightTree().isEmpty()){
                pTree.setLeftTree(pTree.getLeftTree());
            }else if(pTree.getLeftTree().isEmpty()){
                pTree.setLeftTree(pTree.getRightTree());
            }else{
                core = mostLeftElement(rtb);
                remove(core.getContent());
                pTree.setContent(core.getContent());
            }
            return true;
        }
        return false;
    }

    private List<Integer> gibBlaetterList(BinaryTree<Integer> pTree){
        List<Integer> ergebnis = new List<>();
        if(pTree.isEmpty()){
            return ergebnis;
        }
        if(((pTree.getLeftTree().isEmpty() || pTree.getLeftTree() == null) && (pTree.getRightTree().isEmpty() || pTree.getRightTree() == null))){
            ergebnis.append(pTree.getContent());
        }
        ergebnis.concat(gibBlaetterList(pTree.getLeftTree()));
        ergebnis.concat(gibBlaetterList(pTree.getRightTree()));
        return ergebnis;
    }

    private void einfuegen(BinaryTree<Integer> pTree, int pNumber){
        if(pTree.getContent() == pNumber){
            return;
        }

        if(pNumber < pTree.getContent() && !pTree.getLeftTree().isEmpty()){
            einfuegen(pTree.getLeftTree(), pNumber);
        }else if (pNumber < pTree.getContent()){
            pTree.setLeftTree(new BinaryTree<>(pNumber));
        }

        if(pNumber > pTree.getContent() && !pTree.getRightTree().isEmpty()){
            einfuegen(pTree.getRightTree(), pNumber);
        }else if (pNumber > pTree.getContent()){
            pTree.setRightTree(new BinaryTree<>(pNumber));
        }
    }

    private int mostRightElement(BinaryTree<Integer> pTree){
        while(!pTree.getRightTree().isEmpty()){
            pTree = pTree.getRightTree();
        }
        return pTree.getContent();
    }

    private BinaryTree<Integer> mostLeftElement(BinaryTree<Integer> pTree){
        while(!pTree.getLeftTree().isEmpty()){
            pTree = pTree.getLeftTree();
        }
        return pTree;
    }

    private boolean istEnthalten(BinaryTree<Integer> pTree, int pNumber){
        if(pTree.isEmpty()){
            return false;
        }
        if(pTree.getContent() == pNumber){
            return true;
        }
        return istEnthalten(pTree.getLeftTree(), pNumber)||istEnthalten(pTree.getRightTree(), pNumber);
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

    private List<Integer> postOrder(BinaryTree<Integer> pTree){
        List<Integer> ergebnis = new List<>();
        if (pTree.isEmpty()){
            return ergebnis;
        }
        ergebnis.concat(postOrder(pTree.getLeftTree()));
        ergebnis.concat(postOrder(pTree.getRightTree()));
        ergebnis.append(pTree.getContent());

        return ergebnis;
    }

	public static void main(String[] args) {
		new GUI(new BinaryTreeTest());
	}
}
