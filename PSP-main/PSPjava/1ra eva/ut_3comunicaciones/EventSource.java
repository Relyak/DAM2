package ut_3comunicaciones;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class DescansilloDeLaEscalera {
    public interface MarujaMarujo {
        void update(String event);
    }
  
    private final List<MarujaMarujo> observers = new ArrayList<>();
  
    private void notifyObservers(String event) {
       /*observers.forEach(new Consumer<Observer>(){
            @Override
            public void accept(Observer t){
                t.update(event);
            }
        }
        );*/
        observers.forEach(observador -> observador.update(event));
    }
  
    public void addObserver(MarujaMarujo observer) {
        observers.add(observer);
    }
  
    public void seAbreElDescansillo() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            notifyObservers(line);
        }
    }
}