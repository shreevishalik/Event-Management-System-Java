import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

// Event class to represent an event
class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private String eventId;
    private String eventName;
    private String eventType;
    private String date;
    private String venue;
    private int maxParticipants;
    private int currentParticipants;
    private List<String> registeredParticipants;

    public Event(String eventId, String eventName, String eventType, String date, String venue, int maxParticipants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.date = date;
        this.venue = venue;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = 0;
        this.registeredParticipants = new ArrayList<>();
    }

    // Getters and Setters
    public String getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public String getEventType() { return eventType; }
    public String getDate() { return date; }
    public String getVenue() { return venue; }
    public int getMaxParticipants() { return maxParticipants; }
    public int getCurrentParticipants() { return currentParticipants; }
    public List<String> getRegisteredParticipants() { return registeredParticipants; }

    public boolean addParticipant(String participantName) {
        if (currentParticipants < maxParticipants) {
            registeredParticipants.add(participantName);
            currentParticipants++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Type: %s | Date: %s | Venue: %s | Participants: %d/%d",
                eventId, eventName, eventType, date, venue, currentParticipants, maxParticipants);
    }
}

// Main Event Management System
public class EventManagementSystem {
    private static final String EVENTS_FILE = "events.txt";
    private static final String REGISTRATIONS_FILE = "registrations.txt";
    private static List<Event> events = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadEventsFromFile();
        
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   EVENT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Admin");
            System.out.println("2. Participant");
            System.out.println("3. Exit");
            System.out.print("Select your role: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    participantMenu();
                    break;
                case 3:
                    saveEventsToFile();
                    System.out.println("Thank you for using Event Management System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Admin Menu
    private static void adminMenu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   ADMIN MENU");
            System.out.println("========================================");
            System.out.println("1. Create Event");
            System.out.println("2. View All Events");
            System.out.println("3. View Event by Type");
            System.out.println("4. Delete Event");
            System.out.println("5. View Event Registrations");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    createEvent();
                    break;
                case 2:
                    viewAllEvents();
                    break;
                case 3:
                    viewEventsByType();
                    break;
                case 4:
                    deleteEvent();
                    break;
                case 5:
                    viewEventRegistrations();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Participant Menu
    private static void participantMenu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   PARTICIPANT MENU");
            System.out.println("========================================");
            System.out.println("1. View All Events");
            System.out.println("2. View Events by Type");
            System.out.println("3. Register for Event");
            System.out.println("4. View My Registrations");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewAllEvents();
                    break;
                case 2:
                    viewEventsByType();
                    break;
                case 3:
                    registerForEvent();
                    break;
                case 4:
                    viewMyRegistrations();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Create Event (Admin)
    private static void createEvent() {
        System.out.println("\n--- CREATE NEW EVENT ---");
        
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();
        
        // Check if event ID already exists
        for (Event e : events) {
            if (e.getEventId().equals(eventId)) {
                System.out.println("Event ID already exists!");
                return;
            }
        }
        
        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();
        
        System.out.println("\nSelect Event Type:");
        System.out.println("1. Concert");
        System.out.println("2. Seminar");
        System.out.println("3. Workshop");
        System.out.println("4. Competition");
        System.out.println("5. Conference");
        System.out.println("6. Dance Event");
        System.out.println("7. Sports Event");
        System.out.println("8. Cultural Event");
        System.out.print("Enter choice: ");
        
        int typeChoice = getIntInput();
        String eventType = getEventType(typeChoice);
        
        if (eventType == null) {
            System.out.println("Invalid event type!");
            return;
        }
        
        System.out.print("Enter Event Date (DD-MM-YYYY): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Venue: ");
        String venue = scanner.nextLine();
        
        System.out.print("Enter Maximum Participants: ");
        int maxParticipants = getIntInput();
        
        Event event = new Event(eventId, eventName, eventType, date, venue, maxParticipants);
        events.add(event);
        saveEventsToFile();
        
        System.out.println("\n Event created successfully!");
    }

    // Get Event Type String
    private static String getEventType(int choice) {
        switch (choice) {
            case 1: return "Concert";
            case 2: return "Seminar";
            case 3: return "Workshop";
            case 4: return "Competition";
            case 5: return "Conference";
            case 6: return "Dance Event";
            case 7: return "Sports Event";
            case 8: return "Cultural Event";
            default: return null;
        }
    }

    // View All Events
    private static void viewAllEvents() {
        System.out.println("\n--- ALL EVENTS ---");
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }
        
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i));
        }
    }

    // View Events by Type
    private static void viewEventsByType() {
        System.out.println("\nSelect Event Type:");
        System.out.println("1. Concert");
        System.out.println("2. Seminar");
        System.out.println("3. Workshop");
        System.out.println("4. Competition");
        System.out.println("5. Conference");
        System.out.println("6. Dance Event");
        System.out.println("7. Sports Event");
        System.out.println("8. Cultural Event");
        System.out.print("Enter choice: ");
        
        int typeChoice = getIntInput();
        String eventType = getEventType(typeChoice);
        
        if (eventType == null) {
            System.out.println("Invalid event type!");
            return;
        }
        
        System.out.println("\n--- " + eventType.toUpperCase() + " EVENTS ---");
        boolean found = false;
        
        for (Event e : events) {
            if (e.getEventType().equalsIgnoreCase(eventType)) {
                System.out.println(e);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No events found for this type.");
        }
    }

    // Delete Event (Admin)
    private static void deleteEvent() {
        System.out.print("\nEnter Event ID to delete: ");
        String eventId = scanner.nextLine();
        
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId().equals(eventId)) {
                events.remove(i);
                saveEventsToFile();
                System.out.println(" Event deleted successfully!");
                return;
            }
        }
        
        System.out.println("Event not found!");
    }

    // Register for Event (Participant)
    private static void registerForEvent() {
        System.out.print("\nEnter your name: ");
        String participantName = scanner.nextLine();
        
        System.out.print("Enter Event ID to register: ");
        String eventId = scanner.nextLine();
        
        for (Event e : events) {
            if (e.getEventId().equals(eventId)) {
                if (e.addParticipant(participantName)) {
                    saveEventsToFile();
                    saveRegistrationToFile(participantName, e);
                    System.out.println(" Successfully registered for " + e.getEventName());
                } else {
                    System.out.println(" Event is full! Cannot register.");
                }
                return;
            }
        }
        
        System.out.println("Event not found!");
    }

    // View My Registrations (Participant)
    private static void viewMyRegistrations() {
        System.out.print("\nEnter your name: ");
        String participantName = scanner.nextLine();
        
        System.out.println("\n--- YOUR REGISTRATIONS ---");
        boolean found = false;
        
        for (Event e : events) {
            if (e.getRegisteredParticipants().contains(participantName)) {
                System.out.println(e);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No registrations found.");
        }
    }

    // View Event Registrations (Admin)
    private static void viewEventRegistrations() {
        System.out.print("\nEnter Event ID: ");
        String eventId = scanner.nextLine();
        
        for (Event e : events) {
            if (e.getEventId().equals(eventId)) {
                System.out.println("\n--- REGISTRATIONS FOR " + e.getEventName() + " ---");
                List<String> participants = e.getRegisteredParticipants();
                
                if (participants.isEmpty()) {
                    System.out.println("No participants registered yet.");
                } else {
                    for (int i = 0; i < participants.size(); i++) {
                        System.out.println((i + 1) + ". " + participants.get(i));
                    }
                }
                return;
            }
        }
        
        System.out.println("Event not found!");
    }

    // Save Events to File
    private static void saveEventsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(EVENTS_FILE))) {
            for (Event e : events) {
                writer.println("EVENT_ID:" + e.getEventId());
                writer.println("EVENT_NAME:" + e.getEventName());
                writer.println("EVENT_TYPE:" + e.getEventType());
                writer.println("DATE:" + e.getDate());
                writer.println("VENUE:" + e.getVenue());
                writer.println("MAX_PARTICIPANTS:" + e.getMaxParticipants());
                writer.println("CURRENT_PARTICIPANTS:" + e.getCurrentParticipants());
                writer.println("REGISTERED_PARTICIPANTS:" + String.join(",", e.getRegisteredParticipants()));
                writer.println("---");
            }
            System.out.println(" Data saved to file: " + EVENTS_FILE);
        } catch (IOException e) {
            System.out.println("Error saving events: " + e.getMessage());
        }
    }

    // Load Events from File
    private static void loadEventsFromFile() {
        File file = new File(EVENTS_FILE);
        if (!file.exists()) {
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String eventId = null, eventName = null, eventType = null, date = null, venue = null;
            int maxParticipants = 0, currentParticipants = 0;
            List<String> registeredParticipants = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                if (line.equals("---")) {
                    Event event = new Event(eventId, eventName, eventType, date, venue, maxParticipants);
                    for (String participant : registeredParticipants) {
                        event.addParticipant(participant);
                    }
                    events.add(event);
                    registeredParticipants = new ArrayList<>();
                } else if (line.startsWith("EVENT_ID:")) {
                    eventId = line.substring(9);
                } else if (line.startsWith("EVENT_NAME:")) {
                    eventName = line.substring(11);
                } else if (line.startsWith("EVENT_TYPE:")) {
                    eventType = line.substring(11);
                } else if (line.startsWith("DATE:")) {
                    date = line.substring(5);
                } else if (line.startsWith("VENUE:")) {
                    venue = line.substring(6);
                } else if (line.startsWith("MAX_PARTICIPANTS:")) {
                    maxParticipants = Integer.parseInt(line.substring(17));
                } else if (line.startsWith("REGISTERED_PARTICIPANTS:")) {
                    String participants = line.substring(24);
                    if (!participants.isEmpty()) {
                        registeredParticipants = new ArrayList<>(Arrays.asList(participants.split(",")));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading events: " + e.getMessage());
        }
    }

    // Save Registration to File
    private static void saveRegistrationToFile(String participantName, Event event) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(REGISTRATIONS_FILE, true))) {
            writer.println("PARTICIPANT:" + participantName + " | EVENT:" + event.getEventName() + 
                          " | ID:" + event.getEventId() + " | DATE:" + new Date());
        } catch (IOException e) {
            System.out.println("Error saving registration: " + e.getMessage());
        }
    }

    // Helper method to get integer input
    private static int getIntInput() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }
}