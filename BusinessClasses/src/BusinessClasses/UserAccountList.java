/*
 *Developer: MOUAOU JAOUAD
 *Date: 03/01/2015
 */
package BusinessClasses;

import java.nio.file.*;
import java.io.*;
import java.util.HashMap;

public class UserAccountList {

    private final HashMap<String, UserAccount> AccountList;

    public UserAccountList() {
        AccountList = new HashMap<>();

    }

    public UserAccount search(String username) throws Exception {

        try {

            return AccountList.get(username);
        } catch (Exception e) {
            throw new Exception("Error in search() method: " + e.toString());
        }

    }

    public boolean add(String username, UserAccount NewUserAccount) throws Exception {

        try {

            if (AccountList.containsKey(username)) {
                return false;
            } else {
                AccountList.put(username, NewUserAccount);
            }
            return true;

        } catch (Exception e) {
            throw new Exception("Error in add() method: " + e.toString());
        }
    }

    public boolean add(String username, String password, String email) throws Exception {

        UserAccount objUserAccount = UserAccount.getInstance(username, password, email);
        try {
            objUserAccount.setUsername(username);
            objUserAccount.setPassword(password);
            objUserAccount.setEmail(email);

            if (AccountList.containsKey(username)) {
                return false;
            } else {
                AccountList.put(username, objUserAccount);
            }
            return true;

        } catch (Exception e) {
            throw new Exception("Error in add() method values: " + e.toString());
        }
    }

    public boolean edit(String username, UserAccount EditedUserAccount) throws Exception {
        try {

            UserAccount objTempUseraccount = AccountList.get(username);

            if (objTempUseraccount != null) {

                objTempUseraccount.setPassword(EditedUserAccount.getPassword());
                objTempUseraccount.setEmail(EditedUserAccount.getEmail());

                return true;

            } else {
                return false;
            }

        } catch (Exception e) {
            throw new Exception("Error in edit() method values: " + e.toString());
        }
    }

    public boolean edit(String username, String password, String email) throws Exception {
        try {

            UserAccount objTempUseraccount = AccountList.get(username);

            if (objTempUseraccount != null) {

                objTempUseraccount.setPassword(password);
                objTempUseraccount.setEmail(email);

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new Exception("Error in edit() method: " + e.toString());
        }
    }

    public boolean remove(String username) throws Exception {
        try {

            UserAccount objTempUseraccount = AccountList.remove(username);

            if (objTempUseraccount != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new Exception("Error in remove() method: " + e.toString());
        }
    }

    public boolean changeUsername(String username, String newUsername) throws Exception {
        try {

            UserAccount objTempUseraccount = AccountList.get(username);

            if (objTempUseraccount != null) {

                objTempUseraccount.setUsername(newUsername);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error in ChangeUsername() method: " + e.toString());
        }
    }

    public boolean changePassword(String username, String newPassword) throws Exception {
        try {
            UserAccount objTempUseraccount = AccountList.get(username);

            if (objTempUseraccount != null) {

                objTempUseraccount.setPassword(newPassword);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error in ChangePassword() method: " + e.toString());
        }
    }

    public boolean changeEmail(String username, String newEmail) throws Exception {

        try {
            UserAccount objTempUseraccount = AccountList.get(username);

            if (objTempUseraccount != null) {

                objTempUseraccount.setEmail(newEmail);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error in ChangeEmail() method: " + e.toString());
        }
    }

    public void clear() {

        AccountList.clear();

    }

    public boolean Authenticate(String U, String P) throws Exception {
        try {
            load();
            for (UserAccount objTempUseraccount : AccountList.values()) {
                if (objTempUseraccount != null) {
                    if (objTempUseraccount.authenticate(U, P) == true) {
                        clear();
                        return true;

                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("Error in Authenticate() method: " + e.toString());
        }
        clear();
        return false;

    }

    public void load() throws Exception {
        Database_Load();
    }

    public void save() throws IOException {
        Database_Save();

    }

    protected void Database_Load() throws Exception {

        try {
            Path objDirPath = Paths.get("UserAccountData.txt");
            if (Files.notExists(objDirPath)) {
                Files.createFile(objDirPath);
            }
            try (BufferedReader objInStream = new BufferedReader(new FileReader("UserAccountData.txt"))) {
                String line = objInStream.readLine();
                while (line != null) {
                    String[] arrLine = line.split(",");
                    UserAccount objUserAccount = UserAccount.getInstance();
                    objUserAccount.setUserAccountID(arrLine[0]);
                    objUserAccount.setUsername(arrLine[1]);
                    objUserAccount.setPassword(arrLine[2]);
                    objUserAccount.setEmail(arrLine[3]);
                    this.add(objUserAccount.getUsername(), objUserAccount);

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
            try (PrintWriter objOutStream = new PrintWriter(new BufferedWriter(new FileWriter("UserAccountData.txt")))) {
                for (UserAccount objTempAccount : AccountList.values()) {

                    String id, usrname, pwd, email;
                    id = objTempAccount.getUserAccountID();
                    usrname = objTempAccount.getUsername();
                    pwd = objTempAccount.getPassword();
                    email = objTempAccount.getEmail();

                    objOutStream.println(id + "," + usrname + "," + pwd + "," + email);

                }
                objOutStream.close();
            }

        } catch (IOException e) {
            throw new IOException("Error in save() method: " + e.toString());
        }
    }
}
