import java.util.*;
import java.util.function.Predicate;


public class Main {
    static final int AGE_OF_MAJORITY = 18;
    static final int AGE_OF_CONSCRIPTS = 27;
    static final int AGE_OF_ABLE_BODIED_WOMEN = 60;
    static final int AGE_OF_ABLE_BODIED_MEN = 65;


    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Поиск несовершеннолетних
        long minors = persons.stream()
                .filter(person -> person.getAge() < AGE_OF_MAJORITY)
                .count();
        System.out.printf("Количество несовершеннолетних: %s \n", minors);

        //Поиск призывников
        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))    
                .filter(person -> person.getAge() > AGE_OF_MAJORITY)
                .filter(person -> person.getAge() < AGE_OF_CONSCRIPTS)
                .map(Person::getSurname)
                .toList();

        System.out.println("Призывники:");
        System.out.println(conscripts);


        //Поиск работающих
        List<String> workables = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getSex().equals(Sex.MAN) & person.getAge() < AGE_OF_ABLE_BODIED_MEN ||
                        person.getSex().equals(Sex.WOMAN) & person.getAge() < AGE_OF_ABLE_BODIED_WOMEN)
                .map(Person::getSurname)
                .sorted()
                .toList();

        System.out.println("Трудоспособного возраста:");
        System.out.println(workables);

    }
}
