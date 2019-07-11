package Web.Scraper;

import java.net.UnknownHostException;
import java.util.List;

import databaseConnectors.MongoDB;
import interfaces.DatabaseInterface;
import interfaces.RecipeInterface;
import interfaces.Scraper;
import scrapers.Valdemarsro;




/**
 * Hello world!
 *
 */
public class App {
	static String host, dbname, user, password;
    public static void main( String[] args ) throws InterruptedException, UnknownHostException {
    	System.setProperty("webdriver.chrome.driver","C:\\Users\\Guttesen\\Documents\\Java\\chromedriver_win32\\chromedriver.exe");
    	
    	DatabaseInterface db = new MongoDB();
    	
    	while(true) {
    		List<String> links = db.findDownloadRequest();
    		for(String link : links) {
    			RecipeInterface recipe = scrapeLinks(link);
    			db.saveRecipe(recipe);
    		}
    		Thread.sleep(5000);
    	}
    	
    	//Scraper s = new Valdemarsro();
    	//RecipeInterface r = s.scrape(link);
    	
    	
    	//db.saveRecipe(r);
    	
    }
    
    private static RecipeInterface scrapeLinks(String link) {
    	Scraper scraper = null;
    	RecipeInterface recipe = null;
    	if(link.toLowerCase().contains("valdemarsro")) {
    		scraper = new Valdemarsro();
    	}
    	
    	if(link.toLowerCase().contains("allrecipes")) {
    		scraper = new Valdemarsro();
    	}
    	
    	if(scraper != null) {
    		recipe = scraper.scrape(link);
    		return recipe;
    	}
    	return null;
    	
    	
    }
}
