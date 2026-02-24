import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class FileManager {
    static Scanner scan = new Scanner(System.in);


    public static void save_data(Frame parentFrame) {
        while(true) {
            FileDialog dialog = new FileDialog(parentFrame);
            dialog.setVisible(true);
            String directory = dialog.getDirectory();
            String name = dialog.getFile();
            String path;
            if (name != null) {
                path = directory + name;
            }
            else {
                path = directory + "transports.txt";
            }

            try {
                if (path.isEmpty()) {
                    throw new IllegalArgumentException("Путь не должен быть пустым");
                }
                File file = new File(path);
                try (FileWriter writer = new FileWriter(file)){
                    writer.write(Integer.toString(Transport_company.getCars().size()) + '\n');
                    for (int i = 0; i < Transport_company.getCars().size(); i++) {
                        writer.write(Transport_company.getCars().get(i).getName() + ' '
                                + Transport_company.getCars().get(i).getState() + '\n');
                    }
                    writer.write(Integer.toString(Transport_company.getTrains().size()) +'\n');
                    for (int i = 0; i < Transport_company.getTrains().size(); i++) {
                        writer.write(Transport_company.getTrains().get(i).getName() + ' ' +
                                Transport_company.getTrains().get(i).getState() + '\n');
                    }
                    writer.write(Integer.toString(Transport_company.getExpresses().size()) + '\n');
                    for (int i = 0; i < Transport_company.getExpresses().size(); i++) {
                        writer.write(Transport_company.getExpresses().get(i).getName() + ' ' +
                                Transport_company.getExpresses().get(i).getState() + '\n');
                    }
                    break;
                }
                catch (IOException e) {
                    System.out.println("Ошибка вывода: " + e.getMessage());
                }
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                System.out.println("Произошла ошибка");
            }
        }
    }

    public static void load_data(JFrame parentFrame, TransportTable model) {
        while(true) {
            FileDialog dialog = new FileDialog(parentFrame);
            dialog.setVisible(true);
            String directory = dialog.getDirectory();
            String name = dialog.getFile();
            String path;
            if (name != null) {
                path = directory + name;
            }
            else {
                path = directory + "transports.txt";
            }
            try {
                if (path.isEmpty()) {
                    throw new IllegalArgumentException("Путь не должен быть пустым");
                }
                File file = new File(path);
                try (FileReader reader = new FileReader(file)){
                    BufferedReader buffReader = new BufferedReader(reader);
                    int size = Integer.parseInt(buffReader.readLine().trim());
                    Transport current_transport;
                    String line;
                    for (int i = 0; i < size; i++) {
                        line = buffReader.readLine().trim();
                        current_transport = Transport_company.add_transport(1, line.split(" ")[0]);
                        if (current_transport != null){
                            current_transport.setState((Double.parseDouble(line.split(" ")[1])));
                        }
                        else {
                            throw new IllegalArgumentException("Поврежденный или неправильный файл");
                        }
                    }
                    size = Integer.parseInt(buffReader.readLine().trim());
                    for (int i = 0; i < size; i++) {
                        line = buffReader.readLine().trim();
                        current_transport = Transport_company.add_transport(2, line.split(" ")[0]);
                        if (current_transport != null){
                            current_transport.setState((Double.parseDouble(line.split(" ")[1])));
                        }
                        else {
                            throw new IllegalArgumentException("Поврежденный или неправильный файл");
                        }
                    }
                    size = Integer.parseInt(buffReader.readLine().trim());
                    for (int i = 0; i < size; i++) {
                        line = buffReader.readLine().trim();
                        current_transport = Transport_company.add_transport(3, line.split(" ")[0]);
                        if (current_transport != null){
                            current_transport.setState((Double.parseDouble(line.split(" ")[1])));
                        }
                        else {
                            throw new IllegalArgumentException("Поврежденный или неправильный файл");
                        }
                    }
                    model.changeTable();
                    break;
                }
                catch (IOException e) {
                    System.out.println("Ошибка ввода: " + e.getMessage());
                }
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                System.out.println("Произошла неизвестная ошибка");
            }
        }
    }
}
