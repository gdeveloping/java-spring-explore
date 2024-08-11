package tech.gdev.javabasicexplore.validate;

import jakarta.validation.*;

import java.util.Set;

public class UserService {

    private final Validator validator;

    public UserService() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void createUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new RuntimeException("User validation failed:\n" + sb.toString());
        }
    }

    /**
     * Exception in thread "main" java.lang.RuntimeException: User validation failed:
     * Email cannot be null
     * Name cannot be null
     *
     * 	at tech.gdev.javabasicexplore.validate.UserService.createUser(UserService.java:23)
     * 	at tech.gdev.javabasicexplore.validate.UserService.main(UserService.java:34)
     */
    public static void test1() {
        UserService userService = new UserService();
        User user1 = new User();
        user1.setName("user1");
        User leader = new User();
        user1.setLeader(leader);
        leader.setEmail("leader@fake.com");
        userService.createUser(user1);
    }

    public static void main(String[] args) {
        test1();
    }
}
