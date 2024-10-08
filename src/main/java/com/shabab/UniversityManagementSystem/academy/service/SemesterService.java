package com.shabab.UniversityManagementSystem.academy.service;


import com.shabab.UniversityManagementSystem.academy.model.Program;
import com.shabab.UniversityManagementSystem.academy.model.Semester;
import com.shabab.UniversityManagementSystem.academy.repository.FeeRepository;
import com.shabab.UniversityManagementSystem.academy.repository.ProgramRepository;
import com.shabab.UniversityManagementSystem.academy.repository.SemesterRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */
@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private FeeRepository feeRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Semester> semesters = semesterRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (semesters.isEmpty()) {
                return response.returnError("No semesters found");
            }
            response.setData("semesters", semesters);
            response.success("Semesters retrieved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse save(Semester semester) {
        ApiResponse response = new ApiResponse();
        try {
            Program program = programRepository.findById(
                    semester.getProgram().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Program());
            if (program.getId() == null) {
                return response.returnError("Wrong Program");
            }
            semester.setUniversityId(AuthUtil.getCurrentUniversityId());
            semesterRepository.save(semester);
            response.setData("semester", semester);
            response.success("Semester saved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse update(Semester semester) {
        ApiResponse response = new ApiResponse();
        try {
            Semester dbSemester = semesterRepository.findById(
                    semester.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());

            if (dbSemester.getId() == null) {
                return response.returnError("Semester not found");
            }
            semester.setUniversityId(AuthUtil.getCurrentUniversityId());
            semesterRepository.save(semester);
            response.setData("semester", semester);
            response.success("Semester updated successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Semester semester = semesterRepository.findById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Semester not found");
            }
            response.setData("semester", semester);
            response.success("Successfully retrieved semester");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Semester semester = semesterRepository.findById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Semester not found");
            }
            semesterRepository.delete(semester);
            response.success("Semester deleted successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

}

