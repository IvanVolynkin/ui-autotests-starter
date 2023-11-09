package ru.vanjo.qa.uiautotestsstarter.helpers.testdata.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private String firstName;

    private String lastName;

    private String middleName;

    private String street;

    private String city;

    private String email;

    private String phone;

    public Customer(String firstName, String lastName, String middleName, String street, String city, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.street = street;
        this.city = city;
        this.email = email;
        this.phone = phone;
    }

    public String getFormattedPhone() {
        return phone.replaceFirst("(\\d{1})(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "$1 ($2) $3-$4-$5");
    }
}
