package com.infinitivus.project.spring_rest_security_base.entity.person;

import javax.persistence.*;
//import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "mobile_home")
public class MobileHome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
//    @Pattern(regexp = "^([Рђ-РЇР°-СЏРЃС‘]|[A-Za-z]){3,15}$", message = "Error! Enter the sample data")
    private String type;

    @Column(name = "brand")
//    @Pattern(regexp = "^([Рђ-РЇР°-СЏРЃС‘]|[A-Za-z]){3,15}$", message = "Error! Enter the sample data")
    private String brand;

    @Column(name = "model")
//    @Pattern(regexp = "^([Рђ-РЇР°-СЏРЃС‘]|[A-Za-z]){3,15}$", message = "Error! Enter the sample data")
    private String model;

    @Column(name = "vin")
//    @Pattern(regexp = "^([Рђ-РЇР°-СЏРЃС‘]|[A-Za-z]){3,20}$", message = "Error! Enter the sample data")
    private String vin;

    @Column(name = "year_of_release")
//    @Pattern(regexp = "^[1-9][0-9]{3}$", message = "Error! Enter the sample data")
    private String yearOfRelease;

    @Column(name = "license_plate")
//    @Pattern(regexp = "^([Рђ-РЇР°-СЏРЃС‘]|[A-Za-z]){3,15}$", message = "Error! Enter the sample data")
    private String licensePlate;
//
//    @OneToOne(mappedBy = "mobileHome")
//    private Person homePerson;

    @OneToMany(mappedBy = "mobileHomeRepair", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<RepairWork> repairWorkList;

    public MobileHome() {
    }

    public MobileHome(Integer id, String type, String brand, String model, String vin, String yearOfRelease, String licensePlate) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.yearOfRelease = yearOfRelease;
        this.licensePlate = licensePlate;
//        this.homePerson = homePerson;
    }

    public RepairWork addRepairWorkToMobileHome(RepairWork repairWork) {
        if (repairWorkList == null) {
            repairWorkList = new HashSet<>();
        }
        repairWorkList.add(repairWork);
        repairWork.setMobileHomeRepair(this);
        return repairWork;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
//
//    public Person getHomePerson() {
//        return homePerson;
//    }
//
//    public void setHomePerson(Person homePerson) {
//        this.homePerson = homePerson;
//    }

    public Set<RepairWork> getRepairWorkList() {
        return repairWorkList;
    }

    public void setRepairWorkList(Set<RepairWork> repairWorkList) {
        this.repairWorkList = repairWorkList;
    }

    @Override
    public String toString() {
        return "MobileHome{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}

