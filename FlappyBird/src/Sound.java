import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    // Menyimpan objek Clip untuk memutar audio
    private Clip clip;

    public void playLoop(String fileName) {
        // Menghentikan audio sebelumnya sebelum memutar audio baru
        stop();

        try {
            // Mengambil lokasi file audio dari folder assets
            URL audioURL = getClass().getResource(fileName);

            // Memeriksa apakah file audio berhasil ditemukan
            if (audioURL == null) {
                System.out.println("File audio tidak ditemukan: " + fileName);
            } else {
                // Membaca file audio dan membuat objek Clip
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioURL);
                clip = AudioSystem.getClip();
                clip.open(audioStream);

                // Memutar audio secara berulang
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                audioStream.close();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Menampilkan pesan jika audio gagal diputar
            System.out.println("Audio gagal diputar: " + e.getMessage());
        }
    }

    public void playOnce(String fileName) {
        // Menghentikan audio sebelumnya sebelum memutar audio baru
        stop();

        try {
            // Mengambil lokasi file audio dari folder assets
            URL audioURL = getClass().getResource(fileName);

            // Memeriksa apakah file audio berhasil ditemukan
            if (audioURL == null) {
                System.out.println("File audio tidak ditemukan: " + fileName);
            } else {
                // Membaca file audio dan membuat objek Clip
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioURL);
                clip = AudioSystem.getClip();
                clip.open(audioStream);

                // Memutar audio sebanyak satu kali
                clip.start();
                audioStream.close();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Menampilkan pesan jika audio gagal diputar
            System.out.println("Audio gagal diputar: " + e.getMessage());
        }
    }

    public void stop() {
        // Menghentikan dan menutup audio yang sedang diputar
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }
}