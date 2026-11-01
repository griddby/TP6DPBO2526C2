import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FlappyBird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        JPanel[] gamePanel = new JPanel[1];

        // Membuat objek Sound yang digunakan bersama
        Sound sound = new Sound();

        // Memutar MainTheme saat Main Menu dibuka
        sound.playLoop("assets/audio/MainTheme.wav");

        // Membuat Main Menu
        MainMenu menu = new MainMenu(sound, () -> {
            if (gamePanel[0] != null) {
                mainPanel.remove(gamePanel[0]);
                gamePanel[0] = null;
            }

            // Membuat permainan baru
            Logic logic = new Logic(sound, () -> {
                if (gamePanel[0] != null) {
                    mainPanel.remove(gamePanel[0]);
                    gamePanel[0] = null;
                }

                mainPanel.revalidate();
                mainPanel.repaint();
                cardLayout.show(mainPanel, "MENU");

                // Memutar kembali MainTheme saat kembali ke Main Menu
                sound.playLoop("assets/audio/MainTheme.wav");
            });

            View view = new View(logic);
            logic.setView(view);

            gamePanel[0] = view;
            mainPanel.add(view, "GAME");

            cardLayout.show(mainPanel, "GAME");

            SwingUtilities.invokeLater(() -> view.requestFocusInWindow());
        });

        mainPanel.add(menu, "MENU");

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}