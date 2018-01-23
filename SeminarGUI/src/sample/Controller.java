package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import jfx.messagebox.MessageBox;
import sk.upjs.Engine.Categorizers.Categorizer;
import sk.upjs.Engine.Categorizers.CategorizerFactory;
import sk.upjs.Engine.JSONCollectionMapper;
import sk.upjs.Entities.*;
import sk.upjs.Utilities.MapUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    @FXML
    private Button confirmButton;
    @FXML
    private TextField urlTextBox;
    @FXML
    private ListView eventsListView;
    @FXML
    private CheckBox showInfoCheckBox;
    @FXML
    private TextArea eventInfoTextArea;

    @FXML
    private Label accuracyLabel;
    @FXML
    private ProgressBar accuracyProgressBar;

    @FXML
    private GridPane mainWindow;

    private Stage owner;

    private Task calculateAccuracyTask;

    void setOwner(Stage owner) {
        this.owner = owner;
    }

    @FXML
    protected void confirm_OnAction() {
        URL url;

        try {
            url = new URL(urlTextBox.getText());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            MessageBox.show(null,
                    "Cannot load specified url",
                    "Url error",
                    MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
            eventInfoTextArea.setText("Failed to load.");
            return;
        }
        ArrayList<DescribedEvent> events = getDescribedEvents(url);
        fillEventsListView(events);
        calculateAccuracy(events);
        Logger.getLogger("").log(Level.INFO, "Event parsing done.");

    }

    private ArrayList<DescribedEvent> getDescribedEvents(URL url) {
        ArrayList<DescribedEvent> events = null;
        try {
            JSONCollectionMapper data = new JSONCollectionMapper();
            events = data.parseToCollection(url, ArrayList.class, DescribedEvent.class);
        } catch (IOException e) {
            MessageBox.show(null,
                    "Cannot load data",
                    "Data error",
                    MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);

        }
        return events;
    }

    private ArrayList<DescribedEvent> getDescribedEvents(File file) {
        ArrayList<DescribedEvent> events = null;
        try {
            JSONCollectionMapper data = new JSONCollectionMapper();
            events = data.parseToCollection(file, ArrayList.class, DescribedEvent.class);
        } catch (IOException e) {
            MessageBox.show(null,
                    "Cannot load data",
                    "Data error",
                    MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);

        }
        return events;
    }

    private void fillEventsListView(List<DescribedEvent> events) {
        eventsListView.getItems().clear();
        ObservableList<DescribedEvent> eventsView = FXCollections.observableList(events);
        eventsListView.setItems(eventsView);
        eventsListView.setCellFactory(new Callback<ListView<DescribedEvent>, ListCell<DescribedEvent>>() {
            @Override
            public ListCell<DescribedEvent> call(ListView<DescribedEvent> param) {
                ListCell<DescribedEvent> cell = new ListCell<DescribedEvent>() {
                    @Override
                    protected void updateItem(DescribedEvent e, boolean bool) {
                        super.updateItem(e, bool);
                        if (e != null) {
                            setText(e.getName());
                        }
                    }
                };
                return cell;
            }
        });


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            updateEventTextArea();

        });
    }

    private void updateEventTextArea() {
        Event event = (Event) eventsListView.getSelectionModel().getSelectedItem();
        if (showInfoCheckBox.isSelected())
            eventTextArea_putEvent(event);
        else
            eventTextArea_putEventInfo(event);

    }

    private void eventTextArea_putEventInfo(Event event) {
        if (event instanceof TokenedEvent) {
        } else if (event instanceof DescribedEvent) {
            eventInfoTextArea.setText(((DescribedEvent) event).getDescription());
        }
    }


    private void eventTextArea_putEvent(Event event) {

        Categorizer categorizer = CategorizerFactory.createCategorizer(CategorizerFactory.CategorizerType.SIMPLE);
        Category category = categorizer.determineCategory(event);
        String categoryName = category == null ? "Other" : category.getName();

        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(event.getEid())
                .append("\nStart time: ").append(event.getStartTime())
                .append("\nEnd time: ").append(event.getEndTime())
                .append("\nComputed end time: ").append(event.getEndTimeComputed())
                .append("\nCreator: ").append(event.getCreator())
                .append("\nCreator category: ").append(event.getCreatorCategory())
                .append("\nLocation: ").append(event.getLocation())
                .append("\nLocation filter: ").append(event.getLocationFilter())
                .append("\nLatitude: ").append(event.getLatitude())
                .append("\nLongitude: ").append(event.getLongitude())
                .append("\nHost: ").append(event.getHost())
                .append("\nPrivacy: ").append(event.getPrivacy())
                .append("\nType: ").append(event.getType())
                .append("\nDetermined type: ").append(categoryName)
                .append("\nValid: ").append(event.isValid());
        eventInfoTextArea.setText(sb.toString());
    }

    @FXML
    public void selectedCheckBox_OnSelected(ActionEvent actionEvent) {
        updateEventTextArea();
    }

    public void load_OnAction(ActionEvent actionEvent) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Open events file");
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        final File file = fc.showOpenDialog(mainWindow.getScene().getWindow());
        if (file != null) {
            ArrayList<DescribedEvent> events = getDescribedEvents(file);
            //for (int i = 0; i < events.size(); i++) {
            //   System.out.println(events.get(i).getEid());
            //}
            fillEventsListView(events);
            calculateAccuracy(events);
        } else {
        }
    }

    public void calculateAccuracy(List<DescribedEvent> events) {

        if (calculateAccuracyTask != null && calculateAccuracyTask.isRunning()) {
            calculateAccuracyTask.cancel(true);
        }
        calculateAccuracyTask = createAccuracyTask(events);


        accuracyLabel.setText("? %");

        accuracyProgressBar.progressProperty().unbind();
        accuracyProgressBar.setProgress(0);
        accuracyProgressBar.progressProperty().bind(calculateAccuracyTask.progressProperty());
        new Thread(calculateAccuracyTask).start();

    }

    private Task createAccuracyTask(List<DescribedEvent> events) {
        Task task = new Task() {
            @Override
            protected Double call() throws Exception {
                int count = events.size();
                Categorizer categorizer = CategorizerFactory.createCategorizer(CategorizerFactory.CategorizerType.TRANSLATION_SIMPLE);
                int successfullDeterminations = 0;
                for (int i = 0; i < count; i++) {
                    DescribedEvent event = events.get(i);
                    HashMap<Category, Integer> determinedCategories = categorizer.determineCategories(event);
                    Category bestCategory = MapUtils.largestValuedKey(determinedCategories);
                    Category secondBestCategory = MapUtils.secondLargestValuedKey(determinedCategories);
                    if (bestCategory == null) continue;
                    if (Categories.getInstance().areSimilar(bestCategory.getName(), event.getType())
                            || Categories.getInstance().areSimilar(secondBestCategory.getName(), event.getType())) {
                        ++successfullDeterminations;
                    } else {
                        System.out.println("\n========================\n" +
                                //"Determined: "+category.getName()+"\nActual: "+event.getType()
                                "Best determined: " + bestCategory.getName() + "\nSecond best determined: " + secondBestCategory.getName() + "\nActual: " + event.getType()
                                + "\n------");
                        System.out.println("\n" + event.getName() + "\n\n" +
                                event.getDescription());
                    }
                    updateProgress(i, count - 1);
                }

                return 100 * (double) successfullDeterminations / (double) count;
            }
        };
        task.setOnSucceeded(t -> accuracyLabel.setText(((Double) task.getValue()).doubleValue() + " %"));
        return task;
    }

}
