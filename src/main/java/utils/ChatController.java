package utils;

import org.openqa.selenium.WebDriver;

import java.util.Scanner;


public class ChatController {

    private final TaskHandler taskHandler;
    private WebDriver driver;

    public ChatController() {
        taskHandler = new TaskHandler();
    }

    public void startChat() {
        System.out.println("🤖 Welcome to WorkOrder ChatBot!");
        System.out.println("You can type one of the following commands:");
        System.out.println("1. fetch workorders");
        System.out.println("2. search workorder");
        System.out.println("3. post-date workorder");
        System.out.println("4. arb");
        System.out.println("Type 'exit' to quit.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nYou: ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equals("exit")) {
                System.out.println("Bot: Goodbye!");
                DriverFactory.quitDriver();
                ExtentReportManager.flushReport();
                break;
            }

            switch (userInput) {
                case "fetch workorders":
                    taskHandler.fetchWorkOrders();
                    break;

                case "search workorder":
                    System.out.print("Bot: Enter Workorder ID or name to search: ");
                    String query = scanner.nextLine();
                    taskHandler.searchWorkOrder(query);
                    break;

                case "post-date workorder":
                    System.out.print("Bot: Enter Workorder ID to post-date: ");
                    String postDateId = scanner.nextLine();
                    taskHandler.postDateWorkOrder(postDateId);
                    break;

                case "arb":
                    taskHandler.arbAction();
                    break;

                default:
                    System.out.println("Bot: Sorry, I didn't understand that command.");
            }
        }

        scanner.close();
    }
}
