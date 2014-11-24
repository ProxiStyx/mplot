import edu.kit.math.mplot.*;
import java.lang.InterruptedException;


public class test {


    public static void main(String[] args) throws InterruptedException {

        double[] x = new double[10], y = new double[10];
        for(int i=0; i<x.length; i++) {
            x[i] = 0.1*i;
            y[i] = Math.pow(x[i],2);
        }

        // Zur Demonstration öffne Fenster, plotte gegeben Punkte und schließe aktuellstes Fenster - mit Pausen, damits besser aussieht
        MPlot.figure(); /* Figure 0 */  java.lang.Thread.sleep(1000);
        MPlot.plot(x, y, "b--.");       java.lang.Thread.sleep(1000);
        MPlot.figure(); /* Figure 1 */  java.lang.Thread.sleep(1000);
        MPlot.plot(x, x, "b--.");       java.lang.Thread.sleep(1000);
        MPlot.figure();  /* Figure 2 */ java.lang.Thread.sleep(1000);
        MPlot.plot(x, y, "b--.");       java.lang.Thread.sleep(1000);

        /* clf figure with index 1 => figure with index 2 has now index 1 */
        MPlot.clf(1);                   java.lang.Thread.sleep(1000);
        /* set figure 0 active => figure with index 1 has still index 1 */
        MPlot.set(0, "setActive");
        /* clf active figure, thats figure 0 => figure 1 has now index 0 */
        MPlot.clf();                    java.lang.Thread.sleep(1000);
        /* clf last figure */
        MPlot.clf();                    java.lang.Thread.sleep(1000);
    }
}
