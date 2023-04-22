package UI;

import Domain.Country;
import Service.Service;

import java.util.List;
import java.util.Scanner;

public class UI {
    private Service service;

    public UI(Service service) {
        this.service = service;
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("2. Show all countries");
            System.out.println("3. Add country");
            System.out.println("4. Find and delete the countries which have first char S");
            System.out.println("5. Show the middle country");
            System.out.println("6. Add population for each country");
            // 7. The population has already been shown.
            System.out.println("8. Swap first and last country");
            System.out.println("9. Split array by properties");
            System.out.println("10. Left from initial list");
            System.out.println("x. Exit");

            String input = scanner.nextLine();

            switch (input) {
                case "2"-> uiShowAllCountries();
                case "3" -> {addCountry();
                            uiShowAllCountries();}
                case "4" -> {service.findAndDeleteS();
                            uiShowAllCountries();}
                case "5" -> {
                    System.out.println("The country in the middle is: ");
                    uiShowAllCountries(this.service.getTheMiddleCountry());
                }
                case "6" -> {uiAddPopulationForEachCountry();
                            uiShowAllCountries();}
                case "8" -> {service.switchFirstWithLastCountryInRepository();
                            uiShowAllCountries();}
                case "9" -> service.splitCountriesByProperties();
                case "10" -> service.leftFromInitialList();
                case "x" -> {return;}
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void uiShowAllCountries(){
        List<Country> countryList = service.getAllCountries();
        for (Country country:countryList){
            System.out.println(country.toString());
        }
    }

    private void uiShowAllCountries(List<Country> countryList){
        for (Country country:countryList){
            System.out.println(country.toString());
        }
    }
    private void addCountry() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert the country name: ");
            String countryName = scanner.nextLine();
            System.out.println("Insert the country population: ");
            Long countryPopulation = Long.valueOf(scanner.nextLine());

            // Validate population value
            if (countryPopulation <= 0) {
                throw new IllegalArgumentException("Invalid population value. Population must be greater than 0.");
            }

            Country country = new Country(countryName, Math.toIntExact(countryPopulation));
            service.addCountry(country);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uiAddPopulationForEachCountry(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give a number to add at country population: ");
        long countryPopulaitonToAdd = Long.parseLong(scanner.nextLine());

        service.addPopulationForEachCountry(countryPopulaitonToAdd);
    }

}
