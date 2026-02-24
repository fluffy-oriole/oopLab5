import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

public class DeleteDialog extends JDialog {
    private final JButton deleteButton = new JButton("Удалить");
    private final JTextField deleteTextField = new JTextField(15);

    public JTextField getDeleteTextField() {
        return deleteTextField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    DeleteDialog() {
        // JDialog deleteDialog = new JDialog();
        setSize(400, 200);
        JPanel deletePanel = new JPanel(new GridLayout(3, 1, 0, 15));
        deletePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(deletePanel);
        JLabel deleteLabel = new JLabel("Введите имя транспорта для удаления");
        deleteLabel.setHorizontalAlignment(CENTER);
        deletePanel.add(deleteLabel);
        deletePanel.add(deleteTextField);
        deletePanel.add(deleteButton);
        setVisible(true);
    }
}
