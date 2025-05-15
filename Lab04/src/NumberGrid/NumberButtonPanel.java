package NumberGrid;

import javax.swing.*;
import java.awt.*;

public class NumberButtonPanel extends JPanel {
    private JButton[] btnNumbers = new JButton[10];
    private JButton btnDelete, btnReset;

    public NumberButtonPanel(JTextField tfDisplay) {
        setLayout(new GridLayout(4, 3)); // 4 hàng, 3 cột
        ButtonListener listener = new ButtonListener(tfDisplay);

        // Thêm các nút số 1 đến 9
        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton(String.valueOf(i));
            btnNumbers[i].addActionListener(listener);
            add(btnNumbers[i]);
        }

        // Nút DEL
        btnDelete = new JButton("DEL");
        btnDelete.addActionListener(listener);
        add(btnDelete);

        // Nút 0
        btnNumbers[0] = new JButton("0");
        btnNumbers[0].addActionListener(listener);
        add(btnNumbers[0]);

        // Nút C
        btnReset = new JButton("C");
        btnReset.addActionListener(listener);
        add(btnReset);
    }
}

