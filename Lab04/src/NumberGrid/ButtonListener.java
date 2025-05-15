package NumberGrid;

import javax.swing.*;
import java.awt.event.*;

public class ButtonListener implements ActionListener {
    private JTextField tfDisplay;

    public ButtonListener(JTextField tfDisplay) {
        this.tfDisplay = tfDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.matches("[0-9]")) {
            tfDisplay.setText(tfDisplay.getText() + button); // Nút số
        } else if (button.equals("DEL")) {
            String text = tfDisplay.getText();
            if (!text.isEmpty()) {
                tfDisplay.setText(text.substring(0, text.length() - 1)); // Xóa ký tự cuối
            }
        } else if (button.equals("C")) {
            tfDisplay.setText(""); // Xóa toàn bộ
        }
    }
}

