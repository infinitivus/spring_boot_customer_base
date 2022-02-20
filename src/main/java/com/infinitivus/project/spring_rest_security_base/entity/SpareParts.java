package com.infinitivus.project.spring_rest_security_base.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="spare_part")
public class SpareParts {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_spare_part")
    private String nameSparePart;

    @Column(name="cost_part")
    private long costPart;

    @Column(name="article")
    private String article;

    @ManyToMany(mappedBy = "sparePartsList")
//    @JoinTable(name="parts_work", joinColumns = @JoinColumn(name="spare_parts_id"),
//            inverseJoinColumns = @JoinColumn(name="repair_work_id"))
    private List<RepairWork> repairWorkList;
//
//    public SpareParts() {
//    }
//
//    public SpareParts(String nameSparePart, long costPart, String article) {
//        this.nameSparePart = nameSparePart;
//        this.costPart = costPart;
//        this.article = article;
//    }

    public void addRepairWorkToSpareParts(RepairWork repairWork){
        if(repairWorkList==null){
            repairWorkList=new ArrayList<>();
        }
        repairWorkList.add(repairWork);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSparePart() {
        return nameSparePart;
    }

    public void setNameSparePart(String nameSparePart) {
        this.nameSparePart = nameSparePart;
    }

    public long getCostPart() {
        return costPart;
    }

    public void setCostPart(long costPart) {
        this.costPart = costPart;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public List<RepairWork> getRepairWorkList() {
        return repairWorkList;
    }

    public void setRepairWorkList(List<RepairWork> repairWorkList) {
        this.repairWorkList = repairWorkList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpareParts)) return false;
        SpareParts that = (SpareParts) o;
        return getCostPart() == that.getCostPart() && Objects.equals(getId(), that.getId()) && Objects.equals(getNameSparePart(), that.getNameSparePart()) && Objects.equals(getArticle(), that.getArticle()) && Objects.equals(getRepairWorkList(), that.getRepairWorkList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameSparePart(), getCostPart(), getArticle(), getRepairWorkList());
    }
}

