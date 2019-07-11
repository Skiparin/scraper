package scrapers;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import interfaces.RecipeInterface;
import interfaces.Scraper;
import objects.Recipe;

public class AllRecipes implements Scraper{
	WebDriver browser;
	
	
	public AllRecipes(){
		this.browser = new ChromeDriver();
	}
	
	public RecipeInterface scrape(String link){
		browser.get(link);

        WebElement WEName = browser.findElement(By.xpath("//*[@id=\"recipe-main-content\"]"));
        String name = WEName.getText();
        
        List<WebElement> WEList = browser.findElements(By.xpath("//*[@id=\"main-content\"]/div/section/section/div/div/ol/li"));
        List<String> desc = (List<String>) WEList.stream()
				                                 .map(x -> ((WebElement) x).getText())
				                                 .collect(Collectors.toList());
        
        WEList = browser.findElements(By.xpath("//*[@id=\"lst_ingredients_1\"]/li"));
        WEList.addAll(browser.findElements(By.xpath("//*[@id=\"lst_ingredients_2\"]/li")));
        
        Predicate<WebElement> p = x -> !((WebElement) x).getText().equalsIgnoreCase("Add all ingredients to list");
        List<String> ingredients = (List<String>) WEList.stream()
        										 .filter(p)
        										 .map(x -> ((WebElement) x).getText())
        										 .collect(Collectors.toList());
        
        browser.quit();
        
        RecipeInterface r = new Recipe(name, link, desc, ingredients);
        return r;
	}
}
