package src.persons.common;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;

import src.persons.enums.Genrer;

public abstract class Person implements Externalizable {
    private String name;
    private String cpf;
    private LocalDate birthdate;
    private Genrer genrer;
    private Address address;
    private Long registration;
    private Double wage;
    private String department;
    private Integer workload;
    private LocalDate joinedAt;

    public Person() {};
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.name);
        out.writeUTF(this.cpf);
        out.writeObject(this.birthdate);
        out.writeShort(this.genrer.getValue());
        out.writeObject(this.address);
        out.writeLong(this.registration);
        out.writeDouble(this.wage);
        out.writeUTF(this.department);
        out.writeInt(this.workload);
        out.writeObject(this.joinedAt);
    };

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.cpf = in.readUTF();
        this.birthdate = (LocalDate) in.readObject();
        this.genrer = Genrer.fromValue(Short.toUnsignedInt(in.readShort()));
        this.address = (Address) in.readObject();
        this.registration = in.readLong();
        this.wage = in.readDouble();
        this.department = in.readUTF();
        this.workload = in.readInt();
        this.joinedAt = (LocalDate) in.readObject();
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

    public Double getWage() {
        return this.wage;
    };

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
