package Service;

import Domain.Country;
import Repository.CountryRepository;

import java.util.*;

public class Service {
    private CountryRepository countryRepository;

    public Service(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries(){return countryRepository.getAllCountries();}

    public void addCountry(Country country){countryRepository.addCountry(country);}

    public void findAndDeleteS() {
        try {
            List<Country> countryList = countryRepository.getAllCountries();
            Iterator<Country> iterator = countryList.iterator();
            while (iterator.hasNext()) {
                Country country = iterator.next();
                if (country.getCountryName().charAt(0) == 'S') {
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Country> getTheMiddleCountry(){
        List<Country> countryList = countryRepository.getAllCountries();
        List<Country> middleCountriesList = new ArrayList<>();
        middleCountriesList.add(countryList.get(countryList.size()/2));
        if (countryList.size() %2 == 0 )
            middleCountriesList.add(countryList.get(countryList.size()/2+1));
        return middleCountriesList;
    }

    public void addPopulationForEachCountry(long numberToAddAtPopulation){
        List<Country> countryList = countryRepository.getAllCountries();
        for (Country country: countryList){
            country.setCountryPopulation(country.getCountryPopulation()+numberToAddAtPopulation);
        }
    }

    public void switchFirstWithLastCountryInRepository(){
        List<Country> countryList = countryRepository.getAllCountries();
        Country firstCountry = countryList.get(0);
        Country lastCountry = countryList.get(countryList.size()-1);
        countryList.remove(firstCountry);
        countryList.remove(lastCountry);
        countryList.add(firstCountry);
        countryList.add(0, lastCountry);
        countryRepository = new CountryRepository(countryList);
    }

    //Split the countries array in two : the first array will have countries from A to M
    // that have at least 7 characters or fewer than 2 vowels
    // . Second array will have the countries from N to Z that have the following combinations:
    // "a" and " e " pair or an "a" and an  "i" or at least 3 non-vowels
    public void splitCountriesByProperties(){
        List<Country> countryList = countryRepository.getAllCountries();
        List<Country> firstCountryList = new ArrayList<>();
        List<Country> secondCountryList = new ArrayList<>();

        for (Country country: countryList){
            String countryName = country.getCountryName();
            if (countryName.charAt(0) < 'M' && (countryName.length() > 7 || numberOfVowels(countryName) < 2))
                firstCountryList.add(country);
            else if (countryName.charAt(0) > 'N' && (countryName.length() - numberOfVowels(countryName) > 3 ||
                    secondProperty(countryName))) secondCountryList.add(country);
        }
        System.out.println("First array:");
        showAllCountries(firstCountryList);
        System.out.println("Second array");
        showAllCountries(secondCountryList);
    }

    private int numberOfVowels(String word){
        int vowelCount = 0;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i = 0; i < word.length(); i++){
            if (vowels.contains(Character.toLowerCase(word.charAt(i))))
                vowelCount++;
        }
        return vowelCount;
    }

    //pair of a and e and containts a and i
    private boolean secondProperty(String word){
        //if aiSet is empty it means that we found a i else we delete when we find them
        Set<Character> aiSet = new HashSet<>(Arrays.asList('a', 'i'));
        for (int i = 0; i < word.length()-1; i++){
            char letter = word.charAt(i);
            if (aiSet.contains(Character.toLowerCase(letter))){
                aiSet.remove(Character.toLowerCase(letter));
                if (aiSet.size() == 0) return true;
            };
            if ((letter == 'a' && word.charAt(i+1) == 'i') || (letter == 'i' && word.charAt(i+1) == 'a')) return true;
        }
        return false;
    }

    private void showAllCountries(List<Country> countryList){
        for (Country country:countryList){
            System.out.println(country.toString());
        }
    }

    public void leftFromInitialList(){
        List<Country> countryList = countryRepository.getAllCountries();
        List<Country> firstCountryList = new ArrayList<>();
        List<Country> secondCountryList = new ArrayList<>();

        for (Country country: countryList){
            String countryName = country.getCountryName();
            if (countryName.charAt(0) < 'M' && (countryName.length() > 7 || numberOfVowels(countryName) < 2))
                firstCountryList.add(country);
            else if (countryName.charAt(0) > 'N' && (countryName.length() - numberOfVowels(countryName) > 3 ||
                    secondProperty(countryName))) secondCountryList.add(country);
        }

        for (Country country: firstCountryList){
            deleteCountryFromList(countryList, country);
        }

        for (Country country: secondCountryList){
            deleteCountryFromList(countryList, country);
        }
        System.out.println("Left countries are: ");
        showAllCountries(countryList);
    }

    private void deleteCountryFromList(List<Country> countryList, Country country) {
        Iterator<Country> iterator = countryList.iterator();
        while (iterator.hasNext()) {
            Country country1 = iterator.next();
            if (country.equals(country1)) {
                iterator.remove();
            }
        }
    }
}
