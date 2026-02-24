import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    public void execute(MainFrame mainFrame, TransportTable model) {
        mainFrame.getTable().setModel(model);
        mainFrame.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileManager.save_data(mainFrame);
            }
        });
        mainFrame.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileManager.load_data(mainFrame, model);
            }
        });

        mainFrame.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog dialog = new AddDialog(mainFrame, "Добавить транспорт");
                dialog.getDialogButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.addTransport(dialog.getBox().getSelectedIndex() + 1, dialog.getTransportNameToAdd().getText());
                        dialog.dispose();
                    }
                });
            }
        });

        mainFrame.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteDialog dialog = new DeleteDialog();
                dialog.getDeleteButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.removeTransport(dialog.getDeleteTextField().getText());
                        dialog.dispose();
                    }
                });
            }
        });

        mainFrame.getFindButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindDialog dialog = new FindDialog();
                dialog.getAcceptButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.getFoundTransport()[0] = Transport_company.find_transport(dialog.getTransportToFindTextField().getText());
                        if (dialog.getFoundTransport()[0] != null) {
                            dialog.getFoundTransportType().setText(dialog.getFoundTransport()[0].getType());
                            dialog.getFoundTransportName().setText(dialog.getFoundTransport()[0].getName());
                            dialog.getFoundTransportState().setText(Double.toString(dialog.getFoundTransport()[0].getState()));
                            dialog.getCards().show(dialog.getContentPane(), "TRAVEL");
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Транспорт не найден");
                        }
                    }
                });

                dialog.getCloseButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                dialog.getMakeTravelButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (dialog.getFoundTransport()[0] != null) {
                            double travelTime = dialog.getFoundTransport()[0].calculate_travel_time(Integer.parseInt(dialog.getTravelLengthInput().getText()));
                            double calculatedState = dialog.getFoundTransport()[0].calculate_state(Integer.parseInt(dialog.getTravelLengthInput().getText()));
                            dialog.getTime().setText(Double.toString(travelTime));
                            dialog.getNewState().setText(Double.toString(calculatedState));
                            dialog.getCards().show(dialog.getContentPane(), "TRAVEL-RESULT");
                            model.fireTableDataChanged();
                        }
                    }
                });
            }
        });
    }
}