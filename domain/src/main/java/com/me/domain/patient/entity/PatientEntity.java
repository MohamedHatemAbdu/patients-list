package com.me.domain.patient.entity;


public class PatientEntity {

    private Long patientId;
    private String fullName;
    private String email;
    private int age;
    private char gender;

    public PatientEntity(Long patientId) {
        this.patientId = patientId;
    }

    public PatientEntity(String fullName, String email, int age, char gender) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Repo or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof PatientEntity)) {
            return false;
        }

        // typecast o to Repo so that we can compare data members
        PatientEntity c = (PatientEntity) o;

        // Compare the data members and return accordingly
        return patientId.equals(c.patientId) && fullName.compareTo(c.fullName) == 0 &&
                email.compareTo(c.email) == 0 && gender == c.gender;

    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "patientId=" + patientId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
