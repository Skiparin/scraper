package databaseConnectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import interfaces.DatabaseInterface;
import interfaces.RecipeInterface;

public class MongoDB implements DatabaseInterface {

	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	public MongoDB() {
		MongoClientURI uri = new MongoClientURI("mongodb://46.101.130.46:27017");

		this.mongoClient = new MongoClient(uri);
		
		this.database = mongoClient.getDatabase("recipe");
		this.collection = database.getCollection("recipe");
	}
	
	@Override
	public void saveRecipe(RecipeInterface r) {
		this.collection = database.getCollection("recipe");
		Document document = new Document();
		document.put("name", r.getName());
		document.put("link", r.getLink());
		document.put("desc", r.getDesc());
		document.put("ingredients", r.getIngredients());
		
		Document oldItem = new Document();
		oldItem.put("name", r.getName());
		oldItem = this.collection.find(oldItem).first();
		if(oldItem == null){
			this.collection.insertOne(document);
		}
		updateRequest(r.getLink());
		
	}
	
	@Override
	public List<String> findDownloadRequest() {
		this.collection = database.getCollection("request");
		Document document = new Document();
		document.put("downloaded", false);
		FindIterable<Document> requests = this.collection.find(document);
		
		List<String> links = new ArrayList<String>();

		requests.iterator().forEachRemaining(x -> links.add((String) x.get("link")));
		return links;
	}
	
	
	private void updateRequest(String link) {
		this.collection = database.getCollection("request");
		
		Document filter = new Document("link", link);
		Document newValue = new Document("downloaded", true);
		Document update = new Document("$set", newValue);
		this.collection.updateOne(filter, update);
	}
}
