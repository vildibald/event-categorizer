package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.upjs.Entities.Categories;

public class Main extends Application {

    private static final int LOG_SIZE = 1000;
    private static final int LOG_ROTATION_COUNT = 10;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
       // FXMLLoader.se
        primaryStage.setTitle("Seminar");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        Categories.loadFromFile();
        Categories.loadSimilaritiesFromFile();
        //Handler handler = new FileHandler("test.log", LOG_SIZE, LOG_ROTATION_COUNT);
       // Logger.getLogger("").addHandler(handler);
        //initialize();
    }

//    private void initialize(){
//        Data data;
//        try {
//            data = new Data(Constants.DEFAULT_SAMPLES);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return;
//        }
//        ArrayList<DescribedEvent> events = null;
//        try {
//            events = data.parseToCollection(ArrayList.class,DescribedEvent.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//        //for (int i = 0; i < events.size(); i++) {
//        //   System.out.println(events.get(i).getEid());
//        //}
//        Tokenizer tokenizer = new Tokenizer();
//        // ArrayList<DescribedEvent> describedEvents = tokenizer.tokenize(events);
//        List<TokenedEvent> tokenedEvents = tokenizer.toTokenedEvents(events);
//
//       // Controller fooController = (Controller) fxmlLoader.getController();
//
//        System.out.println("Done.");
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
