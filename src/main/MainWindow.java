package main;

import javax.swing.*;
import controller.MainWindowAction;
import java.awt.*;
import java.net.URL;

public class MainWindow extends JFrame {

    // Khai báo các thuộc tính private
    // mainWindow là Jframe như cái khung chứa các panel là cái view muốn jFrame
    // muốn show
    private static MainWindow mainWindow;

    // toolbar để có thể chuyển các panel công cụ muốn mã hóa

    private JToolBar jToolBar;

    // panel và các nút
    private JPanel jPanel;
    private JButton jButton_A5;
    private JButton jButton_RC4;

    // hành động để tương tác
    private MainWindowAction ac;

    private MainWindow() {
        // URL urlIconNotepad = MainWindow.class.getResource("LOGO.png");
        // Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        // this.setIconImage(img);
        // đặt tên cho tựa đề
        this.setTitle("Công cụ mã hóa HDB");
        // chỉnh kích thước
        this.setSize(1100, 700);
        // cho khung Jfram xuất hiện tại vị trí trên màn hình
        this.setLocation(250, 50);
        // tắt khung sẽ dừng chương trình
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // khởi tạo toolbar và các button
        jToolBar = new JToolBar();
        jButton_A5 = new JButton("A5/1");
        jButton_RC4 = new JButton("RC4");

        // khởi tạo jpanel
        jPanel = new JPanel();

        // khởi tạo hành động cho JFrame
        ac = new MainWindowAction(this);

        // gán hành động khi người dùng tương tác vào
        jButton_A5.addActionListener(ac);
        jButton_RC4.addActionListener(ac);

        // chỉnh màu cho các nút ấn
        jButton_A5.setBackground(Color.lightGray);
        jButton_RC4.setBackground(Color.lightGray);

        // thêm các button vào thanh công cụ
        jToolBar.add(jButton_A5);
        jToolBar.add(jButton_RC4);
        // chỉnh layout theo kiểu đông tây nam bắc chính giữa
        this.setLayout(new BorderLayout());
        // bỏ thanh cụ ở phía bắc
        this.add(jToolBar, BorderLayout.NORTH);
        // bỏ panel trống vào trong center
        this.add(jPanel, BorderLayout.CENTER);
    }

    // khởi tạo MainWindow nếu chưa tạo thì tạo mới và có rồi thì gán trả lại
    public static MainWindow getInstance() {
        if (mainWindow == null) {
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }

    // gán vị trí cho toolbbar và xóa panelview đang có thay bằng panel mới muốn
    // show
    public void show(JPanel panel) {
        this.remove(jPanel);
        this.add(panel, BorderLayout.CENTER);
        this.jPanel = panel;
        setVisible(true);
    }

    // nút nào được nhấn thì set lại màu cho nút(jbutton) đó
    public void setjButton_A5() {
        jButton_A5.setBackground(Color.GREEN);
        jButton_RC4.setBackground(Color.lightGray);

    }

    public void setjButton_RC4() {
        jButton_A5.setBackground(Color.lightGray);
        jButton_RC4.setBackground(Color.GREEN);
    }

}
