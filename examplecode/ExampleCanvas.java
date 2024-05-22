import java.awt.*;
import javax.swing.JFrame;

public class ExampleCanvas extends Canvas{

    public void paint(Graphics g) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("Hunters House.jpg");
        g.drawImage(i, 0, 0, this);

    }
        public static void main(String[] args) {
        ExampleCanvas m=new ExampleCanvas();
        JFrame f=new JFrame();
        f.add(m);
        f.setSize(1920, 1080);
        f.setVisible(true);
    }

}