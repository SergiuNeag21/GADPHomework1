package Repository;

import Domain.Country;

import java.util.List;

public class CountryRepository {
    private List<Country> countryList;

    public CountryRepository(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<Country> getAllCountries(){return countryList;}

    public void addCountry(Country country){countryList.add(country);};

    public void deleteCountry(Country country){countryList.remove(country);}
}
