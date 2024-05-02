package com.james.api.stadium.repository;
import com.james.api.stadium.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@SuppressWarnings("hiding")
@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long>,StadiumJpqlRepository {

}
