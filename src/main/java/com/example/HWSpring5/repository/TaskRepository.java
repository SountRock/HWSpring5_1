package com.example.HWSpring5.repository;

import com.example.HWSpring5.domain.Status;
import com.example.HWSpring5.domain.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);

    @Transactional
    @Modifying
    //@Query(value = "${query.updateStatusById}", nativeQuery = true) //Здесь я тоже не понял. Ругается, что не может прочитать с свмого начала
    //@Query(value = "UPDATE TASKS t SET t.status=:status WHERE t.id=:id", nativeQuery = true)
    @Query(nativeQuery = true)
    void updateStatusById(@Param("status") String status, @Param("id") Long id);

    @Transactional
    @Modifying
    //@Query(value = "TRUNCATE TABLE TASKS", nativeQuery = true) //После выполнения невозможно ничего сохоанить
    //@Query(value = "ALTER TABLE TASKS AUTO_INCREMENT = 0;", nativeQuery = true)//Вообще не может прочитать. Вообще очень плохо эта аннотация различает запросы.
    void clear();

    //@Modifying
    //@Transactional
    //@Query(value = "DELETE FROM TASKS t", nativeQuery = true)
    //void deleteAllWithQuery();

    //!!! При любой попытке удаления через запрос, сохранение записей идет к черту, после очистки ничерта он не хочет сохранять. @Query будто просто вообще не совместима с JpaRepository.

    //Наверное мне надо было углубиться в EntityManager
}