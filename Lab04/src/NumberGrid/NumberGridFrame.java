package NumberGrid;


import javax.swing.*;
import java.awt.*;

public class NumberGridFrame extends JFrame {
    private JTextField tfDisplay;
    private NumberButtonPanel panelButtons;

    public NumberGridFrame() {
        tfDisplay = new JTextField();
        tfDisplay.setFont(new Font("Arial", Font.PLAIN, 24)); // Tăng kích cỡ chữ
        tfDisplay.setPreferredSize(new Dimension(0, 50));     // Tăng chiều cao
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        // Tạo panel chứa các nút và truyền tfDisplay để thao tác
        panelButtons = new NumberButtonPanel(tfDisplay);

        // Bố cục giao diện chính
        setLayout(new BorderLayout());
        add(tfDisplay, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(250, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NumberGridFrame();
    }
}
