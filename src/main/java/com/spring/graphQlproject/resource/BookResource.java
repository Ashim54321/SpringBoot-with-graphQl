package com.spring.graphQlproject.resource;

import com.spring.graphQlproject.BookRepository;
import com.spring.graphQlproject.entities.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookResource {

    private final BookRepository bookRepository;

    @MutationMapping("addBook")
    Book addNewBook(@Argument BookInput book){
        Book book1 = Book.builder()
                .title(book.getTitle())
                .price(book.getPrice())
                .pages(book.getPages())
                .author(book.getAuthor())
                .desc(book.getDesc())
                .build();
        return bookRepository.save(book1);
    }
    @QueryMapping("getAllBook")
    List<Book> getAllBooks(){
        return bookRepository.findAll();
    }


    @QueryMapping("getBookById")
    Book getBookById(@Argument Long id){
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("not found"));
    }

}

@Getter
@Setter
class BookInput{
    private String title;
    private String desc;
    private String author;
    private Double price;
    private Integer pages;
}
