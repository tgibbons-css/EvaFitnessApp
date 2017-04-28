package cis3334.fitnessapp;

/**
 * Created by evangelistachicheko on 4/27/17.
 */
import java.io.Serializable;


public class User implements Serializable {
    private String key;
    private String name;
    private String date;
    private String age;
    private String height;
    private String bloodPressure;
    private String heartRate;
    private String weight;

    public User() {
    }

    public User(String key, String name, String date, String age, String height, String bloodPressure, String heartRate, String weight)  {
        this.key = key;
        this.name = name;
        this.date = date;
        this.age = age;
        this.height = height;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.weight = weight;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getWeight(){
        return weight;
    }

    public void  setWeight(String weight){
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", blood pressure='" + bloodPressure + '\'' +
                ", heart rate='" + heartRate + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
