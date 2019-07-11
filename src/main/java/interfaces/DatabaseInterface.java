package interfaces;

import java.util.List;


public interface DatabaseInterface {
	public void saveRecipe(RecipeInterface r);
	public List<String> findDownloadRequest();
}
