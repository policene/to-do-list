package com.policene.to_do_list.domain.specification;

import com.policene.to_do_list.domain.model.Task;
import com.policene.to_do_list.domain.model.enums.TaskPriority;
import com.policene.to_do_list.domain.model.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> isActive () {
        return (((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("active"), true)));
    }

    public static Specification<Task> hasStatus (TaskStatus status) {
        return ((root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status));
    }

    public static Specification<Task> hasPriority (TaskPriority priority) {
        return ((root, query, criteriaBuilder) ->
                priority == null ? null : criteriaBuilder.equal(root.get("priority"), priority));
    }

}
