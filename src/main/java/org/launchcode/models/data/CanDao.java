package org.launchcode.models.data;

import org.launchcode.models.Can;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LaunchCode
 */
@Repository
@Transactional
public interface CanDao extends CrudRepository<Can, Integer> {
}
