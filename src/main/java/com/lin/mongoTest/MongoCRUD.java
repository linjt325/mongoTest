package com.lin.mongoTest;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.UpdateOptions;

public class MongoCRUD {
	
	/**
	 * 
	 * @param mc
	 * @param docs
	 * @throws Exception
	 */
	public void insertMany(MongoCollection<Document> mc,List<Document> docs) throws Exception{
		
		mc.insertMany(docs);		
		
	}
	
	
    //检索所有文档  
    /** 
    * 1. 获取迭代器FindIterable<Document> 
    * 2. 获取游标MongoCursor<Document> 
    * 3. 通过游标遍历检索出的文档集合 
    * */  
	public  void find(MongoCollection<Document> mc,Bson filter){
		FindIterable<Document> findIterable;
		if(filter!=null){
			findIterable=mc.find(filter);
		}else{
			findIterable=mc.find();
		}
		
		MongoCursor<Document> cursor=findIterable.iterator();
		
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
	}
	
	
	public void update(MongoCollection<Document> mc,Bson filter,Bson update){
		
		
		mc.updateMany(filter, update, new UpdateOptions().upsert(true));
	}
	
	public void delete(MongoCollection<Document> mc,Bson filter,boolean deleteMany){
		
		if(deleteMany){
			mc.deleteMany(filter);
		}else{
			mc.deleteOne(filter);
		}
	}

}
