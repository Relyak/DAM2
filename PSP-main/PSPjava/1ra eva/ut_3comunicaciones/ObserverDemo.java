package ut_3comunicaciones;
public class ObserverDemo {
    public static void main(String[] args) {
        System.out.println("Enter Text : ");
        DescansilloDeLaEscalera eventSource = new DescansilloDeLaEscalera();
        
        eventSource.addObserver(event -> {
            System.out.println("Received response: " + event);
        });

        eventSource.scanSystemIn();
    }
}