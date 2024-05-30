package alessiovulpinari.entities;

import java.util.Random;

public class Customer {
    private long id;
    private String name;
    private int tier;

    public Customer(String name, int tier) {
        setName(name);
        setTier(tier);
        Random random = new Random();
        this.setId(random.nextLong(0, 1000000));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
