package tasktracker.services;

import tasktracker.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        loadUsers();
    }

    // users.txt faylından user-ları oxuyur
    private void loadUsers() {
        List<String> lines = FileService.readFile("data/users.txt");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                users.add(new User(parts[0], parts[1], parts[2]));
            }
        }
    }

    // Login yoxlaması
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // tapılmadı
    }

    // Yeni user əlavə et (Admin)
    public void addUser(String username, String password, String role) {
        User newUser = new User(username, password, role);
        users.add(newUser);
        FileService.writeFile("data/users.txt", username + "," + password + "," + role);
        System.out.println("User added successfully!");
    }

    // User sil (Admin)
    public void deleteUser(String username) {
        boolean removed = users.removeIf(u -> u.getUsername().equals(username));
        if (removed) {
            // Faylı yenidən yaz
            List<String> lines = new ArrayList<>();
            for (User u : users) {
                lines.add(u.getUsername() + "," + u.getPassword() + "," + u.getRole());
            }
            FileService.overwriteFile("data/users.txt", lines);
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    // Bütün user-ları göstər (Admin)
    public void listUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}
