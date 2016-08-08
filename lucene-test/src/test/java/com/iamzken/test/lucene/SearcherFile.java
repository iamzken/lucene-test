package com.iamzken.test.lucene;
import java.io.IOException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
public class SearcherFile {
 public static void search(IndexSearcher searcher, String[] q) throws IOException, ParseException {
  Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
  String[] fields = {"title","description","tag","userName"};    
        Query query = MultiFieldQueryParser.parse(Version.LUCENE_35, q, fields, analyzer);
        TopDocs topDocs = searcher.search(query, 100);//100是显示队列的Size
         ScoreDoc[] hits = topDocs.scoreDocs;
         System.out.println("共有" + searcher.maxDoc() + "条索引，命中" + hits.length + "条");
         for (int i = 0; i < hits.length; i++) {
             int DocId = hits[i].doc;
             Document document = searcher.doc(DocId);
             System.out.println("photoId==="+document.get("photoId"));
         }
 }
}