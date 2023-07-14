public class Main {
    public static void main(String[] args) {
        Board theModel = new Board();
        GUI theView = new GUI();
        Controller controller = new Controller(theModel,theView);

        controller.run();
    }
}
