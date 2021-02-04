package hh.exercise.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.exercise.Bookstore.domain.Book;
import hh.exercise.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Book book1 = new Book("Liike-elämän matematiikka", "Pirjo Saaranen", "2011", "12346", 24.99);
			Book book2 = new Book("Liike-elämän kirja", "Matti Saaranen", "2005", "12345", 29.99);
			Book book3 = new Book("Liike-elämän tieto", "Teppo Saaranen", "2009", "12344", 22.99);

			repository.save(book1);
			repository.save(book2);
			repository.save(book3);
		};
	}
}
