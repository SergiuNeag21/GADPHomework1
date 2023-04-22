import Domain.Country;
import Repository.CountryRepository;
import Service.Service;
import UI.UI;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Country> countryList = new ArrayList<>();
        try {
            CountryRepository countryRepository = new CountryRepository(countryList);
            Country country1 = new Country("Romania", 3000);
            Country country2 = new Country("Spain", 200000);
            Country country3 = new Country("USA", 30200);
            Country country4 = new Country("Kenya", 46000000);
            Country country5 = new Country("Senya", 46000000);
            countryList.add(country1);
            countryList.add(country2);
            countryList.add(country3);
            countryList.add(country4);
            countryList.add(country5);


            Service service = new Service(countryRepository);
            UI ui = new UI(service);
            ui.runMenu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}