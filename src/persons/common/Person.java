package src.persons.common;

import java.io.Serializable;
import java.time.LocalDate;

import src.persons.enums.Genrer;

public abstract class Person implements Serializable {
    private String name;
    private String cpf;
    private LocalDate birthdate;
    private Genrer genrer;
    private Address address;
    private Long registration;
    protected Double wage;
    private String department;
    private Integer workload;
    private LocalDate joinedAt;

    // [TODO] Remove it
    public Person() {}

    public Person(
        String name, 
        String cpf, 
        LocalDate birthdate, 
        Genrer genrer, 
        Address address, 
        Long registration,
        Double wage, 
        String department, 
        Integer workload, 
        LocalDate joinedAt
    ) {
        this.name = name;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.genrer = genrer;
        this.address = address;
        this.registration = registration;
        this.wage = wage;
        this.department = department;
        this.workload = workload;
        this.joinedAt = joinedAt;
    };

    public String getName() {
        return name;
    };

    public void setName(String name) {
        this.name = name;
    };

    public String getCpf() {
        return cpf;
    };

    public void setCpf(String cpf) {
        this.cpf = cpf;
    };

    public LocalDate getBirthdate() {
        return birthdate;
    };

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    };

    public Genrer getGenrer() {
        return genrer;
    };

    public void setGenrer(Genrer genrer) {
        this.genrer = genrer;
    };

    public Address getAddress() {
        return address;
    };

    public void setAddress(Address address) {
        this.address = address;
    };

    public Long getRegistration() {
        return registration;
    };

    public void setRegistration(Long registration) {
        this.registration = registration;
    };

    public abstract Double getWage();

    public void setWage(Double wage) {
        this.wage = wage;
    };

    public String getDepartment() {
        return department;
    };

    public void setDepartment(String department) {
        this.department = department;
    };

    public Integer getWorkload() {
        return workload;
    };

    public void setWorkload(Integer workload) {
        this.workload = workload;
    };

    public LocalDate getJoinedAt() {
        return joinedAt;
    };

    public void setJoinedAt(LocalDate joinedAt) {
        this.joinedAt = joinedAt;
    };
};
