package view;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.MainWindow;

public class WelcomeView extends JPanel {
    private MainWindow mainWindow = MainWindow.getInstance();
    private JLabel jlLabel;
    private JPanel jPanel_BackGround;
    private Font font;

    public WelcomeView() {
        // lấy tấm ảnh ở địa chỉ "/view/a.jpg"
        BufferedImage backgroundImage;
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/view/WelcomeBackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Tạo một JPanel để chứa ảnh background
        jPanel_BackGround = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image scaledImage = backgroundImage.getScaledInstance(getWidth(),
                        getHeight(), Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };

        this.setLayout(new GridBagLayout());

        font = new Font("Arial", Font.BOLD, 50);
        jlLabel = new JLabel("CÔNG CỤ MÃ HÓA");
        jlLabel.setFont(font);
        jPanel_BackGround.add(jlLabel);

        this.setLayout(new BorderLayout());
        this.add(jPanel_BackGround, BorderLayout.CENTER);

    }

    public void showWelcome() {
        mainWindow.show(this);
    }
}
