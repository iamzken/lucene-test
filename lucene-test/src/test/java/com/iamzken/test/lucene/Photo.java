package com.iamzken.test.lucene;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Photo {
 private long photoId;
 private String title;
 private String description;
 private String userName;
 private String tag;
 public String getDescription() {
  return description;
 }
 public void setDescription(String description) {
  this.description = description;
 }
 public long getPhotoId() {
  return photoId;
 }
 public void setPhotoId(long photoId) {
  this.photoId = photoId;
 }
 public String getTag() {
  return tag;
 }
 public void setTag(String tag) {
  this.tag = tag;
 }
 public String getTitle() {
  return title;
 }
 public void setTitle(String title) {
  this.title = title;
 }
 public String getUserName() {
  return userName;
 }
 public void setUserName(String userName) {
  this.userName = userName;
 }
 public static Photo[] loadPhotos(Connection con) throws Exception {
  ArrayList<Photo> list = new ArrayList<Photo>();
  PreparedStatement pstm = null;
  ResultSet rs = null;
  String sql = "select photo_id,title,descr,user_name,tag_name from photo";
  try {
   pstm = con.prepareStatement(sql);
   rs = pstm.executeQuery();
   while (rs.next()) {
    Photo photo = new Photo();
    photo.setPhotoId(rs.getLong(1));
    photo.setTitle(rs.getString(2));
    photo.setDescription(rs.getString(3));
    photo.setUserName(rs.getString(4));
    photo.setTag(rs.getString(5));
    list.add(photo);
   }
  } catch (SQLException e) {
   e.printStackTrace();
  } finally {
   if (rs != null) {
    rs.close();
   }
   if (pstm != null) {
    pstm.close();
   }
  }
  return (Photo[]) list.toArray(new Photo[list.size()]);
 }
}