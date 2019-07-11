package scrapers;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import interfaces.RecipeInterface;
import interfaces.Scraper;
import objects.Recipe;

public class Valdemarsro implements Scraper {
	WebDriver browser;
	
	
	public Valdemarsro(){
		this.browser = new ChromeDriver();
	}

	@Override
	public RecipeInterface scrape(String link) {
		browser.get(link);

        WebElement WEName = browser.findElement(By.xpath("//*[@id=\"recipe-intro\"]/div/h2"));
        String name = WEName.getText();
        
        List<WebElement> WEList = browser.findElements(By.xpath("//*[@id=\"recipe-intro\"]/div[2]/div[4]/div[2]/div[3]/p"));
        List<String> desc = (List<String>) WEList.stream()
				                                 .map(x -> ((WebElement) x).getText())
				                                 .collect(Collectors.toList());
        
        WEList = browser.findElements(By.xpath("//*[@id=\"recipe-intro\"]/div[2]/div[4]/div[1]/ul/li"));
        
        List<String> ingredients = (List<String>) WEList.stream()
        										 .map(x -> ((WebElement) x).getText())
        										 .collect(Collectors.toList());
        ingredients.forEach(x -> System.out.println(x));
        browser.quit();

        RecipeInterface r = new Recipe(name, link, desc, ingredients);
        return r;
	}
	
}
