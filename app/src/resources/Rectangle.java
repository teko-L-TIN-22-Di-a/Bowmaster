package app.src.resources;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import app.src.resources.StaticValues.Corners;

public class Rectangle {
    private int _width, _height;
    private Point _position, _topLeft, _topRight, _bottomLeft, _bottomRight;

    public Rectangle(int width, int height, int x, int y) {
        Point position = new Point(x, y);
        setPosition(position);
        setSize(width, height);
    }

    public static void main() {
        Rectangle rect1 = new Rectangle(10, 8, 0, 0);
        Rectangle rect2 = new Rectangle(5, 2, 1, 1);
        Rectangle rect3 = new Rectangle(3, 4, 11, 9);
        Point P1 = new Point(5,4);
        Point P2 = new Point(10,8);
        System.out.println(rect1.collidePoint(P1));
        System.out.println(rect1.collidePoint(P2));
        System.out.println(rect1.collideRect(rect2));
        System.out.println(rect1.collideRect(rect3));
    }

    public boolean collidePoint(Point point) {
        if (_topLeft.x > point.x) {
            return false;
        }
        else if (_topRight.y > point.y) {
            return false;
        }
        else if (_bottomRight.x < point.x) {
            return false;
        }
        else if (_bottomLeft.y < point.y) {
            return false;
        }
        else {return true;}
    }

    public boolean collideRect(Rectangle rectangle) {
        List<Point> points = rectangle.getCorners();
        for (Point point : points) {
            if (collidePoint(point)) {
                return true;
            }
            else {return false;}
        }
        return false;
    }

    public Point getCorner(Corners corner) {
        switch (corner) {
            case BOTTOM_LEFT:
                return _bottomLeft;
            case BOTTOM_RIGHT:
                return _bottomRight;
            case TOP_RIGHT:
                return _topRight;
            default:
                return _topLeft;

        }
    }

    private void setCorners() {
        _topLeft = new Point(_position.x - _width/2, _position.y - _height/2);
        _topRight = new Point(_position.x + _width/2, _position.y - _height/2);
        _bottomLeft = new Point(_position.x - _width/2, _position.y + _height/2);
        _bottomRight = new Point(_position.x + _width/2, _position.y + _height/2);
    }

    public List<Point> getCorners() {
        List<Point> points = new ArrayList<>();
        points.add(_topLeft);
        points.add(_topRight);
        points.add(_bottomLeft);
        points.add(_bottomRight);
        return points;
    }

    public void setSize(int width, int height) {
        _width = width;
        _height = height;
        setCorners();
    }

    public void setPosition(Point newPosition) {
        _position = newPosition;
        setCorners();
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public Point getPosition() {
        return _position;
    }

    public int getX() {
        return _position.x;
    }

    public void updateX(int value) {
        _position.x += value;
    }

    public int getY() {
        return _position.y;
    }
}
