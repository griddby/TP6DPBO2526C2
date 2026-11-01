import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel {
    private Image backgroundImage;
    private Sound sound;

    public MainMenu(Sound sound, Runnable startGame) {
        this.sound = sound;

        setPreferredSize(new Dimension(360, 640));
        setLayout(null);

        // Load background
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        // Load logo FlappyBird
        Image logoImage = new ImageIcon(getClass().getResource("assets/logo.png")).getImage();
        Image scaledLogo = logoImage.getScaledInstance(275, 96, Image.SCALE_DEFAULT);

        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(15, 180, 275, 96);
        add(logoLabel);

        // Load gambar bird
        Image birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        Image scaledBird = birdImage.getScaledInstance(55, 42, Image.SCALE_DEFAULT);

        JLabel birdLabel = new JLabel(new ImageIcon(scaledBird));
        birdLabel.setBounds(295, 205, 55, 42);
        add(birdLabel);

        // Membuat tombol START
        JButton startButton = new PixelButton("START");
        startButton.setBounds(45, 350, 120, 45);
        add(startButton);

        // Membuat tombol EXIT
        JButton exitButton = new PixelButton("EXIT");
        exitButton.setBounds(195, 350, 120, 45);
        add(exitButton);

        // Event tombol START
        startButton.addActionListener(e -> startGame.run());

        // Event tombol EXIT
        exitButton.addActionListener(e -> {
            sound.stop();
            System.exit(0);
        });
    }

    // Menggambar background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    // Custom button dengan tampilan pixel art
    class PixelButton extends JButton {
        public PixelButton(String text) {
            super(text);
            setFont(new Font("Monospaced", Font.BOLD, 18));
            setForeground(Color.white);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

            // Menggambar kotak tombol
            g2.setColor(new Color(230, 90, 0));
            g2.fillRect(0, 0, getWidth(), getHeight());

            // Menggambar tulisan tombol
            g2.setFont(getFont());
            g2.setColor(Color.white);

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }
}