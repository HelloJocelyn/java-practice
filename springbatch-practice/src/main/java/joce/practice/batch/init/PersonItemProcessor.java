package joce.practice.batch.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/07/18
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    public Person process(Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
