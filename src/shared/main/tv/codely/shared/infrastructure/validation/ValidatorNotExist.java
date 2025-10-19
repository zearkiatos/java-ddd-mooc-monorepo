package tv.codely.shared.infrastructure.validation;

public class ValidatorNotExist extends Exception {
    public ValidatorNotExist(String name) {
        super(String.format("The validator <%s> does not exist", name));
    }
}
