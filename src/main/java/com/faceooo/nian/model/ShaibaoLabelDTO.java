package com.faceooo.nian.model;

/**
 * Created by Administrator on 2015/10/16.
 */
public class ShaibaoLabelDTO extends BaseDTO {
    public String id;
    public String labelname;
    public String labelen;
    public String timerecord;
    public String sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getLabelen() {
        return labelen;
    }

    public void setLabelen(String labelen) {
        this.labelen = labelen;
    }

    public String getTimerecord() {
        return timerecord;
    }

    public void setTimerecord(String timerecord) {
        this.timerecord = timerecord;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
