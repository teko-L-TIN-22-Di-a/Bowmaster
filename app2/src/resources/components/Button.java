package app2.src.resources.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Button extends Component{

    private Textfield _label;
    private String _text;
    
    public Button(Point location, int width, int height) {
        super(location, width, height);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setImage(image);
    }

    public Button(Point location, int width, int height, String text) {
        super(location, width, height);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setImage(image);
        updateLabel(text);
    }

    public Button(Point location, int width, int height, Color color) {
        super(location, width, height);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setImage(image);
        fill(color);
    }

    public Button(Point location, int width, int height, String text, Color color) {
        super(location, width, height);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setImage(image);
        fill(color);
        updateLabel(text);
    }

    public void fill(Color color) {
        Graphics g = getImage().getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
    }

    public void updateLabel(String text) {
        if (text != "") {
            _text = text;
        }
        _label = new Textfield(getLocation(), _text);
        int labelWidth = _label.getWidth();
        int labelHeight = _label.getHeight();
        Graphics g = getImage().getGraphics();
        int drawPosX = (getWidth() - labelWidth)/2;
        int drawPosY = (getHeight() - labelHeight)/2;
        g.drawImage(_label.getImage(), drawPosX, drawPosY, null);
        g.dispose();
    }
}
