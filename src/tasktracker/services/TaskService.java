package tasktracker.services;

import tasktracker.models.Task;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskService {

    private List<Task> tasks;

    public TaskService() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    // tasks.txt faylından task-ları oxuyur
    private void loadTasks() {
        List<String> lines = FileService.readFile("data/tasks.txt");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                String taskId = parts[0];
                String assignedTo = parts[1];
                String description = parts[2];
                String deadline = parts[3];
                boolean completed = Boolean.parseBoolean(parts[4]);
                tasks.add(new Task(taskId, assignedTo, description, deadline, completed));
            }
        }
    }

    // Yeni task əlavə et (Admin)
    public void addTask(String taskId, String assignedTo, String description, String deadline) {
        Task newTask = new Task(taskId, assignedTo, description, deadline, false);
        tasks.add(newTask);
        FileService.writeFile("data/tasks.txt",
                taskId + "," + assignedTo + "," + description + "," + deadline + ",false");
        System.out.println("Task added successfully!");
    }

    // Bütün task-ları göstər (Admin və Member)
    public void listAllTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Member task tamamladı kimi işarələyir
    public void markTaskCompleted(String taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                saveTasks();
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task not found!");
    }

    // Member öz task-larını görür
    public void listTasksForUser(String username) {
        for (Task task : tasks) {
            if (task.getAssignedTo().equals(username)) {
                System.out.println(task);
            }
        }
    }

    // Progress qeydi əlavə et (Member)
    public void addProgress(String username, String taskId, String note) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String now = dtf.format(LocalDateTime.now());
        String line = "[" + now + "] - Task " + taskId + ": " + note;
        FileService.writeFile("data/progress_" + username + ".txt", line);
        System.out.println("Progress added!");
    }

    // tasks.txt faylını yenidən yazır (update)
    private void saveTasks() {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.getTaskId() + "," + task.getAssignedTo() + "," +
                    task.getDescription() + "," + task.getDeadline() + "," +
                    task.isCompleted());
        }
        FileService.overwriteFile("data/tasks.txt", lines);
    }
}
