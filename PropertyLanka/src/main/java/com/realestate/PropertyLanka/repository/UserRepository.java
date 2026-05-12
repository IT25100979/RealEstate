package com.realestate.PropertyLanka.repository;


import com.realestate.PropertyLanka.model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final String FILE_NAME = "src/main/resources/database/users_db.txt";

    // 1. CREATE (Register)
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user.toDatabaseString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user to database.");
        }
    }

    // 2. READ ALL (Needed for Login and searching)
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 9) {
                    users.add(new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]));
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet
        } catch (IOException e) {
            System.out.println("Error reading users database.");
        }
        return users;
    }

    // 3. UPDATE (Edit Profile)
    public void update(User updatedUser) {
        List<User> users = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (User u : users) {
                if (u.getId().equals(updatedUser.getId())) {
                    writer.write(updatedUser.toDatabaseString());
                } else {
                    writer.write(u.toDatabaseString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating users database.");
        }
    }

    // 4. DELETE (Delete Account)
    public void delete(String id) {
        List<User> users = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (User u : users) {
                if (!u.getId().equals(id)) {
                    writer.write(u.toDatabaseString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting user from database.");
        }
    }
}