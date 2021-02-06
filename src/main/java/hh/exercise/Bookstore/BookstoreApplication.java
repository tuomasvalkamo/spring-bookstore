package hh.exercise.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.exercise.Bookstore.domain.Book;
import hh.exercise.Bookstore.domain.BookRepository;
import hh.exercise.Bookstore.domain.Category;
import hh.exercise.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catRepository) {
		return (args) -> {
			catRepository.save(new Category("Math"));
			catRepository.save(new Category("IT"));
			catRepository.save(new Category("Cooking"));

			repository.save(new Book("Liike-elämän matematiikka", "Pirjo Saaranen", "2011", "12346", 24.99,
					catRepository.findByName("Math").get(0)));
			repository.save(new Book("Liike-elämän kirja", "Matti Saaranen", "2005", "12345", 29.99,
					catRepository.findByName("IT").get(0)));
			repository.save(new Book("Liike-elämän tieto", "Teppo Saaranen", "2009", "12344", 22.99,
					catRepository.findByName("Cooking").get(0)));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
