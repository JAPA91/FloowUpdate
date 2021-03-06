package com.java.challenge.floow;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

/**
 * @author priya
 *
 */
public class AggregatorRecords {

	private MongoCollection<Document> collection;
	private String fileName;

	/**
     * Imported data aggregator.
     * 
     * @param collection input collection
     * @param inputFileName name of the file to aggregate data
     */
    public AggregatorRecords(MongoCollection<Document> collection, String fileName) {
        this.collection = collection;
        this.fileName = fileName;
    }

	/**
	 * Aggregates data using aggregation framework (faster).
	 */
	public void aggregate(String outputCollectionName) {
		collection.aggregate(Arrays.asList(new Document("$match", new Document("source", fileName)),
				new Document("$project", new Document("words", 1).append("_id", 0)), new Document("$unwind", "$words"),
				new Document("$group",
						new Document("_id", "$words.word").append("count", new Document("$sum", "$words.cnt"))),
				new Document("$out", outputCollectionName))).toCollection();
	}

	/**
	 * Aggregates data using mapReduce (slower).
	 * 
	 */
	public void mapReduce(String outputCollectionName) {
		String mapper = "function() {"
				+ " for (var i = 0, len = this.words.length; i < len; i++) { emit(this.words[i].word, this.words[i].cnt) } }";
		String reducer = "function(key, values) { return Array.sum(values) }";
		collection.mapReduce(mapper, reducer).collectionName(outputCollectionName)
				.filter(new Document("source", fileName))
				.toCollection();
	}

}
