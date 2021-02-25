package edu.epam.auth.validator;

import edu.epam.auth.model.ad.Ad;

import java.math.BigDecimal;

public class AdValidator {

    public static boolean isValidAd(Ad ad){
        BigDecimal price = ad.getPrice();
        return  price.compareTo(new BigDecimal(0) )<0;
    }
}
