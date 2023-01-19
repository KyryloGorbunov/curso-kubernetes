package com.kyrylo.springcloud.msvc.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses", url = "msvc-courses:8002")
public interface CourseClientRest {

    @DeleteMapping("/delete-course-user/{id}")
    void deleteCourseUserById(@PathVariable Long id);
}
