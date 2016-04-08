package entities;

/**
 * Created by USER on 08.04.2016.
 */
public class BDUniverObject<T> {
    Class<T> clazz;

    public BDUniverObject(Class<T> clazz) {
        this.clazz = clazz;
    }

    private T newRequest() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
