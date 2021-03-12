package com.labdaschool.webcountries.repositories;

import com.labdaschool.webcountries.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long>
{
}
