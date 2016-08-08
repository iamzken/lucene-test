package com.iamzken.test.lucene;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
public class TestDb {
 public final static String indexDir ="E:/TestLucene";
 private static Connection getConnection() {
  Connection conn = null;
  String url = "jdbc:mysql://localhost:3306/test";
  String userName = "root";
  String password = "123456";
  try {
   Class.forName("com.mysql.jdbc.Driver");
   conn = java.sql.DriverManager
     .getConnection(url, userName, password);
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("Error Trace in getConnection() : "
     + e.getMessage());
  }
  return conn;
 }
 public static void main(String[] args) throws IOException, ParseException, SQLException {
  index();//做索引
  IndexSearcher searcher=null;
  try{
   IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexDir)),false); 
      searcher = new IndexSearcher(reader);
   search(searcher);//搜索
  }catch(Exception e){
   e.printStackTrace();
  }finally{
   if(searcher!=null)
   searcher.close();
  }
 }
 public static void search(IndexSearcher searcher) throws IOException, ParseException{
  //以下是搜索的关键词
  String[] q = {"美女1","美女2","好人3","好人5"};
  long start=new Date().getTime();
  SearcherFile.search(searcher,q);
  long end=new Date().getTime();
  System.out.println("花费时间："+(double)(end-start)/1000+"秒");
 }
 public static void index() throws SQLException{
  Connection conn = null;
  try {
   conn = getConnection();
   Photo[] list = Photo.loadPhotos(conn);
   IndexerFile.indexFile(indexDir,list);
  } catch (Exception e) {
   e.printStackTrace();
  } finally {
   if (conn != null) {
    conn.close();
   }
  }
 }
}