package com.iamzken.test.lucene;
import java.io.File;
import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.document.Field;
public class IndexerFile {
 public static int indexFile(String indexDir,Photo[] list) throws IOException{
  IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
     conf.setOpenMode(OpenMode.CREATE);
     IndexWriter writer = new IndexWriter(FSDirectory.open(new File(indexDir)), conf);
 
  for(int i=0;i<list.length;i++){
   Document doc=new Document();
   doc.add(new Field("photoId", String.valueOf(list[i].getPhotoId()), Field.Store.YES, Field.Index.NO));
   if(list[i].getTitle()!=null && list[i].getTitle().length()>0)
    doc.add(new Field("title", list[i].getTitle(), Field.Store.YES, Field.Index.ANALYZED));
   if(list[i].getDescription()!=null && list[i].getDescription().length()>0)
    doc.add(new Field("description", list[i].getDescription(), Field.Store.YES, Field.Index.ANALYZED));
   if(list[i].getUserName()!= null && list[i].getUserName().length()>0)
   doc.add(new Field("userName", list[i].getUserName(), Field.Store.YES, Field.Index.ANALYZED));
   if(list[i].getTag()!= null && list[i].getTag().length()>0)
    doc.add(new Field("tag", list[i].getTag(), Field.Store.YES, Field.Index.ANALYZED));
   writer.addDocument(doc);
  }
 
  int numIndexed = writer.maxDoc();
  writer.forceMerge(1);
  writer.close();
  return numIndexed;
 }
}