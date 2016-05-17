/*
 *Developer: MOUAOU JAOUAD
 *Date: 03/01/2015
 */
package BusinessClasses;

import java.nio.file.*;
import java.io.*;
import java.util.HashMap;

public class EmployeeList {

    private final HashMap<String, Employee> EmployeeList;

    public EmployeeList() {
        EmployeeList = new HashMap<>();
    }

    public int getCount() {
        return EmployeeList.size();
    }

    public HashMap getEmployeeList() {
        return EmployeeList;
    }

    public Employee search(String ssNumber) throws Exception {

        try {
            return EmployeeList.get(ssNumber);
        } catch (Exception e) {
            throw new Exception("Error in search() method: " + e.toString());
        }

    }

    public boolean add(String ssNumber, Employee NewEmployee) throws Exception {

        try {

            if (EmployeeList.containsKey(ssNumber)) {
                return false;
            } else {
                EmployeeList.put(ssNumber, NewEmployee);

            }
            return true;
        } catch (Exception e) {
            throw new Exception("Error in add() method: " + e.toString());
        }
    }

    public boolean add(String SSNumber, String FirstName,
            String LastName, String DateOfBirth,
            String Address, String Phone,
            String Email, String JobTitle) throws Exception {

        try {
            Employee objEmployee = new Employee();

            objEmployee.setSSNumber(SSNumber);
            objEmployee.setFirstName(FirstName);
            objEmployee.setLastName(LastName);
            objEmployee.setDateOfBirth(DateOfBirth);
            objEmployee.setAddress(Address);
            objEmployee.setPhone(Phone);
            objEmployee.setEmail(Email);
            objEmployee.setJobTitle(JobTitle);

            if (EmployeeList.containsKey(SSNumber)) {
                return false;
            } else {
                EmployeeList.put(SSNumber, objEmployee);

            }
            return true;

        } catch (Exception e) {
            throw new Exception("Error in add() method values: " + e.toString());
        }
    }

    public boolean edit(String ssNumber, Employee EditedUserAccount) throws Exception {
        try {

            Employee objTempEmployee = EmployeeList.get(ssNumber);

            if (objTempEmployee != null) {

                objTempEmployee.setFirstName(EditedUserAccount.getSSNumber());
                objTempEmployee.setFirstName(EditedUserAccount.getFirstName());
                objTempEmployee.setLastName(EditedUserAccount.getLastName());
                objTempEmployee.setDateOfBirth(EditedUserAccount.getDateOfBirth());
                objTempEmployee.setAddress(EditedUserAccount.getAddress());
                objTempEmployee.setPhone(EditedUserAccount.getPhone());
                objTempEmployee.setEmail(EditedUserAccount.getEmail());
                objTempEmployee.setJobTitle(EditedUserAccount.getJobTitle());

                return true;

            } else {
                return false;
            }

        } catch (Exception e) {
            throw new Exception("Error in edit() method: " + e.toString());
        }
    }

    public boolean edit(String SSNumber, String FirstName,
            String LastName, String DateOfBirth,
            String Address, String Phone,
            String Email, String JobTitle) throws Exception {
        try {

            Employee objTempEmployee = EmployeeList.get(SSNumber);

            if (objTempEmployee != null) {
                objTempEmployee.setSSNumber(SSNumber);
                objTempEmployee.setFirstName(FirstName);
                objTempEmployee.setLastName(LastName);
                objTempEmployee.setDateOfBirth(DateOfBirth);
                objTempEmployee.setAddress(Address);
                objTempEmployee.setPhone(Phone);
                objTempEmployee.setEmail(Email);
                objTempEmployee.setJobTitle(JobTitle);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error in edit() method values: " + e.toString());
        }
    }

    public boolean remove(String SSNumber) throws Exception {
        try {

            Employee objTempEmployee = EmployeeList.remove(SSNumber);

            if (objTempEmployee != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error in remove() method: " + e.toString());
        }
    }

    public boolean print(String ssNumber) throws Exception {
        try {
            Employee objTempEmployee = EmployeeList.get(ssNumber);

            if (objTempEmployee != null) {
                objTempEmployee.print();

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error in print() method: " + e.toString());
        }
    }

    public void printAll() throws Exception {
        try {
            for (Employee objTempEmployee : EmployeeList.values()) {
                objTempEmployee.print();
            }
        } catch (Exception e) {
            throw new Exception("Error in printAll() method: " + e.toString());
        }

    }

    public void clear() {

        EmployeeList.clear();

    }

    public void load() throws Exception {
        Database_Load();
    }

    public void save() throws IOException {
        Database_Save();

    }

    protected void Database_Load() throws Exception {
        try {
            Path objDirPath = Paths.get("EmployeeData.txt");
            if (Files.notExists(objDirPath)) {
                Files.createFile(objDirPath);
            }
            try (BufferedReader objInStream = new BufferedReader(new FileReader("EmployeeData.txt"))) {
                String line = objInStream.readLine();
                while (line != null) {
                    String[] arrLine = line.split(",");

                    Employee objEmplyee = new Employee();

                    objEmplyee.setSSNumber(arrLine[0]);
                    objEmplyee.setFirstName(arrLine[1]);
                    objEmplyee.setLastName(arrLine[2]);
                    objEmplyee.setDateOfBirth(arrLine[3]);
                    objEmplyee.setAddress(arrLine[4]);
                    objEmplyee.setPhone(arrLine[5]);
                    objEmplyee.setEmail(arrLine[6]);
                    objEmplyee.setJobTitle(arrLine[7]);
                    this.add(objEmplyee.getSSNumber(), objEmplyee);

                    line = objInStream.readLine();
                }

                objInStream.close();
            }

        } catch (Exception e) {
            throw new Exception("Error in load() method: " + e.toString());
        }

    }

    protected void Database_Save() throws IOException {
        try {
            try (PrintWriter objOutStream = new PrintWriter(new BufferedWriter(new FileWriter("EmployeeData.txt")))) {
                String SSNumber, FirstName, LastName, DateOfBirth, Address, Phone, Email, JobTitle;
                for (Employee objTempEmployee : EmployeeList.values()) {

                    SSNumber = objTempEmployee.getSSNumber();
                    FirstName = objTempEmployee.getFirstName();
                    LastName = objTempEmployee.getLastName();
                    DateOfBirth = objTempEmployee.getDateOfBirth();
                    Address = objTempEmployee.getAddress();
                    Phone = objTempEmployee.getPhone();
                    Email = objTempEmployee.getEmail();
                    JobTitle = objTempEmployee.getJobTitle();

                    objOutStream.println(SSNumber + "," + FirstName + "," + LastName + "," + DateOfBirth
                            + "," + Address + "," + Phone + "," + Email + "," + JobTitle);

                }

                objOutStream.close();
            }

        } catch (IOException e) {
            throw new IOException("Error in save() method: " + e.toString());
        }
    }
}
