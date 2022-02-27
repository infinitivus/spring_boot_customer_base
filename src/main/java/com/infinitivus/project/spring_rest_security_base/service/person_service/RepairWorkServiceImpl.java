package com.infinitivus.project.spring_rest_security_base.service.person_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_rest_security_base.entity.person.RepairWork;
import com.infinitivus.project.spring_rest_security_base.repository.person_repository.IRepairWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairWorkServiceImpl implements  IRepairWorkService {

        @Autowired
        private IRepairWorkRepository repairWorkRepository;

        @Override
        public List<RepairWork> allRepairWork() {
            return repairWorkRepository.findAll();
        }

        @Override
        public RepairWork saveRepairWork(RepairWork repairWork) {
            return  repairWorkRepository.save(repairWork);
        }

        @Override
        public RepairWork getRepairWork(Integer homeId) {
            return repairWorkRepository.findById(homeId).get();
        }

        @Override
        public void deleteRepairWork(Integer id) {
            repairWorkRepository.deleteById(id);
        }

        public RepairWork applyPatchToRepairWork(JsonPatch patch, RepairWork repairWork) throws JsonPatchException, JsonProcessingException {
            ObjectMapper objectMapper=new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(repairWork, JsonNode.class));
            return objectMapper.treeToValue(patched, RepairWork.class);
        }
}
