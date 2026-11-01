import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.InputStream;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private Logic logic;
    private JLabel scoreLabel;
    private JLabel gameOverTitle;
    private JLabel finalScoreLabel;
    private JPanel gameOverPanel;

    private Font flappyFont;
    private Image backgroundImage;

    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setLayout(null);
        addKeyListener(logic);

        // Load background
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        // Load font Flappy Bird
        try {
            InputStream is = getClass().getResourceAsStream("assets/font/FlappyBird.ttf");
            flappyFont = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (Exception e) {
            System.out.println("Font gagal dimuat: " + e.getMessage());
            flappyFont = new Font("Arial", Font.BOLD, 32);
        }

        // Score saat permainan berlangsung
        scoreLabel = new JLabel("0");
        scoreLabel.setBounds(0, 10, width, 55);
        scoreLabel.setFont(flappyFont.deriveFont(55f));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel);

        // Membuat Game Over Panel
        createGameOverPanel();
    }

    // Membuat panel Game Over
    public void createGameOverPanel() {
        gameOverPanel = new JPanel(null);
        gameOverPanel.setBounds(30, 185, 300, 270);
        gameOverPanel.setBackground(new Color(229, 218, 155));
        gameOverPanel.setBorder(BorderFactory.createLineBorder(new Color(120, 100, 65), 5));

        // Judul GAME OVER
        gameOverTitle = new JLabel("GAME OVER");
        gameOverTitle.setFont(flappyFont.deriveFont(50f));
        gameOverTitle.setForeground(new Color(180, 75, 35));
        gameOverTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverTitle.setBounds(10, 10, 280, 50);
        gameOverPanel.add(gameOverTitle);

        // Label SCORE
        JLabel scoreTitle = new JLabel("SCORE");
        scoreTitle.setFont(flappyFont.deriveFont(40f));
        scoreTitle.setForeground(new Color(150, 75, 40));
        scoreTitle.setHorizontalAlignment(SwingConstants.CENTER);
        scoreTitle.setBounds(20, 65, 260, 40);
        gameOverPanel.add(scoreTitle);

        // Nilai SCORE
        finalScoreLabel = new JLabel("0");
        finalScoreLabel.setFont(flappyFont.deriveFont(55f));
        finalScoreLabel.setForeground(Color.black);
        finalScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        finalScoreLabel.setBounds(20, 105, 260, 65);
        gameOverPanel.add(finalScoreLabel);

        // Tombol RESTART
        JLabel restartLabel = new JLabel("R : RESTART");
        restartLabel.setFont(flappyFont.deriveFont(24f));
        restartLabel.setForeground(Color.black);
        restartLabel.setHorizontalAlignment(SwingConstants.CENTER);
        restartLabel.setBounds(10, 170, 280, 25);
        gameOverPanel.add(restartLabel);

        // Tombol MAIN MENU
        JLabel menuLabel = new JLabel("M : MAIN MENU");
        menuLabel.setFont(flappyFont.deriveFont(24f));
        menuLabel.setForeground(Color.black);
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuLabel.setBounds(10, 200, 280, 25);
        gameOverPanel.add(menuLabel);

        // Tombol EXIT
        JLabel exitLabel = new JLabel("E : EXIT");
        exitLabel.setFont(flappyFont.deriveFont(24f));
        exitLabel.setForeground(Color.black);
        exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exitLabel.setBounds(10, 230, 280, 25);
        gameOverPanel.add(exitLabel);

        gameOverPanel.setVisible(false);
        add(gameOverPanel);
    }

    // Menggambar background dan objek permainan
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        draw(g);
    }

    // Menggambar player dan pipe
    public void draw(Graphics g) {
        Player player = logic.getPlayer();

        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();

        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }
    }

    // Memperbarui score
    public void updateScore(float score) {
        scoreLabel.setText(String.valueOf((int) score));
        repaint();
    }

    // Menampilkan Game Over Panel
    public void showGameOver(int score) {
        scoreLabel.setVisible(false);
        finalScoreLabel.setText(String.valueOf(score));
        gameOverPanel.setVisible(true);
        repaint();
    }

    // Menyembunyikan Game Over Panel
    public void hideGameOver() {
        gameOverPanel.setVisible(false);
        scoreLabel.setVisible(true);
        scoreLabel.setText("0");
        repaint();
    }
}