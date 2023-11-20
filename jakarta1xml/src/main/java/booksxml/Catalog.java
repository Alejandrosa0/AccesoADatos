package booksxml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Catalog 
{
  @XmlElement(name = "book")
  private List<book> bookList = null;

  public List<book> getBook() {
    return bookList;
  }

  public void setBook(List<book> book) {
    this.bookList = book;
  } 
}
