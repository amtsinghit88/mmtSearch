# ui-automation
This is an UI uatomation projetct for MMT search which is build with the help of Java,Selenium and TestNg.
Script can be run in any of the browsers combinations(Chrome,Firefox and headless) it also inculdes reporting,capturing screenshots of failed test cases.
Additionally i have implented WebDriver Listeners to listen the some events ex. Click,Sendkeys,Redirection and etc to do some specific tasks.
Selenium version used here is 3.141.59 and testNg Version is 6.14.3

AUT URL is https://www.makemytrip.com/

Scenarios which is automated are listed below.
1.You are required to book a Hotel. Go Ahead and Select Hotel Section
2.The Hotel stay is for a Single Room and is being booked for a family of 3 with 2 Adults and 1
Child of Age 2 years. They are supposed to stay in Bengaluru for 1 day and exactly 1 week
from now. Ignore the optional fields on the website and proceed to Search.
3. Apply a cost filterforINR X to INR Y. Here X and Y are some dynamic amount inputs eg: 500,
1000
4 Select the First available Hotel that you see in the filtered list
5 Go Back to the Previous Search Window and Remove the Selected Filter
6 Select the Last Option from “Popular Filters” Section and Select the First available Hotel that
you get from the filtered list
