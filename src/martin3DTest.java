import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.Panel;
import java.awt.BorderLayout;

import javax.swing.border.*;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.factories.IChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;

public class martin3DTest {

    public static void main(String args[]) {
        JLabel title=new JLabel("Langeweile und überflüssiger Aufruf");


        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
                return x * Math.sin(x * y);
            }
        };
        // Define range and precision for the function to plot
        Range range = new Range(-300, 300);
        int steps = 80;

        // Create the object to represent the function over the given range.
        final Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);


        Chart chart = AWTChartComponentFactory.chart(Quality.Advanced, IChartComponentFactory.Toolkit.awt);
        chart.getScene().getGraph().add(surface);



        // Embed into Swing.
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(5, 5, 5, 5, java.awt.Color.BLACK)); // To see where is the JPanel drawn
        panel.setLayout(new BorderLayout()); // Use BorderLayout

        if (chart.getCanvas() instanceof Canvas) {
            System.out.println("Canvas " + chart.getCanvas());
            panel.add((Canvas) chart.getCanvas());
        } else if (chart.getCanvas() instanceof Panel) {
            System.out.println("Panel " + chart.getCanvas());
            panel.add((Panel) chart.getCanvas());
        } else {
            System.err.println("Other "+chart.getCanvas());
            panel.add( (Panel) chart.getCanvas());
        }
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setTitle("Martins 3D test");
        frame.setVisible(true);
        frame.setSize(720, 480);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}