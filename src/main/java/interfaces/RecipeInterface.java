package interfaces;

import java.util.List;

public interface RecipeInterface {
	public String getName();


	public void setName(String name);


	public List<String> getDesc();


	public void setDesc(List<String> desc);


	public List<String> getIngredients();


	public void setIngredients(List<String> ingredients);
	
	public String getLink();
}
