package com.karlmarxindustries.herospotter.unused;

import com.karlmarxindustries.herospotter.dto.Super;

import java.util.List;

public interface SuperDao {
    Super getSuperById(int id);
    List<Super> getAllSupers();
    Super addSuper(Super super_);
    void updateSuper(Super super_);
    void deleteSuperById(int id);

    List<Super>  getAllSupersIngOrganization(int orgId);
}
