package cn.edu.hdu.clan;

public class GenBen {
    String name;
    String beanName;

    public GenBen(String name, String beanName) {
        this.name = name;
        this.beanName = beanName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
