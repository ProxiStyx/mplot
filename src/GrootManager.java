package edu.kit.math.mplot;


import java.awt.*;
import java.util.*;


public class GrootManager {



    protected ArrayList<Integer> figureIndexList = new ArrayList<Integer>();   // this is my "book-keeping-list" I can quickly check how many figures are open, what ids are already assigned, which is the highest/newest index etc.
    protected ArrayList<ArrayList> groot = new ArrayList<ArrayList>(); // this is the new ArrayList it does not only contain the figures but also linked with each figure its identifier (id) and p.r.n the associated plots



    protected void printGrootList () {

        ListIterator<ArrayList> li = groot.listIterator();
        System.out.println("Current Figures (#"+ groot.size() +"):");
        while(li.hasNext()) {

            ArrayList content = li.next();
            System.out.println("   - Figure with index " + content.get(0) + ": " + content.get(1));
            if (content.size() > 2) {
                System.out.println("     Associated plots:");
                for (int i = 2; i < content.size(); i++) System.out.println("        * " + content.get(i));
            } else System.out.println("     No plots associated");
        }

        System.out.println();
    }

    protected Figure getFigureToIndex (int index) {

        int id = getIdToIndex(index);
        return (Figure) groot.get(id).get(1);
    }

    protected void clfFigureWithIndex (int index) {

        // Delete figure in this id
        Figure figureToCLF = (Figure) groot.get(index).get(1);
        figureToCLF.getContentPane().removeAll();
        figureToCLF.getContentPane().revalidate();
        figureToCLF.getContentPane().repaint();
    }

    protected void closeFigureWithIndex (int index) {

        // Delete figure in this id
        Figure figureToClose = (Figure) groot.get(index).get(1);
        figureToClose.setVisible(false);
        figureToClose.dispose();
        // Plots are going to be deleted by javas garbage-collector
        // Remove entry in ArrayList
        groot.remove(index);
        // As last we remove the entry in our book-keeping-list
    }

    protected void closeAllFigures() {

        // shouldn't do this with a loop instead use javas listiterator
        for (int indexHandle = 0; indexHandle < groot.size(); indexHandle++) {
            // Delete figure in this id
            Figure figureToClose = (Figure) groot.get(indexHandle).get(1);
            figureToClose.setVisible(false);
            figureToClose.dispose();
            // Plots are going to be deleted by javas garbage-collector
        }

        groot.clear(); // Remove all entries in ArrayList
        figureIndexList.clear(); // Remove all entries in our book-keeping-list
    }

    protected void addIndexIntoFIL(int index) {

        figureIndexList.add(index);
    }

    // check which index (position in ArrayList) has the figure with given id
    protected int getIdToIndex (int givenId) {

        int index = 0;
        if (isIndexInUse(givenId)) { // this line fixes bug occuring if givenId < currentIndex but not in use

            int id = (Integer) groot.get(index).get(0);
            while (id != givenId) {

                index++;
                id = (Integer) groot.get(index).get(0);

                if (index >= groot.size()) {
                    index = -1;
                    break;
                }
            }
        } else index = -1;
        return index;
    }

    protected int getNewestIndex() {

        int index;

        if (figureIndexList.size() > 0) {
            index = (Integer) figureIndexList.get( ( (Integer) figureIndexList.size() ) - 1 );
        } else index = -1;

        return index;
    }

    protected int getHighestIndex () {

        int index;

        if (figureIndexList.size() > 0) {
            index = Collections.max(figureIndexList);
        } else index = -1;

        return index;
    }

    // Check if given index is associated with a figure
    protected boolean isIndexInUse (int id) {
        if (figureIndexList.indexOf(id) > -1) return true;
        else return false;
    }
}
