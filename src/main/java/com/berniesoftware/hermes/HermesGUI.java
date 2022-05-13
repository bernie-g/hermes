package com.berniesoftware.hermes;

import com.berniesoftware.hermes.container.HermesContainer;
import com.berniesoftware.hermes.container.HermesNumberContainer;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HermesGUI extends Application {

    private Pane pane = new Pane();
    private GridPane gridPane = new GridPane();

    public HermesGUI() {
        Hermes.setHermesGUI(this);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(createContent(), 800, 800));
        stage.show();
    }

    private Parent createContent() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(75);
        gridPane.getColumnConstraints().addAll(col1, col2);
        gridPane.setPadding(new Insets(70, 50, 0, 50));
        pane.getChildren().add(gridPane);
        return pane;
    }

    <T> void add(String key, HermesContainer<T> container) {
        int rowCount = gridPane.getRowCount();
        Label label = new Label(key);
        label.setPadding(new Insets(0, 50, 0, 0));
        label.setFont(new Font(20));
        gridPane.add(label, 0, rowCount);

        if (container instanceof HermesNumberContainer) {
            HermesNumberContainer numberContainer = (HermesNumberContainer) container;
            if (container.getClazz() == double.class) {
                MFXSlider slider = new MFXSlider(0, 10, (double) numberContainer
                        .getValue());
                slider.setMax(Double.MAX_VALUE);
                slider.setMin(Double.MIN_VALUE);
                slider.setMax((double)numberContainer.getMax());
                slider.setMin((double)numberContainer.getMin());
                slider.setDecimalPrecision(2);
                slider.setPrefWidth(500);
                slider.valueProperty().addListener((observable, oldValue, newValue) -> Hermes.hermesCache
                        .update(key, double.class, newValue.doubleValue()));
                gridPane.add(slider, 1, rowCount);
            } else if (container.getClazz() == int.class) {
                MFXSlider slider = new MFXSlider(0, 10, (int) numberContainer
                        .getValue());
                slider.setMax(Double.MAX_VALUE);
                slider.setMin(Double.MIN_VALUE);
                slider.setMax((int) numberContainer.getMax());
                slider.setMin((int) numberContainer.getMin());
                slider.setPrefWidth(500);
                gridPane.add(slider, 1, rowCount);
                slider.valueProperty().addListener((observable, oldValue, newValue) -> Hermes.hermesCache
                        .update(key, int.class, newValue.intValue()));
            }
        } else {
            if (container.getClazz() == String.class) {
                MFXTextField textField = new MFXTextField((String) container.getDefaultValue());
                textField.textProperty().addListener((observable, oldValue, newValue) -> Hermes.hermesCache
                        .update(key, String.class, newValue));
                gridPane.add(textField, 1, rowCount);
            } else if (container.getClazz() == boolean.class) {
                MFXCheckbox checkbox = new MFXCheckbox("Enabled");
                checkbox.setSelected((Boolean) container.getDefaultValue());
                checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> Hermes.hermesCache
                        .update(key, boolean.class, newValue));
                gridPane.add(checkbox, 1, rowCount);
            }
        }
    }
}
