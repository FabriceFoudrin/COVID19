package org;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.sio2.model.Person;
import org.sio2.model.SampleData;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable
{

    @FXML
    private TextField firstnameTextField,  lastnameTextField;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private Button removeButton,createButton, updateButton;
    @FXML
    private ListView<Person> listView;

    private final ObservableList<Person> personList = FXCollections.observableArrayList(Person.extractor);
    private Person selectedPerson;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        removeButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());

        UpdateButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull()
                .or(modifiedProperty.not())
                .or(firstnameTextField.textProperty().isEmpty()
                        .or(lastnameTextField.textProperty().isEmpty())));


        SampleData.fillSampleData(personList);
        listView.setItems(personList);


        SortedList<Person> sortedList = new SortedList<>(personList);
        // Maintenant que ‘sortedList’ est créée il faut trier les éléments en les comparant un à un
        sortedList.setComparator((p1, p2) -> {
            int result = p1.getLastname().compareToIgnoreCase(p2.getLastname());
            if (result == 0)
            {
                result = p1.getFirstname().compareToIgnoreCase(p2.getFirstname());
            }
            return result ;
        });


        listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener = (observable, oldValue, newValue) ->
            {
                // si rien n’est sélectionné alors ‘newValue’ peut être null
                selectedPerson = newValue;
                modifiedProperty.set(false);
                if (newValue != null)
                {//mettre à jour les champs
                    firstnameTextField.setText(selectedPerson.getFirstname());
                    lastnameTextField.setText(selectedPerson.getLastname());
                    notesTextArea.setText(selectedPerson.getNotes());
                } else
                {
                    firstnameTextField.setText("");
                    lastnameTextField.setText("");
                    notesTextArea.setText("");
                }
            });
        }




    @FXML
    private void createButtonAction(ActionEvent actionEvent)
        {
            System.out.println("Personne ajoutée");
            Person person = new Person(firstnameTextField.getText(), lastnameTextField.getText(), notesTextArea.getText());
            personList.add(person);
            // on sélectionne ce nouvel ajout
            listView.getSelectionModel().select(person);
        }

    @FXML
    private void updateButtonAction(ActionEvent actionEvent)
        {
            System.out.println("Mise à jour de : " + selectedPerson);
            Person p = listView.getSelectionModel().selectedItemProperty().removeListener(personChangeListener);
            p.setFirstname(firstnameTextField.getText());
            p.setLastname(lastnameTextField.getText());
            p.setNotes(notesTextArea.getText());
            // on met un écouteur sur ce ‘nouvel’ objet
            listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener);
            // on remet le drapeau de modif de propriété à ‘false’ pour le rendre à nouveau modifiable
            modifiedProperty.set(false);
        }


    @FXML
    private void removeButtonAction(ActionEvent actionEvent)
        {
            System.out.println("Suppression de : " + selectedPerson);
            personList.remove(selectedPerson);
        }

    @FXML
    private void handleKeyAction(KeyEvent keyEvent) {modifiedProperty.set(true) ; }
}



