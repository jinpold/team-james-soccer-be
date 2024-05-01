package com.james.api.team.repository;
import com.james.api.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@SuppressWarnings("rawtypes")
@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, TeamJpqlRepository {

}