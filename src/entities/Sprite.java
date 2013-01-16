package entities;

public final class Sprite {

    private final String name;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Sprite(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprite sprite = (Sprite) o;

        if (height != sprite.height) return false;
        if (width != sprite.width) return false;
        if (x != sprite.x) return false;
        if (y != sprite.y) return false;
        if (name != null ? !name.equals(sprite.name) : sprite.name != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Sprite{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", w=" + width +
                ", h=" + height +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
