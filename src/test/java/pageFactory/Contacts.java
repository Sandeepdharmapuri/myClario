package pageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import preRequisites.ExcelReader;

public class Contacts 
{
	private WebDriver driver; 
	 private WebDriverWait wait;
	
	public Contacts(WebDriver driver) 
 	{
 		this.driver = driver;
 	    PageFactory.initElements(driver, this);
 	    // initialize wait after driver is set to avoid NPE
 	    this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
 	   
 	}
 	 
 	 
 	 @FindBy(xpath = "//a[@href='/contacts']")
 	 WebElement Contacts_Link;
 	 
 	 @FindBy(xpath = "//button[text()='Add Contact']")
 	 WebElement AddContact_Button;
 	 
 	 @FindBy(xpath = "//input[@name='first_name']")
 	 WebElement FirstName_Field;
 	 
 	@FindBy(xpath = "//input[@placeholder='Doe']")
 	 WebElement LastName_Field;
 	
 	@FindBy(xpath = "//input[@name='email']") 
 	 WebElement Email_Field;
 	 
 	@FindBy(xpath = "//div[@role='button']")
 	 WebElement CountryFlag_Dropdown;
 	
 	@FindBy(xpath = "//input[@class='search-box']")
 	 WebElement Country_SearchBox;
 	
 	@FindBy(xpath = "//input[@class='form-control w-full']")
 	 WebElement PhoneNumber_Field;
  

	@FindBy(xpath = "//span[@class='country-name']")
	private List<WebElement> country_Name;
	
	@FindBy(xpath = "(//button[starts-with(@aria-controls,'radix')][@role='combobox'])[1]")
	private WebElement Country_Dropdown;
	
	@FindBy(xpath = "//input[@placeholder='Search...']")
	private WebElement Country_Searchbox;
	
	@FindBy(xpath = "//div[@tabindex='-1']")
	private List<WebElement> Country_Dropdown_options;
	
	@FindBy(xpath = "//div[@class='px-4']")
	WebElement AddContact_Form_scroll;
	
	@FindBy(xpath = "(//button[starts-with(@aria-controls,'radix')][@role='combobox'])[2]")
	private WebElement State_Dropdown;
	
	@FindBy(xpath = "//input[@placeholder='Search...']")
	private WebElement State_Searchbox;
	
	@FindBy(xpath = "//div[starts-with(@aria-labelledby,'radix-')]")
	private List<WebElement> State_Dropdown_Options;
	
	@FindBy(xpath = "(//button[starts-with(@aria-controls,'radix')][@role='combobox'])[3]")
	private WebElement City_Dropdown;
	
	@FindBy(xpath = "//input[@placeholder='Search...']")
	private WebElement City_Searchbox;
	
	@FindBy(xpath = "//div[starts-with(@aria-labelledby,'radix-')]")
	private List<WebElement> City_Dropdown_Options;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement CreateContact_Button;
	
	@FindBy(xpath = "//div[@class='grid gap-1']/div[contains(text(),'Contact Created')]")
	WebElement ContactCreated_ToastMessage;
	
	@FindBy(xpath = "//span[@title='Sandy Kumar']")
	WebElement Created_ContactName_in_ContactsList;
	
	@FindBy(xpath = "//div[@class='flex items-center gap-2 w-full']/span [@title='Status: Duplicate - Needs Review']")
	WebElement Duplicate_Contact_Status_in_ContactsList;

   public void Enter_FirstName(String FirstName) 
 	 {
 	   wait.until(ExpectedConditions.visibilityOf(FirstName_Field));
 	   FirstName_Field.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Clear existing text and enter new value
 	   FirstName_Field.sendKeys(FirstName);
 	 }
 	
 	public void Enter_LastName(String LastName) throws Throwable 
 	 {
 	   wait.until(ExpectedConditions.visibilityOf(LastName_Field));
 	   LastName_Field.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Clear existing text and enter new value
 	  //Thread.sleep(5000);
 	   LastName_Field.sendKeys(LastName);
 	   
 	 }
 	
