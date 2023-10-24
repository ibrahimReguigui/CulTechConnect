package com.example.web.quiz.repository;

import com.example.web.quiz.entities.result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface resultRepository extends JpaRepository <result, Integer> {
        List<result> findByQuizId(Integer idQuiz);
        //result findByQuizId(Integer idQuiz);
        //result findByQuiz(Integer idQuiz);

}
