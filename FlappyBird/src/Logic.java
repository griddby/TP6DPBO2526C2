import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerWidth = 34;
    int playerHeight = 24;
    int playerStartPosX = (frameWidth - playerWidth) / 2;
    int playerStartPosY = (frameHeight - playerHeight) / 2;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    Timer gameLoop;
    Timer pipesCooldown;

    int gravity = 1;
    int pipeVelocityX = -2;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    boolean gameStarted = false;
    boolean gameOver = false;

    float score = 0;

    private Sound sound;
    private Runnable backToMenu;

    public Logic(Sound sound, Runnable backToMenu) {
        this.sound = sound;
        this.backToMenu = backToMenu;

        // Load gambar bird
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        // Load gambar pipe
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        pipes = new ArrayList<Pipe>();

        // Timer untuk membuat pipe
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        // Timer untuk game loop
        gameLoop = new Timer(1000 / 60, this);
    }

    // Menghubungkan Logic dengan View
    public void setView(View view) {
        this.view = view;
    }

    // Mengambil data player
    public Player getPlayer() {
        return player;
    }

    // Mengambil data pipe
    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    // Membuat pipe secara acak
    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - (pipeHeight / 4) - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        upperPipe.passed = false;
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight, pipeWidth, pipeHeight, lowerPipeImage);
        lowerPipe.passed = false;
        pipes.add(lowerPipe);
    }

    // Mengatur pergerakan player dan pipe
    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // Menghitung score ketika player melewati pipe
            if (!pipe.passed && player.getPosX() > pipe.getPosX() + pipe.getWidth()) {
                score += 0.5f;
                pipe.passed = true;
            }

            // Mengecek collision
            if (collision(player, pipe)) {
                gameOver = true;
            }
        }

        // Mengecek tabrakan dengan batas bawah frame
        if (player.getPosY() + player.getHeight() > frameHeight) {
            gameOver = true;
        }
    }

    // Menjalankan game loop
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStarted) {
            move();

            if (view != null) {
                view.updateScore(score);
            }

            if (gameOver) {
                finishGame();
            }
        }
    }

    // Mengatur kondisi ketika permainan berakhir
    public void finishGame() {
        gameLoop.stop();
        pipesCooldown.stop();

        // Memutar suara Die satu kali
        sound.playOnce("assets/audio/Die.wav");

        if (view != null) {
            view.showGameOver((int) score);
        }
    }

    // Mengecek tabrakan antara player dan pipe
    public boolean collision(Player player, Pipe pipe) {
        return player.getPosX() < pipe.getPosX() + pipe.getWidth() // Mengecek apakah sisi kiri player berada sebelum sisi kanan pipe
                && player.getPosX() + player.getWidth() > pipe.getPosX() // Mengecek apakah sisi kanan player melewati sisi kiri pipe
                && player.getPosY() < pipe.getPosY() + pipe.getHeight() // Mengecek apakah sisi atas player berada sebelum sisi bawah pipe
                && player.getPosY() + player.getHeight() > pipe.getPosY(); // Mengecek apakah sisi bawah player melewati sisi atas pipe
    }

    // Restart permainan
    public void restartGame() {
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        pipeVelocityX = -2;
        pipes.clear();

        gameOver = false;
        gameStarted = false;
        score = 0;

        gameLoop.stop();
        pipesCooldown.stop();

        // Menampilkan kembali tampilan permainan
        if (view != null) {
            view.hideGameOver();
            view.updateScore(score);
            view.repaint();
            view.requestFocusInWindow();
        }

        // Memutar kembali MainTheme
        sound.playLoop("assets/audio/MainTheme.wav");
    }

    // Menghentikan seluruh timer permainan
    public void stopGame() {
        gameLoop.stop();
        pipesCooldown.stop();
    }

    // Mengatur input keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            if (!gameStarted) {
                gameStarted = true;
                pipesCooldown.start();
                gameLoop.start();
            }
            player.setVelocityY(-10);
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }

        if (e.getKeyCode() == KeyEvent.VK_M && gameOver) {
            // Menghentikan audio sebelum kembali ke Main Menu
            sound.stop();
            backToMenu.run();
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            // Menghentikan audio sebelum keluar dari permainan
            sound.stop();
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}