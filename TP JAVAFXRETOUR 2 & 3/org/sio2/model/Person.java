package org.sio2.model;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;

import java.util.Objects;

public class Person
{
    private StringProperty firstname = new SimpleStringProperty(this,"firstname");
    private StringProperty  surname = new SimpleStringProperty(this,"surname");
    private StringProperty notes = new SimpleStringProperty(this,"notes");

    public static Callback<Person, Observable[]> extractor = person ->new Observable[]
            { person.surnameProperty(), person.firstnameProperty() };



    /** constructeur par defaut
     *
     */
    public Person(){

    }

    /** Construct
     *
     * @param nom
     * @param prenom
     * @param notes
     */
    public Person(String nom, String prenom, String notes){
        setFirstname(nom);
        setSurname(prenom);
        setNotes(notes);
    }

    // Setters and Getters
    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getNotes() {
        return notes.get();
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstname=" + firstname +
                ", surname=" + surname +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return firstname.equals(person.firstname) &&
                surname.equals(person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, surname);
    }



}
