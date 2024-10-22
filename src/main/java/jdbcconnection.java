import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class jdbcconnection {

    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/Qadbt?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "Lucky@123";
        
        WebDriver driver = null;
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM userdetail WHERE userid=1")) {
            
            // Initialize WebDriver using WebDriverManager
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            if (rs.next()) {
                String username = rs.getString("username");
                String pass = rs.getString("password");

                // Navigate to the login page
                driver.get("https://rahulshettyacademy.com/client/");
                
                // Wait for elements and perform login actions
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userEmail")))
                    .sendKeys(username);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userPassword")))
                    .sendKeys(pass);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("login")))
                    .click();

                // Add a wait for page load
                Thread.sleep(2000); // Consider replacing with explicit wait for specific element

                // Verify the page title
                String pageTitle = driver.getTitle();
                String expectedTitle = "Let's Shop"; // Update this with the correct title

                if (pageTitle.equals(expectedTitle)) {
                    System.out.println("Login successful, page title verified: " + pageTitle);
                } else {
                    System.out.println("Login failed, expected title: " + expectedTitle + ", but found: " + pageTitle);
                }

                // Log success but don't print sensitive data
                System.out.println("Successfully retrieved and used database credentials");
            } else {
                System.out.println("No user found with userid=1");
            }

            System.out.println("Database connection successful!");

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread sleep interrupted: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Selenium Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Always close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}