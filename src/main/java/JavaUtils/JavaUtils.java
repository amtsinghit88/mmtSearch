package JavaUtils;

public class JavaUtils {
	public int calculatePrice(String quantity, String price)
	{
		String[] priceArray = price.split(" ");
		int totalPrice = Integer.parseInt(priceArray[1])*Integer.parseInt(quantity);
		return totalPrice;
	}

	public int parseCartprice(String price){
		String[] priceArray = price.split(" ");
		int cartPrice = Integer.parseInt(priceArray[1]);
		return cartPrice;
	}
}
