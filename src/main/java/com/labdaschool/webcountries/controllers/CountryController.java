package com.labdaschool.webcountries.controllers;

import com.labdaschool.webcountries.models.Country;
import com.labdaschool.webcountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController
{

    private List<Country> findCountries(List<Country> myList, CheckCountry tester)
    {
        List<Country>  tempList = new ArrayList<>();

        for (Country c : myList)
        {
            if(tester.test(c))
            {
                tempList.add(c);
            }
        }

        return tempList;
    }
    @Autowired
    CountryRepository countryrepos;

    // http://localhost:2019/names/all
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCountries()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining((myList::add));

        myList.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/names/start/{letter}
    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> listCountryName(@PathVariable char letter)
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining((myList::add));

        List<Country> rtnList = findCountries(myList, e -> e.getName().charAt(0) == letter);

        for(Country c : rtnList)
        {
            System.out.println(c);
        }
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
}
