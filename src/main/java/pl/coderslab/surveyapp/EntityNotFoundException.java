package pl.coderslab.surveyapp;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id, String className) {
        super("Could not find " + className + " with id: " + id);
    }
}
