package content.methods.model;

import javafx.beans.property.SimpleStringProperty;

public class Person {
        public final SimpleStringProperty firstName;
        public final SimpleStringProperty lastName;
        public final SimpleStringProperty email;

        public Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstNameee() {
            return firstName.get();
        }
        public void setFirstNameee(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }
        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }
        public void setEmail(String fName) {
            email.set(fName);
        }
}
