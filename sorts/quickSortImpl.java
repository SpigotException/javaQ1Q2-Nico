package sorts;

import linear.List;
import linear.ListWithViewer;

public class quickSortImpl {
    private ListWithViewer<String> quickSort(ListWithViewer<String> pList) {

        List<String> leftList = new List<>();
        List<String> righList = new List<>();
        ListWithViewer<String> ergebnisList = new ListWithViewer<>();
        //choose Pivot
        pList.toFirst();
        String Pivot = pList.getContent();

        return ergebnisList;
    }
}
