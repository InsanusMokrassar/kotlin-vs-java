package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.ContactBookJava.API;

import java.util.Objects;
import java.util.regex.Pattern;

public class Contact {
    private static final Pattern nameRegex = Pattern.compile("^.+$");
    private static final Pattern lastNameRegex = Pattern.compile("^.*$");
    private static final Pattern phoneRegex = Pattern.compile("^\\+?\\d{2,}$");
    private static final Pattern emailRegex = Pattern.compile("^[^@]+@[^.]+\\..+$");

    private final String name;
    private final String lastName;
    private final String phone;
    private final String email;

    public Contact(String name, String lastName, String phone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    private Boolean isValid = null;

    public Boolean getIsValid() {
        if (isValid == null) {
            isValid = nameRegex.matcher(
                    name
            ).matches() && lastNameRegex.matcher(
                    lastName
            ).matches() && phoneRegex.matcher(
                    phone
            ).matches() && emailRegex.matcher(
                    email
            ).matches();
        }
        return isValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getName(), contact.getName()) &&
                Objects.equals(getLastName(), contact.getLastName()) &&
                Objects.equals(getPhone(), contact.getPhone()) &&
                Objects.equals(getEmail(), contact.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getPhone(), getEmail());
    }
}
