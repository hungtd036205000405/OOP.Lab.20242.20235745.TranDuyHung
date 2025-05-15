package hust.soict.hespi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.hespi.aims.media.Media;
import hust.soict.hespi.aims.media.Playable;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;

        // Set layout dọc
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Label tên media
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Label giá media
        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        // Panel chứa nút (nếu có)
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Nếu media có thể Play → thêm nút Play
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> playMedia());  // Xử lý sự kiện khi nhấn nút Play
            container.add(playButton);
        }

        // Thêm khoảng cách, các thành phần vào panel
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.add(Box.createVerticalGlue());

        // Viền đen xung quanh
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // Phương thức xử lý việc phát phương tiện trong cửa sổ JDialog
    private void playMedia() {
        // Tạo cửa sổ JDialog để phát phương tiện
        JDialog playDialog = new JDialog((Frame) null, "Playing: " + media.getTitle(), true);
        playDialog.setSize(400, 300);  // Thiết lập kích thước cửa sổ JDialog
        playDialog.setLocationRelativeTo(this);  // Đặt cửa sổ JDialog ở vị trí trung tâm

        // Tạo một JLabel để hiển thị thông tin phát phương tiện
        JLabel playLabel = new JLabel("Now playing: " + media.getTitle(), SwingConstants.CENTER);
        playDialog.add(playLabel, BorderLayout.CENTER);

        // Hiển thị cửa sổ JDialog
        playDialog.setVisible(true);
    }
}
