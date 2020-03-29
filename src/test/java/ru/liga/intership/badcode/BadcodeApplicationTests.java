package ru.liga.intership.badcode;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.liga.intership.badcode.domain.Person;
import ru.liga.intership.badcode.service.PersonService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadcodeApplicationTests {
	private static PersonService personService;

	@BeforeClass
	public static void beforeClass() {
		personService = new PersonService();
	}

	@Test
	public void testPersonServiceBMI() {
		assertEquals(25.774209960992707, personService.getAdultMaleUsersAverageBMI(), 0.01f);
	}

	@Test
	public void testPersonBMI() {
		Person person = new Person(Long.valueOf(1), "male", "Vasya", Long.valueOf(80), Long.valueOf(190), Long.valueOf(25));
		long weight = 190;
		double heightInMeters = 25 * 100d;
		double bmi = weight / (double) (heightInMeters * heightInMeters);
		assertEquals(bmi, person.getBmi(), 0.01f);
	}

	@Test
	public void testCountAdultWoman(){
		assertEquals(2, personService.getPersonList("SELECT * FROM person WHERE sex = 'female' AND age >= 18").size());
		}

}
