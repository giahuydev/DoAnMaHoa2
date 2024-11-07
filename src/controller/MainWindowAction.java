package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainWindow;
import view.RC4View;
import view.A51View;

/**
 * MainWindowAction
 * //
 */

public class MainWindowAction implements ActionListener {
    private MainWindow mainWindow;
    // khởi tạo các đối tượng view khi tương tác với button
    private A51View tinyA51View;
    private RC4View rc4View;

    // hàm khỏi tạo
    public MainWindowAction(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

    }

    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.equals("A5/1")) {
            // sử lí khi ấn nút Tiny A5
            tinyA51View = new A51View();
            mainWindow.show(tinyA51View);// cửa sổ jframe chính sẽ đổi panel
            mainWindow.setjButton_A5();// đổi màu cho button đã chọn
        } else if (button.equals("RC4")) {
            // sử lí khi ấn nút RC4
            rc4View = new RC4View();
            mainWindow.show(rc4View);// cửa sổ jframe chính sẽ đổi panel
            mainWindow.setjButton_RC4();// đổi màu cho button đã chọn
        }
    }

}
