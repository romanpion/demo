package com.romao.demo.network.entities;

import java.util.List;

public class UserRequest {

    public boolean includeMembers;

    public int pageNo;

    public int pageSize;

    public Integer companyId;

    public String text;

    public List<SortDescriptorRequest> sortDescriptors;
}
