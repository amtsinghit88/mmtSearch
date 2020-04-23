package JavaUtils;

import org.testng.Reporter;

import java.lang.reflect.Array;

public class JavaUtils {

	public float calculatePrice(String quantity, String price)
	{
		float totalPrice = parseCartprice(price)*Integer.parseInt(quantity);
		return totalPrice;
	}

	public float parseCartprice(String price){
		String[] priceArray = price.split(" ");
		Reporter.log("Cart item price is " + Array.get(priceArray, 1));
		String cartPrice = (String) Array.get(priceArray, 1);
		float itemPrice = Float.parseFloat(cartPrice.replace(",", ""));
		return itemPrice;
	}

}
