package com.infinitivus.project.entity.person;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
@Table(name = "repair_work")
public class RepairWork {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 100, message = "Error!")
    @Column(name = "name_the_work")
    private String nameTheWork;

    @Pattern(regexp = "^([А-Яа-яЁё]|[A-Za-z]){3,10}$", message = "Error!")
    @Column(name = "master")
    private String master;

    @Min(value = 0, message = "Error!")
    @Max(value = 999999999, message = "Error!")
    @Column(name = "cost_work")
    private long costWork;

    @Pattern(regexp = "^((0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d))$", message = "Error!")
    @Column(name = "date")
    private String date;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "home_id")
    private MobileHome mobileHomeRepair;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "parts_work", joinColumns = @JoinColumn(name = "repair_work_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_parts_id"))
    private List<SpareParts> sparePartsList;

    public void addSparePartsToRepairWork(SpareParts spareParts) {
        if (sparePartsList == null) {
            sparePartsList = new ArrayList<>();
        }
        sparePartsList.add(spareParts);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTheWork() {
        return nameTheWork;
    }

    public void setNameTheWork(String nameTheWork) {
        this.nameTheWork = nameTheWork;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public long getCostWork() {
        return costWork;
    }

    public void setCostWork(long costWork) {
        this.costWork = costWork;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public MobileHome getMobileHomeRepair() {
        return mobileHomeRepair;
    }

    public void setMobileHomeRepair(MobileHome mobileHomeRepair) {
        this.mobileHomeRepair = mobileHomeRepair;
    }

    public List<SpareParts> getSparePartsList() {
        return sparePartsList;
    }

    public void setSparePartsList(List<SpareParts> sparePartsList) {
        this.sparePartsList = sparePartsList;
    }
}

