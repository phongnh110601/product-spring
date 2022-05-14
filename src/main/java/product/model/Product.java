package product.model;


import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Product {
	@Id
	private String code;
	private String description;
	private double price;
	
	public String getPriceCurrencyFormat() {
        Locale localeUS = new Locale("en", "US");
        NumberFormat us = NumberFormat.getCurrencyInstance(localeUS);
        return us.format(price);
    }
}
