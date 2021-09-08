package org.example.inbound.task;

import com.google.inject.Singleton;
import javax.inject.Inject;
import org.example.common.config.database.DslContext;
import org.example.jooq.model.tables.daos.TaskDao;

@Singleton
public class TaskRepository extends TaskDao {

  @Inject
  public TaskRepository(DslContext dslContext) {
    super(dslContext.getInstance().configuration());
  }

}
