package com.iamzken.test.lucene;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.document.Field;
public class TestQueryFile {
 
      public static void main(String[] args) throws Exception {
        indexF(); 
        String queryString = "sessionCookieName";
           Query query = null;
        IndexReader reader = IndexReader.open(FSDirectory.open(new File("E:/TestLucene/fileIndex")),true);//read-only
       IndexSearcher searcher = new IndexSearcher(reader);
           String fields = "body";
           try {
            QueryParser qp = new QueryParser(Version.LUCENE_35, fields, new StandardAnalyzer(Version.LUCENE_35));//有变化的地方
               query = qp.parse(queryString);
           } catch (ParseException e) {
           }
           if (searcher != null) {
               TopDocs topDocs = searcher.search(query, 100);//100是显示队列的Size
               ScoreDoc[] hits = topDocs.scoreDocs;
               System.out.println("共有" + searcher.maxDoc() + "条索引，命中" + hits.length + "条");
           }
       }
 
      private static void indexF() throws Exception {
          
           File fileDir = new File("E:/TestLucene/files");
  
          
           File indexDir = new File("E:/TestLucene/fileIndex");
         
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
         conf.setOpenMode(OpenMode.CREATE);
         IndexWriter indexWriter = new IndexWriter(FSDirectory.open(indexDir), conf);
        
           File[] textFiles = fileDir.listFiles();
           long startTime = new Date().getTime();
          
           //增加document到索引去
           for (int i = 0; i < textFiles.length; i++) {
               if (textFiles[i].isFile()
                       && textFiles[i].getName().endsWith(".txt")) {
                   System.out.println("File " + textFiles[i].getCanonicalPath()
                           + "正在被索引....");
                   String temp = FileReaderAll(textFiles[i].getCanonicalPath(),
                           "GBK");
                   System.out.println(temp);
                   Document document = new Document();
                   Field FieldPath = new Field("path", textFiles[i].getPath(),
                           Field.Store.YES, Field.Index.NO);
                   Field FieldBody = new Field("body", temp, Field.Store.YES,
                           Field.Index.ANALYZED,
                           Field.TermVector.WITH_POSITIONS_OFFSETS);
                   document.add(FieldPath);
                   document.add(FieldBody);
                   indexWriter.addDocument(document);
             }
           }
           //optimize()方法是对索引进行优化
           indexWriter.forceMerge(1);
           indexWriter.close();
          
           //测试一下索引的时间
           long endTime = new Date().getTime();
           System.out
                   .println("这花费了"
                           + (endTime - startTime)
                           + " 毫秒来把文档增加到索引里面去!"
                           + fileDir.getPath());
       }
  
       private static String FileReaderAll(String FileName, String charset)
               throws IOException {
           BufferedReader reader = new BufferedReader(new InputStreamReader(
                   new FileInputStream(FileName), charset));
           String line = null;
     StringBuffer temp = new StringBuffer("");
          
           while ((line = reader.readLine()) != null) {
               temp.append(line);
           }
           reader.close();
           return temp.toString();
       }
}