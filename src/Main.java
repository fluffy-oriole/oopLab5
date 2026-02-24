public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("Транспортная компания");
        Controller controller = new Controller();
        TransportTable model = new TransportTable();
        controller.execute(mainFrame, model);
    }
}