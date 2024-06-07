package app.src.resources.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Textfield extends Component{

    private String _text;

    public Textfield(int x, int y, String text) {
        super(x, y, 1, 1);
        _text = text;
        BufferedImage image = _createImage();
        setImage(image);
        setSize(image.getWidth(), image.getHeight());
    }

    private BufferedImage _createImage() {
        BufferedImage ImageTMP = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics gTMP = ImageTMP.getGraphics();
        gTMP.drawString(_text, 0, 0);
        FontMetrics fm = gTMP.getFontMetrics();
        gTMP.dispose();
        BufferedImage image = new BufferedImage(fm.stringWidth(_text), fm.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.drawString(_text, 0, g.getFontMetrics().getAscent());
        g.dispose();
        return image;
    }
}
