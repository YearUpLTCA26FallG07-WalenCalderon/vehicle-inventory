package com.pluralsight;

import java.util.Locale;



public class Main {
    public static void main(String []args){

        Vehicle[] vehicles = new Vehicle[20];
        int countVehicles = 0;

        vehicles[countVehicles++] = new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500);
        vehicles[countVehicles++] = new Vehicle(101122, "Toyota Camry", "Blue", 60000, 11000);
        vehicles[countVehicles++] = new Vehicle(101123, "Chevrolet Malibu", "Black", 50000, 9700);

        vehicles[countVehicles++] = new Vehicle(101124, "Honda Civic", "White", 70000, 7500);

        vehicles[countVehicles++] = new Vehicle(101125, "Subaru Outback", "Green", 55000, 14500);

        vehicles[countVehicles++] = new Vehicle(101126, "Jeep Wrangler", "Yellow", 30000, 16000);



        String mainMenuText = """
                === MAIN MENU ===
                
                What do you want to do?
                1 - List all vehicles
                2 - Search by make/model
                3 - Search by price range
                4 - Search by color
                5 - Add a vehicle
                6 - Quit
                Enter your command:""";

        int choice;




        do{
            choice = Console.promptForInt(mainMenuText);
            switch(choice){
                case 1 -> processListAllVehicles(vehicles,countVehicles);
                case 2 -> processSearchByMakeModel(vehicles,countVehicles);
                case 3 -> processSearchByPrice(vehicles,countVehicles);
                case 4 -> processSearchByColor(vehicles,countVehicles);
                case 5 -> countVehicles = processAddVehicle(vehicles, countVehicles);
                default -> System.out.println("Invalid command, please try again...");
            }
        }  while (choice != 6);


    }

    private static void processListAllVehicles(Vehicle[] vehicles, int countVehicles) {
        for(int i = 0; i < countVehicles; i++){
            Vehicle vehicle = vehicles[i];
            printVehicleInfo(vehicle);
        }


    }

    private static void processSearchByMakeModel(Vehicle[] vehicles, int countVehicles) {
        String search = Console.promptForString("Enter Make model: ");
        for (int i = 0; i < countVehicles; i++){
            String makeModel = vehicles[i].getMakeModel(); //Finding the makeModels

            if(makeModel.toLowerCase().contains(search.toLowerCase())){  //If make model and search are the same we print makeModel
                printVehicleInfo(vehicles[i]);
            }

        }
    }

    private static void processSearchByPrice(Vehicle[] vehicles, int countVehicles) {
        double minPrice = Console.promptForDouble("Enter minimum price: $"); //Max?
        double maxPrice = Console.promptForDouble("Enter maximum price: $"); //Min?
        for (int i = 0; i < countVehicles; i++) {
            double price = vehicles[i].getPrice();

            if (price > minPrice && price < maxPrice) { // Max and Min  do match with prices?
                printVehicleInfo(vehicles[i]);
            }
        }
    }

    private static void processSearchByColor(Vehicle[] vehicles, int countVehicles) {
        String search = Console.promptForString("Enter Color: ");
        for (int i = 0; i < countVehicles; i++){
            String color = vehicles[i].getColor();

            if(color.toLowerCase().contains(search.toLowerCase())){
                printVehicleInfo(vehicles[i]);
            }

        }
    }

    private static int processAddVehicle(Vehicle[] vehicles, int countVehicles) {

        if(countVehicles >= vehicles.length){
            System.out.println("Inventory is full.");
            return countVehicles;
        }
        //Asking
        long vehicleID = Console.promptForLong("Whar is the Id? ");
        String makeModel = Console.promptForString("What is the make Model? ");
        String color = Console.promptForString("What is the Color? ");
        int odometerReading = Console.promptForInt("What is the odometer reading? ");
        double price = Console.promptForDouble("What is the Price? $");

        Vehicle vehicle = new Vehicle(vehicleID,makeModel,color,odometerReading,price);
        vehicles[countVehicles] = vehicle;
        countVehicles++;

        return countVehicles;
    }

    public static void printVehicleInfo (Vehicle vehicle){
        System.out.printf("%d| %s | %s | %d | $%.2f%n",
        vehicle.getVehicleId(),
        vehicle.getMakeModel(),
        vehicle.getColor(),
        vehicle.getOdometerReading(),
        vehicle.getPrice());
    }


}
