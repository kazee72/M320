import java.util.ArrayList;
import java.util.Scanner;

public class garageSim {

    garage garage = new garage();
    private Scanner scan;

    public garageSim() {
        this.scan = new Scanner(System.in);
        this.menuLoop();
    }

    public void menuLoop() {
        boolean running = true;

        while (running) {
            System.out.println("1. Register a car for repair");
            System.out.println("2. Complete a repair");
            System.out.println("3. Show repaired cars and costs");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");
            int choice = Integer.parseInt(this.scan.nextLine());

            if (choice == 1) {
                System.out.println("Enter the model: ");
                this.garage.registerCar(this.scan.nextLine());
            } else if (choice == 2) {
                this.completeRepair();
            } else if (choice == 3) {
                this.showRepairedCars();
            } else if (choice == 4) {
                running = false;
            }
        }
    }

    public void completeRepair() {
        System.out.println("Enter the model: ");
        String model = this.scan.nextLine();

        for (car currentCar : this.garage.getCars()) {
            if (currentCar.getModel().equalsIgnoreCase(model)) {
                System.out.println("Enter the repair cost: ");
                float cost = Float.parseFloat(this.scan.nextLine());
                currentCar.setRepairCost(cost);
                currentCar.setStatus(car.Status.DONE);
                return;
            }
        }

        System.out.println("Car not found: " + model);
    }

    public void showRepairedCars() {
        float totalCost = 0;

        for (car currentCar : this.garage.getCars()) {
            if (currentCar.getStatus() == car.Status.DONE) {
                System.out.println(currentCar.getModel() + ": " + currentCar.getRepairCost());
                totalCost = totalCost + currentCar.getRepairCost();
            }
        }

        System.out.println("Total costs: " + totalCost);
    }

    public static void main(String[] args) {
        new garageSim();
    }
}

class garage {

    private ArrayList<car> cars;
    
    public garage() {
        this.cars = new ArrayList<car>();
    }

    public ArrayList<car> getCars() {
        return this.cars;
    }

    public void addCar(car carToAdd) {
        this.cars.add(carToAdd);
    }

    public void registerCar(String model) {
        car newCar = new car(model);
        this.addCar(newCar);
    }
}

class car {

    public enum Status {
        NOT_STARTED,
        IN_REPAIR,
        DONE
    }

    private String model;
    private Status status;
    private float repairCost;

    public car(String model) {
        this.model = model;
        this.status = Status.NOT_STARTED;
        // Random repairCost for the sake of simplicity
        this.repairCost = (float)(Math.random() * 10000);
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    public void setRepairCost(float newRepairCost) {
        this.repairCost = newRepairCost;
    }

    public float getRepairCost() {
        return this.repairCost;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getModel() {
        return this.model;
    }

 }
