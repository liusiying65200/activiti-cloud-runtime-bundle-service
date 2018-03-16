package org.activiti.cloud.services.rest.api;

import org.activiti.cloud.services.api.model.Task;
import org.activiti.cloud.services.rest.api.resources.TaskResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/admin/v1", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public interface AdminController {

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    PagedResources<TaskResource> getAllTasks(Pageable pageable);
}
