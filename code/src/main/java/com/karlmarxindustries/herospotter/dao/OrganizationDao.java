package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Organization;
import com.karlmarxindustries.herospotter.dto.Super;

import java.util.List;

public interface OrganizationDao {
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization course);
    void updateOrganization(Organization course);
    void deleteOrganizationById(int id);
    List<Organization> getOrganizationsForSuper(Super super_);
}
