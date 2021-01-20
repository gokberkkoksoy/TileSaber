
import javafx.application.Application;
import javafx.stage.Stage;
import Scenes.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage){

        EntryScreen entryScreen = new EntryScreen(primaryStage);

        primaryStage.setScene(entryScreen.getScene());

        primaryStage.setTitle("TileSaber");
        primaryStage.setResizable(false);

        primaryStage.setFullScreen(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
