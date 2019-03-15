package sample;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;


public class Controller {

    @FXML
    BorderPane borderPane;

    @FXML
    GridPane gridSecundario;

    @FXML
    GridPane gridPrincipal;
    @FXML
    GridPane gridTerciario;


    @FXML
    Button ordenar1;

    @FXML
    Button ordenar2;


    public void initialize() {
        ordenar1.addEventHandler(MOUSE_CLICKED, mouseEvent -> {
            mostrarLista(ordenar(gridSecundario), gridSecundario);
        });

        ordenar2.addEventHandler(MOUSE_CLICKED, mouseEvent -> {
            mostrarLista(ordenar(gridTerciario), gridTerciario);
        });
    }

    private List<Double> lista = new ArrayList<>();
    private int conLista = 0;


    @FXML
    public void anadirALista() {
        loadDialog();
        mostrarLista(this.lista, gridPrincipal);
    }

    private void loadDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setDialogPane(new DialogPane());
        dialog.setTitle("Añadir numero");
        dialog.setContentText("Ingresa un numero para agregarlo a la lista");
        TextField textField = new TextField();
        ButtonType button = new ButtonType("Añadir");
        dialog.getDialogPane().setGraphic(textField);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(button);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(button)) {
            try {
                double x = Double.parseDouble(textField.getText());
                if (conLista < 10) {
                    lista.add(x);
                    conLista++;
                } else {
                    JOptionPane.showMessageDialog(null, "Ya no hay mas espacio");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingresa solo numeros");
            }
        }
    }


    public void mostrarLista(List<Double> lista, GridPane gridPane) {
        int listSize = lista.size();
        int con = 0;
        for (Node node : gridPane.getChildren()) {
            if (node.getClass() == TextField.class && con < listSize) {
                TextField textField = (TextField) node;
                textField.setText(Double.toString(lista.get(con)));
                con++;
            }
        }
    }

    public List<Double> ordenar(GridPane gridPane) {

        ArrayList<Double> lista = new ArrayList<>(this.lista);
        if (gridPane == gridTerciario) {

            ArrayList<Double> doubles = new ArrayList<>();
            doubles.add(0.0);
            lista.removeAll(doubles);

        }
        boolean flag = true;
        if (lista.size() > 1) {
            while (flag) {
                flag = false;
                for (int i = 0; i < lista.size() - 1; i++) {
                    if (lista.get(i) > lista.get(i + 1)) {
                        double cambio = lista.get(i);
                        lista.set(i, lista.get(i + 1));
                        lista.set(i + 1, cambio);
                        flag = true;
                    }
                }
            }
        }
        return lista;
    }
}
