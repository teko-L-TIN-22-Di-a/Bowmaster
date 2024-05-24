package app.src;

public class Vector3D {
    private int x;
    private int y;
    private int z;

    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
}
