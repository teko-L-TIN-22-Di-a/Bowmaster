package app.src.resources;

import java.awt.Point;

public class Hitbox extends Rectangle {
    private int _offsetX, _offsetY, _damageMultiplier;
    
    public Hitbox(Rectangle imageBox, int width, int height, int offsetX, int offsetY, int damageMultiplier) {
        super(width, height, imageBox.getPosition().x + offsetX, imageBox.getPosition().y + offsetY);
        _offsetX = offsetX;
        _offsetY = offsetY;
        _damageMultiplier = damageMultiplier;
    }

    @Override
    public void setPosition(Point newPosition) {
        Point pos = new Point(newPosition.x + _offsetX, newPosition.y + _offsetY);
        super.setPosition(pos);
    }

    public int getOffsetX() {
        return _offsetX;
    }

    public int getOffsetY() {
        return _offsetY;
    }

    public int getDamageMutiplier() {
        return _damageMultiplier;
    }
}
