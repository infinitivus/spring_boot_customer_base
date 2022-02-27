package com.infinitivus.project.spring_rest_security_base.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.infinitivus.project.spring_rest_security_base.entity.person.RepairWork;
import com.infinitivus.project.spring_rest_security_base.service.person_service.IRepairWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RepairWorkController {

    @Autowired
    private IRepairWorkService repairWorkService;

    //Создание новой записи ремонта
    @PostMapping("/create")
    public ResponseEntity<String> createRepairWork(@RequestBody RepairWork repairWork) {
        ResponseEntity<String> resp;
        try {
            RepairWork work = repairWorkService.saveRepairWork(repairWork);
            resp = new ResponseEntity<>(
                    "RepairWork '" + repairWork.getId() + "' created:" + work.toString(), HttpStatus.CREATED); //201
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to save repair work",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }

    // Вывод списка всех ремонтов
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRepairWork() {
        ResponseEntity<?> resp;
        try {
            List<RepairWork> list = repairWorkService.allRepairWork();
            resp = new ResponseEntity<>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all repair work",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // получение ремонтов тс по id автодома
    @GetMapping("/getWorks/{homeId}")
    public ResponseEntity<?> getRepairWork(@PathVariable Integer homeId) {
        ResponseEntity<?> resp;
        try {
            RepairWork repairWork = repairWorkService.getRepairWork(homeId);
            resp = new ResponseEntity<>(repairWork, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to find repair work",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // Удаление информации о ремонте
    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deleteRepairWork(@PathVariable Integer id) {
        ResponseEntity<String> resp;
        try {
            repairWorkService.deleteRepairWork(id);
            resp = new ResponseEntity<>(
                    "RepairWork '" + id + "' deleted", HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to delete repair work", HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    //  изменение данных о ремонте
    @PatchMapping(path = "/modify/{id}")
    public ResponseEntity<String> updateRepairWork(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        ResponseEntity<String> resp ;
        try {
            RepairWork repairWork = repairWorkService.getRepairWork(id);
            RepairWork repairWorkPatched=repairWorkService.applyPatchToRepairWork(patch,repairWork);
            repairWorkService.saveRepairWork(repairWorkPatched);
            resp = new ResponseEntity<>(
                    "RepairWork '" + repairWorkPatched.getId() + "' updated:"+ repairWorkPatched,
                    HttpStatus.PARTIAL_CONTENT); //206
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to update repair work",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }
}
