package tasktracker.models;

public class Task {
    private String taskId;
    private String assignedTo;
    private String description;
    private String deadline;
    private boolean completed;

    public Task(String taskId, String assignedTo, String description, String deadline, boolean completed) {
        this.taskId = taskId;
        this.assignedTo = assignedTo;
        this.description = description;
        this.deadline = deadline;
        this.completed = completed;
    }

    // Getters
    public String getTaskId() { return taskId; }
    public String getAssignedTo() { return assignedTo; }
    public String getDescription() { return description; }
    public String getDeadline() { return deadline; }
    public boolean isCompleted() { return completed; }

    // Setters
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    public void setDescription(String description) { this.description = description; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() {
        return "Task ID: " + taskId +
                ", Assigned To: " + assignedTo +
                ", Description: " + description +
                ", Deadline: " + deadline +
                ", Completed: " + (completed ? "Yes" : "No");
    }
}
