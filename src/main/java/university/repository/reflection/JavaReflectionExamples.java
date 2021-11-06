package university.repository.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class JavaReflectionExamples {
    public static void main(String[] args) {

        Subject subject = new Subject("Програмированние");
        Object obj = subject;
        Class<?> objClass = obj.getClass();

        // 1. Через объект
        Class<?> subjClass = subject.getClass();
        // 2. чере имя класса и литерал
        Class<?> subjectClass = Subject.class;

        Field[] fields = subjectClass.getDeclaredFields();
        for ( Field field  : fields
             ) {
            System.out.println(field.getType().getName() + " " + field.getName());
        }

        System.out.println("Constructors ...");
        Constructor<?>[] constructors = subjClass.getDeclaredConstructors();

        for (Constructor<?> constructor: constructors
             ) {
            Class<?>[] types = constructor.getParameterTypes();
            System.out.println(Arrays.toString(types));
        }
    }
}
