package com.lin.mongoTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DataBase {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		DataBase dbClass=new DataBase(); 
		
		//验证
		MongoCredential  mct=MongoCredential.createCredential("lin", "admin", "123456".toCharArray());
		//mongo客户端
		MongoClient mc =new MongoClient(new ServerAddress(),Arrays.asList(mct));
		//获取数据库
		MongoDatabase mdb=mc.getDatabase("foobar");
		//创建collections
//		mdb.createCollection("test1");
		//获取指定collection
		MongoCollection<Document> mcd=mdb.getCollection("test");
		
		MongoCRUD crud=new MongoCRUD();
		try {
			
			
			//批量插入
			crud.insertMany(mcd,dbClass.createDocument());
			System.out.println("插入成功!");
			//查找 可以按照条件查找
			
//			crud.find(mcd, Filters.gt("name", "test09000"));
			
			//更新 
//			crud.update(mcd, Filters.gt("name", "test09500"),new Document("$set",new Document("age",100)));
//			crud.find(mcd,Filters.gt("name","test09500"));
			
			//删除  
//			crud.delete(mcd, Filters.gt("name","test09988"), true);
//			crud.find(mcd,Filters.gt("name","test09500"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	//批量生成document
	public List<Document > createDocument(){
		
		List<Document> list=new ArrayList<Document>();
		for(int i=0;i<10000;i++){
			Document doc=new Document("name", "test"+String.format("%05d", i));
			list.add(doc);
		}
		BasicDBObject dd=new BasicDBObject();
		
		return list;
		
	}

}
