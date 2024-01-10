package com.project.service;

import com.project.entity.Thesis;
import com.project.entity.ManagementSystem;

public interface ManagementService {
    ManagementSystem save(ManagementSystem managementSystem);

    Thesis uploadThesis(Thesis thesis);

    void processThesis(Thesis thesis);

    ManagementSystem createOrUpdateManagementSystem(ManagementSystem managementSystem);
}
