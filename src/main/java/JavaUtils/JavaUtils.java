package JavaUtils;

import org.testng.Reporter;
import org.testng.SuiteRunner;

import java.lang.reflect.Array;
import java.text.DateFormatSymbols;
import java.time.LocalDate;

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

	public String[] getDayPlusMonthYear(int noOfDays) {
		LocalDate localDate = LocalDate.now().plusDays(noOfDays);
		System.out.println("Selected Check in Date : "+localDate);
		String[] date = localDate.toString().split("-");
		return  date;
	}

	public String getMonthName(int n){
		String[] months = new DateFormatSymbols().getMonths();
		String monthName= months[months.length-1];
		return monthName;
	}

	public LocalDate getFutureDate(int noOfDays){
		LocalDate localDate = LocalDate.now().plusDays(noOfDays);
		return  localDate;
	}

	public LocalDate getCurrentDay() {
		LocalDate localDate = LocalDate.now();
		return localDate;
	}

	public  String[] dateSplitter(LocalDate date)
	{
		String[] day = date.toString().split("-");
		return day;
	}

	public  int monthNumber(String monthName){
		int number = 0;
		switch (monthName)
		{
			case "January":
				System.out.println ("The number of January month is 1");
				number = 1;
				break;
			case "February":
				System.out.println ("The number of February month is 2");
				number = 2;
				break;
			case "March":
				System.out.println ("The number March month is 3");
				number = 3;
				break;
			case "April":
				System.out.println ("The number of April month is 4 ");
				number = 4;
				break;
			case "May":
				System.out.println ("The name of month number 5 is May");
				number = 5;
				break;
			case "June":
				System.out.println ("The name of month number 6 is June");
				number = 6;
				break;
			case "July":
				System.out.println ("The name of month number 7 is July");
				number = 7;
				break;
			case "August":
				System.out.println ("The name of month number 8 is August");
				number = 8;
				break;
			case "September":
				System.out.println ("The name of month number 9 is September");
				number = 9;
				break;
			case "October":
				System.out.println ("The name of month number 10 is October");
				number = 10;
				break;
			case "November":
				System.out.println ("The name of month number 11 is November");
				number = 11;
				break;
			case "December":
				System.out.println ("The name of month number 12 is December");
				number = 12;
				break;
			default:
				System.out.println ("You have entered an invalid number");
		}
		return number;
	}
}
