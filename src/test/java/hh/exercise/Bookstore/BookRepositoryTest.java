package hh.exercise.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.exercise.Bookstore.domain.Book;
import hh.exercise.Bookstore.domain.BookRepository;
import hh.exercise.Bookstore.domain.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookRepositoryTest {
	@Autowired
	private BookRepository repository;
	
	@Test
	public void createNewBook() {
		Book book = new Book("Book", "Tuomas Valkamo", "2019", "1920-1919", 29.99, new Category("TEST"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void findByIdShouldReturnBook() {
		Book book = new Book("Book", "Tuomas Valkamo", "2019", "1920-1919", 29.99, new Category("TEST"));
		repository.save(book);
		assertThat(repository.findById(book.getId())).isNotNull();
	}
	
	@Test
	public void saveAndDeleteBookById() {
		Book book = new Book("Book", "Tuomas Valkamo", "2019", "1920-1919", 29.99, new Category("TEST"));
		repository.save(book);
		repository.deleteById(book.getId());
		assertThat(repository.findById(book.getId())).isEmpty();
	}
}
