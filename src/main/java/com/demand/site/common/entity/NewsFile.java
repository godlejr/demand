package com.demand.site.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "NEWS_FILES")
public class NewsFile extends Base{
   @ManyToOne
   @JoinColumn(name="NEWS_ID")
   @JsonManagedReference
   private News news;
   
   @ManyToOne
   @JoinColumn(name="FILE_ID")
   @JsonManagedReference
   private File file;

   public NewsFile() {
      super();
   }

   public NewsFile(News news, File file) {
      super();
      this.news = news;
      this.file = file;
   }

   public News getNews() {
      return news;
   }

   public void setNews(News news) {
      this.news = news;
   }

   public File getFile() {
      return file;
   }

   public void setFile(File file) {
      this.file = file;
   }
   

}