package org.sio2.model;

import javafx.collections.ObservableList;

public class SampleData
{
    public static void fillSampleData(ObservableList<Person> backingList)
    {
        backingList.add(new Person("Frédéric","Chopin","première note"));
        backingList.add(new Person("Bob","Dylan","deuxième note"));
        backingList.add(new Person("Frank","Zappa","troisième note"));
        backingList.add(new Person("Johnny", "Haliday", "quatrième note"));
        backingList.add(new Person("Billie", "Haliday", "cinquième note"));
        backingList.add(new Person("Georges", "Brassens", "sixième note"));
        backingList.add(new Person("Danièl", "Waro", "septième note"));
        backingList.add(new Person("Jean-Sébastien", "Bach", "huitième note"));
        backingList.add(new Person("Janis", "Joplin", "neuvième note"));
        backingList.add(new Person("Aldi", "Meola", "dixième note"));
    }
}