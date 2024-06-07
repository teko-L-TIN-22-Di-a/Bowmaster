package app.src.resources.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Button extends Component{

    private Textfield _label;
    private String _text;
    private Runnable _buttonAction;

    public void setAction(Runnable action) {
        _buttonAction = action;
    }

    public void action() {
        if (_buttonAction != null) {
            _buttonAction.run();
        }
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
        _label = new Textfield(getLocation().x, getLocation().y, _text);
        int labelWidth = _label.getWidth();
        int labelHeight = _label.getHeight();
        Graphics g = getImage().getGraphics();
        int drawPosX = (getWidth() - labelWidth)/2;
        int drawPosY = (getHeight() - labelHeight)/2;
        g.drawImage(_label.getImage(), drawPosX, drawPosY, null);
        g.dispose();
    }

    public void actionCheck(Point mousePosition) {
        if (
            mousePosition.x >= getLocation().x &&
            mousePosition.x <= getLocation().x + getWidth() &&
            mousePosition.y >= getLocation().y &&
            mousePosition.y <= getLocation().y + getHeight()
        ) {
            action();
        }
    }

    private void setUpStandardButton(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setImage(image);
    }

    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
        setUpStandardButton(width, height);
    }

    public Button(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        setUpStandardButton(width, height);
        updateLabel(text);
    }

    public Button(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        setUpStandardButton(width, height);
        fill(color);
    }

    public Button(int x, int y, int width, int height, String text, Color color) {
        super(x, y, width, height);
        setUpStandardButton(width, height);
        fill(color);
        updateLabel(text);
    }

    public Button(int x, int y, BufferedImage image) {
        super(x, y, 1, 1);
        int width = image.getWidth();
        int height = image.getHeight();
        setSize(width, height);
        setImage(image);
    }

    public Button(int x, int y, BufferedImage image, String text) {
        super(x, y, 1, 1);
        int width = image.getWidth();
        int height = image.getHeight();
        setSize(width, height);
        setImage(image);
        updateLabel(text);
    }
}
