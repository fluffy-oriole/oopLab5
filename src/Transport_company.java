import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Transport_company {
    static private Scanner scan = new Scanner(System.in);
    static private ArrayList<Car> cars = new ArrayList<>();
    static private ArrayList<Train> trains = new ArrayList<>();
    static private ArrayList<Express> expresses = new ArrayList<>();

    public static ArrayList<Car> getCars() {
        return cars;
    }

    public static ArrayList<Train> getTrains() {
        return trains;
    }

    public static ArrayList<Express> getExpresses() {
        return expresses;
    }

    public static Transport add_transport(int object, String name){
        if (object == 1){
            Car new_car = new Car(name);
            cars.add(new_car);
            return new_car;
        }
        else if (object == 2){
            Train new_train = new Train(name, 100);
            trains.add(new_train);
            return new_train;
        }
        else if (object == 3){
            Express new_express = new Express(name);
            expresses.add(new_express);
            return new_express;
        }
        return null;
    }

    public static void remove_transport(String transport) {
        Transport object = find_transport(transport);
        if (object != null) {
            try {
                if (Objects.equals(object.getType(), "Машина")){
                    cars.remove((Car)object);
                }
                else if (Objects.equals(object.getType(), "Поезд")){
                    trains.remove((Train)object);
                }
                else {
                    expresses.remove((Express)object);
                }
            }
            catch (InputMismatchException e){
                System.out.println("Значение должно быть числом");
            }
            catch (IndexOutOfBoundsException e){
                System.out.print("Транспортного средства с таки индексом нет");
            }
            catch (Exception e){
                System.out.println("Произошла неизвестная ошибка");
            }
        }
    }

    public static Transport find_transport(String name){
        Car found_car;
        for (Car car : cars) {
            if (car.getName().equals(name)) {
                found_car = car;
                return found_car;
            }
        }

        Train found_train;
        for (Train train : trains) {
            if (train.getName().equals(name)) {
                found_train = train;
                return found_train;
            }
        }

        Express found_express;
        for (Express express : expresses) {
            if (express.getName().equals(name)) {
                found_express = express;
                return found_express;
            }
        }
        return null;
    }
}
