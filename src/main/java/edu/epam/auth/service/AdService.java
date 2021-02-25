package edu.epam.auth.service;

import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.ad.Ad;

import java.util.Map;
import java.util.Optional;


public interface AdService {

    Map<String, String> addAd(Ad ad) throws ServiceException;
}
