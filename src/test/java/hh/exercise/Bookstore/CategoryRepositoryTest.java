package hh.exercise.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.exercise.Bookstore.domain.Category;
import hh.exercise.Bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void createNewCategory() {
		Category cat = new Category("TIKO");
		repository.save(cat);
		assertThat(cat.getCategoryid()).isNotNull();
	}
	
	@Test
	public void findByIdShouldReturnCategory() {
		Category cat = new Category("TIKO");
		repository.save(cat);
		assertThat(repository.findById(cat.getCategoryid())).isNotNull();
	}
	
	@Test
	public void saveAndDeleteCategoryById() {
		Category cat = new Category("TIKO");
		repository.save(cat);
		repository.deleteById(cat.getCategoryid());
		assertThat(repository.findById(cat.getCategoryid())).isEmpty();
	}
}
