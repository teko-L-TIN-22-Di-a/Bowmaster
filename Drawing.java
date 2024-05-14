import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {
    public static final int width = 600;
    public static final int height = 400;

    public void paint(Graphics g) {
        System.out.println(g);
        Image image = createImage();
        //g.drawString("hello World", 20, 20);
        g.drawImage(image, 0, 0, this);
    }
    private void render(Graphics g, Dimension size) {
        g.drawString("hello World" + size, 20, size.height/2);
        g.drawLine(0, 0, size.width, size.height);
    }
    private Image createImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawString("hello World", 20, 20);
        g.drawLine(0, 0, width, height);
        g.drawLine(0, height, width, 0);
        return bufferedImage;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Drawing");
        Drawing drawing = new Drawing();
        frame.getContentPane().add(new Drawing());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setVisible(true);
    }
}
