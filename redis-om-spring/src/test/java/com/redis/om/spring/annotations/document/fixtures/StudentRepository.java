package com.redis.om.spring.annotations.document.fixtures;

import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public interface StudentRepository extends RedisDocumentRepository<Student, Long>, QueryByExampleExecutor<Student> {
  List<Student> findByUserName(String userName);
  List<Student> findByUserNameAndEventTimestampBetweenOrderByEventTimestampAsc(String userName, LocalDateTime fromDate, LocalDateTime toDate);

  default Student findFirstByPropertyOrderByEventTimestamp(
    Student student,
    ExampleMatcher exampleMatcher,
    Function<FetchableFluentQuery<Student>, Student> queryFunction) {
    return findBy(Example.of(student, exampleMatcher), queryFunction);
  }
}
