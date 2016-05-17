/*
 *Developer: MOUAOU JAOUAD
 *Date: 03/01/2015
 */
package BusinessClasses;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public abstract class Person {

    private String ssNumber;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int age;
    private String address;
    private String phone;
    private String email;
    private static int count = 0;

    public String getSSNumber() {
        return ssNumber;
    }

    public void setSSNumber(String newSSnum) {
        ssNumber = newSSnum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String f) {
        firstName = f;

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String l) {
        lastName = l;
    }

    public String getDateOfBirth() {
        return dateOfBirth;

    }

    public void setDateOfBirth(String d) {
        dateOfBirth = d;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate bod = LocalDate.parse(d, formatter);
        LocalDate today = LocalDate.now();
        age = today.getYear() - bod.getYear();

    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String a) {
        address = a;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String p) {
        phone = p;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        email = e;
    }

    public static int getCount() {

        return count;

    }

    public static void setCount(int new_value) {
        count = new_value;
    }

    public Person() {
        ssNumber = "";
        firstName = "";
        lastName = "";
        dateOfBirth = "00/00/0000";
        age = 0;
        address = "";
        phone = "";
        email = "";

    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Person(String ssnum, String fname,
            String lname, String dob, String adrs,
            String tel, String e_mail, String jtitle) {

        this.setSSNumber(ssnum);
        this.setFirstName(fname);
        this.setLastName(lname);
        this.setDateOfBirth(dob);
        this.age = 0;
        this.setAddress(adrs);
        this.setPhone(tel);
        this.setEmail(e_mail);

    }

    public abstract void print() throws IOException;

}
