package objects;

import java.util.List;

import interfaces.RecipeInterface;

public class Recipe implements RecipeInterface{
	
	String name;
	String link;
	List<String> desc;
	List<String> ingredients;
	
	
	public Recipe(String link) {
		this.link = link;
	}

	public Recipe(String name, String link, List<String> desc, List<String> ingredients) {
		this.name = name;
		this.link = link;
		this.desc = desc;
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getLink() {
		return this.link;
	}


	public List<String> getDesc() {
		return desc;
	}


	public void setDesc(List<String> desc) {
		this.desc = desc;
	}


	public List<String> getIngredients() {
		return ingredients;
	}


	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
