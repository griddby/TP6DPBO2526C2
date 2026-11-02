import java.awt.*;

public class Pipe {
    // Menyimpan posisi horizontal dan vertikal pipe
    private int posX;
    private int posY;

    // Menyimpan ukuran pipe
    private int width;
    private int height;

    // Menyimpan gambar pipe
    private Image image;

    // Menyimpan kecepatan pergerakan pipe pada sumbu X
    private int velocityX;

    // Menandai apakah pipe sudah dilewati oleh player
    boolean passed;

    // Constructor untuk menginisialisasi atribut pipe
    public Pipe(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityX = 0;
        this.passed = false;
    }

    // Mengubah posisi horizontal pipe
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Mengubah posisi vertikal pipe
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Mengubah lebar pipe
    public void setWidth(int width) {
        this.width = width;
    }

    // Mengubah tinggi pipe
    public void setHeight(int height) {
        this.height = height;
    }

    // Mengubah gambar pipe
    public void setImage(Image image) {
        this.image = image;
    }

    // Mengubah kecepatan pergerakan pipe pada sumbu X
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    // Mengubah status apakah pipe sudah dilewati player
    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    // Mengambil posisi horizontal pipe
    public int getPosX() {
        return posX;
    }

    // Mengambil posisi vertikal pipe
    public int getPosY() {
        return posY;
    }

    // Mengambil lebar pipe
    public int getWidth() {
        return width;
    }

    // Mengambil tinggi pipe
    public int getHeight() {
        return height;
    }

    // Mengambil gambar pipe
    public Image getImage() {
        return image;
    }

    // Mengambil kecepatan pergerakan pipe pada sumbu X
    public int getVelocityX() {
        return velocityX;
    }

    // Mengambil status apakah pipe sudah dilewati player
    public boolean isPassed() {
        return passed;
    }
}