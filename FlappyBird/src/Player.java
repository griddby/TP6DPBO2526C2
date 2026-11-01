import java.awt.*;

public class Player {
    // Menyimpan posisi horizontal dan vertikal player
    private int posX;
    private int posY;

    // Menyimpan ukuran player
    private int width;
    private int height;

    // Menyimpan gambar yang digunakan oleh player
    private Image image;

    // Menyimpan kecepatan gerak player pada sumbu Y
    private int velocityY = -0;

    // Constructor untuk menginisialisasi atribut player
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.image = image;
        this.velocityY = -0;
    }

    // Mengubah posisi horizontal player
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Mengubah posisi vertikal player
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Mengubah lebar player
    public void setWidth(int width) {
        this.width = width;
    }

    // Mengubah tinggi player
    public void setHeight(int height) {
        this.height = height;
    }

    // Mengubah gambar player
    public void setImage(Image image) {
        this.image = image;
    }

    // Mengubah kecepatan gerak player pada sumbu Y
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    // Mengambil posisi horizontal player
    public int getPosX() {
        return posX;
    }

    // Mengambil posisi vertikal player
    public int getPosY() {
        return posY;
    }

    // Mengambil lebar player
    public int getWidth() {
        return width;
    }

    // Mengambil tinggi player
    public int getHeight() {
        return height;
    }

    // Mengambil gambar player
    public Image getImage() {
        return image;
    }

    // Mengambil kecepatan gerak player pada sumbu Y
    public int getVelocityY() {
        return velocityY;
    }
}