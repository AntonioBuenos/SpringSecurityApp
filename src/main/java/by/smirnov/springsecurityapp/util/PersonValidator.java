package by.smirnov.springsecurityapp.util;

import by.smirnov.springsecurityapp.models.Person;
import by.smirnov.springsecurityapp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        try {
            personDetailsService.loadUserByUsername(person.getUsername());
        }catch (UsernameNotFoundException ignored){
            return; // если пользователь не найден - все ок. Это плохой код (с опорой на исключение).
            // Нужен свой сервис с Optional, а здесь - проверка есть ли человек в Optional.
        }

        errors.rejectValue("username", "",
                "Человек с таким именем уже существует, выберите другой логин");
    }


}