 	public void Enter_Email(String Email) 
 	 {
 	   wait.until(ExpectedConditions.visibilityOf(Email_Field));
 	   Email_Field.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Clear existing text and enter new value
 	   Email_Field.sendKeys(Email);
 	 }
 	
 	public void Enter_PhoneNumber(String PhoneNumber) 
 	 {
 	   wait.until(ExpectedConditions.visibilityOf(PhoneNumber_Field));
 	   PhoneNumber_Field.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Clear existing text and enter new value
 	   PhoneNumber_Field.sendKeys(PhoneNumber);
 	 }
 	
 	public void Enter_Country(String country) 
 	 {
 	   wait.until(ExpectedConditions.visibilityOf(Country_SearchBox));
 	   Country_SearchBox.sendKeys(country);
 	 }
 	
 	 public void Click_Contacts_Link() 
 	 {
 	   Contacts_Link.click();
 	 }
 	 
 	 public void Click_AddContact_Button()  
 	 {
 	   AddContact_Button.click();
 	 }
 	 
// 	 public void EnterUserDetails(DataTable dataTable) {  //DataTable logic
//         List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//         if (rows.isEmpty()) {
//             throw new IllegalArgumentException("Credentials table is empty.");
//         }
//         Map<String, String> row = rows.get(0);
//
//         String firstname = row.getOrDefault("FirstName", "").trim();
//         String lastname = row.getOrDefault("LastName", "").trim();
//         String email = row.getOrDefault("Email", "").trim();
//
//        	Enter_FirstName(firstname);
//       	Enter_LastName(lastname);
//       	Enter_Email(email);
//         }

//   public void EnterCountry(DataTable dataTable) {
//        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//        if (rows.isEmpty()) {
//            throw new IllegalArgumentException("CountryField table is empty.");
//        }
//        Map<String, String> row = rows.get(0);
//
//        String Cty = row.getOrDefault("Country", "").trim();
//        
//        	  Enter_Country(Cty);
//    
//		}
 
public void EnteruserDetails() throws Throwable {
	

	ExcelReader ER = new ExcelReader("excel.filepath2");
    List<Map<String, String>> data = ER.getSheetDataAsMap("Add_contact");

    if (data.isEmpty()) {
        System.out.println("No data found in the 'Add_contact' sheet.");
        return;
    }

    for (Map<String, String> rowData : data) {

        System.out.println("Available keys in rowData: " + rowData.keySet());

        String FirstName = rowData.getOrDefault("firstName", "").trim();
        String LastName = rowData.getOrDefault("lastName", "").trim();
        String Email = rowData.getOrDefault("email", "").trim();
        String PhoneNumber = rowData.getOrDefault("phoneNumber", "").trim();
        String Country = rowData.getOrDefault("Country", "").trim();
        String State = rowData.getOrDefault("State", "").trim();
        String City = rowData.getOrDefault("City", "").trim();

//        FirstName_Field.sendKeys(FirstName);
//        LastName_Field.sendKeys(LastName);
//        Email_Field.sendKeys(Email);
//        PhoneNumber_Field.sendKeys(PhoneNumber);
        
        Enter_FirstName(FirstName);
	   	Enter_LastName(LastName);
	   	Enter_Email(Email);
	   	Enter_PhoneNumber(PhoneNumber);

     // Bring label into view
     ((JavascriptExecutor) driver).executeScript(
         "arguments[0].scrollIntoView({block: 'start'});", Country_Dropdown);

     // Bump a bit lower to avoid sticky header overlap
     ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 150);");

        
        // Scroll to make dropdowns visible
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", AddContact_Form_scroll);
     	Select_Country(Country);
		Select_Country_FromDropdown(Country);
		Select_State_FromDropdown(State);
		Select_City_FromDropdown(City);
		Thread.sleep(5000);
		
		click_CreateContact_Button();
		Thread.sleep(5000);
		Click_AddContact_Button();
    }
}
    
  	 
 	public void Select_Country(String Country) {

 	    CountryFlag_Dropdown.click();

 	    wait.until(ExpectedConditions.visibilityOf(Country_SearchBox));
 	    Country_SearchBox.clear();
 	    Country_SearchBox.sendKeys(Country);

 	    wait.until(ExpectedConditions.visibilityOfAllElements(country_Name));

 	    country_Name.stream()
 	        .filter(e -> e.getText() != null &&
 	                     e.getText().trim().equalsIgnoreCase(Country))
 	        .findFirst()
 	        .orElseThrow(() -> 
 	            new RuntimeException("Country not found: " + Country))
 	        .click();
 	}

 	 public void Select_Country_FromDropdown(String country) throws Throwable 
 	 {
 		 

  	    // Wait and click
  	    wait.until(ExpectedConditions.elementToBeClickable(Country_Dropdown));
  	   Country_Dropdown.click();
 	   

 	    wait.until(ExpectedConditions.visibilityOf(Country_Searchbox));
 	    Country_Searchbox.clear();
 	    Country_Searchbox.sendKeys(country);
 	    
 	   // Thread.sleep(5000); // Adding a brief pause to allow options to load
 	    
 	  

 	    wait.until(ExpectedConditions.visibilityOfAllElements(Country_Dropdown_options));

	    Country_Dropdown_options.stream()
 	        .filter(e -> e.getText() != null &&
 	                     e.getText().trim().equalsIgnoreCase(country))
 	        .findFirst()
 	        .orElseThrow(() -> 
 	            new RuntimeException("Country not found in dropdown: " + country))
 	        .click();
	    
	    Thread.sleep(2000);
 	 } 	 
 	 
 	 public void Select_State_FromDropdown(String state) throws Throwable 
 	 {
 		 

  	    // Wait and click
  	    wait.until(ExpectedConditions.elementToBeClickable(State_Dropdown));
  	   State_Dropdown.click();
 	   

 	    wait.until(ExpectedConditions.visibilityOf(State_Searchbox));
 	    State_Searchbox.clear();
 	    State_Searchbox.sendKeys(state);
 	    
 	   // Thread.sleep(5000); // Adding a brief pause to allow options to load
 	    
 	  

 	    wait.until(ExpectedConditions.visibilityOfAllElements(State_Dropdown_Options));

	    State_Dropdown_Options.stream()
 	        .filter(e -> e.getText() != null &&
 	                     e.getText().trim().equalsIgnoreCase(state))
 	        .findFirst()
 	        .orElseThrow(() -> 
 	            new RuntimeException("State not found in dropdown: " + state))
 	        .click();
	    
	    //Thread.sleep(2000);
 	 }
 	 
 	 public void Select_City_FromDropdown(String city) throws Throwable 
 	 {
 		 

  	    // Wait and click
  	    wait.until(ExpectedConditions.elementToBeClickable(City_Dropdown));
  	   City_Dropdown.click();
 	   

 	    wait.until(ExpectedConditions.visibilityOf(City_Searchbox));
 	    City_Searchbox.clear();
 	    City_Searchbox.sendKeys(city);
 	    
 	   // Thread.sleep(5000); // Adding a brief pause to allow options to load
 	    
 	  

 	    wait.until(ExpectedConditions.visibilityOfAllElements(City_Dropdown_Options));

	    City_Dropdown_Options.stream()
 	        .filter(e -> e.getText() != null &&
 	                     e.getText().trim().equalsIgnoreCase(city))
 	        .findFirst()
 	        .orElseThrow(() -> 
 	            new RuntimeException("City not found in dropdown: " + city))
 	        .click();
	    
	    //Thread.sleep(9000);
 	 }
 	 
 	 public void click_CreateContact_Button() 
 	 {
 	   CreateContact_Button.click();
 	 }
 	 
 	 public String ContactCreated_ToastMessage() 
 	 {
 	   wait.until(ExpectedConditions.visibilityOf(ContactCreated_ToastMessage));
 	   return ContactCreated_ToastMessage.getText();
 	 }
 	 
 	 public String Created_ContactName_in_ContactsList() 
 	 {
 	   wait.until(ExpectedConditions.visibilityOf(Created_ContactName_in_ContactsList));
 	   return Created_ContactName_in_ContactsList.getText();
 	 }
 	 
 	

}