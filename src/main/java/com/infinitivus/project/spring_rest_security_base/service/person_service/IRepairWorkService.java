package com.infinitivus.project.spring_rest_security_base.service.person_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_rest_security_base.entity.person.Person;
import com.infinitivus.project.spring_rest_security_base.entity.person.RepairWork;

import java.util.List;

public interface IRepairWorkService {

    RepairWork saveRepairWork(RepairWork repairWork);

    RepairWork getRepairWork(Integer homeId);

    List<RepairWork> allRepairWork();

    void deleteRepairWork(Integer workId);

    RepairWork applyPatchToRepairWork(JsonPatch patch, RepairWork repairWork) throws JsonPatchException, JsonProcessingException;

}
