package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Organization;
import com.karlmarxindustries.herospotter.dto.Super;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    public List<Organization> getOrganizationsForSuper(Super super_);
}
