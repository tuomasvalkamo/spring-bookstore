package hh.exercise.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.exercise.Bookstore.web.BookController;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(bookController).isNotNull();
    }	
}
