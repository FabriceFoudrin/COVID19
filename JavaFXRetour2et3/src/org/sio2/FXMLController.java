package org.sio2;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.sio2.model.Person;
import org.sio2.model.SampleData;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private TextField firstnameTextField, lastnameTextField;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private Button removeButton,
            createButton,
            updateButton;
    @FXML
    private ListView<Person> listView;

    private final ObservableList<Person> personList = FXCollections.observableArrayList(Person.extractor);

    private final BooleanProperty modifiedProperty = new SimpleBooleanProperty(false);
    private Person selectedPerson;
    private ChangeListener<Person> personChangeListener;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Disable the Remove/Edit buttons if nothing is selected in the ListView control
        removeButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
        updateButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull()
                .or(modifiedProperty.not())
                .or(firstnameTextField.textProperty().isEmpty()
                        .or(lastnameTextField.textProperty().isEmpty())));
        createButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNotNull()
                .or(firstnameTextField.textProperty().isEmpty()
                        .or(lastnameTextField.textProperty().isEmpty())));

        SampleData.fillSampleData(personList);

        // Use a sorted list; sort by lastname; then by firstname
        SortedList<Person> sortedList = new SortedList<>(personList);

        // sort by lastname first, then by firstname; ignore notes
        sortedList.setComparator((p1, p2) -> {
            int result = p1.getSurname().compareToIgnoreCase(p2.getSurname());
            if (result == 0) {
                result = p1.getFirstname().compareToIgnoreCase(p2.getFirstname());
            }
            return result;
        });
        listView.setItems(sortedList);


        listView.getSelectionModel().selectedItemProperty().addListener(
                personChangeListener = (observable, oldValue, newValue) -> {
                    System.out.println("Selected item: " + newValue);
                    // newValue can be null if nothing is selected
                    selectedPerson = newValue;
                    modifiedProperty.set(false);
                    if (newValue != null) {
                        // Populate controls with selected Person
                        firstnameTextField.setText(selectedPerson.getFirstname());
                        lastnameTextField.setText(selectedPerson.getSurname());
                        notesTextArea.setText(selectedPerson.getNotes());
                    } else {
                        firstnameTextField.setText("");
                        lastnameTextField.setText("");
                        notesTextArea.setText("");
                    }
                });

        // Pre-select the first item
        listView.getSelectionModel().selectFirst();
        }

        @FXML
        private void createButtonAction (ActionEvent actionEvent)
        {
            System.out.println("Personne ajoutée");
            Person person = new Person(firstnameTextField.getText(), lastnameTextField.getText(), notesTextArea.getText());
            personList.add(person);
            // on sélectionne ce nouvel ajout
            listView.getSelectionModel().select(person);
        }

        @FXML
        private void updateButtonAction (ActionEvent actionEvent)
        {
            System.out.println("Mise à jour de : " + selectedPerson);
            Person p = listView.getSelectionModel().getSelectedItem();
            p.setFirstname(firstnameTextField.getText());
            p.setSurname(lastnameTextField.getText());
            p.setNotes(notesTextArea.getText());
            // on met un écouteur sur ce ‘nouvel’ objet
            listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener);
            // on remet le drapeau de modif de propriété à ‘false’ pour le rendre à nouveau modifiable
            modifiedProperty.set(false);
        }


        @FXML
        private void removeButtonAction (ActionEvent actionEvent)
        {
            System.out.println("Suppression de : " + selectedPerson);
            personList.remove(selectedPerson);
        }

        @FXML
        private void handleKeyAction (KeyEvent keyEvent){
            modifiedProperty.set(true);
        }
    }







