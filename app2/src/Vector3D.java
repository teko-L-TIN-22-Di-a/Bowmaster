package app2.src;

public class Vector3D {
    public int x, y ,z, anglexy;

    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.anglexy = (int)Math.toDegrees(Math.atan2(y, x));
        // Adjusting the angle to be in the range [0, 360) degrees
        if (this.anglexy < 0) {
            this.anglexy += 360;
        }
    }

    public Vector3D(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.anglexy = (int)Math.toDegrees(Math.atan2(y, x));

        // Adjusting the angle to be in the range [0, 360) degrees
        if (this.anglexy < 0) {
            this.anglexy += 360;
        }
    }

    public Vector3D(int x) {
        this.x = x;
        this.y = 0;
        this.z = 0;
        this.anglexy = (int)Math.toDegrees(Math.atan2(y, x));

        // Adjusting the angle to be in the range [0, 360) degrees
        if (this.anglexy < 0) {
            this.anglexy += 360;
        }
    }

    public void print() {
        String output = "x: " + this.x + ", y: " + this.y + ", z: " + this.z;
        System.out.println(output);
    }

    public void add(Vector3D that) {
        this.x += that.x;
        this.y += that.y;
        this.z += that.z;
    }

    public void scale(double factor) {
        this.x *= factor;
        this.y *= factor;
        this.z *= factor;
    }

    public void scaleX(double factor) {
        this.x *= factor;
    }

    public void scaleY(double factor) {
        this.y *= factor;
    }

    public void scaleZ(double factor) {
        this.z *= factor;
    }
}
